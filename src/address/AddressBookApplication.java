package address;

import address.data.AddressBook;
import address.data.AddressEntry;
import address.gui.MenuGUI;
import oracle.jdbc.driver.OracleDriver;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

/**
 * AddressBookApplication is the entry point of the application and
 * calls some of the {@link Menu} class methods.
 */
public class AddressBookApplication {

    /**
     * The entry point of the {@link AddressBookApplication} and calls
     * some of the {@link Menu} class methods.
     *
     * @param args The input arguments.
     */
    public static void main(String[] args) {

        try {
            // Load the Oracle JDBC driver
            DriverManager.registerDriver(new OracleDriver());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        AddressBook ab = new AddressBook();

        if (args.length != 5) {
            System.out.println("Invalid login. Could not connect to database.");
            return;
        }
        String login = args[0];
        String password = args[1];
        String host = args[2];
        String port = args[3];
        String sid = args[4];
        try {
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:" + host + ":" + port + "/" + sid, login, password);

            // Create a Statement
            Statement stmt = conn.createStatement();

            ResultSet resultSet = stmt.executeQuery("SELECT * FROM ADDRESSENTRYTABLE");

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
                ab.addEntry(entry);
                System.out.println(entry);
            }

            resultSet.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                var frame = new JFrame();
                MenuGUI menu = new MenuGUI(ab);

                frame.setTitle("Address Book Application");
                frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

                frame.setMinimumSize(new Dimension(360, 360));
                frame.setSize(640, 480);
                frame.setLocationRelativeTo(null);
                frame.setDefaultLookAndFeelDecorated(false);

                frame.add(menu.getRoot());
                frame.setVisible(true);
            }
        });
    }

    /**
     * Initializes the {@link AddressBook} ab from a text file.
     *
     * @param ab       The {@link AddressBook} that will have two
     *                 {@link AddressEntry address entries} added to it.
     * @param filePath The path to the text file to load {@link AddressEntry address entries} from.
     */
    private static void initAddressBook(AddressBook ab, String filePath) {
        ab.loadFile(filePath);
    }
}
