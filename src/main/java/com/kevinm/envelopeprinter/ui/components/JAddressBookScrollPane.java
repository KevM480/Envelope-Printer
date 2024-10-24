package com.kevinm.envelopeprinter.ui.components;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import com.kevinm.envelopeprinter.text.editor.cell.StatesCellEditor;

public class JAddressBookScrollPane extends JScrollPane {
	public JAddressBookScrollPane() {
		super(createTable());
		this.setMinimumSize(new Dimension(100, 100));
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_ALWAYS);
		this.setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_ALWAYS);

		this.setVisible(true);
	}

	private static JTable createTable() {
		String columNames[] = { "Company", "First Name", "Middle Name", "Last Name", "Address", "City", "State", "Zip Code" };
		final DefaultTableModel tableModel = new DefaultTableModel(columNames, 8);

		/*
		 * Object value = tableModel.getValueAt(e.getLastRow(), e.getColumn());
		 * ConnectedAddresseeComponents.getEntryFromBook(e.getColumn()).updateFormEntry(
		 * value);
		 */

		/*
		 * TableRowSorter<TableModel> sorter = new
		 * TableRowSorter<TableModel>(tableModel); List<RowSorter.SortKey> sortKeys =
		 * new ArrayList<RowSorter.SortKey>(); sortKeys.add(new RowSorter.SortKey(1,
		 * SortOrder.ASCENDING)); addressTable.setRowSorter(sorter);
		 */

		final JTable table = new JTable(tableModel);

		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (e.getValueIsAdjusting() && table.getSelectedRowCount() <= 1)
					System.out.println(table.getSelectedRow());
			}
		});
		TableColumn stateColumn = table.getColumnModel().getColumn(6);
		stateColumn.setCellEditor(new StatesCellEditor(new JStatesComboBox(), table));

		table.getTableHeader().setDefaultRenderer(new TableCellRenderer() {
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
				final JLabel label = new JLabel((String) value);
				label.setVerticalAlignment(SwingConstants.CENTER);
				label.setHorizontalAlignment(SwingConstants.CENTER);
				label.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
				return label;
			}
		});
		table.putClientProperty("terminateEditOnFocusLost", true);
		table.setCellSelectionEnabled(true);
		table.setShowGrid(true);

		TableColumnModel columnModel = table.getColumnModel();
		columnModel.getColumn(0).setMinWidth(120);
		columnModel.getColumn(1).setMinWidth(90);
		columnModel.getColumn(2).setMinWidth(90);
		columnModel.getColumn(3).setMinWidth(90);
		columnModel.getColumn(4).setMinWidth(280);
		columnModel.getColumn(5).setMinWidth(120);
		columnModel.getColumn(6).setMinWidth(200);
		columnModel.getColumn(7).setMinWidth(90);
		return table;
	}
}
