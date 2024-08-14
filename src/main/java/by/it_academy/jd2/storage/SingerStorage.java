package by.it_academy.jd2.storage;

import by.it_academy.jd2.entity.ESingers;
import by.it_academy.jd2.storage.api.ISingerStorage;

import java.util.HashMap;
import java.util.Map;

public class SingerStorage implements ISingerStorage {
    private static SingerStorage INSTANCE = new SingerStorage();
    private Map<Long, String> data = new HashMap<>();


    private SingerStorage() {
        for (ESingers value : ESingers.values()) {
            long id = getNextIdFromMap();
            data.put(id, value.name());
        }
    }

    @Override
    public Long create(String name) {
        long id = getNextIdFromMap();
        data.put(id, name);
        return id;
    }

    @Override
    public String get(Long id) {
        return data.get(id);
    }

    @Override
    public Map<Long, String> get() {
        return this.data;
    }

    public static SingerStorage getInstance() {
        return INSTANCE;
    }

    private int getNextIdFromMap() {
        return data.size() + 1;
    }
}
