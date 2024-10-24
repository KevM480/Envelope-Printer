package com.kevinm.envelopeprinter.text.editor.cell;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.DefaultCellEditor;
import javax.swing.JTextField;

public class ConstantCellEditor extends DefaultCellEditor {

	public ConstantCellEditor(JTextField textField) {
		super(textField);
		textField.removeActionListener(delegate);
		this.delegate = new ConstantKeyListenerDelegate() {
			public void setValue(Object value) {
				System.out.println("changed");
				textField.setText((value != null) ? value.toString() : "");
			}

			public Object getCellEditorValue() {
				return textField.getText();
			}

			@Override
			public void keyPressed(KeyEvent e) {
				System.out.println("changed2");
			}
		};
		textField.addActionListener(delegate);
		textField.addKeyListener((KeyListener) delegate);
	}

	protected class ConstantKeyListenerDelegate extends EditorDelegate implements KeyListener {

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
