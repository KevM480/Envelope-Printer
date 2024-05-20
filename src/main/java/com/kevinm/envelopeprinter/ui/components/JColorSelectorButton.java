package com.kevinm.envelopeprinter.ui.components;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class JColorSelectorButton extends JButton {

	public JColorSelectorButton() {
		super(null, createIcon());
	}

	private static Icon createIcon() {
		ImageIcon iconUnderline = new ImageIcon(ClassLoader.getSystemClassLoader().getResource("assets/icons/font.png"));
		return new ImageIcon(iconUnderline.getImage().getScaledInstance(12, 12, Image.SCALE_SMOOTH));
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(this.getForeground());
		g.fillRect(5, 18, 16, 3);
	}
}
