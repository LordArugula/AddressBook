package address;

import address.data.AddressBook;
import address.data.AddressEntry;
import address.gui.MenuGUI;

import javax.swing.*;

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
        initAddressBook(ab, filePath);

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                var frame = new MenuGUI(ab);
                frame.setVisible(true);
            }
        });
    }

    /**
     * Initializes the {@link AddressBook} ab from a text file.
     *
     * @param ab       The {@link AddressBook} that will have two
     *                 {@link AddressEntry address entries} added to it.
     * @param filePath The path to the text file to load {@link AddressEntry address entries} from.
     */
    private static void initAddressBook(AddressBook ab, String filePath) {
        ab.loadFile(filePath);
    }
}
