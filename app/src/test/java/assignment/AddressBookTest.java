package assignment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class AddressBookTest {
    private static final Person ALAN_BARKER = new Person("Alan Barker", Gender.MALE, LocalDate.parse("15/03/60", DateTimeFormatter.ofPattern("dd/MM/yy")));
    private static final Person BEN_BROOK = new Person("Ben Brook", Gender.MALE, LocalDate.parse("16/03/60", DateTimeFormatter.ofPattern("dd/MM/yy")));
    private static final Person LEA_BARKER = new Person("Lea Barker", Gender.FEMALE, LocalDate.parse("16/05/77", DateTimeFormatter.ofPattern("dd/MM/yy")));

    private static final List<Person> people = List.of(
            BEN_BROOK,
            ALAN_BARKER,
            LEA_BARKER
    );

    @Test
    void countPeopleByGender() {
        AddressBook addressBook = new AddressBook(people);

        assertEquals(addressBook.countPeopleByGender(Gender.MALE), 2);
        assertEquals(addressBook.countPeopleByGender(Gender.FEMALE), 1);
    }

    @Test
    void countPeopleByGender_emptyCollection_returns0() {
        AddressBook addressBook = new AddressBook(Collections.emptyList());

        assertEquals(addressBook.countPeopleByGender(Gender.FEMALE), 0);
    }

    @Test
    void findTheOldest() {
        AddressBook addressBook = new AddressBook(people);

        assertTrue(addressBook.findTheOldest().isPresent());
        assertEquals(addressBook.findTheOldest().get(), ALAN_BARKER);
    }

    @Test
    void findTheOldest_emptyCollection_returnsEmpty() {
        AddressBook addressBook = new AddressBook(Collections.emptyList());

        assertTrue(addressBook.findTheOldest().isEmpty());
    }

    @Test
    void countDaysBetweenBirthdays() {
        AddressBook addressBook = new AddressBook(people);

        Optional<Long> result = addressBook.countDaysBetweenBirthdays(ALAN_BARKER.name(), BEN_BROOK.name());

        assertTrue(result.isPresent());
        assertEquals(result.get(), 1);
    }

    @Test
    void countDaysBetweenBirthdays_emptyCollection_returnEmpty() {
        AddressBook addressBook = new AddressBook(Collections.emptyList());

        Optional<Long> result = addressBook.countDaysBetweenBirthdays(ALAN_BARKER.name(), "name");

        assertTrue(result.isEmpty());
    }

    @Test
    void countDaysBetweenBirthdays_oneNotFound_returnEmpty() {
        AddressBook addressBook = new AddressBook(people);

        Optional<Long> result = addressBook.countDaysBetweenBirthdays(ALAN_BARKER.name(), "Name not in the collection.");

        assertTrue(result.isEmpty());
    }
}
