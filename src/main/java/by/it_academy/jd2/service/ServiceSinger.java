package by.it_academy.jd2.service;

import by.it_academy.jd2.entity.Artist;
import by.it_academy.jd2.service.api.ISingerService;
import by.it_academy.jd2.storage.api.IStorageDB;
import by.it_academy.jd2.storage.db.ArtistStorageDB;
import by.it_academy.jd2.storage.memory.SingerStorageMemory;
import by.it_academy.jd2.storage.api.ISingerStorage;

import java.util.Map;
import java.util.stream.Collectors;

public class ServiceSinger implements ISingerService {
    private static final ServiceSinger INSTANCE = new ServiceSinger();

    // private final ISingerStorage singerStorage = SingerStorageMemory.getInstance();       // в IN-MEMORY

    private final IStorageDB<Artist> singerStorageDB = ArtistStorageDB.getInstance();

    private ServiceSinger() {}

    @Override
    public Long create(String name) {
        Artist artist = new Artist(name);
        return singerStorageDB.create(artist);

        // return singerStorage.create(name);          в IN-MEMORY
    }

    @Override
    public String get(Long id) {
        Artist artist = singerStorageDB.get(id);
        return artist.getName();

        //return singerStorage.get(id);          из IN-MEMORY
    }

    @Override
    public Map<Long, String> get() {
        Map<Long, Artist> longArtistMap = singerStorageDB.get();
        return longArtistMap.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().getName()));    //сохраняю старую структуру

        //return singerStorage.get();     из IN-MEMORY
    }

    public static ServiceSinger getInstance() {
        return INSTANCE;
    }
}
