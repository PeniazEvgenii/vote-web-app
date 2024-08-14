package by.it_academy.jd2.util;

import by.it_academy.jd2.entity.EJanres;
import by.it_academy.jd2.entity.ESingers;
import by.it_academy.jd2.entity.TextAndTimeVote;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public final class SortUtil {
    private SortUtil() {}

    public static  List<Map.Entry<Long, Long>> sort(Map<Long, Long> map) {
        return map.entrySet().stream()
                .sorted(Map.Entry.<Long, Long>comparingByValue().reversed())
                .collect(Collectors.toList());
    }

    public static List<TextAndTimeVote> sortListByTime(List<TextAndTimeVote> list) {
        return list.stream()
                .sorted(Comparator.comparing(TextAndTimeVote::getTime).reversed())
                .collect(Collectors.toList());
    }

    public static   List<Map.Entry<EJanres, Integer>> sortJanres(Map<EJanres, Integer> map) {
        return map.entrySet().stream()
                .sorted((e1,e2) -> e2.getValue() - e1.getValue() !=  0 ? e2.getValue() - e1.getValue() : e1.getKey().name().compareTo(e2.getKey().name()))
                .collect(Collectors.toList());
    }

    public static   List<Map.Entry<ESingers, Integer>> sortSingers(Map<ESingers, Integer> map) {
        return map.entrySet().stream()
                .sorted((e1,e2) -> e2.getValue() - e1.getValue() !=  0 ? e2.getValue() - e1.getValue() : e1.getKey().name().compareTo(e2.getKey().name()))
                .collect(Collectors.toList());
    }



}
