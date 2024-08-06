package by.it_academy.jd2.storage.old;

import by.it_academy.jd2.storage.api.IStorageCount;
import by.it_academy.jd2.entity.ESingers;

import java.util.HashMap;
import java.util.Map;
@Deprecated(forRemoval = true)
public class StorageForSingersVote implements IStorageCount<ESingers> {

    private final Map<ESingers, Integer> mapSingers = new HashMap<>();
    private static final StorageForSingersVote INSTANCE = new StorageForSingersVote();

    {
        init();
    }

    private StorageForSingersVote(){
    }

    public static StorageForSingersVote getInstance() {
        return INSTANCE;
    }

    public Map<ESingers, Integer> getStorage() {
        return mapSingers;
    }

    public void addElement(String... element) {
        for (String el : element) {
            ESingers.getSingers(el).ifPresent(singer -> mapSingers.merge(singer, 1, Integer::sum));
        }
    }

    private void init() {
        for (ESingers s : ESingers.values()) {
            mapSingers.put(s, 0);
        }
    }

}
