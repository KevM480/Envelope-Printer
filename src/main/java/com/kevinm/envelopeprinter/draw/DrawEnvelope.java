package com.kevinm.envelopeprinter.draw;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

public class DrawEnvelope {

	/**
	 * 
	 * @param width        width of envelope in inches
	 * @param height       height of envelope in inches
	 * @param addresseFont
	 * @param senderFont
	 * @return
	 */
	public static Dimension drawExampleEnvelope(Graphics g, double zoom, double width, double height, Font addresseFont,
			Font senderFont) {
		int w = (int) Math.round(width * 72D);
		int h = (int) Math.round(height * 72D);
		Graphics2D g2 = (Graphics2D) g;

		AffineTransform tran = g2.getTransform();
		g2.setFont(addresseFont);
		int fontHeight = g2.getFontMetrics().getHeight();
		g2.scale(zoom, zoom);
		g2.setColor(Color.BLACK);
		g2.drawString("Your Name", 5, fontHeight);
		g2.setTransform(tran);
		return new Dimension((int) (w * zoom), (int) (h * zoom));
	}

}
