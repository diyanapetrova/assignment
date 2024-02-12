package assignment;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;

public class AddressBookTest {
    @Test
    void countMales() {
        AddressBook addressBook = new AddressBook(List.of(
                new Person("Alan Barker", Sex.MALE, LocalDate.parse("16/03/77", DateTimeFormatter.ofPattern("dd/MM/yy"))),
                new Person("Alan Barker1", Sex.MALE, LocalDate.parse("16/03/78", DateTimeFormatter.ofPattern("dd/MM/yy"))),
                new Person("Lea Barker", Sex.FEMALE, LocalDate.parse("16/05/77", DateTimeFormatter.ofPattern("dd/MM/yy")))));

        assertEquals(addressBook.count(Sex.MALE), 2);
    }

    @Test
    void countFemales() {
        AddressBook addressBook = new AddressBook(List.of(
                new Person("Alan Barker", Sex.MALE, LocalDate.parse("16/03/77", DateTimeFormatter.ofPattern("dd/MM/yy"))),
                new Person("Alan Barker1", Sex.MALE, LocalDate.parse("16/03/78", DateTimeFormatter.ofPattern("dd/MM/yy"))),
                new Person("Lea Barker", Sex.FEMALE, LocalDate.parse("16/05/77", DateTimeFormatter.ofPattern("dd/MM/yy")))));

        assertEquals(addressBook.count(Sex.FEMALE), 1);
    }

    @Test
    void countEmpty() {
        AddressBook addressBook = new AddressBook(Collections.emptyList());

        assertEquals(addressBook.count(Sex.FEMALE), 0);
    }
}
