package com.kevinm.envelopeprinter.text;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.util.EventObject;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JTable;

public class StatesCellEditor extends DefaultCellEditor {

	public StatesCellEditor(JComboBox<?> comboBox, JTable table) {
		super(comboBox);
		comboBox.removeActionListener(delegate);
		delegate = new StateEditorDelegate() {

			public void setValue(Object value) {
				comboBox.getEditor().getItem();
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

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_SPACE) {
					table.editCellAt(table.getSelectedRow(), table.getSelectedColumn(), e);
					if (table.getCellEditor(table.getSelectedRow(), table.getSelectedColumn()).equals(StatesCellEditor.this)) {
						comboBox.showPopup();
						comboBox.requestFocus();
					}
				}
			}
		};
		table.addKeyListener((KeyListener) delegate);

	}

	protected class StateEditorDelegate extends EditorDelegate implements KeyListener {

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub

		}

	}

}
