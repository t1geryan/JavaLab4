package org.example;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
import org.example.model.Division;
import org.example.model.Gender;
import org.example.model.Human;
import org.example.utils.Sequence;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class Main {
    public static void main(String[] args) {
        final Sequence divisionIdSequence = new Sequence();
        final List<Human> humans = new ArrayList<>();
        final List<Division> divisions = new ArrayList<>();
        readCSVFile(divisionIdSequence, humans, divisions);
        try (Writer writer = new FileWriter(OUTPUT_FILE_NAME)) {
            outputData(writer, humans, divisions);
            System.out.printf("Results of programs was wrote to the %s file\n", OUTPUT_FILE_NAME);
        } catch (IOException e) {
            System.out.println("There is exception while writing results");
        }
        System.out.println("Program Done.");
    }

    private static void readCSVFile(Sequence divisionIdSequence, List<Human> humans, List<Division> divisions) {
        final var fullPath = System.getProperty("user.dir") + INPUT_FILE_PATH;
        try {
            File in = new File(fullPath);
            try (CSVReader reader = new CSVReaderBuilder(new FileReader(in))
                    .withCSVParser(CSV_PARSER)
                    .build()) {
                String[] nextLine;
                // Skip title
                reader.skip(1);
                while ((nextLine = reader.readNext()) != null) {
                    handleCSVLine(divisionIdSequence, humans, divisions, nextLine);
                }
            }
        } catch (CsvValidationException e) {
            System.out.printf("There is invalid CSV file: %s\n", fullPath);
        } catch (IOException e) {
            System.out.printf("There is exception while working with CSV file: %s:\n%s\n", fullPath, e);
        } catch (NumberFormatException e) {
            System.out.println("There is invalid value in CSV file");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("There is missing value in CSV file");
        } catch (ParseException e) {
            System.out.println("There is invalid date value in CSV file");
        }
    }

    private static void handleCSVLine(Sequence divisionIdSequence, List<Human> humans, List<Division> divisions, String[] nextLine) throws ParseException {
        final var humanId = Integer.parseInt(nextLine[ID_INDEX]);
        final var humanName = nextLine[NAME_INDEX];
        final var gender = nextLine[GENDER_INDEX].equals("Male") ? Gender.MALE : Gender.FEMALE;
        final var formatter = new SimpleDateFormat("dd.MM.yyyy");
        final var date = formatter.parse(nextLine[DATE_INDEX]);
        final var divisionName = nextLine[DIVISION_NAME_INDEX];
        final var oldDivision = divisions.stream().filter(e -> e.getName().equals(divisionName)).findFirst();
        Division division;
        if (oldDivision.isPresent()) {
            division = oldDivision.get();
        } else {
            division = new Division(divisionIdSequence.getNext(), divisionName);
            divisions.add(division);
        }
        final var salary = Float.parseFloat(nextLine[SALARY_INDEX]);
        humans.add(new Human(humanId, humanName, gender, date, salary, division));
    }

    private static void outputData(Writer writer, List<Human> humans, List<Division> divisions) throws IOException {
        final var collector = Collectors.joining(",\n", "", "\n");
        writer.append("Divisions:\n");
        writer.append(divisions.stream().map(Division::toString).collect(collector));
        writer.append("\nHumans:\n");
        writer.append(humans.stream().map(Human::toString).collect(collector));
    }

    private static final CSVParser CSV_PARSER = new CSVParserBuilder().withSeparator(';').build();
    private static final int ID_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int GENDER_INDEX = 2;
    private static final int DATE_INDEX = 3;
    private static final int DIVISION_NAME_INDEX = 4;
    private static final int SALARY_INDEX = 5;
    private static final String INPUT_FILE_PATH = "\\src\\main\\java\\org\\example\\data\\foreign_names.csv";
    private static final String OUTPUT_FILE_NAME = "output.txt";
}