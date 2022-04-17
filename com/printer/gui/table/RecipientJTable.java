package com.printer.gui.table;

import java.awt.Font;
import java.util.HashMap;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.printer.gui.listener.mouse.RecipientJTableMouseListener;
import com.printer.util.RecipientDataUtil;

public class RecipientJTable extends JTable {
	
	private static final long serialVersionUID = 2186736052599402206L;
	
	public RecipientJTable() {
		this.addMouseListener(new RecipientJTableMouseListener());
		this.getTableHeader().setFont(new Font("Tahoma", Font.PLAIN, 15));
		this.setFont(new Font("Tahoma", Font.PLAIN, 15));
		this.setRowHeight(25);
		this.getTableHeader().setReorderingAllowed(false);
		this.setFocusable(true);
		this.setCellSelectionEnabled(true);
		this.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Row", "Recipient's Name", "Recipient's Address" }) {
			private static final long serialVersionUID = 3215596842468196207L;
			boolean[] columnEditables = new boolean[] { false, false, false };
			
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		this.getColumnModel().getColumn(0).setPreferredWidth(37);
		this.getColumnModel().getColumn(0).setMaxWidth(37);
		this.getColumnModel().getColumn(1).setPreferredWidth(100);
		this.getColumnModel().getColumn(2).setPreferredWidth(500);
		
		this.getColumnModel().getColumn(0).setResizable(false);
		this.getColumnModel().getColumn(1).setResizable(true);
		this.getColumnModel().getColumn(2).setResizable(true);
	}
	
	/** Updates table when ever the text fields get updated. */
	public void updateTableOnInput() {
		HashMap<String, String> stringArr = RecipientDataUtil.dataList.get(this.getSelectedRow());
		this.setValueAt(stringArr.get("FullName") + " " + stringArr.get("CompanyName"), this.getSelectedRow(), 1);
		this.setValueAt(stringArr.get("Address1") + " " + stringArr.get("Address2") + " " + stringArr.get("City") + " " + stringArr.get("State") + " " + stringArr.get("Zip") + " " + stringArr.get("Country"), this.getSelectedRow(), 2);
		
	}
	
	/** Updates table whenever you open a file with data. */
	public void updateTableOnOpen() {
		
		List<HashMap<String, String>> dataList = RecipientDataUtil.dataList;
		int count = 0;
		for (HashMap<String, String> stringArr : dataList) {
			String fullName = stringArr.get("FullName") == null ? "" : stringArr.get("FullName");
			String companyName = stringArr.get("CompanyName") == null ? "" : stringArr.get("CompanyName");
			String address1 = stringArr.get("Address1") == null ? "" : stringArr.get("Address1");
			String address2 = stringArr.get("Address2") == null ? "" : stringArr.get("Address2");
			String city = stringArr.get("City") == null ? "" : stringArr.get("City");
			String state = stringArr.get("State") == null ? "" : stringArr.get("State");
			String zip = stringArr.get("Zip") == null ? "" : stringArr.get("Zip");
			String country = stringArr.get("Country") == null ? "" : stringArr.get("Country");
			
			this.setValueAt(fullName + " " + companyName, count, 1);
			this.setValueAt(address1 + " " + address2 + " " + city + " " + state + " " + zip + " " + country, count, 2);
			count++;
		}
		
	}
}
