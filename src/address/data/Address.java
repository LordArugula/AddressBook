package address.data;

/**
 * Represents a street address.
 */
public class Address {

    /**
     * The street.
     */
    private String street;

    /**
     * The city.
     */
    private String city;

    /**
     * The state.
     */
    private String state;

    /**
     * The zip code.
     */
    private int zip;

    /**
     * Creates an {@link Address} containing a street, city, state, and zip code.
     * @param street The number and name of the street.
     * @param city The name of the city.
     * @param state The name of the state.
     * @param zip The zip code.
     */
    public Address(String street, String city, String state, int zip) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
    }

    /**
     * Gets the street.
     *
     * @return The street.
     */
    public String getStreet() {
        return street;
    }

    /**
     * Sets the street to the given street.
     *
     * @param street The given street.
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * Gets the city.
     *
     * @return The city.
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets the city to the given city.
     *
     * @param city The given city.
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Gets the state.
     *
     * @return The state.
     */
    public String getState() {
        return state;
    }

    /**
     * Sets the state to the given state.
     *
     * @param state The given state.
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * Gets the zip.
     *
     * @return The zip.
     */
    public int getZip() {
        return zip;
    }

    /**
     * Sets the zip to the given zip.
     *
     * @param zip The given zip.
     */
    public void setZip(int zip) {
        this.zip = zip;
    }

    /**
     * Returns a string representation of the address.
     *
     * @return a string representation of the address.
     */
    @Override
    public String toString() {
        return street + '\n' + city + ", " + state + '\n' + zip;
    }
}
