package com.kevinm.envelopeprinter.ui.components;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import com.kevinm.envelopeprinter.text.StatesCellEditor;

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
		final DefaultTableModel tableModel = new DefaultTableModel(columNames, 1) {

		};
		final JTable addressTable = new JTable(tableModel) {

			@Override
			public boolean getScrollableTracksViewportWidth() {
				return getPreferredSize().width < getParent().getWidth();
			}

			@Override
			public TableCellEditor getCellEditor() {
				return super.getCellEditor();
			}
		};
		TableColumn cc = addressTable.getColumnModel().getColumn(5);
		TableColumn stateColumn = addressTable.getColumnModel().getColumn(6);
		stateColumn.setCellEditor(new StatesCellEditor(new JStatesComboBox(), addressTable));
		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(tableModel);
		List<RowSorter.SortKey> sortKeys = new ArrayList<RowSorter.SortKey>();
		sortKeys.add(new RowSorter.SortKey(1, SortOrder.ASCENDING));
		addressTable.setRowSorter(sorter);
		addressTable.setCellSelectionEnabled(true);
		addressTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				System.out.println(addressTable.getSelectedRow());
			}
		});
		addressTable.getTableHeader().setDefaultRenderer(new TableCellRenderer() {
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
				final JLabel label = new JLabel((String) value);
				label.setVerticalAlignment(SwingConstants.CENTER);
				label.setHorizontalAlignment(SwingConstants.CENTER);
				label.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
				return label;
			}
		});
		addressTable.setShowGrid(true);
		TableColumnModel columnModel = addressTable.getColumnModel();
		columnModel.getColumn(0).setMinWidth(120);
		columnModel.getColumn(1).setMinWidth(90);
		columnModel.getColumn(2).setMinWidth(90);
		columnModel.getColumn(3).setMinWidth(90);
		columnModel.getColumn(4).setMinWidth(280);
		columnModel.getColumn(5).setMinWidth(120);
		columnModel.getColumn(6).setMinWidth(200);
		columnModel.getColumn(7).setMinWidth(90);
		return addressTable;
	}
}
