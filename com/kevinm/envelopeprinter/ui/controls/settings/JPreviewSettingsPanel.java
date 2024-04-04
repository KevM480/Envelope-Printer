package com.kevinm.envelopeprinter.ui.controls.settings;

import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

public class JPreviewSettingsPanel extends JPanel {
	public JPreviewSettingsPanel() {

		SpringLayout layout = new SpringLayout();
		this.setLayout(layout);
		this.setPreferredSize(new Dimension(410, 120));
		Border border = BorderFactory.createLineBorder(getForeground());
		TitledBorder titledBorder = new TitledBorder(border, "Previews", TitledBorder.CENTER, TitledBorder.BOTTOM);
		this.setBorder(titledBorder);

	}
}
