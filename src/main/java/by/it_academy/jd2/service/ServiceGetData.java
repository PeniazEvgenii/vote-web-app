package by.it_academy.jd2.service;

import by.it_academy.jd2.dto.SortedDateDTO;
import by.it_academy.jd2.dto.TextTimeString;
import by.it_academy.jd2.entity.TextAndTimeVote;
import by.it_academy.jd2.entity.VoteEntity;
import by.it_academy.jd2.service.api.IServiceGetData;
import by.it_academy.jd2.storage.memory.StorageVote;
import by.it_academy.jd2.storage.api.IStorageDB;
import by.it_academy.jd2.storage.api.IVoteStorage;
import by.it_academy.jd2.storage.db.VoteStorageDB;
import by.it_academy.jd2.util.SortUtil;
import by.it_academy.jd2.util.TimeUtil;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServiceGetData implements IServiceGetData {
    private static final IServiceGetData INSTANCE = new ServiceGetData();
    private final IVoteStorage storageVote = StorageVote.getInstance();
    private final IStorageDB<VoteEntity> voteStorageDB = VoteStorageDB.getInstance();

    private static final String FORMAT_DATE = "HH:mm:ss _ dd.MM.yyyy";

    private ServiceGetData(){}

    /**
     * Метод получения отсортированной информации по голосованию
     * @return SortedDateDTO - объект с отсортированной информацией по голосованию
     */
    @Override
    public SortedDateDTO getData() {
    //    List<TextAndTimeVote> listTextTime = storageVote.getListTextTime();                 // in-memory
    //    Map<Long, Long> mapSingers2 = storageVote.getMapSingers();                          // in-memory
    //    Map<Long, Long> mapJanres2 = storageVote.getMapJanres();                            // in-memory

    //    List<Map.Entry<Long, Long>> sortSingers = SortUtil.sort(mapSingers2);                // in-memory
    //    List<Map.Entry<Long, Long>> sortJanres = SortUtil.sort(mapJanres2);                  // in-memory
    //    List<TextAndTimeVote> textAndTimeVotes = SortUtil.sortListByTime(listTextTime);      // in-memory
    //    List<TextTimeString> textAndFormatTime = getListWithFormatTime(textAndTimeVotes);     // in-memory

        Map<Long, VoteEntity> votes = voteStorageDB.get();
        
        return countAndSortVote(votes);

        // return new SortedDateDTO(sortSingers, sortJanres, textAndFormatTime);              // in-memory
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

    private SortedDateDTO countAndSortVote(Map<Long, VoteEntity> votes) {
        List<VoteEntity> listVote = new ArrayList<>(votes.values());

        Map<Long, Long> artistCount = new HashMap<>();
        Map<Long, Long> genresCount = new HashMap<>();
        List<TextAndTimeVote> listTextTime = new ArrayList<>();

        for (VoteEntity vote : listVote) {
            artistCount.merge(vote.getArtistId(), 1L, Long::sum);
            for (Long genre : vote.getGenresId()) {
                genresCount.merge(genre, 1L, Long::sum);
            }
            String info = vote.getInfo();
            LocalDateTime localDateTime = TimeUtil.convertOffsetToLocalDateTime(vote.getCreate_at(), "Europe/Minsk");
            listTextTime.add(new TextAndTimeVote(info, localDateTime));
        }

        List<TextAndTimeVote> textAndTimeVotes = SortUtil.sortListByTime(listTextTime);

        return new SortedDateDTO(SortUtil.sort(artistCount),
                SortUtil.sort(genresCount),
                getListWithFormatTime(textAndTimeVotes));
    }


    /**
     * Метод получения экземпляра синглтон класса ServiceGetData
     * @return INSTANCE - объект типа IServiceGetData
     */
    public static IServiceGetData getInstance() {
        return INSTANCE;
    }

}
