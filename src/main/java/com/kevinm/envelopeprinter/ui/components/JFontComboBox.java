package com.kevinm.envelopeprinter.ui.components;

import java.awt.Component;
import java.awt.Font;

import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.plaf.basic.BasicComboBoxRenderer;

import com.formdev.flatlaf.util.FontUtils;
import com.kevinm.envelopeprinter.text.AutoCompleteDocument;

public class JFontComboBox extends JComboBox<String> {
	public JFontComboBox() {
		super(FontUtils.getAvailableFontFamilyNames());
		this.setRenderer(new FontComboBoxRenderer());
		this.setEditable(true);
		AutoCompleteDocument.addDocumentTo(this);

	}

	private static class FontComboBoxRenderer extends BasicComboBoxRenderer {
		@Override
		public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
			super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
			String fontFamilyName = (String) value;
			setFont(new Font(fontFamilyName, Font.PLAIN, 12));
			return this;
		}
	}

}
