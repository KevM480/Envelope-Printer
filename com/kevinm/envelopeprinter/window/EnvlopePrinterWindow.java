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
import com.kevinm.envelopeprinter.ui.controls.JFontPanel;
import com.kevinm.envelopeprinter.ui.controls.JTopMenuBar;

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
		final JFontPanel settingTools = new JFontPanel();
		layout.putConstraint(SpringLayout.WEST, settingTools, 0, SpringLayout.WEST, contentPane);
		layout.putConstraint(SpringLayout.NORTH, settingTools, 0, SpringLayout.NORTH, contentPane);
		contentPane.add(settingTools);
		final JAddressBookPane addressPane = new JAddressBookPane();
		final JAddresseeFormPanel formPanel = new JAddresseeFormPanel();

		layout.putConstraint(SpringLayout.NORTH, formPanel, 0, SpringLayout.SOUTH, settingTools);
		layout.putConstraint(SpringLayout.EAST, formPanel, -3, SpringLayout.EAST, contentPane);
		layout.putConstraint(SpringLayout.SOUTH, formPanel, -3, SpringLayout.SOUTH, contentPane);

		layout.putConstraint(SpringLayout.NORTH, addressPane, 0, SpringLayout.SOUTH, settingTools);
		layout.putConstraint(SpringLayout.WEST, addressPane, 3, SpringLayout.WEST, contentPane);
		layout.putConstraint(SpringLayout.EAST, addressPane, -3, SpringLayout.WEST, formPanel);
		layout.putConstraint(SpringLayout.SOUTH, addressPane, -3, SpringLayout.SOUTH, contentPane);

		contentPane.add(formPanel);
		contentPane.add(addressPane);

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
