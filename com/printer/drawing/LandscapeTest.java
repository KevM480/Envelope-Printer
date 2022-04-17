package com.printer.drawing;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;

public class LandscapeTest implements Printable {
	public static void main(String[] args) {
		new LandscapeTest();
	}
	
	public LandscapeTest() {
		testDialog();
		testManual();
	}
	
	void testDialog() {
		try {
			PrinterJob printer = PrinterJob.getPrinterJob();
			printer.setJobName("LandscapeTest.testDialog");
			printer.setPrintable(this);
			if (printer.printDialog())
				printer.print();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	void testManual() {
		try {
			PrintService[] services = PrintServiceLookup.lookupPrintServices(null, null);
			for (PrintService service : services) {
				if (service.getName().indexOf("XPS") != -1) {
					PrinterJob printer = PrinterJob.getPrinterJob();
					printer.setPrintService(service);
					printer.setJobName("LandscapeTest.testManual");
					
					PageFormat pg = new PageFormat();
					Paper p = new Paper();
					p.setSize(500, 710);
					p.setImageableArea(0, 0, 500, 710);
					pg.setPaper(p);
					pg.setOrientation(PageFormat.LANDSCAPE);
					
					printer.setPrintable(this, pg);
					printer.print();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
		if (pageIndex == 1)
			return Printable.NO_SUCH_PAGE;
		
		graphics.setColor(Color.RED);
		graphics.setFont(new Font("Arial", Font.BOLD, 18));
		graphics.drawString("Test Landscape Orientation", 50, 50);
		return Printable.PAGE_EXISTS;
	}
}