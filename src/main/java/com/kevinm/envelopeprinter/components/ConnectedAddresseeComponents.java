package com.kevinm.envelopeprinter.components;

import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.kevinm.envelopeprinter.ui.components.JStatesComboBox;

public enum ConnectedAddresseeComponents {

	Company(0, "recipient_panel.company"), FirstName(1, "recipient_panel.first_name"), MiddleName(2, "recipient_panel.middle_name"), LastName(3, "recipient_panel.last_name"),
	Address(4, "address_panel.address"), City(5, "address_panel.city"), State(6, "address_panel.state"), ZipCode(7, "address_panel.zip");

	final private int columnIndex;
	final private JComponent formPanelEntry;
	final private JTable addressBook;

	ConnectedAddresseeComponents(int i, String formComponentName) {
		columnIndex = i;
		formPanelEntry = ComponentFinderUtils.get(JComponent.class, "split_pane_vertical.form_panel." + formComponentName + ".entry");
		addressBook = ComponentFinderUtils.get(JTable.class, "split_pane_vertical.split_pane_horizontal.address_book_pane.table");
	}

	public static ConnectedAddresseeComponents getEntryFromBook(int columnIndex) {
		switch (columnIndex) {
		case 0:
			return Company;
		case 1:
			return FirstName;
		case 2:
			return MiddleName;
		case 3:
			return LastName;
		case 4:
			return Address;
		case 5:
			return City;
		case 6:
			return State;
		case 7:
			return ZipCode;

		default:
			return null;
		}
	}

	public static ConnectedAddresseeComponents genEntryFromForm(String componentName) {
		switch (componentName) {
		case "company":
			return Company;
		case "first_name":
			return FirstName;
		case "middle_name":
			return MiddleName;
		case "last_name":
			return LastName;
		case "address":
			return Address;
		case "city":
			return City;
		case "state":
			return State;
		case "zip":
			return ZipCode;

		default:
			return null;
		}

	}

	public void updateAddressBppl(Object value) {
		addressBook.getModel().setValueAt(value, addressBook.getSelectedRow(), columnIndex);
	}

	public void updateFormEntry(Object value) {
		if (formPanelEntry instanceof JTextField field) {
			field.setText((String) value);
		} else if (formPanelEntry instanceof JStatesComboBox comboBox) {
			comboBox.setSelectedItem(value);
		}
	}

	public int getColumnIndex() {
		return columnIndex;
	}

}
