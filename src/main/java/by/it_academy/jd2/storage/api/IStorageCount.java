package by.it_academy.jd2.storage.api;

import java.util.Map;

public interface IStorageCount<T> {

    Map<T, Integer> getStorage();

    void addElement(String... element);
}
