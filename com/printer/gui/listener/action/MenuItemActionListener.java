package com.printer.gui.listener.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.DefaultTableModel;

import com.printer.drawing.PrintEnvelope;
import com.printer.gui.table.RecipientJTable;
import com.printer.util.RecipientDataUtil;
import com.printer.window.MainWindow;

public class MenuItemActionListener implements ActionListener {
	
	public String fileLoc = "";
	public File f = null;
	public boolean isFileLoaded = false;
	
	/** Saves the file with the data that is on displayed on screen */
	private void saveFile() {
		if (!isFileLoaded) {
			JOptionPane.showMessageDialog(null, "No File Is Currently Opened. Please Create A New File Or Open An Existing One.", "Error", JOptionPane.ERROR_MESSAGE);
		} else {
			try {
				FileWriter writer = new FileWriter(fileLoc, false);
				PrintWriter print = new PrintWriter(writer);
				
				for (String string : RecipientDataUtil.compiledDataList) {
					print.println(string);
				}
				print.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	/** Creates a new file.
	 * 
	 * @param mainGui
	 *            Used for the parent gui for the showSaveDialog
	 * @param tableModel
	 *            Used to add rows to the table */
	private void newFile(MainWindow mainGui, DefaultTableModel tableModel) {
		JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		fileChooser.setFileFilter(new FileNameExtensionFilter(".txt", "txt"));
		
		fileChooser.setDialogTitle("Specify a file to save");
		
		int userSelection = fileChooser.showSaveDialog(mainGui);
		
		if (userSelection == JFileChooser.APPROVE_OPTION) {
			
			File fileToSave = fileChooser.getSelectedFile();
			System.out.println("Save as file: " + fileToSave.getAbsolutePath());
			fileLoc = fileToSave.getAbsolutePath();
			f = new File(fileLoc);
			
			try {
				FileWriter writer = new FileWriter(f);
				writer.write("");
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			tableModel.setRowCount(0);
			if (tableModel.getRowCount() != 100)
				for (int i = 0; i < 100; i++) {
					tableModel.addRow(new Object[] { i + 1 });
				}
			RecipientDataUtil.cleanData();
			isFileLoaded = true;
			mainGui.setTitle("Addresses  " + f.getAbsolutePath());
			
		}
	}
	
	public void saveFileAs() {
		if (!isFileLoaded) {
			JOptionPane.showMessageDialog(null, "No File Is Currently Opened. Please Create A New File Or Open An Existing One.", "Error", JOptionPane.ERROR_MESSAGE);
		} else {
			try {
				FileWriter writer = new FileWriter(fileLoc, false);
				PrintWriter print = new PrintWriter(writer);
				for (String string : RecipientDataUtil.compiledDataList) {
					print.println(string);
				}
				print.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	/** Opens a file
	 * 
	 * @param mainGui
	 *            Used for the parent gui for the showOpenDialog
	 * @param tableModel
	 *            Used to add rows to the table */
	public void openFile(MainWindow mainGui, DefaultTableModel tableModel) {
		JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		jfc.setFileFilter(new FileNameExtensionFilter(".txt", "txt"));
		int returnValue = jfc.showOpenDialog(mainGui);
		
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			try {
				File selectedFile = jfc.getSelectedFile();
				System.out.println(selectedFile.getAbsolutePath());
				fileLoc = selectedFile.getAbsolutePath();
				f = new File(fileLoc);
				BufferedReader reader = new BufferedReader(new FileReader(f));
				String line = reader.readLine();
				
				List<String> compiledData = new ArrayList<String>();
				while (line != null) {
					compiledData.add(line);
					line = reader.readLine();
				}
				reader.close();
				
				tableModel.setRowCount(0);
				if (tableModel.getRowCount() != 100)
					for (int i = 0; i < 100; i++) {
						tableModel.addRow(new Object[] { i + 1 });
					}
				RecipientDataUtil.cleanData();
				RecipientDataUtil.setDataFromOpenFile(compiledData);
				((RecipientJTable) MainWindow.getMainGui().table).updateTableOnOpen();
				
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			isFileLoaded = true;
			mainGui.setTitle("Addresses  " + f.getAbsolutePath());
		}
	}
	
	private void print() {
		
		if (!isFileLoaded) {
			JOptionPane.showMessageDialog(null, "No File Is Currently Opened. Please Create A New File Or Open An Existing One.", "Error", JOptionPane.ERROR_MESSAGE);
		} else {
			//System.out.println(mainGui.fontTypeChoice.getSelectedObjects()[0]);
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					try {
						PrintEnvelope.print();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		MainWindow mainGui = MainWindow.getMainGui();
		DefaultTableModel tableModel = ((DefaultTableModel) mainGui.table.getModel());
		
		if (e.getSource() == mainGui.save)
			saveFile();
		if (e.getSource() == mainGui.open)
			openFile(mainGui, tableModel);
		if (e.getSource() == mainGui.newFile)
			newFile(mainGui, tableModel);
		if (e.getSource() == mainGui.saveAs)
			saveFileAs();
		if (e.getSource() == mainGui.print)
			print();
		
	}
	
}
