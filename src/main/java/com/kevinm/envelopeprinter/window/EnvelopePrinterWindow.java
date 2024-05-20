package com.kevinm.envelopeprinter.window;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JSplitPane;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;

import com.kevinm.envelopeprinter.ui.components.JAddressBookScrollPane;
import com.kevinm.envelopeprinter.ui.components.JAddresseeFormPanel;
import com.kevinm.envelopeprinter.ui.components.JPreviewScrollPane;
import com.kevinm.envelopeprinter.ui.components.JTopMenuBar;
import com.kevinm.envelopeprinter.ui.components.settings.JFontSettingsPanel;
import com.kevinm.envelopeprinter.ui.components.settings.JPreviewSettingsPanel;

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
		final JSplitPane splitPaneHorizontal = new JSplitPane(SwingConstants.HORIZONTAL, addressPane, previewPane);
		splitPaneHorizontal.setName("split_pane_horizontal");
		final JSplitPane splitPaneVertical = new JSplitPane(SwingConstants.VERTICAL, splitPaneHorizontal, formPanel);
		splitPaneVertical.setDividerLocation(this.getWidth() - 300);
		splitPaneVertical.setName("split_pane_vertical");
		contentPane.add(splitPaneVertical);

		SpringLayout layout = (SpringLayout) contentPane.getLayout();
		layout.putConstraint(SpringLayout.WEST, fontSettings, 0, SpringLayout.WEST, contentPane);
		layout.putConstraint(SpringLayout.NORTH, fontSettings, 0, SpringLayout.NORTH, contentPane);

		layout.putConstraint(SpringLayout.WEST, previewSettings, 0, SpringLayout.EAST, fontSettings);
		layout.putConstraint(SpringLayout.NORTH, previewSettings, 0, SpringLayout.NORTH, contentPane);

		layout.putConstraint(SpringLayout.NORTH, formPanel, 0, SpringLayout.SOUTH, fontSettings);
		layout.putConstraint(SpringLayout.EAST, formPanel, -3, SpringLayout.EAST, contentPane);
		layout.putConstraint(SpringLayout.SOUTH, formPanel, -3, SpringLayout.SOUTH, contentPane);

		layout.putConstraint(SpringLayout.NORTH, splitPaneVertical, 0, SpringLayout.SOUTH, fontSettings);
		layout.putConstraint(SpringLayout.WEST, splitPaneVertical, 3, SpringLayout.WEST, contentPane);
		layout.putConstraint(SpringLayout.EAST, splitPaneVertical, -3, SpringLayout.EAST, contentPane);
		layout.putConstraint(SpringLayout.SOUTH, splitPaneVertical, -3, SpringLayout.SOUTH, contentPane);
		contentPane.revalidate();
		this.setVisible(true);
	}

}
