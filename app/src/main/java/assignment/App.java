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

        //Create AddressBook

        //Print results
    }
}
