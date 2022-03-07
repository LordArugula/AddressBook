package address.gui;

import javax.swing.*;

public class MenuGUI extends JFrame {
    private JPanel root;
    private JButton displayButton;
    private JButton newButton;
    private JButton removeButton;
    private JScrollPane scrollPane;
    private JPanel entryFormContainer;
    private AddressBook ab;

    public MenuGUI(AddressBook ab) {
        this.ab = ab;

        initUI();
    }

    private void initUI() {
        setTitle("Address Book Application");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setSize(640, 480);
        setLocationRelativeTo(null);
        setDefaultLookAndFeelDecorated(false);

        add(root);
    }

    public AddressBook getAddressBook() {
        return ab;
    }

    public void setAddressBook(AddressBook ab) {
        this.ab = ab;
    }
}
