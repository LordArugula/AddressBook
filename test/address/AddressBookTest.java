package address;

import address.data.AddressBook;
import address.data.AddressEntry;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Collection;
import java.util.Optional;

/**
 * Tests the {@link AddressBook} class.
 */
public class AddressBookTest {

    /**
     * The {@link AddressBook} used for tests.
     */
    private AddressBook ab;

    /**
     * The {@link AddressEntry} used for tests.
     */
    private AddressEntry entry;

    /**
     * Initializes the {@link AddressBook} used for tests.
     */
    @BeforeEach
    void setUp() {
        ab = new AddressBook();
        entry = new AddressEntry("Victor", "Pan", "12345 Street", "City", "California", 69420, "555-420-6969", "vpan2@horizon.csueastbay.edu");
        ab.addEntry(entry);
    }

    /**
     * Tests that the size returns the correct value.
     */
    @Test
    void size() {
        Assertions.assertEquals(1, ab.size());

        AddressEntry entry1 = new AddressEntry();
        entry1.setLastName("Mars");
        ab.addEntry(entry1);
        Assertions.assertEquals(2, ab.size());

        ab.removeEntry(entry1);
        Assertions.assertEquals(1, ab.size());
    }

    /**
     * Tests that loadFile adds the correct entries.
     */
    @Test
    void loadFile() {
        Assertions.assertEquals(1, ab.size());

        ab.loadFile("resources/AddressInputDataFile.txt");
        Assertions.assertEquals(3, ab.size());

        // Should just print error message, but not throw
        Assertions.assertDoesNotThrow(() -> ab.loadFile("resources/nonexistentFile.txt"));
    }

    /**
     * Tests that addEntry adds the entry, but does not add duplicate entries.
     */
    @Test
    void addEntry() {
        Assertions.assertEquals(1, ab.size());

        AddressEntry newEntry = new AddressEntry();
        newEntry.setLastName("Mars");
        ab.addEntry(newEntry);
        Assertions.assertEquals(2, ab.size());

        // Check that newEntry was added
        Collection<AddressEntry> entries = ab.findEntries(newEntry.getLastName());
        Assertions.assertEquals(1, entries.size());
        Optional<AddressEntry> entry1Alias = entries.stream().findFirst();
        Assertions.assertTrue(entry1Alias.isPresent());
        Assertions.assertEquals(newEntry, entry1Alias.get());

        // No duplicates, so size should not change
        ab.addEntry(newEntry);
        Assertions.assertEquals(2, ab.size());

        // Check that nothing changed
        entries = ab.findEntries(newEntry.getLastName());
        Assertions.assertEquals(1, entries.size());
        entry1Alias = entries.stream().findFirst();
        Assertions.assertTrue(entry1Alias.isPresent());
        Assertions.assertEquals(newEntry, entry1Alias.get());
    }

    /**
     * Tests that removeEntry removes the right entry.
     */
    @Test
    void removeEntry() {
        Assertions.assertEquals(1, ab.size());

        Collection<AddressEntry> entries = ab.findEntries(entry.getLastName());
        Optional<AddressEntry> pan = entries.stream().findFirst();
        Assertions.assertTrue(pan.isPresent());
        Assertions.assertTrue(ab.removeEntry(pan.get()));
        Assertions.assertEquals(0, ab.size());
    }

    /**
     * Tests that findEntries returns a {@link Collection<AddressEntry>}
     * of the {@link AddressEntry entries} that start with the last name.
     */
    @Test
    void findEntries() {
        // Contains "Pan"
        Collection<AddressEntry> entries = ab.findEntries(entry.getLastName());
        Assertions.assertEquals(1, entries.size());
        Optional<AddressEntry> entry = entries.stream().findFirst();
        Assertions.assertTrue(entry.isPresent());
        Assertions.assertEquals(this.entry, entry.get());

        AddressEntry newEntry = new AddressEntry();
        newEntry.setLastName(this.entry.getLastName());
        ab.addEntry(newEntry);

        // Contains both entries
        entries = ab.findEntries(this.entry.getLastName());
        Assertions.assertEquals(2, entries.size());
        Assertions.assertTrue(entries.contains(newEntry));
        Assertions.assertTrue(entries.contains(entry.get()));
    }

    /**
     * Tests that listEntries lists all the {@link AddressEntry entries}
     * sorted by last name then first name.
     */
    @Test
    void listEntries() {

        Assertions.assertDoesNotThrow(() -> {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            PrintStream old = System.out;
            System.setOut(new PrintStream(stream));

            ab.listEntries();

            System.out.flush();
            System.setOut(old);

            String list = "1: Victor Pan\n12345 Street\nCity, California 69420\n555-420-6969\nvpan2@horizon.csueastbay.edu\n\r\n";
            Assertions.assertEquals(list, stream.toString());
        });

        AddressEntry newEntry = new AddressEntry("Emily", "Mars", "987 Street", "City", "California", 42069, "555-123-6942", "email@test.com");
        ab.addEntry(newEntry);

        Assertions.assertDoesNotThrow(() -> {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            PrintStream old = System.out;
            System.setOut(new PrintStream(stream));

            ab.listEntries();

            System.out.flush();
            System.setOut(old);

            String list = "1: Emily Mars\n987 Street\nCity, California 42069\n555-123-6942\nemail@test.com\n\r\n2: Victor Pan\n12345 Street\nCity, California 69420\n555-420-6969\nvpan2@horizon.csueastbay.edu\n\r\n";
            Assertions.assertEquals(list, stream.toString());

        });
    }
}