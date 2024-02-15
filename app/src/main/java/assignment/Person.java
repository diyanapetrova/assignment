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
 * @param name     of the person
 * @param gender   of the person
 * @param birthday of the person
 */
public record Person(String name, Gender gender, LocalDate birthday) {

    /**
     * Parses a line of CSV to a Person.
     *
     * @param line of the CSV
     * @return Person
     */
    private static Person parsePersonFromLine(String line) {
        String[] data = line.split(", ");
        String name = data[0];
        Gender gender = Gender.valueOf(data[1].toUpperCase());
        // Local date assumes that yy corresponds to 20yy which is probably the wrong assumption in our use case.
        // See https://stackoverflow.com/questions/38354151/how-to-force-java-time-localdate-to-assume-19th-century-as-yy-year-patternfor
        LocalDate birthday = LocalDate.parse(data[2], ofPattern("dd/MM/yy"));

        return new Person(name, gender, birthday);
    }

    /**
     * Parses a CSV file to a collection of people.
     * <p>
     * If the data is not in the correct format a runtime exception will be thrown.
     *
     * @param fileName of the CSV
     * @return a list of Person
     * @throws IOException if any IO problems occur during reading
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
