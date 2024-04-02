package com.kevinm.envelopeprinter.ui.controls;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.annotation.Nullable;
import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.plaf.basic.BasicComboBoxRenderer;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.JTextComponent;
import javax.swing.text.PlainDocument;

import com.formdev.flatlaf.util.FontUtils;

public class JFontComboBox extends JComboBox<String> {
	public JFontComboBox() {
		super(FontUtils.getAvailableFontFamilyNames());
		this.setRenderer(new FontComboBoxRenderer());
		this.setEditable(true);
		new AutoCompleteDocument(this);
	}

	private static class AutoCompleteDocument extends PlainDocument {
		private ComboBoxModel<String> model;
		private boolean selecting = false;
		private JTextComponent editor;

		public AutoCompleteDocument(JFontComboBox comboBox) {
			model = comboBox.getModel();
			editor = (JTextComponent) comboBox.getEditor().getEditorComponent();
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
			editor.setDocument(this);
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
		public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
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

	private static class FontComboBoxRenderer extends BasicComboBoxRenderer {
		@Override
		public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
				boolean cellHasFocus) {
			super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
			String fontFamilyName = (String) value;
			setFont(new Font(fontFamilyName, Font.PLAIN, 12));
			return this;
		}
	}

}
