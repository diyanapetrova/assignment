package assignment;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;

import org.junit.jupiter.api.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class PersonTest {

    private static final Person ALAN_BARKER = new Person("Alan Barker", Gender.MALE, LocalDate.parse("15/03/60", DateTimeFormatter.ofPattern("dd/MM/yy")));

    @Test
    void parsePeopleFromCSV() {
        String file = "test.txt";
        List<Person> input = List.of(ALAN_BARKER);
        generateTestCSV(file, input);

        List<Person> result = Person.parsePeopleFromCSV(file);

        assertIterableEquals(result, input);
    }

    private void generateTestCSV(String fileName, List<Person> people) {
        try {
            FileWriter writer = new FileWriter(fileName);
            for (Person person : people) {
                writer.append(person.name()).append(", ")
                        .append(person.gender().toString()).append(", ")
                        .append(person.birthday().format(DateTimeFormatter.ofPattern("dd/MM/yy")))
                        .append("\n");
            }

            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
