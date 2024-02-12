package assignment;

import static java.time.format.DateTimeFormatter.ofPattern;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * A record class capturing a person's properties.
 *
 * @param name of the person
 * @param gender of the person
 * @param birthday of the person
 */
public record Person(String name, Gender gender, LocalDate birthday) {

    /**
     * Parses a line of CSV file to Person object.
     * @param line of the CSV
     * @return Person
     */
    private static Person parsePersonFromLine(String line) {
        String[] data = line.split(", ");
        String name = data[0];
        Gender gender = Gender.valueOf(data[1].toUpperCase());
        LocalDate birthday = LocalDate.parse(data[2], ofPattern("dd/MM/yy"));

        return new Person(name, gender, birthday);
    }

    /**
     * Parses a CSV file to a collection of people.
     *
     * In case the input is not in the right format the program crashes.
     *
     * @param fileName of the CSV
     * @return a list of Person
     */
    static List<Person> parsePeopleFromCSV(String fileName) {
        List<Person> people = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                people.add(parsePersonFromLine(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return people;
    }
}
