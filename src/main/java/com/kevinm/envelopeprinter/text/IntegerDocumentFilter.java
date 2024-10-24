package com.kevinm.envelopeprinter.text;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;

public class IntegerDocumentFilter extends DocumentFilter {

	@Override
	public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
		StringBuilder stringBuilder = createStringBuilder(fb).insert(offset, string);
		if (isStringValid(stringBuilder.toString())) {
			super.insertString(fb, offset, string, attr);
		}
	}

	@Override
	public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
		StringBuilder stringBuilder = createStringBuilder(fb).replace(offset, offset + length, text);
		if (isStringValid(stringBuilder.toString())) {
			super.replace(fb, offset, length, text, attrs);
		}
	}

	@Override
	public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
		StringBuilder stringBuilder = createStringBuilder(fb).delete(offset, offset + length);
		if (isStringValid(stringBuilder.toString())) {
			super.remove(fb, offset, length);
		}
	}

	private StringBuilder createStringBuilder(FilterBypass fb) throws BadLocationException {
		Document doc = fb.getDocument();
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(doc.getText(0, doc.getLength()));
		return stringBuilder;
	}

	protected boolean isStringValid(String value) {
		try {
			Integer.parseInt(value);
			return true;
		} catch (NumberFormatException e) {
			if (value.equals(""))
				return true;
			return false;
		}
	}
}
