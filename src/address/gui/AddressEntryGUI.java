package address.gui;

import javax.swing.*;

public class AddressEntryGUI {
    private JPanel root;
    private JLabel label;

    public AddressEntryGUI(String text) {
        label.setText(text);
    }

    public JPanel getRoot() {
        return root;
    }

    public void setText(String s) {
        label.setText(s);
    }
}
