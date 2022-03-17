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
     * The name of the {@link AddressEntry} contact.
     */
    private Name name;

    /**
     * The address of the {@link AddressEntry} contact.
     */
    private Address address;

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
        id = -1;
        name = new Name("", "");
        address = new Address("", "", "", 0);
    }

    /**
     * Creates a filled-out {@link AddressEntry}.
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
        id = -1;
        name = new Name(firstName, lastName);
        address = new Address(street, city, state, zip);
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
        return name.getFirst();
    }

    /**
     * Changes the entry's first name.
     *
     * @param firstName The entry's new first name.
     */
    public void setFirstName(String firstName) {
        name.setFirst(firstName);
    }

    /**
     * Gets the entry's last name.
     *
     * @return The last name of the entry.
     */
    public String getLastName() {
        return name.getLast();
    }

    /**
     * Changes the entry's last name.
     *
     * @param lastName The entry's new last name.
     */
    public void setLastName(String lastName) {
        name.setLast(lastName);
    }

    /**
     * Gets the entry's street name.
     *
     * @return The street name of the entry.
     */
    public String getStreet() {
        return address.getStreet();
    }

    /**
     * Changes the entry's street name.
     *
     * @param street The entry's new street.
     */
    public void setStreet(String street) {
        address.setStreet(street);
    }

    /**
     * Gets the entry's city name.
     *
     * @return The city of the entry.
     */
    public String getCity() {
        return address.getCity();
    }

    /**
     * Changes the entry's city name.
     *
     * @param city The entry's new city.
     */
    public void setCity(String city) {
        address.setCity(city);
    }

    /**
     * Gets the entry's state.
     *
     * @return The state of the entry.
     */
    public String getState() {
        return address.getState();
    }

    /**
     * Changes the entry's state.
     *
     * @param state The entry's new state.
     */
    public void setState(String state) {
        address.setState(state);
    }

    /**
     * Gets the entry's zipcode.
     *
     * @return The zipcode of the entry.
     */
    public int getZip() {
        return address.getZip();
    }

    /**
     * Changes the entry's zipcode.
     *
     * @param zip The entry's new zipcode.
     */
    public void setZip(int zip) {
        address.setZip(zip);
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
        return name.toString() + '\n' + address.toString() + '\n' + email + '\n' + phone;
    }
}
