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
		int w, h, outerW, outerH, centerH, centerW, outerCenterW, outerCenterH, fontHeight;
		w = (int) Math.round(width * 72D);
		h = (int) Math.round(height * 72D);
		outerW = (int) (w * zoom) * 3;
		outerH = (int) (h * zoom) * 3;
		centerW = (int) Math.round(w * 0.5);
		centerH = (int) Math.round(h * 0.5);
		outerCenterW = (int) Math.round(outerW * 0.5);
		outerCenterH = (int) Math.round(outerH * 0.5);
		Graphics2D g2 = (Graphics2D) g;
		AffineTransform tran = g2.getTransform();
		g2.translate(outerCenterW - centerW, outerCenterH - centerH);
		g2.setFont(addresseFont);
		fontHeight = g2.getFontMetrics().getHeight();
		g2.scale(zoom, zoom);
		g2.setColor(Color.BLACK);
		g2.drawRect(0, 0, w, h);
		g2.drawString("Your Name", 5, fontHeight);
		g2.setTransform(tran);
		return new Dimension(outerW, outerH);
	}

}
