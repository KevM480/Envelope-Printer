package com.kevinm.envelopeprinter.ui.controls;

import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class JAddresseeFormPanel extends JPanel {
	public JAddresseeFormPanel() {
		this.setBorder(BorderFactory.createLineBorder(getForeground()));
		this.setPreferredSize((new Dimension(431, 0)));
	}

}
