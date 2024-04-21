package com.kevinm.envelopeprinter.draw;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
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
	public static void drawExampleEnvelope(Graphics g, double zoom, Dimension envelopeSize, Dimension expandedSize,
			Font addresseFont, Font senderFont) {
		int outerW, outerH, centerH, centerW, outerCenterW, outerCenterH;

		outerW = expandedSize.width;
		outerH = expandedSize.height;
		centerW = (int) Math.round(envelopeSize.width * 0.5);
		centerH = (int) Math.round(envelopeSize.height * 0.5);
		outerCenterW = (int) Math.round(outerW * 0.5);
		outerCenterH = (int) Math.round(outerH * 0.5);
		Graphics2D g2 = (Graphics2D) g;
		AffineTransform tran = g2.getTransform();
		g2.scale(zoom, zoom);
		g2.translate(outerCenterW - centerW, outerCenterH - centerH);
		g2.setColor(Color.BLACK);
		g2.drawRect(0, 0, envelopeSize.width, envelopeSize.height);
		g2.setFont(addresseFont);
		Graphics2DTextBox sender = new Graphics2DTextBox(g2, zoom,
				"Mike Smith\n100 Green Lime Ave\nLake City, MA,12345-1234");
		sender.drawText(5, 4);
		g2.setFont(senderFont);
		Graphics2DTextBox recipient = new Graphics2DTextBox(g2, 1,
				"Sam William\nSam's Boating School\n987 North River ST STE 201\nLake City, MA, 23456-1233");
		recipient.drawTextCentered(centerW, centerH);
		g2.setTransform(tran);

	}

	private static class Graphics2DTextBox {
		Dimension size = new Dimension();
		String lines[] = {};
		Graphics2D g2;

		private Graphics2DTextBox(Graphics2D g2, double zoom, String string) {
			this.g2 = g2;
			lines = string.split("\\n");
			FontMetrics fontMetric = g2.getFontMetrics();
			int maxWidth = 0, totalHeight = 0, lineHeight = fontMetric.getHeight();
			for (String line : lines) {
				maxWidth = Math.max(maxWidth, fontMetric.stringWidth(line));
				totalHeight += lineHeight;
			}
			size.height = (int) ((totalHeight - 2) * zoom);
			size.width = (int) (maxWidth * zoom);
		}

		private void drawText(int x, int y) {
			int fontHeight = g2.getFontMetrics().getHeight() - 1;
			for (int i = 0; i < lines.length; i++)
				g2.drawString(lines[i], x, fontHeight * i + (y + fontHeight));

		}

		private void drawTextCentered(int offX, int offY) {
			int x = (int) (size.width * 0.5), y = (int) (size.height * 0.5),
					fontHeight = g2.getFontMetrics().getHeight() - 1;

			for (int i = 0; i < lines.length; i++)
				this.g2.drawString(lines[i], -x + offX, fontHeight * i + (-y + fontHeight) + offY);
		}
	}

}
