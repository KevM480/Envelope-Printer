package com.printer.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JTextField;

import com.printer.window.MainWindow;

public class RecipientDataUtil {
	
	private static MainWindow mainGui = MainWindow.getMainGui();
	
	public static List<HashMap<String, String>> dataList = new ArrayList<HashMap<String, String>>();
	public static List<String> compiledDataList = new ArrayList<String>();
	
	/** Fills Lists with empty data to avoid Null errors. */
	public static void cleanData() {
		if (mainGui.table.getRowCount() != dataList.size())
			for (int i = 0; i < mainGui.table.getRowCount(); i++) {
				dataList.add(new HashMap<String, String>());
				compiledDataList.add("");
			}
		else
			for (int i = 0; i < mainGui.table.getRowCount(); i++) {
				dataList.set(i, new HashMap<String, String>());
				compiledDataList.set(i, "");
			}
	}
	
	/** Collects information that is stored Recipient input panel */
	@SuppressWarnings("unchecked")
	public static void setDataFromRecipientPanel() {
		HashMap<String, String> stringMap = new HashMap<String, String>();
		
		for (int i = 0; i < mainGui.recipScrollPanel.getComponentCount(); i++) {
			String name = "";
			String data = "";
			
			if (mainGui.recipScrollPanel.getComponent(i) instanceof JTextField) {
				data = ((JTextField) mainGui.recipScrollPanel.getComponent(i)).getText();
				name = mainGui.recipScrollPanel.getComponent(i).getName();
			} else if (mainGui.recipScrollPanel.getComponent(i) instanceof JComboBox) {
				data = (String) ((JComboBox<String>) mainGui.recipScrollPanel.getComponent(i)).getSelectedItem();
				name = mainGui.recipScrollPanel.getComponent(i).getName();
			}
			setData(name, data, stringMap);
		}
		dataList.set(mainGui.table.getSelectedRow(), stringMap);
		compileData(stringMap);
	}
	
	public static void setDataFromOpenFile(List<String> compiledData) {
		int counter = 0;
		List<HashMap<String, String>> decompiledData = decompileData(compiledData);
		for (HashMap<String, String> stringMap : decompiledData) {
			dataList.set(counter, stringMap);
			counter++;
		}
	}
	
	private static void setData(String name, String data, HashMap<String, String> stringMap) {
		switch (name) {
		case "FullName":
			stringMap.put(name, data);
			break;
		case "CompanyName":
			stringMap.put(name, data);
			break;
		case "Address1":
			stringMap.put(name, data);
			break;
		case "Address2":
			stringMap.put(name, data);
			break;
		case "City":
			stringMap.put(name, data);
			break;
		case "State":
			stringMap.put(name, data);
			break;
		case "Zip":
			stringMap.put(name, data);
			break;
		case "Country":
			if (data.equals("Don't Add To Envelope"))
				stringMap.put(name, "");
			else
				stringMap.put(name, data);
			break;
		default:
			break;
		}
	}
	
	private static void compileData(HashMap<String, String> stringMap) {
		compiledDataList.set(mainGui.table.getSelectedRow(), stringMap.get("FullName") + "-|-" + stringMap.get("CompanyName") + "-|-" + stringMap.get("Address1") + "-|-" + stringMap.get("Address2") + "-|-" + stringMap.get("City") + "-|-" + stringMap.get("State") + "-|-" + stringMap.get("Zip") + "-|-" + stringMap.get("Country"));
	}
	
	private static List<HashMap<String, String>> decompileData(List<String> compiledData) {
		List<HashMap<String, String>> dataLs = new ArrayList<HashMap<String, String>>();
		
		for (String eachData : compiledData) {
			HashMap<String, String> stringMap = new HashMap<String, String>();
			List<String> splitData = Arrays.asList(eachData.split("-\\|-"));
			for (int i = 0; i < splitData.size(); i++) {
				String name = "";
				String data = "";
				data = splitData.get(i);
				switch (i) {
				case 0:
					name = "FullName";
					break;
				case 1:
					name = "CompanyName";
					break;
				case 2:
					name = "Address1";
					break;
				case 3:
					name = "Address2";
					break;
				case 4:
					name = "City";
					break;
				case 5:
					name = "State";
					break;
				case 6:
					name = "Zip";
					break;
				case 7:
					name = "Country";
					break;
				default:
					break;
				}
				setData(name, data, stringMap);
			}
			dataLs.add(stringMap);
		}
		return dataLs;
	}
	
	/** @param data
	 *            sender and reciever's addresses
	 * @return
	 *         true if data compiled properly. */
	public static boolean isDataPrintable(HashMap<String, String> data) {
		if (!data.containsKey("FullName") || !data.containsKey("Address1") || !data.containsKey("City") || !data.containsKey("State") || !data.containsKey("Zip"))
			return false;
		
		if (data.get("FullName").equals("") || data.get("Address1").equals("") || data.get("City").equals("") || data.get("State").equals("") || data.get("Zip").equals(""))
			return false;
		else
			return true;
		
	}
	
}

/*
 * 
 * 
			*/
