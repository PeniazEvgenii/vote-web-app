package by.it_academy.jd2.storage;

import by.it_academy.jd2.storage.api.IJanreStorage;

import java.util.HashMap;
import java.util.Map;

public class JanreStorage implements IJanreStorage {
    private static final JanreStorage INSTANCE = new JanreStorage();
    private Map<Long, String> data = new HashMap<>();

    private JanreStorage() {}



    @Override
    public Long create(String name) {
        long id = data.size() + 1;
        data.put(id, name);
        return id;
    }

    @Override
    public String get(Long id) {
        return this.data.get(id);
    }

    @Override
    public Map<Long, String> get() {
        return this.data;
    }


    public static JanreStorage getInstance() {
        return INSTANCE;
    }
}
