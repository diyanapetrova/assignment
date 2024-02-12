/*
 * Main class for the AddressBook.
 */
package assignment;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class App {

    public static void main(String[] args) {
        //Read data
        String file = "AddressBook.txt";
        List<Person> people;

        try {
            people = Person.parsePeopleFromCSV(file);
        } catch (IOException e) {
            // If we can't load data this application is not useful so we crash.
            throw new IllegalArgumentException(e);
        }

        // Create AddressBook
        AddressBook addressBook = new AddressBook(people);

        //Print results
        System.out.println("Number of males: " + addressBook.countPeopleByGender(Gender.MALE));
        System.out.println("The oldest is: " + (addressBook.findTheOldest().isPresent() ? addressBook.findTheOldest().get().name() : "Can't find oldest."));
        Optional<Long> daysBetween = addressBook.countDaysBetweenBirthdays("Bill McKnight", "Paul Robinson");
        System.out.println(daysBetween.map(days -> "Bill is " + days + " days older than Paul.").orElse("Paul or Bill are not found."));

    }
}
