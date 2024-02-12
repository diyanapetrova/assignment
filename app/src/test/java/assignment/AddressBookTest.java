package assignment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class AddressBookTest {
    private static final Person alanBarker = new Person("Alan Barker", Sex.MALE, LocalDate.parse("15/03/60", DateTimeFormatter.ofPattern("dd/MM/yy")));
    private static final Person benBrook = new Person("Ben Brook", Sex.MALE, LocalDate.parse("16/03/60", DateTimeFormatter.ofPattern("dd/MM/yy")));

    private static final List<Person> people = List.of(
            benBrook,
            alanBarker,
            new Person("Lea Barker", Sex.FEMALE, LocalDate.parse("16/05/77", DateTimeFormatter.ofPattern("dd/MM/yy"))));

    @Test
    void countMales() {
        AddressBook addressBook = new AddressBook(people);

        assertEquals(addressBook.count(Sex.MALE), 2);
    }

    @Test
    void countFemales() {
        AddressBook addressBook = new AddressBook(people);

        assertEquals(addressBook.count(Sex.FEMALE), 1);
    }

    @Test
    void count_emptyCollection_return0() {
        AddressBook addressBook = new AddressBook(Collections.emptyList());

        assertEquals(addressBook.count(Sex.FEMALE), 0);
    }

    @Test
    void getOldest_notEmpty_returnPerson() {
        AddressBook addressBook = new AddressBook(people);

        assertFalse(addressBook.getOldest().isEmpty());
        assertEquals(addressBook.getOldest().get(), alanBarker);
    }

    @Test
    void getOldest_emptyCollection_returnEmpty() {
        AddressBook addressBook = new AddressBook(Collections.emptyList());

        assertTrue(addressBook.getOldest().isEmpty());
    }

    @Test
    void getPersonByName_returnPerson() {
        AddressBook addressBook = new AddressBook(people);

        Optional<Person> result = addressBook.getPersonByName(alanBarker.name());

        assertTrue(result.isPresent());
        assertEquals(result.get(), alanBarker);
    }

    @Test
    void getPersonByName_returnEmpty() {
        AddressBook addressBook = new AddressBook(Collections.emptyList());

        Optional<Person> result = addressBook.getPersonByName(alanBarker.name());

        assertTrue(result.isEmpty());
    }

    @Test
    void daysBetween_emptyCollection_returnEmpty() {
        AddressBook addressBook = new AddressBook(Collections.emptyList());

        Optional<Long> result = addressBook.daysBetween(alanBarker.name(), "name");

        assertTrue(result.isEmpty());
    }

    @Test
    void daysBetween_oneNotFound_returnEmpty() {
        AddressBook addressBook = new AddressBook(people);

        Optional<Long> result = addressBook.daysBetween(alanBarker.name(), "Name not in the collection.");

        assertTrue(result.isEmpty());
    }

    @Test
    void daysBetween() {
        AddressBook addressBook = new AddressBook(people);

        Optional<Long> result = addressBook.daysBetween(alanBarker.name(), benBrook.name());

        assertTrue(result.isPresent());
        assertEquals(result.get(), 1);
    }
}
