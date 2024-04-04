package com.kevinm.envelopeprinter.ui.controls;

import java.awt.Color;
import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

public class JAddressBookPane extends JScrollPane {
	public JAddressBookPane() {
		super(createTable());
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_ALWAYS);
		this.setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_ALWAYS);
	}

	private static JTable createTable() {
		String columNames[] = { "Addressee", "Address", "City", "State", "Zip Code" };
		DefaultTableModel tableModel = new DefaultTableModel(columNames, 100);
		final JTable addressTable = new JTable(tableModel) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}

			@Override
			public boolean getScrollableTracksViewportWidth() {
				return getPreferredSize().width < getParent().getWidth();
			}
		};
		addressTable.getTableHeader().setDefaultRenderer(new TableCellRenderer() {

			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				final JLabel label = new JLabel((String) value);
				label.setVerticalAlignment(SwingConstants.CENTER);
				label.setHorizontalAlignment(SwingConstants.CENTER);
				label.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
				return label;
			}
		});
		addressTable.setShowGrid(true);
		TableColumnModel columnModel = addressTable.getColumnModel();
		columnModel.getColumn(0).setMinWidth(200);
		columnModel.getColumn(1).setMinWidth(500);
		columnModel.getColumn(2).setMinWidth(150);
		columnModel.getColumn(3).setMinWidth(150);
		return addressTable;
	}
}
