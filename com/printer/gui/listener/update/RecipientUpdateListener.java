package com.printer.gui.listener.update;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.printer.gui.table.RecipientJTable;
import com.printer.util.RecipientDataUtil;
import com.printer.window.MainWindow;

public class RecipientUpdateListener implements DocumentListener, ActionListener {
	
	public static boolean isListening = true;
	
	public RecipientUpdateListener() {
	}
	
	@Override
	public void changedUpdate(DocumentEvent e) {
	}
	
	@Override
	public void insertUpdate(DocumentEvent e) {
		if (isListening)
			out();
	}
	
	@Override
	public void removeUpdate(DocumentEvent e) {
		if (isListening)
			out();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (isListening)
			out();
	}
	
	public static void stopListening() {
		isListening = false;
	}
	
	public static void startListening() {
		isListening = true;
	}
	
	/** runs whenever a text field is updated */
	public void out() {
		RecipientDataUtil.setDataFromRecipientPanel();
		((RecipientJTable) MainWindow.getMainGui().table).updateTableOnInput();
		
	}
}
