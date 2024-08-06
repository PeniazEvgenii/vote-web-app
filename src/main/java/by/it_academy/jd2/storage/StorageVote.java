package by.it_academy.jd2.storage;

import by.it_academy.jd2.dto.InfoFromUserDTO;
import by.it_academy.jd2.entity.EJanres;
import by.it_academy.jd2.entity.ESingers;
import by.it_academy.jd2.entity.TextAndTimeVote;
import by.it_academy.jd2.storage.api.IVoteStorage;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StorageVote implements IVoteStorage {
    private static final StorageVote INSTANCE = new StorageVote();

    private final Map<ESingers, Integer> mapSingers = new HashMap<>();
    private final Map<EJanres, Integer> mapJanres = new HashMap<>();
    private final List<TextAndTimeVote> listTextTime = new ArrayList<>();

    {
        initSingers();      // чтобы в коллекции были все исполнители с ноль голосов
        initJanres();
    }

    private StorageVote() {
    }

    /**
     * Метод сохранения результатов голования в storage
     * @param infoFromUserDto объект дто с данными о голосовании
     */
    @Override
    public void saveVote(InfoFromUserDTO infoFromUserDto) {
        addTextTime(infoFromUserDto);
        addElementSingers(infoFromUserDto);
        addElementJanres(infoFromUserDto);
    }

    /**
     * Метод для сохрания в storage информации о времени голосования и информации от пользователя
     * @param infoFromUserDto объект дто с данными о голосовании
     */
    private void addTextTime(InfoFromUserDTO infoFromUserDto) {
        String info = infoFromUserDto.getInfo();
        LocalDateTime dateTimeVote = infoFromUserDto.getDateTime();
        TextAndTimeVote textAndTimeVote = new TextAndTimeVote(info, dateTimeVote);
        listTextTime.add(textAndTimeVote);
    }

    /**
     * Метод сохранения исполнителя в коллекцию mapSingers, с увеличением счетчика
     * @param infoFromUserDto  объект дто с данными о голосовании
     */
    private void addElementSingers(InfoFromUserDTO infoFromUserDto) {
        String singer = infoFromUserDto.getSinger();
        ESingers.getSingers(singer).ifPresent(s -> mapSingers.merge(s, 1, Integer::sum));
    }

    /**
     * Метод сохранения жанров в коллекцию mapJanres, с увеличением счетчика
     * @param infoFromUserDto  объект дто с данными о голосовании
     */
    private void addElementJanres(InfoFromUserDTO infoFromUserDto) {
        String[] janres = infoFromUserDto.getJanres();
        for (String janre : janres) {
            EJanres.getJanre(janre).ifPresent(j -> mapJanres.merge(j, 1, Integer::sum));
        }
    }

    /**
     * метод получения списка с информацией о времени голосования и информации от пользователя
     * @return List<TextAndTimeVote> listTextTime
     */
    public List<TextAndTimeVote> getListTextTime() {
        return listTextTime;
    }

    /**
     * Метод получения данных с исполнителями
     * @return Map<ESingers, Integer> с исполнителем и количеством голосов
     */
    public Map<ESingers, Integer> getMapSingers() {
        return mapSingers;
    }

    /**
     * Метод получения данных с жанрами
     * @return Map<EJanres, Integer> с исполнителем и количеством голосов
     */
    public Map<EJanres, Integer> getMapJanres() {
        return mapJanres;
    }

    /**
     * Метод получения экземпляра класса StorageVote
     * @return INSTANCE - объект типа StorageVote
     */
    public static StorageVote getInstance() {
        return INSTANCE;
    }

    /**
     * Метод для заполнения количества голосов по жанрам нулями
     */
    private void initJanres() {
        for (EJanres j : EJanres.values()) {
            mapJanres.put(j,0);
        }
    }

    /**
     * Метод для заполнения количества голосов по исполнителям нулями
     */
    private void initSingers() {
        for (ESingers s : ESingers.values()) {
            mapSingers.put(s, 0);
        }
    }
}
