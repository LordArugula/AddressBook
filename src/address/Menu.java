package address;

import address.data.AddressBook;
import address.data.AddressEntry;

import java.sql.SQLException;
import java.util.Collection;

/**
 * {@link Menu} class is used to display menu options to the user to modify an {@link AddressBook}.
 */
public class Menu {
    /**
     * The {@link AddressBook} modified by the menu's interface.
     */
    private final AddressBook addressBook;

    /**
     * A connection to a database.
     */
    private final AddressBookConnection connection;

    /**
     * Creates a menu with an {@link AddressBook}.
     *
     * @param addressBook An {@link AddressBook}.
     */
    public Menu(AddressBook addressBook) {
        this(addressBook, null);
    }

    /**
     * Creates a menu with an {@link AddressBook}.
     *
     * @param addressBook An {@link AddressBook}.
     * @param connection  A connection to the database.
     */
    public Menu(AddressBook addressBook, AddressBookConnection connection) {
        this.addressBook = addressBook;
        this.connection = connection;
    }

    /**
     * Adds an {@link AddressEntry} given by the user to the {@link AddressBook}.
     *
     * @param entry The {@link AddressEntry} to add to the {@link AddressBook}.
     */
    public void addEntry(AddressEntry entry) {
        addressBook.addEntry(entry);
        try {
            connection.addEntry(entry);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Removes an {@link AddressEntry entry} from the {@link AddressBook}
     * chosen by the user based on the last name of the entry.
     *
     * @param entry The {@link AddressEntry} to remove from the {@link AddressBook}.
     */
    public void removeEntry(AddressEntry entry) {
        addressBook.removeEntry(entry);
        try {
            connection.deleteEntry(entry);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Finds and prints {@link AddressEntry address entries} matching
     * the lastName from the {@link AddressBook}.
     *
     * @param lastName The last name to filter {@link AddressEntry entries} by.
     * @return A {@link Collection<AddressEntry>} of {@link AddressEntry address entries}
     * with entries that match the given last name.
     */
    public Collection<AddressEntry> findEntries(String lastName) {
        return addressBook.findEntries(lastName);
    }

    /**
     * Returns a collection containing the {@link AddressEntry entries}
     * in the {@link AddressBook}.
     *
     * @return a collection containing the {@link AddressEntry entries}
     * in the {@link AddressBook}.
     */
    public Collection<AddressEntry> getEntries() {
        return addressBook.getEntries();
    }

    /**
     * Updates the {@link AddressEntry}. If the entry was not already
     * present in the {@link AddressBook}, it is added.
     *
     * @param entry     The {@link AddressEntry} to update.
     * @param firstName The updated first name.
     * @param lastName  The updated last name.
     * @param street    The updated street.
     * @param city      The updated city.
     * @param state     The updated state.
     * @param zip       The updated zip.
     * @param phone     The updated phone.
     * @param email     The updated email.
     */
    public void updateEntry(AddressEntry entry, String firstName, String lastName, String street, String city, String state, int zip, String phone, String email) {
        // must remove entry, then add it to maintain sorting
        addressBook.removeEntry(entry);

        entry.setFirstName(firstName);
        entry.setLastName(lastName);
        entry.setStreet(street);
        entry.setCity(city);
        entry.setState(state);
        entry.setZip(zip);
        entry.setPhone(phone);
        entry.setEmail(email);
        // re-add the entry
        addressBook.addEntry(entry);

        // update database
        try {
            connection.updateEntry(entry);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Refreshes the {@link AddressBook} from the
     * entries in the database.
     */
    public void refreshAddressBook() {
        addressBook.clear();
        try {
            for (AddressEntry entry : connection.getEntries()) {
                addressBook.addEntry(entry);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Closes the database connection.
     */
    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
