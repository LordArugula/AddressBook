package address.gui;

import address.AddressBookApplication;
import address.data.AddressBook;
import address.data.AddressEntry;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.awt.event.ActionEvent;

/**
 * Defines the GUI for the {@link AddressBookApplication}.
 */
public class MenuGUI {
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
    private JPanel firstNameField;
    private JPanel lastNameField;

    /**
     * The {@link AddressBook}.
     */
    private AddressBook ab;

    /**
     * The currently selected {@link AddressEntry}.
     */
    private AddressEntry selectedEntry;

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
     * Initializes the GUI and hooks up event handlers.
     */
    private void initUI() {
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

        removeButton.addActionListener(this::onRemoveSelectedEntry);
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

    /**
     * Called when the display button is pressed.
     * @param evt The button event.
     */
    private void onDisplayEntries(ActionEvent evt) {
        displayEntries();
    }

    /**
     * Displays a list of {@link AddressEntry address entries}
     * sorted in alphabetical order by last name, then first name.
     */
    private void displayEntries() {
        addressEntryJList.setListData((ab.getEntries()).toArray(new AddressEntry[0]));
    }

    /**
     * Called when an address entry is selected.
     * @param evt The list selection event.
     */
    private void onSelectEntry(ListSelectionEvent evt) {
        selectedEntry = addressEntryJList.getSelectedValue();
        if (selectedEntry == null) {
            return;
        }
        showAddressEntryForm(selectedEntry, "Edit Entry", "Cancel Changes");
    }

    /**
     * Called when the new button is pressed. Opens the
     * {@link AddressEntry} form to create a new {@link AddressEntry}.
     * @param evt The button event.
     */
    private void onRequestNewEntry(ActionEvent evt) {
        removeSelection();

        selectedEntry = new AddressEntry();
        showAddressEntryForm(selectedEntry, "Create New", "Cancel");
    }

    /**
     * Displays the {@link AddressEntry} form.
     * @param entry The {@link AddressEntry} used to populate the form fields.
     * @param submitText The text for the submit button.
     * @param cancelText The text for the cancel button.
     */
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

    /**
     * Called when the submit button is pressed.
     * Changes or creates an {@link AddressEntry}.
     * @param evt The button event.
     */
    private void onSubmitEntry(ActionEvent evt) {
        String firstName = firstNameInput.getText();
        if (firstName == null || firstName.isBlank()) {
            return;
        }

        String lastName = lastNameInput.getText();
        if (lastName == null || lastName.isBlank()) {
            return;
        }

        String street = streetInput.getText();
        String city = cityInput.getText();
        String state = stateInput.getText();
        String zip = zipInput.getText();
        String phone = phoneInput.getText();
        String email = emailInput.getText();

        int zipcode;

        if (zip == null || zip.isBlank()) {
            zipcode = 0;
        } else {
            try {
                zipcode = Integer.parseInt(zip);
            } catch (NumberFormatException ex) {
                // Give error
                return;
            }
        }

        ab.removeEntry(selectedEntry);

        selectedEntry.setFirstName(firstName);
        selectedEntry.setLastName(lastName);
        selectedEntry.setStreet(street);
        selectedEntry.setCity(city);
        selectedEntry.setState(state);
        selectedEntry.setZip(zipcode);
        selectedEntry.setPhone(phone);
        selectedEntry.setEmail(email);

        ab.addEntry(selectedEntry);
        hideAddressEntryForm();
        displayEntries();
    }

    /**
     * Called when the cancel button is pressed.
     * Stops creating or editing an {@link AddressEntry}
     * and closes the address entry form.
     * @param evt The button event.
     */
    private void onCancelEntry(ActionEvent evt) {
        hideAddressEntryForm();
    }

    /**
     * Closes the {@link AddressEntry} form.
     */
    private void hideAddressEntryForm() {
        addressEntryForm.setVisible(false);
        removeSelection();
    }

    /**
     * Clears the selected {@link AddressEntry}.
     */
    private void removeSelection() {
        addressEntryJList.setSelectedValue(null, false);
    }

    private void onRemoveSelectedEntry(ActionEvent evt) {
        if (selectedEntry == null) {
            return;
        }

        ab.removeEntry(selectedEntry);
        displayEntries();
    }

    public JPanel getRoot() {
        return root;
    }
}
