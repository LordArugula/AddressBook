package address.data;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * The {@link AddressBook} contains a collection of {@link AddressEntry address entries}.
 */
public class AddressBook {

    /**
     * A collection of {@link AddressEntry address entries}, sorted by last name.
     */
    private SortedSet<AddressEntry> entries;

    /**
     * Compares {@link AddressEntry address entries} by last name, then first name.
     */
    public final class AddressEntryComparator implements Comparator<AddressEntry> {

        /**
         * @param entry1 The first {@link AddressEntry}.
         * @param entry2 The second {@link AddressEntry}.
         * @return <l>
         * <li> &#8804; -1 if entry1 is less than entry2.</li>
         * <li>0 if entry1 and entry2 are equal.</li>
         * <li> &#8805; 1 if entry1 is greater than entry2.</li>
         * </l>
         */
        @Override
        public int compare(AddressEntry entry1, AddressEntry entry2) {
            if (entry1 == null && entry2 == null) {
                return 0;
            }
            if (entry1 == null && entry2 != null) {
                return -1;
            }
            if (entry1 != null && entry2 == null) {
                return 1;
            }

            if (entry1.getLastName() == null && entry2.getLastName() == null) {
                return 0;
            }
            if (entry1.getLastName() == null && entry2.getLastName() != null) {
                return -1;
            }

            int comp = entry1.getLastName().compareTo(entry2.getLastName());
            if (comp == 0) {
                if (entry1.getFirstName() == null && entry2.getFirstName() == null) {
                    return 0;
                }
                if (entry1.getFirstName() == null && entry2.getFirstName() != null) {
                    return -1;
                }
                return entry1.getFirstName().compareTo(entry2.getFirstName());
            }
            return comp;
        }
    }

    /**
     * Initializes an empty address book.
     */
    public AddressBook() {
        entries = new TreeSet<>(new AddressEntryComparator());
    }

    /**
     * Returns the number of {@link AddressEntry address entries} in the {@link AddressBook}.
     *
     * @return the number of elements in this {@link AddressBook}.
     */
    public int size() {
        return entries.size();
    }

    /**
     * Loads {@link AddressEntry entries} from a file at the given filePath.
     *
     * @param filePath The file path of the text file.
     */
    public void loadFile(String filePath) {
        try {
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String firstName = scanner.nextLine();
                String lastName = scanner.nextLine();
                String street = scanner.nextLine();
                String city = scanner.nextLine();
                String state = scanner.nextLine();
                String zip = scanner.nextLine();
                String email = scanner.nextLine();
                String phone = scanner.nextLine();

                addEntry(new AddressEntry(firstName, lastName, street, city, state, Integer.parseInt(zip), email, phone));
            }
            scanner.close();
        } catch (IOException e) {
            System.out.println("Could not read from file at: " + filePath);
        } catch (NoSuchElementException e) {
            System.out.println("Reached the end of file.");
        }
    }

    /**
     * Adds an {@link AddressEntry} to the {@link AddressBook}.
     * Entries with a duplicate first and last name are not added.
     *
     * @param entry The {@link AddressEntry} to add to this {@link AddressBook}.
     * @return True if the entry was added to the {@link AddressBook}.
     */
    public boolean addEntry(AddressEntry entry) {
        return entries.add(entry);
    }

    /**
     * Removes the {@link AddressEntry} from the {@link AddressBook}.
     *
     * @param entry The {@link AddressEntry} to remove.
     * @return True if the addressEntry was removed.
     */
    public boolean removeEntry(AddressEntry entry) {
        return entries.remove(entry);
    }

    /**
     * Finds {@link AddressEntry address entries} that have a matching
     * lastName from the {@link AddressBook}.
     *
     * @param lastName The last name of the entry.
     * @return A {@link List<AddressEntry>} of {@link AddressEntry address entries}
     * that start with last name.
     */
    public Collection<AddressEntry> findEntries(String lastName) {
        return entries.stream()
                .filter(e -> e.getLastName().startsWith(lastName))
                .toList();
    }

    /**
     * Prints each {@link AddressEntry} in this {@link AddressBook}
     * in order by last name, then first name.
     */
    public void listEntries() {
        listEntries(entries);
    }

    /**
     * Prints each {@link AddressEntry} in entries
     * in order by last name, then first name.
     *
     * @param entries A collection of {@link AddressEntry address entries}.
     */
    public static void listEntries(Collection<AddressEntry> entries) {
        int i = 1;
        StringBuilder stringBuilder = new StringBuilder(255);
        for (AddressEntry entry : entries) {
            stringBuilder
                    .append(i).append(": ").append(entry.getFirstName()).append(' ').append(entry.getLastName())
                    .append('\n')
                    .append(entry.getStreet())
                    .append('\n')
                    .append(entry.getCity()).append(", ").append(entry.getState()).append(' ').append(entry.getZip())
                    .append('\n')
                    .append(entry.getPhone())
                    .append('\n')
                    .append(entry.getEmail())
                    .append('\n');

            System.out.println(stringBuilder.toString());
            // clearing the string builder
            stringBuilder.delete(0, stringBuilder.length());
            i++;
        }
    }
}
