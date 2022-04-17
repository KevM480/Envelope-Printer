package com.printer.drawing;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.util.HashMap;

public class DrawEnvelope implements Printable {
	
	private HashMap<String, String> data;
	private String fontType;
	private int fontAddSize;
	private int fontAddeeSize;
	private int pageIndex;
	
	public DrawEnvelope(HashMap<String, String> data, String font, int fontAddSz, int fontAddeeSz, int pageIndex) {
		this.data = data;
		fontType = font;
		fontAddSize = fontAddSz;
		fontAddeeSize = fontAddeeSz;
		this.pageIndex = pageIndex;
	}
	
	@Override
	public int print(Graphics g, PageFormat pf, int pageIndex) {
		pageIndex = this.pageIndex;
		Graphics2D g2 = (Graphics2D) g;
		Font font = g2.getFont();
		Rectangle2D outline = new Rectangle2D.Double(0, 0, 8.62d * 72d, 4d * 72d);
		g2.translate(pf.getImageableX(), pf.getImageableY());
		
		System.out.println("print" + pf.getHeight());
		System.out.println("print" + pf.getWidth());
		Rectangle rect = outline.getBounds();
		FontMetrics metrics = g2.getFontMetrics(font);
		g2.setFont(new Font(fontType, Font.PLAIN, fontAddeeSize));
		
		g2.setPaint(Color.black);
		int x = rect.x + 10;
		int y = rect.y + 20 + fontAddeeSize;
		g2.drawString("Mike Little", x, y - (fontAddeeSize / 2));
		g2.drawString("101 Random Ave, 25555, MD", x, y + (fontAddeeSize / 2));
		
		g2.setFont(new Font(fontType, Font.PLAIN, fontAddSize));
		font = g2.getFont();
		metrics = g2.getFontMetrics(font);
		x = rect.x + (rect.width - metrics.stringWidth("504 Example Street, 21061, MD")) / 2;
		y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();
		g2.setFont(font);
		
		if (!data.get("Address2").equals("")) {
			g2.drawString(data.get("FullName"), x, y - (fontAddSize));
			g2.drawString(data.get("Address2"), x, y);
			g2.drawString(data.get("Address1") + ", " + data.get("Zip") + ", " + data.get("State"), x, y + (fontAddSize));
		} else {
			g2.drawString(data.get("FullName"), x, y - (fontAddSize / 2));
			g2.drawString(data.get("Address1") + ", " + data.get("Zip") + ", " + data.get("State"), x, y + (fontAddSize / 2));
		}
		return PAGE_EXISTS;
	}
	
}
