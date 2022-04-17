package com.printer.gui.listener.event;

import java.awt.Component;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import com.printer.drawing.DrawExample;
import com.printer.window.MainWindow;

public class FontSelectorItemEventListener implements ItemListener {
	
	private static MainWindow mainGui = MainWindow.getMainGui();
	
	/** Updates the example */
	@Override
	public void itemStateChanged(ItemEvent arg0) {
		DrawExample graphic = getGraphic();
		if (graphic != null) {
			int fontAddeeSize = (int) mainGui.fontSizeAddresseeComboBox.getSelectedItem();
			int fontAddSize = (int) mainGui.fontSizeComboBox.getSelectedItem();
			String font = (String) mainGui.fontTypeChoice.getSelectedItem();
			mainGui.envolopeExamplePanel.remove(graphic);
			graphic = new DrawExample(font, fontAddSize, fontAddeeSize);
			mainGui.envolopeExamplePanel.add(graphic);
			mainGui.envolopeExamplePanel.revalidate();
			mainGui.envolopeExamplePanel.repaint();
		}
	}
	
	private DrawExample getGraphic() {
		Component arr[] = mainGui.envolopeExamplePanel.getComponents();
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] instanceof DrawExample) {
				return (DrawExample) arr[i];
			}
		}
		
		return null;
	}
	
}
