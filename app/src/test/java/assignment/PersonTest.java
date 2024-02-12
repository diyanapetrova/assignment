package assignment;

import org.junit.jupiter.api.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

public class PersonTest {
    @Test
    public void parsePeopleFromCSV() {
        String file = "test.txt";
        List<Person> input = List.of(new Person("Alan Barker", Sex.MALE, LocalDate.parse("16/03/77", DateTimeFormatter.ofPattern("dd/MM/yy"))));
        generateTestCSV(file, input);

        List<Person> result = Person.parsePeopleFromCSV(file);
        assertIterableEquals(result, input);
    }

    private void generateTestCSV(String fileName, List<Person> people) {
        try {
            FileWriter writer = new FileWriter(fileName);
            for (Person person : people) {
                writer.append(person.name()).append(", ")
                        .append(person.sex().toString()).append(", ")
                        .append(person.birthDate().format(DateTimeFormatter.ofPattern("dd/MM/yy")))
                        .append("\n");
            }

            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
