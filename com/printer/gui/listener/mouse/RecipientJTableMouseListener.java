package com.printer.gui.listener.mouse;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.printer.gui.jpanel.RecipientAddressInputJPanel;
import com.printer.gui.listener.update.RecipientUpdateListener;

public class RecipientJTableMouseListener implements MouseListener {
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			RecipientUpdateListener.stopListening();
			RecipientAddressInputJPanel.update();
			RecipientUpdateListener.startListening();
		}
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
