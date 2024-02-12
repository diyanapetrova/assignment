/*
 * Main class for the AddressBook.
 */
package assignment;

import java.util.List;

public class App {

    public static void main(String[] args) {
        //Read data
        String file = "AddressBook.txt";
        List<Person> people = Person.parsePeopleFromCSV(file);
        System.out.println(people);

        // Create AddressBook
        AddressBook addressBook = new AddressBook(people);

        //Print results
        System.out.println(addressBook.count(Sex.MALE));
        System.out.println(
                addressBook.getOldest().isPresent() ? addressBook.getOldest().get().name() : "Can't find oldest.");
    }
}
