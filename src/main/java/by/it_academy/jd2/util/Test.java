package by.it_academy.jd2.util;

import java.io.*;
import java.sql.Timestamp;
import java.time.*;
import java.util.List;
import java.util.stream.Collectors;

public class Test {
    public static void main(String[] args) {

        try (InputStream inputStream = Test.class.getClassLoader().getResourceAsStream("ArtistsForTestLoad");
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))) {
            List<String> collect = bufferedReader.lines()
                    .filter(str -> !str.isBlank())
                    .collect(Collectors.toList());
            System.out.println(collect);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
class TestTime {
    public static void main(String[] args) {
        System.out.println(OffsetDateTime.now());

        //2).
        LocalDateTime ldt = LocalDateTime.now();
// Change the ZoneId as required e.g. ZoneId.of("Europe/London")
        ZoneId zoneId = ZoneId.systemDefault();
        OffsetDateTime odt = ZonedDateTime.of(ldt, zoneId).toOffsetDateTime();
        System.out.println(odt);
        System.out.println(ZoneId.systemDefault());


 //1).       // Получаем текущее время в LocalDateTime
        LocalDateTime now = LocalDateTime.now();
        // Конвертируем LocalDateTime в OffsetDateTime
        // Предположим, что мы знаем, что время соответствует Минску (UTC+3)
        ZoneId minskZoneId = ZoneId.of("Europe/Minsk");
        ZoneOffset minskOffset = minskZoneId.getRules().getOffset(now);
        OffsetDateTime offsetDateTime = now.atOffset(minskOffset);
        System.out.println(offsetDateTime);


        OffsetDateTime offsetDateTime3 = ldt.atOffset(ZoneOffset.UTC);
        System.out.println(offsetDateTime3);
    }
}

class TestTime2 {
    public static void main(String[] args) {
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        System.out.println(zonedDateTime);


        ZonedDateTime zonedDateTime1 = zonedDateTime.withZoneSameInstant(ZoneId.of("Europe/Paris"));
        System.out.println(zonedDateTime1);

        ZonedDateTime zonedDateTime2 = zonedDateTime.withZoneSameInstant(ZoneId.of("-11:00"));
        System.out.println(zonedDateTime2);
    }
}