package by.it_academy.jd2.storage.api;

import java.util.Map;

public interface IStorageDB<T> {

    Long create(T t);

    T get(Long id);

    Map<Long, T> get();
}
