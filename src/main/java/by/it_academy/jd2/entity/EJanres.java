package by.it_academy.jd2.entity;

import java.util.Arrays;
import java.util.Optional;

public enum EJanres {
    ROCK,
    POP,
    METAL,
    HIP_HOP,
    CLASSICAL,
    JAZZ,
    RAP,
    ELECTRONIC,
    DISCO,
    BLUES;

    /**
     * Метод получения жанра из строки
     * @param janre строка с названием жанра
     * @return жанр типа Optional<Janres>
     */
    public static Optional<EJanres> getJanre(String janre) {

       return Arrays.stream(EJanres.values())
                .filter(janres -> janres.name().equalsIgnoreCase(janre))
                .findFirst();
    }
}
