package by.it_academy.jd2.storage.old;

import by.it_academy.jd2.entity.TextAndTimeVote;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Deprecated(forRemoval = true)
public class StorageForTextDateVote {
    private static final StorageForTextDateVote INSTANCE = new StorageForTextDateVote();
    private final List<TextAndTimeVote> listTextTime = new ArrayList<>();

    private StorageForTextDateVote(){}

    public void addTextTime(String textInfo, LocalDateTime time) {
        TextAndTimeVote textAndTimeVote = new TextAndTimeVote(textInfo, time);
        listTextTime.add(textAndTimeVote);
    }

    public List<TextAndTimeVote> getListTextTime() {
        return listTextTime;
    }

    public static StorageForTextDateVote getInstance() {
        return INSTANCE;
    }


}
