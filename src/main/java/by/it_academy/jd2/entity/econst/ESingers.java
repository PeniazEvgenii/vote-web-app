package by.it_academy.jd2.entity.econst;

import java.util.Arrays;
import java.util.Optional;

public enum ESingers {
    EMINEM,
    QUEEN,
    LINKIN_PARK,
    SNOOP_DOG;

    public static Optional<ESingers> getSingers(String singer) {
        return Arrays.stream(ESingers.values())
                .filter(singers -> singers.name().equalsIgnoreCase(singer))
                .findFirst();
    }
}
