package com.kevinm.envelopeprinter.ui.components;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class JTopMenuBar extends JMenuBar implements ActionListener {
	public JTopMenuBar() {
		JMenu file, print, edit;
		JMenuItem newFile, openFile, saveFile, saveAsFile, printEnvlope;

		newFile = new JMenuItem("New");
		newFile.addActionListener(this);
		newFile.setMnemonic(KeyEvent.VK_N);

		openFile = new JMenuItem("Open File");
		openFile.addActionListener(this);
		openFile.setMnemonic(KeyEvent.VK_O);

		saveFile = new JMenuItem("Save");
		saveFile.addActionListener(this);
		saveFile.setMnemonic(KeyEvent.VK_S);

		saveAsFile = new JMenuItem("Save As");
		saveAsFile.addActionListener(this);
		saveAsFile.setMnemonic(KeyEvent.VK_A);

		printEnvlope = new JMenuItem("Print");
		printEnvlope.addActionListener(this);
		printEnvlope.setMnemonic(KeyEvent.VK_P);

		file = new JMenu("File");
		file.setMnemonic(KeyEvent.VK_F);
		file.getAccessibleContext().setAccessibleDescription("File");
		file.add(newFile);
		file.add(openFile);
		file.add(saveFile);
		file.add(saveAsFile);
		this.add(file);

		print = new JMenu("Print");
		print.setMnemonic(KeyEvent.VK_P);
		print.getAccessibleContext().setAccessibleDescription("Print");
		print.add(printEnvlope);
		this.add(print);

		edit = new JMenu("Edit");
		edit.setMnemonic(KeyEvent.VK_E);
		edit.getAccessibleContext().setAccessibleDescription("Edit");
		this.add(edit);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}
}
