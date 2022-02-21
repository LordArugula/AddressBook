package address;

import address.data.AddressBook;
import address.data.AddressEntry;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Scanner;

public class MenuTest {

    private AddressBook ab;

    @BeforeEach
    void setUp() {
        ab = new AddressBook();
        AddressEntry entry = new AddressEntry("Victor", "Pan", "12345 Street", "City", "CA", 12345, "555-555-5555", "vpan2@horizon.csueastbay.edu");
        ab.addEntry(entry);
    }

    @Test
    void prompt_FirstName() {

        String FIRST_NAME_1 = "Victor";
        String FIRST_NAME_2 = "Dani";
        ByteArrayInputStream inputStream = new ByteArrayInputStream((FIRST_NAME_1 + '\n' + FIRST_NAME_2).getBytes());

        Menu menu = new Menu(new Scanner(inputStream));

        Assertions.assertEquals(menu.prompt_FirstName(), FIRST_NAME_1);

        Assertions.assertEquals(menu.prompt_FirstName(), FIRST_NAME_2);
    }

    @Test
    void prompt_LastName() {
        String LAST_NAME_1 = "Pan";
        String LAST_NAME_2 = "Bob";
        ByteArrayInputStream inputStream = new ByteArrayInputStream((LAST_NAME_1 + '\n' + LAST_NAME_2).getBytes());

        Menu menu = new Menu(new Scanner(inputStream));

        Assertions.assertEquals(menu.prompt_LastName(), LAST_NAME_1);

        Assertions.assertEquals(menu.prompt_LastName(), LAST_NAME_2);
    }

    @Test
    void prompt_Street() {
        String STREET_1 = "12345 Street Name";
        String STREET_2 = "789 Street Name";
        ByteArrayInputStream inputStream = new ByteArrayInputStream((STREET_1 + '\n' + STREET_2).getBytes());

        Menu menu = new Menu(new Scanner(inputStream));

        Assertions.assertEquals(menu.prompt_Street(), STREET_1);

        Assertions.assertEquals(menu.prompt_Street(), STREET_2);
    }

    @Test
    void prompt_City() {
        String CITY_1 = "City Name";
        String CITY_2 = "City Name";
        ByteArrayInputStream inputStream = new ByteArrayInputStream((CITY_1 + '\n' + CITY_2).getBytes());

        Menu menu = new Menu(new Scanner(inputStream));

        Assertions.assertEquals(menu.prompt_City(), CITY_1);

        Assertions.assertEquals(menu.prompt_City(), CITY_2);
    }

    @Test
    void prompt_State() {
        String STATE_1 = "California";
        String STATE_2 = "California";
        ByteArrayInputStream inputStream = new ByteArrayInputStream((STATE_1 + '\n' + STATE_2).getBytes());

        Menu menu = new Menu(new Scanner(inputStream));

        Assertions.assertEquals(menu.prompt_State(), STATE_1);

        Assertions.assertEquals(menu.prompt_State(), STATE_2);
    }

    @Test
    void prompt_Zip() {
        int ZIP_1 = 42069;
        int ZIP_2 = 12345;
        String BAD_ZIP = "da12s3";
        ByteArrayInputStream inputStream = new ByteArrayInputStream((ZIP_1 + "\n" + ZIP_2 + "\n" + BAD_ZIP).getBytes());

        Menu menu = new Menu(new Scanner(inputStream));

        Assertions.assertEquals(menu.prompt_Zip(), ZIP_1);

        Assertions.assertEquals(menu.prompt_Zip(), ZIP_2);

        Assertions.assertThrows(NumberFormatException.class, menu::prompt_Zip);
    }

    @Test
    void prompt_phone() {
        String PHONE_1 = "555-789-5555";
        String PHONE_2 = "555-123-5555";
        ByteArrayInputStream inputStream = new ByteArrayInputStream((PHONE_1 + '\n' + PHONE_2).getBytes());

        Menu menu = new Menu(new Scanner(inputStream));

        Assertions.assertEquals(menu.prompt_Phone(), PHONE_1);

        Assertions.assertEquals(menu.prompt_Phone(), PHONE_2);
    }

    @Test
    void prompt_Email() {
        String EMAIL_1 = "vpan2@horizon.csueastbay.edu";
        String EMAIL_2 = "test@test.com";
        ByteArrayInputStream inputStream = new ByteArrayInputStream((EMAIL_1 + '\n' + EMAIL_2).getBytes());

        Menu menu = new Menu(new Scanner(inputStream));

        Assertions.assertEquals(menu.prompt_Email(), EMAIL_1);

        Assertions.assertEquals(menu.prompt_Email(), EMAIL_2);
    }

    @Test
    void option_LoadFile() {
        ByteArrayInputStream inputStream = new ByteArrayInputStream("resources/AddressInputDataFile.txt\nfakeFile.txt".getBytes());

        Menu menu = new Menu(new Scanner(inputStream));

        Assertions.assertEquals(1, ab.size());
        menu.option_LoadFile(ab);
        Assertions.assertEquals(3, ab.size());

        // Non existent file
        menu.option_LoadFile(ab);
        // Assertions.assertThrows(IOException.class, () -> menu.option_LoadFile(ab));
    }

    @Test
    void option_AddEntry() {
        ByteArrayInputStream inputStream = new ByteArrayInputStream("""
                Vincent
                Pan
                12345 Street
                City
                State
                12345
                555-420-6969
                test@test.com
                """.getBytes());

        Menu menu = new Menu(new Scanner(inputStream));

        Assertions.assertEquals(1, ab.size());
        menu.option_AddEntry(ab);
        Assertions.assertEquals(2, ab.size());

        // no input
        Assertions.assertThrows(NoSuchElementException.class, () -> menu.option_AddEntry(ab));
        Assertions.assertEquals(2, ab.size());
    }

    @Test
    void option_RemoveEntry() {
        ByteArrayInputStream inputStream = new ByteArrayInputStream("resources/AddressInputDataFile.txt\nGr\n2\nPan\n1\nG\n0".getBytes());

        Menu menu = new Menu(new Scanner(inputStream));

        menu.option_LoadFile(ab);

        // Remove "Lynne Grewe" from address book
        Assertions.assertEquals(3, ab.size());
        menu.option_RemoveEntry(ab);
        Assertions.assertEquals(2, ab.size());

        Collection<AddressEntry> entries = ab.findEntries("G");
        Assertions.assertEquals(1, entries.size());
        Optional<AddressEntry> entry = entries.stream().findFirst();
        Assertions.assertTrue(entry.isPresent());
        Assertions.assertEquals("Butch", entry.get().getFirstName());

        // Remove "Victor Pan" from address book
        menu.option_RemoveEntry(ab);
        Assertions.assertEquals(1, ab.size());

        // Remove invalid index
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        PrintStream old = System.out;
        System.setOut(new PrintStream(stream));

        menu.option_RemoveEntry(ab);
        System.out.flush();
        System.setOut(old);

        String errorMessage = "Invalid index.\r\n";
        Assertions.assertTrue(stream.toString().endsWith(errorMessage));

        // no input
        Assertions.assertThrows(NoSuchElementException.class, () -> menu.option_RemoveEntry(ab));
    }

    @Test
    void option_FindEntry() {
        ByteArrayInputStream inputStream = new ByteArrayInputStream("resources/AddressInputDataFile.txt\nG\nG".getBytes());

        Menu menu = new Menu(new Scanner(inputStream));

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        PrintStream old = System.out;
        System.setOut(new PrintStream(stream));

        menu.option_FindEntry(ab);

        System.out.flush();

        String errorMessage = "No address entries.\r\n";
        Assertions.assertTrue(stream.toString().endsWith(errorMessage));

        menu.option_LoadFile(ab);

        System.out.flush();
        System.setOut(old);

        Assertions.assertNotEquals(errorMessage, stream.toString());

    }

    @Test
    void option_ListEntries() {
    }
}
