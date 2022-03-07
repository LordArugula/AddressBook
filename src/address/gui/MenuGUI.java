package address.gui;

import address.AddressBookApplication;
import address.data.AddressBook;
import address.data.AddressEntry;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
     * The text field for the first name.
     */
    private JTextField firstNameInput;

    /**
     * The text field for the last name.
     */
    private JTextField lastNameInput;

    /**
     * The text field for the street.
     */
    private JTextField streetInput;

    /**
     * The text field for the city.
     */
    private JTextField cityInput;

    /**
     * The text field for the state.
     */
    private JTextField stateInput;

    /**
     * The text field for the zip code.
     */
    private JTextField zipInput;

    /**
     * The text field for the phone number.
     */
    private JTextField phoneInput;

    /**
     * The text field for the email address.
     */
    private JTextField emailInput;

    /**
     * The button to submit changes to the {@link AddressEntry}.
     */
    private JButton submitButton;


    /**
     * The button to cancels changes to the {@link AddressEntry}.
     */
    private JButton cancelButton;

    /**
     * The container for the {@link AddressEntry} input fields.
     */
    private JPanel addressEntryForm;

    /**
     * The {@link AddressBook}.
     */
    private AddressBook ab;

    /**
     * Initializes a {@link MenuGUI} with an {@link AddressBook}.
     *
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

        setMinimumSize(new Dimension(400, 400));
        setSize(640, 480);
        setLocationRelativeTo(null);
        setDefaultLookAndFeelDecorated(false);

        add(root);
        addressEntryForm.setVisible(false);

        newButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                addressEntryForm.setVisible(true);
                submitButton.setText("Add Entry");
                cancelButton.setText("Cancel");
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                addressEntryForm.setVisible(false);
            }
        });
    }

    /**
     * Gets the {@link AddressBook}.
     *
     * @return the {@link AddressBook}.
     */
    public AddressBook getAddressBook() {
        return ab;
    }

    /**
     * Sets the {@link AddressBook}.
     *
     * @param ab The {@link AddressBook}.
     */
    public void setAddressBook(AddressBook ab) {
        this.ab = ab;
    }
}
