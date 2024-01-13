package pl.javastart.task;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.util.List;
import java.util.Scanner;

import static pl.javastart.task.Format.FORMAT_1;

public class Main {

    public static void main(String[] args) {
        Main main = new Main();
        main.run(new Scanner(System.in));
    }

    public void run(Scanner scanner) {

        try {
            DateTimeManager dateTimeManager = new DateTimeManager();
            dateTimeManager.printOptions();
            String userDateTime = dateTimeManager.readDate(scanner);
            DateTimeFormatter dateTimeFormatter = dateTimeManager.getDateTimeFormatter(userDateTime);
            LocalDateTime dateTime = dateTimeManager.createDateTime(userDateTime, dateTimeFormatter);
            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern(FORMAT_1.getPattern());

            var zones = List.of(
                    new TimeZoneToDisplay("Czas lokalny", ZoneId.systemDefault().getId()),
                    new TimeZoneToDisplay("UTC", "UTC"),
                    new TimeZoneToDisplay("Londyn", "Europe/London"),
                    new TimeZoneToDisplay("Los Angeles", "America/Los_Angeles"),
                    new TimeZoneToDisplay("Sydney", "Australia/Sydney")
            );

            for (TimeZoneToDisplay zone : zones) {
                System.out.printf("%s: %s%n", zone.display(), dateTimeManager.getTimeForGivenZone(dateTime,
                        outputFormatter, zone.zoneName()));
            }

        } catch (DateTimeParseException e) {
            System.out.println(e.getMessage());
        }
    }

}
