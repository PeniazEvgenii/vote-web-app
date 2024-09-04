package by.it_academy.jd2.mapper;

import by.it_academy.jd2.dto.InfoFromUserDTO;
import by.it_academy.jd2.entity.VoteEntity;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.stream.Collectors;

public class MapperVoteEntity {
    private static final MapperVoteEntity INSTANCE = new MapperVoteEntity();
    private static final String OFFSET_BEL = "+03:00";

    public VoteEntity mapFrom(InfoFromUserDTO infoFromUserDTO) {
        return VoteEntity.builder()
                .setArtist(Long.valueOf(infoFromUserDTO.getSinger()))
                .setGenres(Arrays.stream(infoFromUserDTO.getJanres()).map(Long::valueOf).collect(Collectors.toList()))
                .setInfo(infoFromUserDTO.getInfo())
                .setCreate_at(OffsetDateTime.of(infoFromUserDTO.getDateTime(), ZoneOffset.of(OFFSET_BEL)))
                .build();
    }

    public static MapperVoteEntity getInstance() {
        return INSTANCE;
    }
}
