package address.gui;

import address.AddressBookApplication;
import address.data.AddressBook;
import address.data.AddressEntry;

import javax.swing.*;

/**
 * Defines the GUI for the {@link AddressBookApplication}.
 */
public class MenuGUI extends JFrame {
    /**
     * The root panel.
     */
    private JPanel root;

    /**
     * The button to display {@link AddressEntry address entries}.
     */
    private JButton displayButton;

    /**
     * The button to add new {@link AddressEntry address entries}.
     */
    private JButton newButton;

    /**
     * The button to remove {@link AddressEntry address entries}.
     */
    private JButton removeButton;

    /**
     * The scroll pane to view {@link AddressEntry address entries}.
     */
    private JScrollPane scrollPane;

    /**
     * The panel to contain the address entry form.
     * This is shown when adding a new {@link AddressEntry}.
     */
    private JPanel entryFormContainer;

    /**
     * The {@link AddressBook}.
     */
    private AddressBook ab;

    /**
     * Initializes a {@link MenuGUI} with an {@link AddressBook}.
     * @param ab The {@link AddressBook} to use with the {@link MenuGUI}.
     */
    public MenuGUI(AddressBook ab) {
        this.ab = ab;

        initUI();
    }

    /**
     * Initializes the GUI.
     */
    private void initUI() {
        setTitle("Address Book Application");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setSize(640, 480);
        setLocationRelativeTo(null);
        setDefaultLookAndFeelDecorated(false);

        add(root);
    }

    /**
     * Gets the {@link AddressBook}.
     * @return the {@link AddressBook}.
     */
    public AddressBook getAddressBook() {
        return ab;
    }

    /**
     * Sets the {@link AddressBook}.
     * @param ab The {@link AddressBook}.
     */
    public void setAddressBook(AddressBook ab) {
        this.ab = ab;
    }
}
