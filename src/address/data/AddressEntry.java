package address.data;

/**
 * {@link AddressEntry} represents a single contact in an {@link AddressBook}
 * and contains their contact information.
 */
public class AddressEntry {

    /**
     * The id of the {@link AddressEntry} contact.
     */
    private int id;

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
     *
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
     * Gets the id of the {@link AddressEntry}.
     *
     * @return the id of the {@link AddressEntry}.
     */
    public int getId() {
        return id;
    }

    /**
     * Changes the id of the {@link AddressEntry}.
     *
     * @param id The entry's new id.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the entry's first name.
     *
     * @return The first name of the entry.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Changes the entry's first name.
     *
     * @param firstName The entry's new first name.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets the entry's last name.
     *
     * @return The last name of the entry.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Changes the entry's last name.
     *
     * @param lastName The entry's new last name.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets the entry's street name.
     *
     * @return The street name of the entry.
     */
    public String getStreet() {
        return street;
    }

    /**
     * Changes the entry's street name.
     *
     * @param street The entry's new street.
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * Gets the entry's city name.
     *
     * @return The city of the entry.
     */
    public String getCity() {
        return city;
    }

    /**
     * Changes the entry's city name.
     *
     * @param city The entry's new city.
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Gets the entry's state.
     *
     * @return The state of the entry.
     */
    public String getState() {
        return state;
    }

    /**
     * Changes the entry's state.
     *
     * @param state The entry's new state.
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * Gets the entry's zipcode.
     *
     * @return The zipcode of the entry.
     */
    public int getZip() {
        return zip;
    }

    /**
     * Changes the entry's zipcode.
     *
     * @param zip The entry's new zipcode.
     */
    public void setZip(int zip) {
        this.zip = zip;
    }

    /**
     * Gets the entry's email address.
     *
     * @return The email address of the entry.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Changes the entry's email address.
     *
     * @param email The entry's new email address.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the entry's phone number.
     *
     * @return The phone number of the entry.
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Changes the entry's phone number.
     *
     * @param phone The entry's new phone number.
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Returns a string representation of an {@link AddressEntry} containing
     * each field of the {@link AddressEntry} on new lines.
     *
     * @return Returns a string representation of an {@link AddressEntry}.
     */
    @Override
    public String toString() {
        return firstName + '\n' + lastName + '\n' + street + '\n' + city + '\n' + state + '\n' + zip + '\n' + email + '\n' + phone;
    }
}
