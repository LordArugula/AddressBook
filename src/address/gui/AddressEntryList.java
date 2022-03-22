package address.gui;

import address.data.AddressEntry;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import java.util.Collection;
import java.util.Vector;

/**
 * The AddressEntryList extends the JList and shows a list of entries.
 */
public class AddressEntryList extends JList<AddressEntry> {
    /**
     * A vector containing the {@link AddressEntry} list data.
     */
    private final Vector<AddressEntry> listData;

    /**
     * Creates a new AddressEntryList with a listener to the selection event.
     * @param listener the selection event listener.
     */
    public AddressEntryList(ListSelectionListener listener) {
        setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        setCellRenderer(new AddressEntryCellRenderer());
        addListSelectionListener(listener);

        listData = new Vector<>();
    }

    /**
     * Updates the list of {@link AddressEntry} to display.
     * @param listData The list of {@link AddressEntry} to display.
     */
    public void setListData(Collection<AddressEntry> listData) {
        this.listData.clear();
        this.listData.addAll(listData);

        super.setListData(this.listData);
    }
}
