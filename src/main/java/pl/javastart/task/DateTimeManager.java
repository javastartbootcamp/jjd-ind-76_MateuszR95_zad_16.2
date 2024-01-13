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
            System.out.println(value.getPattern());
        }
    }

    public String readDate(Scanner scanner) {
        System.out.println("Podaj datę");
        return scanner.nextLine();
    }

    public LocalDateTime createDateTime(String date, DateTimeFormatter formatter) {
        return LocalDateTime.parse(date, formatter);
    }

    public String getTimeForGivenZone(LocalDateTime localDateTime, DateTimeFormatter dateTimeFormatter, String timeZone) {
        ZonedDateTime ldtZoned = localDateTime.atZone(ZoneId.systemDefault());
        ZonedDateTime timeZonedTime = ldtZoned.withZoneSameInstant(ZoneId.of(timeZone));
        return timeZonedTime.toLocalDateTime().format(dateTimeFormatter);
    }

    private List<DateTimeFormatter> initFormattersList() {
        List<DateTimeFormatter> formatters = new ArrayList<>();
        DateTimeFormatter dateTimeFormatter;
        Format[] formatsValues = Format.values();
        for (Format formatsValue : formatsValues) {
            if (!formatsValue.isContainsTime()) {
                dateTimeFormatter = new DateTimeFormatterBuilder()
                        .appendPattern("yyyy-MM-dd")
                        .optionalStart()
                        .appendPattern(" HH:mm")
                        .optionalEnd()
                        .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
                        .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
                        .toFormatter();

            } else {
                dateTimeFormatter = DateTimeFormatter.ofPattern(formatsValue.getPattern());
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
