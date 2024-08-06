package by.it_academy.jd2.storage.api;

import by.it_academy.jd2.dto.InfoFromUserDTO;
import by.it_academy.jd2.entity.EJanres;
import by.it_academy.jd2.entity.ESingers;
import by.it_academy.jd2.entity.TextAndTimeVote;

import java.util.List;
import java.util.Map;

public interface IVoteStorage {

    void saveVote(InfoFromUserDTO infoFromUserDto);

    List<TextAndTimeVote> getListTextTime();

    Map<ESingers, Integer> getMapSingers();

    Map<EJanres, Integer> getMapJanres();
}

