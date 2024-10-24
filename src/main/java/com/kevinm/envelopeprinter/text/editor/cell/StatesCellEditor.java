package com.kevinm.envelopeprinter.text.editor.cell;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.util.EventObject;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

public class StatesCellEditor extends DefaultCellEditor {

	public StatesCellEditor(JComboBox<?> comboBox, JTable table) {
		super(comboBox);
		comboBox.removeActionListener(delegate);
		delegate = new EditorDelegate() {

			public void setValue(Object value) {
				comboBox.setSelectedItem(value);
			}

			public Object getCellEditorValue() {
				return comboBox.getSelectedItem();
			}

			public boolean shouldSelectCell(EventObject anEvent) {
				if (anEvent instanceof MouseEvent) {
					MouseEvent e = (MouseEvent) anEvent;
					return e.getID() != MouseEvent.MOUSE_DRAGGED;
				}
				return true;
			}

		};
		table.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {

			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (table.getCellEditor(table.getSelectedRow(), table.getSelectedColumn()).equals(StatesCellEditor.this)) {
					table.editCellAt(table.getSelectedRow(), table.getSelectedColumn(), e);
					TableCellEditor editor = table.getCellEditor(table.getEditingRow(), table.getEditingColumn());
					JComboBox<?> combo = (JComboBox<?>) editor.getTableCellEditorComponent(table, e, true, table.getSelectedRow(), table.getSelectedColumn());
					System.out.println();

				}
			}
		});
		comboBox.addActionListener(delegate);

	}

}
