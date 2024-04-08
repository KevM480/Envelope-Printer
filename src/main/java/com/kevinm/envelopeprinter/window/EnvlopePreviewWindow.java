package com.kevinm.envelopeprinter.window;

import java.awt.Container;

import javax.swing.JFrame;

import com.kevinm.envelopeprinter.ui.controls.JPreviewScrollPane;

public class EnvlopePreviewWindow extends JFrame {

	public EnvlopePreviewWindow() {
		Container contentPane = this.getContentPane();
		final JPreviewScrollPane previewPanel = new JPreviewScrollPane();
		contentPane.add(previewPanel);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.pack();
		this.setVisible(true);
	}

}
