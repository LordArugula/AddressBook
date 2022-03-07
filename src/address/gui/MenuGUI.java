package address.gui;

import address.AddressBookApplication;
import address.data.AddressBook;
import address.data.AddressEntry;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.awt.*;
import java.awt.event.ActionEvent;

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
     * The view for the scroll pane.
     */
    private JList<AddressEntry> addressEntryJList;

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

        addressEntryJList = new JList<AddressEntry>();
        addressEntryJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        addressEntryJList.setCellRenderer(new AddressEntryCellRenderer());
        addressEntryJList.addListSelectionListener(this::onSelectEntry);
        scrollPane.setViewportView(addressEntryJList);
        displayButton.addActionListener(this::onDisplayEntries);

        newButton.addActionListener(this::onRequestNewEntry);

        submitButton.addActionListener(this::onSubmitEntry);

        cancelButton.addActionListener(this::onCancelEntry);
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

    private void onDisplayEntries(ActionEvent evt) {
        addressEntryJList.setListData((ab.getEntries()).toArray(new AddressEntry[0]));
    }

    private AddressEntry selectedEntry;

    private void onSelectEntry(ListSelectionEvent e) {
        selectedEntry = addressEntryJList.getSelectedValue();
        showAddressEntryForm(selectedEntry, "Edit Entry", "Cancel Changes");
    }

    private void onRequestNewEntry(ActionEvent evt) {
        showAddressEntryForm(new AddressEntry(), "Create New", "Cancel");
    }

    private void showAddressEntryForm(AddressEntry entry, String submitText, String cancelText) {
        addressEntryForm.setVisible(true);
        submitButton.setText(submitText);
        cancelButton.setText(cancelText);

        firstNameInput.setText(entry.getFirstName());
        lastNameInput.setText(entry.getLastName());
        streetInput.setText(entry.getStreet());
        cityInput.setText(entry.getCity());
        stateInput.setText(entry.getState());
        zipInput.setText(entry.getZip() == 0 ? "" : Integer.toString(entry.getZip()));
        phoneInput.setText(entry.getPhone());
        emailInput.setText(entry.getEmail());
    }

    private void onSubmitEntry(ActionEvent evt) {
        String firstName = firstNameInput.getText();
        if (firstName == null || firstName.isBlank()) {

        }

        String lastName = lastNameInput.getText();
        if (lastName == null) {

        }

        String street = streetInput.getText();
        String city = cityInput.getText();
        String state = stateInput.getText();
        String zip = zipInput.getText();
        String phone = phoneInput.getText();
        String email = emailInput.getText();

        int zipcode;
        try {
            zipcode = Integer.parseInt(zip);
        } catch (NumberFormatException ex) {
            // Give error
            return;
        }

        ab.addEntry(new AddressEntry(firstName, lastName, street, city, state, zipcode, phone, email));
    }

    private void onCancelEntry(ActionEvent evt) {
        addressEntryForm.setVisible(false);
    }
}
