package by.it_academy.jd2.dto;

import by.it_academy.jd2.entity.EJanres;
import by.it_academy.jd2.entity.ESingers;

import java.util.List;
import java.util.Map;

public class SortedDateDTO {
    private final List<Map.Entry<ESingers, Integer>> sortSingers;
    private final List<Map.Entry<EJanres, Integer>> sortJanres;
    private final List<TextTimeString> textTimeString;

    public SortedDateDTO(List<Map.Entry<ESingers, Integer>> sortSingers, List<Map.Entry<EJanres, Integer>> sortJanres, List<TextTimeString> textTimeString) {
        this.sortSingers = sortSingers;
        this.sortJanres = sortJanres;
        this.textTimeString = textTimeString;
    }

    public List<Map.Entry<ESingers, Integer>> getSortSingers() {
        return sortSingers;
    }

    public List<Map.Entry<EJanres, Integer>> getSortJanres() {
        return sortJanres;
    }

    public List<TextTimeString> getTextAndTimeVotes() {
        return textTimeString;
    }
}
