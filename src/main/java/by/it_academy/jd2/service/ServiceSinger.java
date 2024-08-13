package by.it_academy.jd2.service;

import by.it_academy.jd2.service.api.ISingerService;
import by.it_academy.jd2.storage.SingerStorage;
import by.it_academy.jd2.storage.api.ISingerStorage;

import java.util.Map;

public class ServiceSinger implements ISingerService {
    private static final ServiceSinger INSTANCE = new ServiceSinger();
    private final ISingerStorage singerStorage = SingerStorage.getInstance();

    private ServiceSinger() {}

    @Override
    public Long create(String name) {
        return singerStorage.create(name);
    }

    @Override
    public String get(Long id) {
        return singerStorage.get(id);
    }

    @Override
    public Map<Long, String> get() {
        return singerStorage.get();
    }

    public static ServiceSinger getInstance() {
        return INSTANCE;
    }
}
