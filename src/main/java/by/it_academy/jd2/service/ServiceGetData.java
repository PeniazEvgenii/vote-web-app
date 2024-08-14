package by.it_academy.jd2.service;

import by.it_academy.jd2.dto.SortedDateDTO;
import by.it_academy.jd2.dto.TextTimeString;
import by.it_academy.jd2.entity.TextAndTimeVote;
import by.it_academy.jd2.service.api.IServiceGetData;
import by.it_academy.jd2.storage.StorageVote;
import by.it_academy.jd2.storage.api.IVoteStorage;
import by.it_academy.jd2.util.SortUtil;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ServiceGetData implements IServiceGetData {
    private static final IServiceGetData INSTANCE = new ServiceGetData();
    private static final String FORMAT_DATE = "HH:mm:ss dd.MM.yyyy";
    private final IVoteStorage storageVote = StorageVote.getInstance();

    private ServiceGetData(){}

    /**
     * Метод получения отсортированной информации по голосованию
     * @return SortedDateDTO - объект с отсортированной информацией по голосованию
     */
    @Override
    public SortedDateDTO getData() {
        List<TextAndTimeVote> listTextTime = storageVote.getListTextTime();
        Map<Long, Long> mapSingers2 = storageVote.getMapSingers();
        Map<Long, Long> mapJanres2 = storageVote.getMapJanres();

        List<Map.Entry<Long, Long>> sortSingers = SortUtil.sort(mapSingers2);
        List<Map.Entry<Long, Long>> sortJanres = SortUtil.sort(mapJanres2);
        List<TextAndTimeVote> textAndTimeVotes = SortUtil.sortListByTime(listTextTime);

        List<TextTimeString> textAndFormatTime = getListWithFormatTime(textAndTimeVotes);

        return new SortedDateDTO(sortSingers, sortJanres, textAndFormatTime);
    }

    /**
     * Метод для преобразования списка, хранящего объект TextAndTimeVote в список с объектом TextTimeString
     * TextAndTimeVote хранит текстовую информацию от пользователя, а также временя и дату в LocalDateTime
     * TextTimeString хранит текстовую информацию от пользователя и строковое представление времени и даты
     * @param textAndTimeVotes  лист объектов TextAndTimeVote из хранилища
     * @return List<TextTimeString> лист объектов с преобразованными датой и временем
     */
    private List<TextTimeString> getListWithFormatTime(List<TextAndTimeVote> textAndTimeVotes) {
        List<TextTimeString>  newList = new ArrayList<>();
        for (TextAndTimeVote timeVote : textAndTimeVotes) {
            LocalDateTime time = timeVote.getTime();
            TextTimeString textTimeString = new TextTimeString(timeVote.getTextInfo(),time.format(DateTimeFormatter.ofPattern(FORMAT_DATE)));
            newList.add(textTimeString);
        }
        return newList;
    }

    /**
     * Метод получения экземпляра синглтон класса ServiceGetData
     * @return INSTANCE - объект типа IServiceGetData
     */
    public static IServiceGetData getInstance() {
        return INSTANCE;
    }

}
