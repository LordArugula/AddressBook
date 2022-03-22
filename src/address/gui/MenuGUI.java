package address.gui;

import address.AddressBookApplication;
import address.Menu;
import address.data.AddressBook;
import address.data.AddressEntry;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.awt.event.ActionEvent;
import java.util.Collection;

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
    private AddressEntryList addressEntryList;

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
     * The search field text input.
     */
    private JTextField searchField;

    /**
     * The {@link Menu} to interface with to modify
     * its internal {@link AddressBook}.
     */
    private final Menu menu;

    /**
     * The currently selected {@link AddressEntry}.
     */
    private AddressEntry currentEntry;

    /**
     * Initializes a {@link MenuGUI} with an {@link AddressBook}.
     *
     * @param menu The {@link Menu} to interface with.
     */
    public MenuGUI(Menu menu) {
        this.menu = menu;
        initUI();
    }

    /**
     * Initializes the GUI and hooks up event handlers.
     */
    private void initUI() {
        addressEntryForm.setVisible(false);

        addressEntryList = new AddressEntryList(this::onSelectEntry);
        scrollPane.setViewportView(addressEntryList);
        displayButton.addActionListener(this::onDisplayEntries);

        newButton.addActionListener(this::onRequestNewEntry);

        submitButton.addActionListener(this::onSubmitEntry);

        cancelButton.addActionListener(this::onCancelEntry);

        removeButton.addActionListener(this::onRemoveSelectedEntry);

        searchField.addActionListener(this::onSearchEntries);
    }

    /**
     * Called when the search field text input is submitted.
     *
     * @param evt The submit event.
     */
    private void onSearchEntries(ActionEvent evt) {
        String query = searchField.getText();
        Collection<AddressEntry> entries = menu.findEntries(query);
        displayEntries(entries);
    }

    /**
     * Called when the display button is pressed.
     *
     * @param evt The button event.
     */
    private void onDisplayEntries(ActionEvent evt) {
        displayEntries();
    }

    /**
     * Displays a list of all {@link AddressEntry address entries}
     * in the {@link AddressBook} sorted in alphabetical order by
     * last name, then first name.
     */
    private void displayEntries() {
        menu.refreshAddressBook();
        addressEntryList.setListData(menu.getEntries());
        hideAddressEntryForm();
    }

    /**
     * Displays a list of {@link AddressEntry address entries}
     * sorted in alphabetical order by last name, then first name.
     *
     * @param entries A collection of {@link AddressEntry address entries}
     *                to display.
     */
    private void displayEntries(Collection<AddressEntry> entries) {
        addressEntryList.setListData(entries);
        hideAddressEntryForm();
    }

    /**
     * Called when an address entry is selected.
     *
     * @param evt The list selection event.
     */
    private void onSelectEntry(ListSelectionEvent evt) {
        AddressEntry entry = addressEntryList.getSelectedValue();
        if (entry == currentEntry) {
            return;
        }
        currentEntry = entry;
        if (entry == null) {
            return;
        }
        showAddressEntryForm(entry, "Edit Entry", "Cancel Changes");
    }

    /**
     * Called when the new button is pressed. Opens the
     * {@link AddressEntry} form to create a new {@link AddressEntry}.
     *
     * @param evt The button event.
     */
    private void onRequestNewEntry(ActionEvent evt) {
        addressEntryList.clearSelection();

        currentEntry = new AddressEntry();
        showAddressEntryForm(currentEntry, "Create New", "Cancel");
    }

    /**
     * Displays the {@link AddressEntry} form.
     *
     * @param entry      The {@link AddressEntry} used to populate the form fields.
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
     *
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

        menu.updateEntry(currentEntry, firstName, lastName, street, city, state, zipcode, phone, email);
        displayEntries();
    }

    /**
     * Called when the cancel button is pressed.
     * Stops creating or editing an {@link AddressEntry}
     * and closes the address entry form.
     *
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
        addressEntryList.clearSelection();
        currentEntry = null;
    }

    /**
     * Called when the remove button is clicked. Removes
     * the selected {@link AddressEntry}.
     *
     * @param evt the button event.
     */
    private void onRemoveSelectedEntry(ActionEvent evt) {
        AddressEntry selected = addressEntryList.getSelectedValue();
        if (selected == null) {
            return;
        }
        menu.removeEntry(selected);
        displayEntries();
    }

    /**
     * Returns the root panel.
     *
     * @return the root panel.
     */
    public JPanel getRoot() {
        return root;
    }
}
