package address.gui;

import address.data.AddressEntry;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import java.util.Collection;
import java.util.Vector;

public class AddressEntryList extends JList<AddressEntry> {

//    private final JList<AddressEntry> list;
    private final Vector<AddressEntry> listData;

    public AddressEntryList(ListSelectionListener listener) {
//        list = new JList<>();
        setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        setCellRenderer(new AddressEntryCellRenderer());
        addListSelectionListener(listener);

        listData = new Vector<>();
    }

    public void setListData(Collection<AddressEntry> listData) {
        this.listData.clear();
        this.listData.addAll(listData);

        super.setListData(this.listData);
    }
}
