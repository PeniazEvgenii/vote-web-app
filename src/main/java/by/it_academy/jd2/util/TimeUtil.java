package by.it_academy.jd2.util;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public final class TimeUtil {

    public static LocalDateTime convertOffsetToLocalDateTime(OffsetDateTime offsetDateTime, String zone) {
        ZoneId zoneId = ZoneId.of(zone);
        ZonedDateTime zonedDateTime = offsetDateTime.atZoneSameInstant(zoneId);
        return zonedDateTime.toLocalDateTime();
    }
}
