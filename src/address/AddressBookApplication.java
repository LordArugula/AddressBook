package address;

import address.data.AddressBook;
import address.data.AddressEntry;

/**
 * AddressBookApplication is the entry point of the application and
 * calls some of the {@link Menu} class methods.
 */
public class AddressBookApplication {

    /**
     * The entry point of the {@link AddressBookApplication} and calls
     * some of the {@link Menu} class methods.
     *
     * @param args The input arguments.
     */
    public static void main(String[] args) {
        final String filePath = "resources/AddressInputDataFile.txt";

        AddressBook ab = new AddressBook();
        initAddressBookApplication(ab, filePath);

        Menu menu = new Menu();
        boolean shouldQuit;

        do {
            shouldQuit = menu.promptMenu(ab);

        } while (!shouldQuit);
    }

    /**
     * Initializes the {@link AddressBook} ab from a text file.
     *
     * @param ab       The {@link AddressBook} that will have two
     *                 {@link AddressEntry address entries} added to it.
     * @param filePath The path to the text file to load {@link AddressEntry address entries} from.
     */
    private static void initAddressBookApplication(AddressBook ab, String filePath) {
        ab.loadFile(filePath);
    }
}
