package by.it_academy.jd2.service;

import by.it_academy.jd2.entity.Genre;
import by.it_academy.jd2.service.api.IJanreService;
import by.it_academy.jd2.storage.api.IStorageDB;
import by.it_academy.jd2.storage.db.GenreStorageDB;
import by.it_academy.jd2.storage.memory.JanreStorageMemory;
import by.it_academy.jd2.storage.api.IJanreStorage;

import java.util.Map;
import java.util.stream.Collectors;

public class ServiceJanre implements IJanreService {
    private static final ServiceJanre INSTANCE = new ServiceJanre();

    //private final IJanreStorage janreStorage = JanreStorageMemory.getInstance();      // в IN-MEMORY

    private final IStorageDB<Genre> genreStorageDB = GenreStorageDB.getInstance();

    private ServiceJanre(){}

    @Override
    public Long create(String name) {
        Genre genre = new Genre(name);
        return genreStorageDB.create(genre);

       // return janreStorage.create(name);  в IN-MEMORY
    }

    @Override
    public String get(Long id) {
        Genre genre = genreStorageDB.get(id);
        return genre.getName();

       // return janreStorage.get(id);   из IN-MEMORY
    }

    @Override
    public Map<Long, String> get() {
        Map<Long, Genre> longStringMap = genreStorageDB.get();
        return longStringMap.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().getName()));  //сохраняю старую структуру

        //return janreStorage.get();       запрос из IN-MEMORY
    }

    public static ServiceJanre getInstance() {
        return INSTANCE;
    }
}
