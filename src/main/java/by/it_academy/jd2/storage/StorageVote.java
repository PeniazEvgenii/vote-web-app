package by.it_academy.jd2.storage;

import by.it_academy.jd2.dto.InfoFromUserDTO;
import by.it_academy.jd2.entity.TextAndTimeVote;
import by.it_academy.jd2.storage.api.IJanreStorage;
import by.it_academy.jd2.storage.api.ISingerStorage;
import by.it_academy.jd2.storage.api.IVoteStorage;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StorageVote implements IVoteStorage {
    private static final StorageVote INSTANCE = new StorageVote();

    private final List<TextAndTimeVote> listTextTime = new ArrayList<>();
    private final Map<Long, Long> mapSingers2 = new HashMap<>();
    private final Map<Long, Long> mapJanres2 = new HashMap<>();

    private final ISingerStorage singerStorage = SingerStorage.getInstance();
    private final IJanreStorage janreStorage = JanreStorage.getInstance();

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
        Long idSinger = Long.valueOf(infoFromUserDto.getSinger());
        mapSingers2.merge(idSinger, 1L, Long::sum);
    }

    /**
     * Метод сохранения жанров в коллекцию mapJanres, с увеличением счетчика
     * @param infoFromUserDto  объект дто с данными о голосовании
     */
    private void addElementJanres(InfoFromUserDTO infoFromUserDto) {
        String[] janresId = infoFromUserDto.getJanres();
        for (String jId : janresId) {
            Long id = Long.valueOf(jId);
            mapJanres2.merge(id, 1L, Long::sum);
        }
    }

    /**
     * Метод получения данных с исполнителями
     * @return Map<Long, Long> с исполнителем и количеством голосов
     */
    public Map<Long, Long> getMapSingers() {
        return mapSingers2;
    }

    /**
     * Метод получения данных с жанрами
     * @return Map<Long, Long> с id жанра и количеством голосов
     */
    public Map<Long, Long> getMapJanres() {
        return mapJanres2;
    }

    /**
     * метод получения списка с информацией о времени голосования и информации от пользователя
     * @return List<TextAndTimeVote> listTextTime
     */
    public List<TextAndTimeVote> getListTextTime() {
        return listTextTime;
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
        Map<Long, String> janreMap = janreStorage.get();
        for (Long l : janreMap.keySet()) {
            mapJanres2.put(l, 0L);
        }
    }

    /**
     * Метод для заполнения количества голосов по исполнителям нулями
     */
    private void initSingers() {
        Map<Long, String> singerMap = singerStorage.get();
        for (Long l : singerMap.keySet()) {
            mapSingers2.put(l, 0L);
        }

    }
}
