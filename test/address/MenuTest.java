package address;

import address.data.AddressBook;
import address.data.AddressEntry;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * Tests the {@link Menu} class.
 */
public class MenuTest {

    /**
     * The {@link AddressBook} used for tests.
     */
    private AddressBook ab;

    /**
     * Sets up the address book and entry used for tests.
     */
    @BeforeEach
    void setUp() {
        ab = new AddressBook();
        AddressEntry entry = new AddressEntry("Victor", "Pan", "12345 Street", "City", "CA", 12345, "555-555-5555", "vpan2@horizon.csueastbay.edu");
        ab.addEntry(entry);
    }

    /**
     * Tests that addEntry adds the entries.
     */
    @Test
    void testAddEntry() {
        Menu menu = new Menu(ab);
        AddressEntry entry = new AddressEntry("Vincent", "Pan", "12345 Street", "City", "State", 12345, "555-420-6969", "test@test.com");

        Assertions.assertEquals(1, ab.size());
        menu.addEntry(entry);
        Assertions.assertEquals(2, ab.size());

        // duplicate entry does nothing
        Assertions.assertThrows(NoSuchElementException.class, () -> menu.addEntry(entry));
        Assertions.assertEquals(2, ab.size());
    }

    /**
     * Tests that removeEntry lists the correct number of entries and removes the correct entries.
     */
    @Test
    void testRemoveEntry() {
        Menu menu = new Menu(ab);

        AddressEntry entryB = new AddressEntry();
        entryB.setLastName("Bob");
        menu.addEntry(entryB);

        Assertions.assertEquals(2, ab.size());
        menu.removeEntry(entryB);
        Assertions.assertEquals(1, ab.size());

        // Did not remove wrong entry
        Optional<AddressEntry> entryC = menu.findEntries("Bob").stream().findFirst();
        Assertions.assertTrue(entryC.isPresent());

        Optional<AddressEntry> entryA = menu.findEntries("Pan").stream().findFirst();
        Assertions.assertTrue(entryA.isPresent());

        // Remove last entry should make it empty
        menu.removeEntry(entryA.get());
        Assertions.assertEquals(0, ab.size());
    }

    @Test
    void testFindEntries() {
        Menu menu = new Menu(ab);

        // Finds correct entry
        Optional<AddressEntry> entryA = menu.findEntries("P").stream().findFirst();
        Assertions.assertTrue(entryA.isPresent());

        AddressEntry entryB = new AddressEntry();
        entryB.setLastName("Bob");
        menu.addEntry(entryB);

        // Can find new entry
        Optional<AddressEntry> entryC = menu.findEntries("Bob").stream().findFirst();
        Assertions.assertTrue(entryC.isPresent());

        // Adding new entry does not change old result
        Optional<AddressEntry> entryD = menu.findEntries("P").stream().findFirst();
        Assertions.assertTrue(entryD.isPresent());

        AddressEntry entryE = new AddressEntry();
        entryE.setLastName("Bob");
        entryE.setFirstName("Ross");
        menu.addEntry(entryE);

        // Can find new entry
        Collection<AddressEntry> bobs = menu.findEntries("Bob");
        Assertions.assertTrue(bobs.size() > 1);
        Optional<AddressEntry> entryF = menu.findEntries("Bob").stream().findFirst();
        Assertions.assertTrue(entryF.isPresent());


    }
}
