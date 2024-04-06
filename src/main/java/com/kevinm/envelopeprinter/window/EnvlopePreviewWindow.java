package com.kevinm.envelopeprinter.window;

import java.awt.Container;

import javax.swing.JFrame;

import com.kevinm.envelopeprinter.ui.controls.JPreviewPanel;

public class EnvlopePreviewWindow extends JFrame {

	public EnvlopePreviewWindow() {
		Container contentPane = this.getContentPane();
		final JPreviewPanel previewPanel = new JPreviewPanel();
		contentPane.add(previewPanel);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.pack();
		this.setVisible(true);
	}

}
