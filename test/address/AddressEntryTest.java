package address;

import address.data.AddressEntry;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests the {@link AddressEntry} class.
 */
class AddressEntryTest {

    /**
     * An empty {@link AddressEntry} used for tests.
     */
    private AddressEntry emptyEntry;

    /**
     * A filled {@link AddressEntry} used for tests.
     */
    private AddressEntry filledEntry;

    /**
     * The first name of the filledEntry.
     */
    private final String FIRST_NAME = "Victor";

    /**
     * The last name of the filledEntry.
     */
    private final String LAST_NAME = "Pan";

    /**
     * The street of the filledEntry.
     */
    private final String STREET = "12345 Street Name";

    /**
     * The city of the filledEntry.
     */
    private final String CITY = "City Name";

    /**
     * The state of the filledEntry.
     */
    private final String STATE = "California";

    /**
     * The zip of the filledEntry.
     */
    private final int ZIP = 42069;

    /**
     * The phone number of the filledEntry.
     */
    private final String PHONE = "555-555-5555";

    /**
     * The email address of the filledEntry.
     */
    private final String EMAIL = "vpan2@horizon.csueastbay.edu";

    /**
     * Initializes, before each test, two {@link  AddressEntry address entries}, one empty
     * and the other filled.
     */
    @BeforeEach
    void setUp() {
        emptyEntry = new AddressEntry();
        filledEntry = new AddressEntry(FIRST_NAME, LAST_NAME, STREET, CITY, STATE, ZIP, PHONE, EMAIL);
    }

    /**
     * Tests that getFirstName returns the correct value.
     */
    @Test
    void getFirstName() {
        Assertions.assertNull(emptyEntry.getFirstName());
        Assertions.assertEquals(FIRST_NAME, filledEntry.getFirstName());

        emptyEntry.setFirstName("Mars");
        Assertions.assertEquals("Mars", emptyEntry.getFirstName());
    }

    /**
     * Tests that setFirstName changes the firstName.
     */
    @Test
    void setFirstName() {
        Assertions.assertNull(emptyEntry.getFirstName());
        emptyEntry.setFirstName(FIRST_NAME);
        Assertions.assertNotNull(emptyEntry.getFirstName());
        Assertions.assertEquals(FIRST_NAME, emptyEntry.getFirstName());

        Assertions.assertEquals(FIRST_NAME, filledEntry.getFirstName());
        filledEntry.setFirstName(null);
        Assertions.assertNull(filledEntry.getFirstName());

        final String NEW_NAME = "Emily";
        emptyEntry.setFirstName(NEW_NAME);
        Assertions.assertEquals(emptyEntry.getFirstName(), NEW_NAME);
    }

    /**
     * Tests that getLastName returns the correct value.
     */
    @Test
    void getLastName() {
        Assertions.assertNull(emptyEntry.getLastName());
        Assertions.assertEquals(LAST_NAME, filledEntry.getLastName());

        emptyEntry.setLastName("Last");
        Assertions.assertEquals("Last", emptyEntry.getLastName());
    }

    /**
     * Tests that setLastName changes the lastName.
     */
    @Test
    void setLastName() {
        Assertions.assertNull(emptyEntry.getLastName());
        emptyEntry.setLastName(LAST_NAME);
        Assertions.assertNotNull(emptyEntry.getLastName());
        Assertions.assertEquals(emptyEntry.getLastName(), LAST_NAME);

        Assertions.assertEquals(filledEntry.getLastName(), LAST_NAME);
        filledEntry.setLastName(null);
        Assertions.assertNull(filledEntry.getLastName());

        final String NEW_NAME = "Nerd";
        emptyEntry.setLastName(NEW_NAME);
        Assertions.assertEquals(emptyEntry.getLastName(), NEW_NAME);
    }

    /**
     * Tests that getStreet returns the correct value.
     */
    @Test
    void getStreet() {
        Assertions.assertNull(emptyEntry.getStreet());
        Assertions.assertEquals(filledEntry.getStreet(), STREET);

        String street = "23 Street Name";
        emptyEntry.setStreet(street);
        Assertions.assertEquals(street, emptyEntry.getStreet());
    }

    /**
     * Tests that setStreet changes the street.
     */
    @Test
    void setStreet() {
        Assertions.assertNull(emptyEntry.getStreet());
        emptyEntry.setStreet(STREET);
        Assertions.assertNotNull(emptyEntry.getStreet());
        Assertions.assertEquals(emptyEntry.getStreet(), STREET);

        Assertions.assertEquals(filledEntry.getStreet(), STREET);
        filledEntry.setStreet(null);
        Assertions.assertNull(filledEntry.getStreet());

        final String NEW_STREET = "12345 Testing St";
        emptyEntry.setStreet(NEW_STREET);
        Assertions.assertEquals(emptyEntry.getStreet(), NEW_STREET);
    }

    /**
     * Tests that getCity returns the correct value.
     */
    @Test
    void getCity() {
        Assertions.assertNull(emptyEntry.getCity());
        Assertions.assertEquals(filledEntry.getCity(), CITY);

        String city = "Fremont";
        emptyEntry.setCity(city);
        Assertions.assertEquals(city, emptyEntry.getCity());
    }

    /**
     * Tests that setCity changes the city.
     */
    @Test
    void setCity() {
        Assertions.assertNull(emptyEntry.getCity());
        emptyEntry.setCity(CITY);
        Assertions.assertNotNull(emptyEntry.getCity());
        Assertions.assertEquals(emptyEntry.getCity(), CITY);

        Assertions.assertEquals(filledEntry.getCity(), CITY);
        filledEntry.setCity(null);
        Assertions.assertNull(filledEntry.getCity());

        final String NEW_CITY = "Fremont";
        emptyEntry.setCity(NEW_CITY);
        Assertions.assertEquals(emptyEntry.getCity(), NEW_CITY);
    }

    /**
     * Tests that getCity returns the correct value.
     */
    @Test
    void getState() {
        Assertions.assertNull(emptyEntry.getState());
        Assertions.assertEquals(filledEntry.getState(), STATE);

        String state = "CA";
        emptyEntry.setState(state);
        Assertions.assertEquals(state, emptyEntry.getState());
    }

    /**
     * Tests that setState changes the state.
     */
    @Test
    void setState() {
        Assertions.assertNull(emptyEntry.getState());
        emptyEntry.setState(STATE);
        Assertions.assertNotNull(emptyEntry.getState());
        Assertions.assertEquals(emptyEntry.getState(), STATE);

        Assertions.assertEquals(filledEntry.getState(), STATE);
        filledEntry.setState(null);
        Assertions.assertNull(filledEntry.getState());

        final String NEW_STATE = "California";
        emptyEntry.setState(NEW_STATE);
        Assertions.assertEquals(emptyEntry.getState(), NEW_STATE);
    }

    /**
     * Tests that getZip returns the correct value.
     */
    @Test
    void getZip() {
        Assertions.assertEquals(0, emptyEntry.getZip());
        Assertions.assertEquals(filledEntry.getZip(), ZIP);

        int zip = 69420;
        emptyEntry.setZip(zip);
        Assertions.assertEquals(zip, emptyEntry.getZip());
    }

    /**
     * Tests that setZip changes the zip.
     */
    @Test
    void setZip() {
        Assertions.assertEquals(emptyEntry.getZip(), 0);
        emptyEntry.setZip(ZIP);
        Assertions.assertEquals(emptyEntry.getZip(), ZIP);

        Assertions.assertEquals(filledEntry.getZip(), ZIP);
        final int NEW_ZIP = 12345;
        filledEntry.setZip(NEW_ZIP);
        Assertions.assertEquals(filledEntry.getZip(), NEW_ZIP);
    }

    /**
     * Tests that getPhone returns the correct value.
     */
    @Test
    void getPhone() {
        Assertions.assertNull(emptyEntry.getPhone());
        Assertions.assertEquals(filledEntry.getPhone(), PHONE);

        String phone = "555-420-6969";
        emptyEntry.setPhone(phone);
        Assertions.assertEquals(phone, emptyEntry.getPhone());
    }

    /**
     * Tests that setPhone changes the phone.
     */
    @Test
    void setPhone() {
        Assertions.assertNull(emptyEntry.getPhone());
        emptyEntry.setPhone(PHONE);
        Assertions.assertNotNull(emptyEntry.getPhone());
        Assertions.assertEquals(emptyEntry.getPhone(), PHONE);

        Assertions.assertEquals(filledEntry.getPhone(), PHONE);
        filledEntry.setPhone(null);
        Assertions.assertNull(filledEntry.getPhone());

        final String NEW_PHONE = "555-420-6969";
        emptyEntry.setPhone(NEW_PHONE);
        Assertions.assertEquals(emptyEntry.getPhone(), NEW_PHONE);
    }

    /**
     * Tests that getEmail returns the correct value.
     */
    @Test
    void getEmail() {
        Assertions.assertNull(emptyEntry.getEmail());
        Assertions.assertEquals(filledEntry.getEmail(), EMAIL);

        String email = "test@test.com";
        emptyEntry.setEmail(email);
        Assertions.assertEquals(email, emptyEntry.getEmail());
    }

    /**
     * Tests that setEmail changes the email.
     */
    @Test
    void setEmail() {
        Assertions.assertNull(emptyEntry.getEmail());
        emptyEntry.setEmail(EMAIL);
        Assertions.assertNotNull(emptyEntry.getEmail());
        Assertions.assertEquals(emptyEntry.getEmail(), EMAIL);

        Assertions.assertEquals(filledEntry.getEmail(), EMAIL);
        filledEntry.setEmail(null);
        Assertions.assertNull(filledEntry.getEmail());

        final String NEW_EMAIL = "test@test.com";
        emptyEntry.setEmail(NEW_EMAIL);
        Assertions.assertEquals(emptyEntry.getEmail(), NEW_EMAIL);
    }
}
