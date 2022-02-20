package address.data;

/**
 * {@link AddressEntry} represents a single contact in an {@link AddressBook}
 * and contains their contact information.
 */
public class AddressEntry {

    /**
     * The first name of the {@link AddressEntry} contact.
     */
    private String firstName;

    /**
     * The last name of the {@link AddressEntry} contact.
     */
    private String lastName;

    /**
     * The street name of the {@link AddressEntry} contact.
     */
    private String street;

    /**
     * The city of the {@link AddressEntry} contact.
     */
    private String city;

    /**
     * The state of the {@link AddressEntry} contact.
     */
    private String state;

    /**
     * The zip code of the {@link AddressEntry} contact.
     */
    private int zip;

    /**
     * The email address of the {@link AddressEntry} contact.
     */
    private String email;

    /**
     * The phone number of the {@link AddressEntry} contact.
     */
    private String phone;

    /**
     * Creates an empty {@link AddressEntry}.
     */
    public AddressEntry() {

    }

    /**
     * Creates a complete {@link AddressEntry}.
     * @param firstName The first name.
     * @param lastName  The last name.
     * @param street    The street name.
     * @param city      The city name.
     * @param state     The street name.
     * @param zip       The zipcode.
     * @param email     The email address.
     * @param phone     The phone number.
     */
    public AddressEntry(String firstName, String lastName, String street, String city, String state, int zip, String phone, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.phone = phone;
        this.email = email;
    }

    /**
     * Gets the contact's first name.
     *
     * @return The first name of the contact.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Changes the contact's first name.
     *
     * @param firstName The contact's new first name.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets the contact's last name.
     *
     * @return The last name of the contact.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Changes the contact's last name.
     *
     * @param lastName The contact's new last name.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets the contact's street name.
     *
     * @return The street name of the contact.
     */
    public String getStreet() {
        return street;
    }

    /**
     * Changes the contact's street name.
     *
     * @param street The contact's new street.
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * Gets the contact's city name.
     *
     * @return The city of the contact.
     */
    public String getCity() {
        return city;
    }

    /**
     * Changes the contact's city name.
     *
     * @param city The contact's new city.
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Gets the contact's state.
     *
     * @return The state of the contact.
     */
    public String getState() {
        return state;
    }

    /**
     * Changes the contact's state.
     *
     * @param state The contact's new state.
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * Gets the contact's zipcode.
     *
     * @return The zipcode of the contact.
     */
    public int getZip() {
        return zip;
    }

    /**
     * Changes the contact's zipcode.
     *
     * @param zip The contact's new zipcode.
     */
    public void setZip(int zip) {
        this.zip = zip;
    }

    /**
     * Gets the contact's email address.
     *
     * @return The email address of the contact.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Changes the contact's email address.
     *
     * @param email The contact's new email address.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the contact's phone number.
     *
     * @return The phone number of the contact.
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Changes the contact's phone number.
     *
     * @param phone The contact's new phone number.
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }
}
