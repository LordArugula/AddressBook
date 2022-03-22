package address;

import address.data.AddressEntry;
import oracle.jdbc.driver.OracleDriver;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Manages the connection to the database.
 */
public class AddressBookConnection {
    /**
     * The connection to the database.
     */
    private final Connection connection;

    /**
     * Creates a connection to the SQL database
     * using the login information.
     *
     * @param url      The database url.
     * @param login    The database login.
     * @param password The database password.
     * @throws SQLException Thrown if there is a database error.
     */
    public AddressBookConnection(String url, String login, String password) throws SQLException {
        // Load the Oracle JDBC driver
        DriverManager.registerDriver(new OracleDriver());
        connection = DriverManager.getConnection(url, login, password);
    }

    /**
     * Adds the {@link AddressEntry} to the database.
     *
     * @param entry the {@link AddressEntry} to add.
     * @throws SQLException Thrown if there is a database error.
     */
    public void addEntry(AddressEntry entry) throws SQLException {
        PreparedStatement insertStatement = connection.prepareStatement("INSERT INTO ADDRESSENTRYTABLE (FIRSTNAME, LASTNAME, STREET, CITY, STATE, ZIP, PHONE, EMAIL) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
        setValues(entry, insertStatement);
        int row = insertStatement.executeUpdate();
        insertStatement.close();

        PreparedStatement selectNthStatement = connection.prepareStatement("SELECT DISTINCT NTH_VALUE(ID, ?) over () FROM ADDRESSENTRYTABLE");
        selectNthStatement.setInt(1, row);
        ResultSet resultSet = selectNthStatement.executeQuery();
        if (resultSet.next()) {
            int id = resultSet.getInt(1);
            entry.setId(id);
        }
        resultSet.close();
        selectNthStatement.close();
    }

    /**
     * Sets the values of the query statement.
     *
     * @param entry     the {@link AddressEntry} to set the values with.
     * @param statement the ADD or INSERT sql statement.
     * @throws SQLException Thrown if there is a database error.
     */
    private void setValues(AddressEntry entry, PreparedStatement statement) throws SQLException {
        statement.setString(1, entry.getFirstName());
        statement.setString(2, entry.getLastName());
        statement.setString(3, entry.getStreet());
        statement.setString(4, entry.getCity());
        statement.setString(5, entry.getState());
        statement.setInt(6, entry.getZip());
        statement.setString(7, entry.getPhone());
        statement.setString(8, entry.getEmail());
    }

    /**
     * Updates the {@link AddressEntry} in the database.
     *
     * @param entry the {@link AddressEntry}
     * @throws SQLException Thrown if there is a database error.
     */
    public void updateEntry(AddressEntry entry) throws SQLException {
        if (entry.getId() == -1) {
            addEntry(entry);
            return;
        }

        PreparedStatement statement = connection.prepareStatement("UPDATE ADDRESSENTRYTABLE SET FIRSTNAME = ?, LASTNAME = ?, STREET = ?, CITY = ?, STATE = ?, ZIP = ?, PHONE = ?, EMAIL = ? WHERE ID = ?");
        setValues(entry, statement);
        statement.setInt(9, entry.getId());

        statement.executeUpdate();
        statement.close();
    }

    /**
     * Deletes the entry from the database.
     *
     * @param entry The {@link AddressEntry} to delete.
     * @throws SQLException Thrown if there is a database error.
     */
    public void deleteEntry(AddressEntry entry) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("DELETE FROM ADDRESSENTRYTABLE WHERE ID = ?");
        statement.setInt(1, entry.getId());
        statement.executeUpdate();
        statement.close();
    }

    /**
     * Gets all entries from the database.
     *
     * @return a {@link Collection<AddressEntry>} of
     * {@link AddressEntry address entries}.
     * @throws SQLException Thrown if there is a database error.
     */
    public Collection<AddressEntry> getEntries() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM ADDRESSENTRYTABLE");

        Collection<AddressEntry> entries = new ArrayList<>();
        while (resultSet.next()) {
            int id = resultSet.getInt("ID");
            String firstName = resultSet.getString("FIRSTNAME");
            String lastName = resultSet.getString("LASTNAME");
            String street = resultSet.getString("STREET");
            String city = resultSet.getString("CITY");
            String state = resultSet.getString("STATE");
            int zip = resultSet.getInt("ZIP");
            String phone = resultSet.getString("PHONE");
            String email = resultSet.getString("EMAIL");

            AddressEntry entry = new AddressEntry(firstName, lastName, street, city, state, zip, phone, email);
            entry.setId(id);
            entries.add(entry);
        }

        resultSet.close();
        statement.close();
        return entries;
    }

    /**
     * Closes the connection to the database.
     *
     * @throws SQLException Thrown if there is a database error.
     */
    public void close() throws SQLException {
        connection.close();
    }
}
