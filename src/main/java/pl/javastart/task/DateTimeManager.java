package pl.javastart.task;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DateTimeManager {

    public void printOptions() {
        System.out.println("Podaj datę w jednym z poniższych formatów:");
        for (Format value : Format.values()) {
            System.out.println(value.getDescription());
        }
    }

    public String readDate(Scanner scanner) {
        return scanner.nextLine();
    }

    public LocalDateTime createDateTime(String date, DateTimeFormatter formatter) {
        return LocalDateTime.parse(date, formatter);
    }

    public String getLocalDateTime(LocalDateTime localDateTime, DateTimeFormatter dateTimeFormatter) {
        return localDateTime.format(dateTimeFormatter);
    }

    public String getUtcTime(LocalDateTime localDateTime, DateTimeFormatter dateTimeFormatter) {
        ZonedDateTime ldtZoned = localDateTime.atZone(ZoneId.systemDefault());
        ZonedDateTime utcZoned = ldtZoned.withZoneSameInstant(ZoneId.of("UTC"));
        return utcZoned.toLocalDateTime().format(dateTimeFormatter);
    }

    public String getTimeInLondon(LocalDateTime localDateTime, DateTimeFormatter dateTimeFormatter) {
        ZonedDateTime ldtZoned = localDateTime.atZone(ZoneId.systemDefault());
        ZonedDateTime londonZoned = ldtZoned.withZoneSameInstant(ZoneId.of("Europe/London"));
        return londonZoned.toLocalDateTime().format(dateTimeFormatter);
    }

    public String getTimeInLosAngeles(LocalDateTime localDateTime, DateTimeFormatter dateTimeFormatter) {
        ZonedDateTime ldtZoned = localDateTime.atZone(ZoneId.systemDefault());
        ZonedDateTime londonZoned = ldtZoned.withZoneSameInstant(ZoneId.of("America/Los_Angeles"));
        return londonZoned.toLocalDateTime().format(dateTimeFormatter);
    }

    public String getTimeInSydney(LocalDateTime localDateTime, DateTimeFormatter dateTimeFormatter) {
        ZonedDateTime ldtZoned = localDateTime.atZone(ZoneId.systemDefault());
        ZonedDateTime sydneyTime = ldtZoned.withZoneSameInstant(ZoneId.of("Australia/Sydney"));
        return sydneyTime.toLocalDateTime().format(dateTimeFormatter);
    }

    private List<DateTimeFormatter> initFormattersList() {
        List<DateTimeFormatter> formatters = new ArrayList<>();
        DateTimeFormatter dateTimeFormatter;
        Format[] formatsValues = Format.values();
        for (Format formatsValue : formatsValues) {
            if (formatsValue == Format.FORMAT_2) {
                dateTimeFormatter = new DateTimeFormatterBuilder()
                        .appendPattern("yyyy-MM-dd")
                        .optionalStart()
                        .appendPattern(" HH:mm")
                        .optionalEnd()
                        .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
                        .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
                        .toFormatter();

            } else {
                dateTimeFormatter = DateTimeFormatter.ofPattern(formatsValue.getDescription());
            }

            formatters.add(dateTimeFormatter);

        }

        return formatters;
    }

    public DateTimeFormatter getDateTimeFormatter(String dateString) throws DateTimeParseException {
        DateTimeFormatter formatter = null;
        LocalDateTime localDateTime = null;
        List<DateTimeFormatter> dateTimeFormatters = initFormattersList();
        for (DateTimeFormatter dateTimeFormatter : dateTimeFormatters) {
            try {
                localDateTime = LocalDateTime.parse(dateString, dateTimeFormatter);
                formatter = dateTimeFormatter;
                break;
            } catch (DateTimeParseException e) {
                //
            }

        }
        if (localDateTime == null) {
            throw new DateTimeParseException("Nieprawidłowy format daty", dateString, 0);
        }

        return formatter;
    }

}
