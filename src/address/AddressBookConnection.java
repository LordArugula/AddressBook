package address;

import address.data.AddressEntry;
import oracle.jdbc.driver.OracleDriver;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class AddressBookConnection {
    private final Connection connection;

    public AddressBookConnection(String url, String login, String password) throws SQLException {
        // Load the Oracle JDBC driver
        DriverManager.registerDriver(new OracleDriver());
        connection = DriverManager.getConnection(url, login, password);
    }

    public void addEntry(AddressEntry entry) throws SQLException {
        PreparedStatement insertStatement = connection.prepareStatement("INSERT INTO ADDRESSENTRYTABLE (FIRSTNAME, LASTNAME, STREET, CITY, STATE, ZIP, PHONE, EMAIL) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
        setValues(entry, insertStatement);
        int row = insertStatement.executeUpdate();
        insertStatement.close();

        PreparedStatement selectNthStatement = connection.prepareStatement("SELECT DISTINCT NTH_VALUE(ID, ?) over () FROM ADDRESSENTRYTABLE");
        selectNthStatement.setInt(1, row);
        ResultSet resultSet = selectNthStatement.executeQuery();
        if (resultSet.next()) {
            int id = resultSet.getInt("ID");
            entry.setId(id);
        }
        resultSet.close();
        selectNthStatement.close();
    }

    private void setValues(AddressEntry entry, PreparedStatement insertStatement) throws SQLException {
        insertStatement.setString(1, entry.getFirstName());
        insertStatement.setString(2, entry.getLastName());
        insertStatement.setString(3, entry.getStreet());
        insertStatement.setString(4, entry.getCity());
        insertStatement.setString(5, entry.getState());
        insertStatement.setInt(6, entry.getZip());
        insertStatement.setString(7, entry.getPhone());
        insertStatement.setString(8, entry.getEmail());
    }

    public void updateEntry(AddressEntry entry) throws SQLException {
        if (entry.getId() == -1) {
            addEntry(entry);
            return;
        }

        PreparedStatement statement = connection.prepareStatement("UPDATE ADDRESSENTRYTABLE SET FIRSTNAME = ?, LASTNAME = ?, CITY = ?, STATE = ?, ZIP = ?, PHONE = ?, EMAIL = ? WHERE ID = ?");
        setValues(entry, statement);
        statement.executeUpdate();

    public void deleteEntry(AddressEntry entry) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("DELETE FROM ADDRESSENTRYTABLE WHERE ID = ?");
        statement.setInt(1, entry.getId());
        statement.executeUpdate();
        statement.close();
    }

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

    public void close() throws SQLException {
        connection.close();
    }
}
