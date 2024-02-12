package assignment;

import static java.time.format.DateTimeFormatter.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public record Person(String name, Sex sex, LocalDate birthDate) {

    static Person parsePersonFromLine(String line) {
        String[] data = line.split(", ");
        String name = data[0];
        Sex sex = Sex.valueOf(data[1].toUpperCase());
        LocalDate birthDate = LocalDate.parse(data[2], ofPattern("dd/MM/yy"));

        return new Person(name, sex, birthDate);
    }

    static List<Person> parsePeopleFromCSV(String filename) {
        List<Person> people = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
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
