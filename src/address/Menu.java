package address;

import address.data.AddressBook;
import address.data.AddressEntry;

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
}
