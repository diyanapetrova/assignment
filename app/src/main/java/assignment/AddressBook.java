package assignment;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

public class AddressBook {
    List<Person> people;

    public AddressBook(List<Person> people) {
        this.people = people;
    }

    public long count(Sex sex) {
        return people.stream().filter(person -> person.sex() == sex).count();
    }

    public Optional<Person> getOldest() {
        return people.stream().reduce((curr, next) -> {
            if (curr.birthDate().isBefore(next.birthDate())) {
                return curr;
            } else {
                return next;
            }
        });
    }

    public Optional<Person> getPersonByName(String name) {
        return people.stream().filter(person -> person.name().equals(name)).findFirst();
    }

    public Optional<Long> daysBetween(String a, String b) {
        Optional<Person> personA = getPersonByName(a);
        Optional<Person> personB = getPersonByName(b);
        if (personA.isPresent() && personB.isPresent()) {
            return Optional.of(
                    Duration.between(personA.get().birthDate().atStartOfDay(),
                            personB.get().birthDate().atStartOfDay()).toDays());
        } else {
            return Optional.empty();
        }
    }


}
