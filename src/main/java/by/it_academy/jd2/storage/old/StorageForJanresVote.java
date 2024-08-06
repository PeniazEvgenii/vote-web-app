package by.it_academy.jd2.storage;

import by.it_academy.jd2.storage.api.IStorageCount;
import by.it_academy.jd2.entity.EJanres;

import java.util.HashMap;
import java.util.Map;

public class StorageForJanresVote implements IStorageCount<EJanres> {
    private static final StorageForJanresVote INSTANCE = new StorageForJanresVote();

    private final Map<EJanres, Integer> mapJanres = new HashMap<>();

    {
        init();
    }

    private StorageForJanresVote() {
    }

    public static StorageForJanresVote getInstance() {
        return INSTANCE;
    }

    @Override
    public Map<EJanres, Integer> getStorage() {
        return mapJanres;
    }

    @Override
    public void addElement(String... element) {
        for (String el : element) {
            EJanres.getJanre(el).ifPresent(janre -> mapJanres.merge(janre, 1, Integer::sum));
        }
    }

    private void init() {
        for (EJanres j : EJanres.values()) {
            mapJanres.put(j,0);
        }
    }


}
