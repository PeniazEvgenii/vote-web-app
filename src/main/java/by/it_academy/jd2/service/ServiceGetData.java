package by.it_academy.jd2.service;

import by.it_academy.jd2.dto.SortedDateDTO;
import by.it_academy.jd2.dto.TextTimeString;
import by.it_academy.jd2.entity.EJanres;
import by.it_academy.jd2.entity.ESingers;
import by.it_academy.jd2.entity.TextAndTimeVote;
import by.it_academy.jd2.service.api.IServiceGetData;
import by.it_academy.jd2.storage.StorageForJanresVote;
import by.it_academy.jd2.storage.StorageForSingersVote;
import by.it_academy.jd2.storage.StorageForTextDateVote;
import by.it_academy.jd2.util.SortUtil;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ServiceGetData implements IServiceGetData {
    private static final IServiceGetData INSTANCE = new ServiceGetData();

    private final StorageForSingersVote storageForSingersVote = StorageForSingersVote.getInstance();
    private final StorageForJanresVote storageForJanresVote = StorageForJanresVote.getInstance();
    private final StorageForTextDateVote storageForTextDateVote = StorageForTextDateVote.getInstance();

    private final String FORMAT_DATE = "HH:mm:ss dd.MM.yyyy";

    private ServiceGetData(){}

    public SortedDateDTO getData() {
        Map<ESingers, Integer> storageSingers = storageForSingersVote.getStorage();
        Map<EJanres, Integer> storageJanres = storageForJanresVote.getStorage();
        List<TextAndTimeVote> listTextTime = storageForTextDateVote.getListTextTime();

        List<Map.Entry<ESingers, Integer>> sortSingers = SortUtil.sortSingers(storageSingers);
        List<Map.Entry<EJanres, Integer>> sortJanres = SortUtil.sortJanres(storageJanres);
        List<TextAndTimeVote> textAndTimeVotes = SortUtil.sortListByTime(listTextTime);

        List<TextTimeString> textAndFormatTime = getListWithFormatTime(textAndTimeVotes);

        return new SortedDateDTO(sortSingers, sortJanres, textAndFormatTime);
    }


    public static IServiceGetData getInstance() {
        return INSTANCE;
    }

    private List<TextTimeString> getListWithFormatTime(List<TextAndTimeVote> textAndTimeVotes) {
        List<TextTimeString>  newList = new ArrayList<>();
        for (TextAndTimeVote timeVote : textAndTimeVotes) {

            LocalDateTime time = timeVote.getTime();

            TextTimeString textTimeString = new TextTimeString(timeVote.getTextInfo(),time.format(DateTimeFormatter.ofPattern(FORMAT_DATE)));

            newList.add(textTimeString);
        }
        return newList;
    }
}
