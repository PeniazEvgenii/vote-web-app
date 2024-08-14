package by.it_academy.jd2.dto;

import java.util.List;
import java.util.Map;

public class SortedDateDTO {
    private final List<TextTimeString> textTimeString;
    private final List<Map.Entry<Long, Long>> sortSing;
    private final List<Map.Entry<Long, Long>> sortJanr;


    public SortedDateDTO(List<Map.Entry<Long, Long>> sortSing, List<Map.Entry<Long, Long>> sortJanr, List<TextTimeString> textTimeString) {
        this.textTimeString = textTimeString;
        this.sortSing = sortSing;
        this.sortJanr = sortJanr;
    }

    public List<TextTimeString> getTextAndTimeVotes() {
        return textTimeString;
    }

    public List<Map.Entry<Long, Long>> getSortSing() {
        return sortSing;
    }

    public List<Map.Entry<Long, Long>> getSortJanr() {
        return sortJanr;
    }
}
