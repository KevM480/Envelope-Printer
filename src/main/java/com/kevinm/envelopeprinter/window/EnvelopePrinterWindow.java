package com.kevinm.envelopeprinter.window;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JSplitPane;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;

import com.kevinm.envelopeprinter.ui.controls.JAddressBookScrollPane;
import com.kevinm.envelopeprinter.ui.controls.JAddresseeFormPanel;
import com.kevinm.envelopeprinter.ui.controls.JPreviewScrollPane;
import com.kevinm.envelopeprinter.ui.controls.JTopMenuBar;
import com.kevinm.envelopeprinter.ui.controls.settings.JFontSettingsPanel;
import com.kevinm.envelopeprinter.ui.controls.settings.JPreviewSettingsPanel;

public class EnvelopePrinterWindow extends JFrame {

	public EnvelopePrinterWindow() {
		super("Envlope Printer");
		this.setName("envlope_printer");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setExtendedState(MAXIMIZED_BOTH);
		this.setMinimumSize(new Dimension(1000, 500));
		this.setJMenuBar(new JTopMenuBar());
		SpringLayout layout = new SpringLayout();
		this.setLayout(layout);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width, height;
		width = (int) screenSize.getWidth();
		height = (int) screenSize.getHeight();
		this.setSize(width, height);

		this.setVisible(true);
	}

	public void createComponents() {

		Container contentPane = this.getContentPane();
		contentPane.setName("content");

		final JFontSettingsPanel fontSettings = new JFontSettingsPanel();
		fontSettings.setName("font_setting_panel");
		final JPreviewSettingsPanel previewSettings = new JPreviewSettingsPanel();
		previewSettings.setName("preview_setting_panel");
		final JAddresseeFormPanel formPanel = new JAddresseeFormPanel();
		formPanel.setName("form_panel");

		contentPane.add(fontSettings);
		contentPane.add(previewSettings);
		contentPane.add(formPanel);

		final JAddressBookScrollPane addressPane = new JAddressBookScrollPane();
		addressPane.setName("address_book_pane");
		final JPreviewScrollPane previewPane = new JPreviewScrollPane();
		previewPane.setName("preview_pane");
		final JSplitPane splitPane = new JSplitPane(SwingConstants.HORIZONTAL, addressPane, previewPane);
		splitPane.setName("split_pane");

		contentPane.add(splitPane);
		SpringLayout layout = (SpringLayout) contentPane.getLayout();
		layout.putConstraint(SpringLayout.WEST, fontSettings, 0, SpringLayout.WEST, contentPane);
		layout.putConstraint(SpringLayout.NORTH, fontSettings, 0, SpringLayout.NORTH, contentPane);

		layout.putConstraint(SpringLayout.WEST, previewSettings, 0, SpringLayout.EAST, fontSettings);
		layout.putConstraint(SpringLayout.NORTH, previewSettings, 0, SpringLayout.NORTH, contentPane);

		layout.putConstraint(SpringLayout.NORTH, formPanel, 0, SpringLayout.SOUTH, fontSettings);
		layout.putConstraint(SpringLayout.EAST, formPanel, -3, SpringLayout.EAST, contentPane);
		layout.putConstraint(SpringLayout.SOUTH, formPanel, -3, SpringLayout.SOUTH, contentPane);

		layout.putConstraint(SpringLayout.NORTH, splitPane, 0, SpringLayout.SOUTH, fontSettings);
		layout.putConstraint(SpringLayout.WEST, splitPane, 3, SpringLayout.WEST, contentPane);
		layout.putConstraint(SpringLayout.EAST, splitPane, -3, SpringLayout.WEST, formPanel);
		layout.putConstraint(SpringLayout.SOUTH, splitPane, -3, SpringLayout.SOUTH, contentPane);
		contentPane.revalidate();
	}

}
