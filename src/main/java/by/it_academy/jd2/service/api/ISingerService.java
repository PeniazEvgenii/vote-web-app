package by.it_academy.jd2.service.api;

import java.util.Map;

public interface ISingerService {
    Long create(String name);

    String get(Long id);

    Map<Long, String> get();
}
