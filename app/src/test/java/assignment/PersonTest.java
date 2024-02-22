package assignment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class PersonTest {

    private static final Person ALAN_BARKER = new Person("Alan Barker", Gender.MALE,
            LocalDate.parse("15/03/1960", DateTimeFormatter.ofPattern("dd/MM/yyyy")));

    @Test
    void parsePeopleFromCSV() throws IOException {
        String file = "test.txt";
        List<Person> input = List.of(ALAN_BARKER);
        generateTestCSV(file, generateCorrectInput(input));

        List<Person> result = Person.parsePeopleFromCSV(file);

        assertIterableEquals(result, input);
    }

    @Test
    void parsePeopleFromCSV_yearIsInTheFuture_changeCentury() throws IOException {
        String file = "test.txt";
        List<Person> input = List.of(ALAN_BARKER);
        generateTestCSV(file, generateCorrectInput(input));

        List<Person> result = Person.parsePeopleFromCSV(file);

        assertEquals(result.get(0).birthday().getYear(), 1960);
    }

    @Test
    void parsePeopleFromCSV_yearIsInThePast_keep20Base() throws IOException {
        String file = "test.txt";
        List<Person> input = List.of(
                new Person("Alan Barker Junior", Gender.MALE,
                        LocalDate.parse("15/03/23", DateTimeFormatter.ofPattern("dd/MM/yy"))));
        generateTestCSV(file, generateCorrectInput(input));

        List<Person> result = Person.parsePeopleFromCSV(file);

        assertEquals(result.get(0).birthday().getYear(), 2023);
    }

    @Test
    void parsePeopleFromCSV_nonexistentFile_throwsException() throws IOException {
        assertThrows(FileNotFoundException.class, () ->
                Person.parsePeopleFromCSV("nonexistent.txt")
        );
    }

    @Test
    void parsePeopleFromCSV_wrongGenderFormat_throwsException() throws IOException {
        assertThrows(IllegalArgumentException.class, () -> {
                    String file = "test.txt";
                    List<Person> input = List.of(ALAN_BARKER);
                    generateTestCSV(file, generateWrongGenderFormat(input));

                    Person.parsePeopleFromCSV(file);
                }
        );
    }

    @Test
    void parsePeopleFromCSV_wrongDateFormat_throwsException() throws IOException {
        assertThrows(DateTimeParseException.class, () -> {
                    String file = "test.txt";
                    List<Person> input = List.of(ALAN_BARKER);
                    generateTestCSV(file, generateWrongDateFormat(input));

                    Person.parsePeopleFromCSV(file);
                }
        );
    }

    private String generateWrongGenderFormat(List<Person> people) {
        StringBuilder builder = new StringBuilder();

        for (Person person : people) {
            builder.append(person.name()).append(", ")
                    .append("WRONG").append(", ")
                    .append(person.birthday().format(DateTimeFormatter.ofPattern("dd/MM/yy")))
                    .append("\n");
        }

        return builder.toString();
    }

    private String generateWrongDateFormat(List<Person> people) {
        StringBuilder builder = new StringBuilder();

        for (Person person : people) {
            builder.append(person.name()).append(", ")
                    .append(person.gender().toString()).append(", ")
                    .append(person.birthday().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                    .append("\n");
        }

        return builder.toString();
    }

    private void generateTestCSV(String fileName, String input) {
        try {
            FileWriter writer = new FileWriter(fileName);
            writer.append(input);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String generateCorrectInput(List<Person> people) {
        StringBuilder builder = new StringBuilder();
        for (Person person : people) {
            builder.append(person.name()).append(", ")
                    .append(person.gender().toString()).append(", ")
                    .append(person.birthday().format(DateTimeFormatter.ofPattern("dd/MM/yy")))
                    .append("\n");
        }

        return builder.toString();
    }

}
