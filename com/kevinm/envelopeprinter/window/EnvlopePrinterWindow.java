package com.kevinm.envelopeprinter.window;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.SpringLayout;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import com.formdev.flatlaf.FlatIntelliJLaf;
import com.kevinm.envelopeprinter.ui.controls.JAddressBookPane;
import com.kevinm.envelopeprinter.ui.controls.JAddresseeFormPanel;
import com.kevinm.envelopeprinter.ui.controls.JPreviewPanel;
import com.kevinm.envelopeprinter.ui.controls.JTopMenuBar;
import com.kevinm.envelopeprinter.ui.controls.settings.JFontSettingsPanel;
import com.kevinm.envelopeprinter.ui.controls.settings.JPreviewSettingsPanel;

public class EnvlopePrinterWindow extends JFrame {

	public EnvlopePrinterWindow() {
		super("Envlope Printer");

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
		Container contentPane = this.getContentPane();
		final JFontSettingsPanel fontSettings = new JFontSettingsPanel();
		layout.putConstraint(SpringLayout.WEST, fontSettings, 0, SpringLayout.WEST, contentPane);
		layout.putConstraint(SpringLayout.NORTH, fontSettings, 0, SpringLayout.NORTH, contentPane);
		contentPane.add(fontSettings);
		final JPreviewSettingsPanel previewSettings = new JPreviewSettingsPanel();
		layout.putConstraint(SpringLayout.WEST, previewSettings, 0, SpringLayout.EAST, fontSettings);
		layout.putConstraint(SpringLayout.NORTH, previewSettings, 0, SpringLayout.NORTH, contentPane);
		contentPane.add(previewSettings);

		final JAddressBookPane addressPane = new JAddressBookPane();
		final JAddresseeFormPanel formPanel = new JAddresseeFormPanel();
		final JPreviewPanel previewPanel = new JPreviewPanel();

		layout.putConstraint(SpringLayout.NORTH, formPanel, 0, SpringLayout.SOUTH, fontSettings);
		layout.putConstraint(SpringLayout.EAST, formPanel, -3, SpringLayout.EAST, contentPane);
		layout.putConstraint(SpringLayout.SOUTH, formPanel, -3, SpringLayout.SOUTH, contentPane);

		layout.putConstraint(SpringLayout.NORTH, addressPane, 0, SpringLayout.SOUTH, fontSettings);
		layout.putConstraint(SpringLayout.WEST, addressPane, 3, SpringLayout.WEST, contentPane);
		layout.putConstraint(SpringLayout.EAST, addressPane, -3, SpringLayout.WEST, formPanel);
		layout.putConstraint(SpringLayout.SOUTH, addressPane, -3, SpringLayout.NORTH, previewPanel);

		layout.putConstraint(SpringLayout.WEST, previewPanel, 3, SpringLayout.WEST, contentPane);
		layout.putConstraint(SpringLayout.EAST, previewPanel, -3, SpringLayout.WEST, formPanel);
		layout.putConstraint(SpringLayout.SOUTH, previewPanel, -3, SpringLayout.SOUTH, contentPane);

		contentPane.add(formPanel);
		contentPane.add(addressPane);
		contentPane.add(previewPanel);

		/*
		 * JLabel label = new JLabel("Label: ");
		 * 
		 * contentPane.add(label); JTextField textField = new JTextField("Text field",
		 * 15); contentPane.add(textField); SpringLayout layout = new SpringLayout();
		 * this.setLayout(layout); layout.putConstraint(SpringLayout.WEST, label, 5,
		 * SpringLayout.WEST, contentPane); layout.putConstraint(SpringLayout.NORTH,
		 * label, 5, SpringLayout.NORTH, contentPane);
		 * 
		 * layout.putConstraint(SpringLayout.WEST, textField, 5, SpringLayout.EAST,
		 * label); layout.putConstraint(SpringLayout.NORTH, textField, 5,
		 * SpringLayout.NORTH, contentPane);
		 */
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					FlatIntelliJLaf.setup();
					UIManager.put("ScrollBar.showButtons", true);
					UIManager.put("ScrollBar.width", 16);
					// FlatDarkLaf.setup();
				} catch (Exception ignored) {
				}
				new EnvlopePrinterWindow().setVisible(true);
			}
		});
	}
}
