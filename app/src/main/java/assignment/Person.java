package assignment;

import static java.time.format.DateTimeFormatter.ofPattern;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * A record class capturing a person's properties.
 *
 * @param name     of the person
 * @param gender   of the person
 * @param birthday of the person
 */
public record Person(String name, Gender gender, LocalDate birthday) {

    /**
     * Parses a line of CSV file to Person object.
     *
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
     * <p>
     * If the data is not in the correct format this would fail.
     *
     * @param fileName of the CSV
     * @throws IOException if any IO problems occur during reading
     * @return a list of Person
     */
    static List<Person> parsePeopleFromCSV(String fileName) throws IOException {
        List<Person> people = new ArrayList<>();

        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line;
        while ((line = reader.readLine()) != null) {
            people.add(parsePersonFromLine(line));
        }

        return people;
    }
}
