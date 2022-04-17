package com.printer.drawing;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

import javax.swing.JComponent;

public class DrawExample extends JComponent {
	private String fontType;
	private int fontAddSize;
	private int fontAddeeSize;
	
	public DrawExample(String font, int fontAddSz, int fontAddeeSz) {
		fontType = font;
		fontAddSize = fontAddSz;
		fontAddeeSize = fontAddeeSz;
	}
	
	private static final long serialVersionUID = 1735075342823476142L;
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D) g;
		Font font = g2.getFont();
		Rectangle2D outline = new Rectangle2D.Double(0, 0, 800, 347);
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
		
		g2.drawString("Sarah Orange", x, y - (fontAddSize / 2));
		g2.drawString("504 Example Street, 21555, MD", x, y + (fontAddSize / 2));
		
		g2.draw(outline);
	}
}
