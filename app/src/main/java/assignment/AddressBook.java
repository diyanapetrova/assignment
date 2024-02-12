package assignment;

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
}
