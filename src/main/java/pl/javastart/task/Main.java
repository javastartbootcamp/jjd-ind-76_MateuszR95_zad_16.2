package pl.javastart.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.util.Scanner;

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
            System.out.println("Czas lokalny: " + dateTimeManager.getLocalDateTime(dateTime, dateTimeFormatter));
            System.out.println("UTC: " + dateTimeManager.getUtcTime(dateTime, dateTimeFormatter));
            System.out.println("Londyn: " + dateTimeManager.getTimeInLondon(dateTime, dateTimeFormatter));
            System.out.println("Los Angeles: " + dateTimeManager.getTimeInLosAngeles(dateTime, dateTimeFormatter));
            System.out.println("Sydney : " + dateTimeManager.getTimeInSydney(dateTime, dateTimeFormatter));

        } catch (DateTimeParseException e) {
            System.out.println(e.getMessage());
        }

    }

}
