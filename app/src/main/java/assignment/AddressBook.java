package assignment;

import java.util.List;

public class AddressBook {
    List<Person> people;

    public AddressBook(List<Person> people) {
        this.people = people;
    }

    public long count(Sex sex){
        return people.stream().filter(person -> person.sex() == sex).count();
    }
}
