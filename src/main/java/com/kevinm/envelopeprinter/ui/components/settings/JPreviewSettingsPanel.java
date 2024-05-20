package com.kevinm.envelopeprinter.ui.components.settings;

import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import com.kevinm.envelopeprinter.ui.components.JShowPreviewCheckBox;

public class JPreviewSettingsPanel extends JPanel {
	public JPreviewSettingsPanel() {

		SpringLayout layout = new SpringLayout();
		this.setLayout(layout);
		this.setPreferredSize(new Dimension(410, 120));
		Border border = BorderFactory.createLineBorder(getForeground());
		TitledBorder titledBorder = new TitledBorder(border, "Preview", TitledBorder.CENTER, TitledBorder.BOTTOM);
		this.setBorder(titledBorder);

		JShowPreviewCheckBox showPreview = new JShowPreviewCheckBox();
		this.add(showPreview);
	}
}
