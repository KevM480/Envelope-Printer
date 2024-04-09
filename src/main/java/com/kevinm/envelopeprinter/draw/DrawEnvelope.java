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
	public static Dimension drawExampleEnvelope(Graphics g, double zoom, Dimension envelopeSize, Font addresseFont,
			Font senderFont) {
		int outerW, outerH, centerH, centerW, outerCenterW, outerCenterH, fontHeight;

		outerW = (int) (envelopeSize.width * zoom) * 3;
		outerH = (int) (envelopeSize.height * zoom) * 3;
		centerW = (int) Math.round(envelopeSize.width * 0.5);
		centerH = (int) Math.round(envelopeSize.height * 0.5);
		outerCenterW = (int) Math.round(outerW * 0.5);
		outerCenterH = (int) Math.round(outerH * 0.5);
		Graphics2D g2 = (Graphics2D) g;
		AffineTransform tran = g2.getTransform();
		g2.translate(outerCenterW - centerW, outerCenterH - centerH);
		g2.setFont(addresseFont);
		fontHeight = g2.getFontMetrics().getHeight();
		g2.scale(zoom, zoom);
		g2.setColor(Color.BLACK);
		g2.drawRect(0, 0, envelopeSize.width, envelopeSize.height);
		g2.drawString("Your Name", 5, fontHeight);
		g2.setTransform(tran);
		return new Dimension(outerW, outerH);
	}

}
