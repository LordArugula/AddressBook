package address.data;

/**
 * Represents the name of a person.
 *
 * Contains a first and last name, but no middle names.
 */
public class Name {
    /**
     * The first name.
     */
    private String first;

    /**
     * The last name.
     */
    private String last;

    /**
     * Creates a name from the first and last names.
     * @param first The first name.
     * @param last The last name.
     */
    public Name(String first, String last) {
        this.first = first;
        this.last = last;
    }

    /**
     * Gets the first name.
     *
     * @return The first name.
     */
    public String getFirst() {
        return first;
    }

    /**
     * Sets the first name to the given name.
     *
     * @param first The given first name.
     */
    public void setFirst(String first) {
        this.first = first;
    }

    /**
     * Gets the last name.
     *
     * @return The last name.
     */
    public String getLast() {
        return last;
    }

    /**
     * Sets the last name to the given name.
     *
     * @param last The given last name.
     */
    public void setLast(String last) {
        this.last = last;
    }

    /**
     * Returns a string representation of the name.
     *
     * @return a string representation of the name.
     */
    @Override
    public String toString() {
        return last + ", " + first;
    }
}
