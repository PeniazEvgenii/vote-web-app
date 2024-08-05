package by.it_academy.jd2.storage;

import by.it_academy.jd2.dto.InfoFromUserDTO;

import java.util.ArrayList;
import java.util.List;

public class StorageForInfoFromUser {
    private final List<InfoFromUserDTO> list = new ArrayList<>();

    public StorageForInfoFromUser() {
    }

    public void addInfoFromUser(InfoFromUserDTO info) {
        list.add(info);
    }

    public List<InfoFromUserDTO> getList() {
        return list;
    }
}
