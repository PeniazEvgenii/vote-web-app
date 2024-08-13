package by.it_academy.jd2.storage.api;

import java.util.Map;

public interface ISingerStorage {
    Long create(String name);

    String get(Long id);

    Map<Long, String> get();
}
