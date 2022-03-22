package address;

import address.data.AddressBook;
import address.data.AddressEntry;

import javax.swing.*;
import java.sql.SQLException;

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

        AddressBook ab = new AddressBook();
        AddressBookConnection connection = null;
        if (args.length == 5) {
            try {
                String login = args[0];
                String password = args[1];
                String host = args[2];
                String port = args[3];
                String sid = args[4];

                connection = new AddressBookConnection("jdbc:oracle:thin:@" + host + ":" + port + "/" + sid, login, password);
                initAddressBook(ab, connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Invalid login. Could not connect to database.");
        }

        ab.listEntries();

        SwingUtilities.invokeLater(new AddressBookRunnable(new Menu(ab, connection)));
    }

    /**
     * Initializes the {@link AddressBook} ab from a database.
     *
     * @param ab   The {@link AddressBook} that will have two
     *             {@link AddressEntry address entries} added to it.
     * @param conn A database connection.
     */
    private static void initAddressBook(AddressBook ab, AddressBookConnection conn) throws SQLException {
        for (AddressEntry entry : conn.getEntries()) {
            ab.addEntry(entry);
        }
    }
}
