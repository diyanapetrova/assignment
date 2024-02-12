package assignment;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

/**
 * AddressBook class that keeps a collection of people.
 * <p>
 * It exposes the following functionality:
 * - count number of female or males in the address book
 * - find the oldest person in the address book
 * - count the days between two people's birthdays
 */
public class AddressBook {
    private final List<Person> people;

    public AddressBook(List<Person> people) {
        this.people = people;
    }

    /**
     * Counts the number of people in the address book by gender.
     *
     * @param gender the desired gender
     * @return the number of people by gender
     */
    public long countPeopleByGender(Gender gender) {
        return people.stream().filter(person -> person.gender() == gender).count();
    }

    /**
     * Finds the oldest person in the address book.
     *
     * @return an optional of the oldest person or empty if the address book is empty
     */
    public Optional<Person> findTheOldest() {
        return people.stream().reduce((curr, next) ->
                curr.birthday().isBefore(next.birthday()) ? curr : next
        );
    }

    /**
     * Calculates the number of days between two people birthdays.
     *
     * @param name1 name of the first person
     * @param name2 name of the second person
     * @return an optional of the number of days or empty if any of the people was not found in the address book
     */
    public Optional<Long> countDaysBetweenBirthdays(String name1, String name2) {
        Optional<Person> optionalPerson1 = findPersonByName(name1);
        Optional<Person> optionalPerson2 = findPersonByName(name2);

        if (optionalPerson1.isPresent() && optionalPerson2.isPresent()) {
            return Optional.of(
                    Duration.between(optionalPerson1.get().birthday().atStartOfDay(),
                            optionalPerson2.get().birthday().atStartOfDay()).toDays());
        } else {
            return Optional.empty();
        }
    }

    private Optional<Person> findPersonByName(String name) {
        return people.stream().filter(person -> person.name().equals(name)).findFirst();
    }
}
