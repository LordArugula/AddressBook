package address;

import address.gui.MenuGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

/**
 * Implements the Runnable class.
 */
public class AddressBookRunnable implements Runnable {
    /**
     * An instance of the menu class.
     */
    private final Menu menu;

    /**
     * Initializes the runnable.
     * @param menu the Menu.
     */
    public AddressBookRunnable(Menu menu) {
        this.menu = menu;
    }

    /**
     * When an object implementing interface {@code Runnable} is used
     * to create a thread, starting the thread causes the object's
     * {@code run} method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method {@code run} is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        var frame = new JFrame();
        frame.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                menu.closeConnection();
            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });
        MenuGUI menu = new MenuGUI(this.menu);

        frame.setTitle("Address Book Application");
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        frame.setMinimumSize(new Dimension(360, 360));
        frame.setSize(640, 480);
        frame.setLocationRelativeTo(null);

        frame.add(menu.getRoot());
        frame.setVisible(true);
    }
}
