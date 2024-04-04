package com.kevinm.envelopeprinter.text;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.annotation.Nullable;
import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.JTextComponent;
import javax.swing.text.PlainDocument;

/**
 * <h1>Document for a JComboBox that will attempt to auto complete String
 * entered</h1>
 * <p>
 * This Document class will search through the provided JComboBox's
 * ComboBoxModel for the first String that matches the String entered in the
 * JComboBox's Editor Component.
 * 
 */
public class AutoCompleteDocument extends PlainDocument {
	private ComboBoxModel<String> model;
	private boolean selecting = false;
	private JTextComponent editor;

	/**
	 * Use this method to add AutoCompleteDocument to the ComboBox
	 * 
	 * @param comboBox JComboBox you want to make use this Document.
	 */
	public static void addDocumentTo(JComboBox<String> comboBox) {
		JTextComponent editor = (JTextComponent) comboBox.getEditor().getEditorComponent();

		editor.setDocument(new AutoCompleteDocument(comboBox, editor));
	}

	private AutoCompleteDocument(JComboBox<String> comboBox, JTextComponent editor) {
		this.model = comboBox.getModel();
		this.editor = editor;
		String selected = (String) comboBox.getSelectedItem();
		if (selected != null)
			setText(selected.toString(), null);
		editor.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER)
					editor.select(editor.getText().length(), editor.getText().length());

				if (comboBox.isDisplayable())
					comboBox.setPopupVisible(true);
			}
		});
	}

	/***
	 * @param text Text to set onto the document
	 * @param a    Optional AttributeSet object for the text.
	 */
	private void setText(String text, @Nullable AttributeSet a) {
		try {
			super.remove(0, getLength());
			super.insertString(0, text, a);
		} catch (BadLocationException e) {
			throw new RuntimeException(e.toString());
		}
	}

	@Override
	public void remove(int offs, int len) throws BadLocationException {
		if (selecting)
			return;
		super.remove(offs, len);
	}

	@Override
	public void insertString(int offs, String str, @Nullable AttributeSet a) throws BadLocationException {
		super.insertString(offs, str, a);
		if (selecting)
			return;
		String text = lookupText(getText(0, getLength()));
		if (text == null)
			return;
		setSelectedText(text);
		setText(text, a);
		editor.setSelectionStart(offs + str.length());
		editor.setSelectionEnd(getLength());
	}

	private void setSelectedText(String text) {
		selecting = true;
		model.setSelectedItem(text);
		selecting = false;
	}

	private boolean startsWithIgnoreCase(String str1, String str2) {
		return str1.toUpperCase().startsWith(str2.toUpperCase());
	}

	private String lookupText(String pattern) {
		String selectedItem = (String) model.getSelectedItem();
		if (selectedItem != null && startsWithIgnoreCase(selectedItem.toString(), pattern)) {
			return selectedItem;
		} else {
			for (int i = 0, n = model.getSize(); i < n; i++) {
				String currentItem = model.getElementAt(i);
				if (startsWithIgnoreCase(currentItem.toString(), pattern)) {
					return currentItem;
				}
			}
		}
		return null;
	}
}
