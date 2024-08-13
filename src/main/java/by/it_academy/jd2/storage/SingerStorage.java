package by.it_academy.jd2.storage;

import by.it_academy.jd2.storage.api.ISingerStorage;

import java.util.HashMap;
import java.util.Map;

public class SingerStorage implements ISingerStorage {
    private static SingerStorage INSTANCE = new SingerStorage();
    private Map<Long, String> data = new HashMap<>();

    private SingerStorage() {}


    @Override
    public Long create(String name) {
        long id = data.size() + 1;
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
}
