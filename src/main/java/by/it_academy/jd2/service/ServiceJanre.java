package by.it_academy.jd2.service;

import by.it_academy.jd2.service.api.IJanreService;
import by.it_academy.jd2.storage.JanreStorage;
import by.it_academy.jd2.storage.api.IJanreStorage;

import java.util.Map;

public class ServiceJanre implements IJanreService {
    private static final ServiceJanre INSTANCE = new ServiceJanre();
    private final IJanreStorage janreStorage = JanreStorage.getInstance();

    private ServiceJanre(){}

    @Override
    public Long create(String name) {
        return janreStorage.create(name);
    }

    @Override
    public String get(Long id) {
        return janreStorage.get(id);
    }

    @Override
    public Map<Long, String> get() {
        return janreStorage.get();
    }

    public static ServiceJanre getInstance() {
        return INSTANCE;
    }
}
