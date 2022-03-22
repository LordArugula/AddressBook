package address.gui;

import javax.swing.*;

/**
 * Creates the GUI for an {@link address.data.AddressEntry}.
 * Displays the last and first name of the entry.
 */
public class AddressEntryGUI {

    /**
     * The root panel.
     */
    private JPanel root;

    /**
     * The label that displays the name of the entry.
     */
    private JLabel label;

    /**
     * Initializes the GUI with text as the label.
     * @param text the string to make the label.
     */
    public AddressEntryGUI(String text) {
        label.setText(text);
    }

    /**
     * Returns the root panel.
     *
     * @return the root panel.
     */
    public JPanel getRoot() {
        return root;
    }

    /**
     * Sets the text of the label to display the names and an index.
     * @param firstName The first name of an {@link address.data.AddressEntry}.
     * @param lastName The last name of an {@link address.data.AddressEntry}.
     * @param index The index of the {@link address.data.AddressEntry}.
     */
    public void setText(String firstName, String lastName, int index) {
        label.setText(index + "    " + lastName + ", " + firstName);
    }
}
