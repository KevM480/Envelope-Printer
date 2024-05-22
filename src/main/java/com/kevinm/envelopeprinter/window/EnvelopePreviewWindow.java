package com.kevinm.envelopeprinter.window;

import java.awt.Container;

import javax.swing.JFrame;

import com.kevinm.envelopeprinter.ui.components.JPreviewScrollPane;

public class EnvelopePreviewWindow extends JFrame {

	public EnvelopePreviewWindow() {
		Container contentPane = this.getContentPane();
		final JPreviewScrollPane previewPanel = new JPreviewScrollPane();
		contentPane.add(previewPanel);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.pack();
		this.setVisible(true);
	}

}
