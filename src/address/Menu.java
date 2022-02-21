package address;

import address.data.AddressBook;
import address.data.AddressEntry;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

/**
 * {@link Menu} class is used to display menu options to the user to modify an {@link AddressBook}.
 */
public class Menu {

    /**
     * The scanner used to read input.
     */
    private final Scanner scanner;

    /**
     * Creates a menu that reads from the system input.
     */
    public Menu() {
        this(new Scanner(System.in));
    }

    /**
     * Creates a menu that reads from custom input.
     *
     * @param inputReader The user's custom input.
     */
    public Menu(Scanner inputReader) {
        scanner = inputReader;
    }

    /**
     * Helper method that prints the given prompt,
     * then reads input from the user and returns the result.
     *
     * @param prompt The prompt message to display.
     * @return The user input string.
     */
    private String prompt(String prompt) {
        System.out.println(prompt);
        return scanner.nextLine();
    }

    /**
     * Prompts the user for their first name.
     *
     * @return The first name.
     */
    public String prompt_FirstName() {
        return prompt("First Name:");
    }

    /**
     * Prompts the user for their last name.
     *
     * @return The last name.
     */
    public String prompt_LastName() {
        return prompt("Last Name:");
    }

    /**
     * Prompts the user for their street name.
     *
     * @return The street name.
     */
    public String prompt_Street() {
        return prompt("Street:");
    }

    /**
     * Prompts the user for their city name.
     *
     * @return The city name.
     */
    public String prompt_City() {
        return prompt("City:");
    }

    /**
     * Prompts the user for their state name.
     *
     * @return The state name.
     */
    public String prompt_State() {
        return prompt("State:");
    }

    /**
     * Prompts the user for their zipcode.
     *
     * @return The zipcode.
     */
    public int prompt_Zip() throws NumberFormatException {
        String zipString = prompt("Zip:");
        return Integer.parseInt(zipString);
    }

    /**
     * Prompts the user for their phone number.
     *
     * @return The phone number.
     */
    public String prompt_Phone() {
        return prompt("Phone:");
    }

    /**
     * Prompts the user for their email address.
     *
     * @return The email address.
     */
    public String prompt_Email() {
        return prompt("Email:");
    }

    /**
     * Displays the menu options to the user and prompts the user
     * for input, then executes the selected menu item.
     *
     * @return True if the user selects 'f' to quit. False otherwise.
     */
    public boolean promptMenu(AddressBook addressBook) {
        final String menuOptions = """
                *****************************
                Please enter in your menu selection
                a) Loading From File
                b) Addition
                c) Removal
                d) Find
                e) Listing
                f) Quit
                *****************************
                """;
        String input = prompt(menuOptions);

        switch (input) {
            case "a":
                loadFile(addressBook);
                break;
            case "b":
                addEntry(addressBook);
                break;
            case "c":
                removeEntry(addressBook);
                break;
            case "d":
                findEntry(addressBook);
                break;
            case "e":
                listEntries(addressBook);
                break;
            case "f":
                return true;
            default:
                System.out.println("That is not an option. Please try again.");
        }
        return false;
    }

    /**
     * Reads {@link AddressEntry entries} from a file given by the user.
     *
     * @param addressBook The {@link AddressBook} to add entries to.
     */
    public void loadFile(AddressBook addressBook) {
        String fileName = prompt("Enter a file name:");
        addressBook.loadFile(fileName);
    }

    /**
     * Adds an {@link AddressEntry} given by the user to the {@link AddressBook}.
     *
     * @param addressBook The {@link AddressBook} to add an entry to.
     */
    public void addEntry(AddressBook addressBook) {
        AddressEntry entry = promptEntry();
        addressBook.addEntry(entry);
    }

    /**
     * Removes an {@link AddressEntry entry} from the {@link AddressBook}
     * chosen by the user based on the last name of the entry.
     *
     * @param addressBook The {@link AddressBook} to remove an entry from.
     */
    public void removeEntry(AddressBook addressBook) {
        Collection<AddressEntry> entries = findEntries(addressBook, prompt_LastName());
        String indexString = prompt("Enter index of address entry:");
        try {
            int i = Integer.parseInt(indexString);
            AddressEntry entry = new ArrayList<>(entries).get(i - 1);
            addressBook.removeEntry(entry);
        } catch (Exception e) {
            System.out.println("Invalid index.");
        }
    }

    /**
     * Finds and prints {@link AddressEntry address entries} matching
     * the lastName given by the user from the {@link AddressBook}.
     *
     * @param addressBook The address book to search from.
     */
    public void findEntry(AddressBook addressBook) {
        findEntries(addressBook, prompt_LastName());
    }

    /**
     * Lists each entry in the {@link AddressBook} in alphabetical order by last name.
     *
     * @param addressBook The address book to list from {@link AddressEntry entries}.
     */
    public void listEntries(AddressBook addressBook) {
        addressBook.listEntries();
    }

    /**
     * Finds and prints {@link AddressEntry address entries} matching
     * the lastName from the {@link AddressBook}.
     *
     * @param addressBook The address book to search from.
     * @param lastName    The last name to filter {@link AddressEntry entries} by.
     * @return A {@link Collection<AddressEntry>} of {@link AddressEntry address entries}
     * with entries that match the given last name.
     */
    private Collection<AddressEntry> findEntries(AddressBook addressBook, String lastName) {
        Collection<AddressEntry> addresses = addressBook.findEntries(lastName);
        AddressBook.listEntries(addresses);
        return addresses;
    }

    /**
     * Prompts the user to enter information to create an {@link AddressEntry}.
     *
     * @return The new address entry {@link AddressEntry}.
     */
    private AddressEntry promptEntry() {
        String firstName = prompt_FirstName();
        String lastName = prompt_LastName();
        String street = prompt_Street();
        String city = prompt_City();
        String state = prompt_State();
        int zip = prompt_Zip();
        String email = prompt_Email();
        String telephone = prompt_Phone();

        return new AddressEntry(firstName, lastName, street, city, state, zip, email, telephone);
    }
}
