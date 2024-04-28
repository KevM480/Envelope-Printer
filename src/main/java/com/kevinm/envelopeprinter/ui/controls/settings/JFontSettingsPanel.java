package com.kevinm.envelopeprinter.ui.controls.settings;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JToggleButton;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import com.kevinm.envelopeprinter.EnvelopePrinter;
import com.kevinm.envelopeprinter.properties.EnvelopePrinterConfig;
import com.kevinm.envelopeprinter.properties.events.PropertyItemListener;
import com.kevinm.envelopeprinter.ui.controls.JColorSelectorButton;
import com.kevinm.envelopeprinter.ui.controls.JFontComboBox;
import com.kevinm.envelopeprinter.window.EnvelopePrinterWindow;

public class JFontSettingsPanel extends JPanel {
	private final static Integer[] FONT_SIZES = new Integer[] { 8, 9, 10, 11, 12, 14, 16, 18, 20, 22, 24, 26, 28 };

	public JFontSettingsPanel() {
		SpringLayout layout = new SpringLayout();
		this.setLayout(layout);
		this.setPreferredSize(new Dimension(410, 120));
		Border border = BorderFactory.createLineBorder(getForeground());
		TitledBorder titledBorder = new TitledBorder(border, "Font", TitledBorder.CENTER, TitledBorder.BOTTOM);
		this.setBorder(titledBorder);

		final JPanel recipientFontPanel = new JPanel();
		final JPanel senderFontPanel = new JPanel();
		createFontSettingUI(recipientFontPanel, border, "Reciever");
		createFontSettingUI(senderFontPanel, border, "Sender");
		layout.putConstraint(SpringLayout.NORTH, recipientFontPanel, 0, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, recipientFontPanel, 0, SpringLayout.WEST, this);

		layout.putConstraint(SpringLayout.NORTH, senderFontPanel, 0, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, senderFontPanel, 0, SpringLayout.EAST, recipientFontPanel);

	}

	private void createFontSettingUI(JPanel panel, Border border, String name) {
		SpringLayout layout = new SpringLayout();
		TitledBorder panelBorder = new TitledBorder(border, name + "'s", TitledBorder.CENTER, TitledBorder.TOP);
		panel.setPreferredSize(new Dimension(200, 100));
		panel.setLayout(layout);
		panel.setBorder(panelBorder);
		this.add(panel);

		final JFontComboBox fontSelector = new JFontComboBox();
		fontSelector.setPreferredSize(new Dimension(120, 25));
		fontSelector.addItemListener(new PropertyItemListener() {
			@Override
			public void setProperties(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED)
					if (name.equals("Reciever"))
						EnvelopePrinterConfig.receiverFontName = (String) e.getItem();
					else if (name.equals("Sender"))
						EnvelopePrinterConfig.senderFontName = (String) e.getItem();
				EnvelopePrinterWindow window = EnvelopePrinter.getRoot();
				this.repaintComponet(((JSplitPane) window.getComponentNamed("split_pane")).getBottomComponent());
			}
		});
		layout.putConstraint(SpringLayout.NORTH, fontSelector, 0, SpringLayout.NORTH, panel);
		layout.putConstraint(SpringLayout.WEST, fontSelector, 5, SpringLayout.WEST, panel);
		panel.add(fontSelector);

		final JComboBox<Integer> fontSizeSelector = new JComboBox<>(FONT_SIZES);
		fontSizeSelector.setEditable(true);
		fontSizeSelector.setPreferredSize(new Dimension(50, 25));
		layout.putConstraint(SpringLayout.NORTH, fontSizeSelector, 0, SpringLayout.NORTH, fontSelector);
		layout.putConstraint(SpringLayout.WEST, fontSizeSelector, 5, SpringLayout.EAST, fontSelector);
		panel.add(fontSizeSelector);

		ImageIcon iconBold = new ImageIcon(ClassLoader.getSystemClassLoader().getResource("assets/icons/bold.png"));
		iconBold = new ImageIcon(iconBold.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH));
		final JToggleButton bold = new JToggleButton(iconBold);
		layout.putConstraint(SpringLayout.NORTH, bold, 5, SpringLayout.SOUTH, fontSelector);
		layout.putConstraint(SpringLayout.WEST, bold, 0, SpringLayout.WEST, fontSelector);
		panel.add(bold);

		ImageIcon iconItalic = new ImageIcon(ClassLoader.getSystemClassLoader().getResource("assets/icons/italic.png"));
		iconItalic = new ImageIcon(iconItalic.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH));
		final JToggleButton italic = new JToggleButton(iconItalic);
		layout.putConstraint(SpringLayout.NORTH, italic, 5, SpringLayout.SOUTH, fontSelector);
		layout.putConstraint(SpringLayout.WEST, italic, 2, SpringLayout.EAST, bold);
		panel.add(italic);

		ImageIcon iconUnderline = new ImageIcon(ClassLoader.getSystemClassLoader().getResource("assets/icons/underline.png"));
		iconUnderline = new ImageIcon(iconUnderline.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH));
		final JToggleButton underline = new JToggleButton(iconUnderline);
		layout.putConstraint(SpringLayout.NORTH, underline, 5, SpringLayout.SOUTH, fontSelector);
		layout.putConstraint(SpringLayout.WEST, underline, 2, SpringLayout.EAST, italic);
		panel.add(underline);

		JColorSelectorButton color = new JColorSelectorButton();
		color.setForeground(Color.BLACK);
		color.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Color newColor = JColorChooser.showDialog(JFontSettingsPanel.this, "Choose Background Color", Color.BLACK);
				color.setForeground(newColor);
			}
		});
		color.setVerticalAlignment(SwingConstants.TOP);
		color.setPreferredSize(new Dimension(26, color.getPreferredSize().height + 4));
		layout.putConstraint(SpringLayout.NORTH, color, 5, SpringLayout.SOUTH, fontSelector);
		layout.putConstraint(SpringLayout.WEST, color, 2, SpringLayout.EAST, underline);
		panel.add(color);

	}
}
