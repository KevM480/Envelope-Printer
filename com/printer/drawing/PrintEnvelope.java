package com.printer.drawing;

import java.awt.print.Book;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.HashMap;

import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.DialogTypeSelection;
import javax.print.attribute.standard.OrientationRequested;

import com.printer.util.RecipientDataUtil;
import com.printer.window.MainWindow;

public class PrintEnvelope {
	
	/** Prints the image drawn by DrawEnvelope */
	public static void print() {
		
		int fontAddSize = (int) MainWindow.getMainGui().fontSizeComboBox.getSelectedItem();
		int fontAddeeSize = (int) MainWindow.getMainGui().fontSizeAddresseeComboBox.getSelectedItem();
		String font = (String) MainWindow.getMainGui().fontTypeChoice.getSelectedItem();
		
		PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
		
		aset.add(DialogTypeSelection.NATIVE);
		aset.add(OrientationRequested.LANDSCAPE);
		
		PrinterJob pj = PrinterJob.getPrinterJob();
		if (pj.printDialog(aset)) {
			
			PageFormat pf = pj.getPageFormat(aset);
			
			Paper paper = pf.getPaper();
			
			pf.setPaper(paper);
			
			Book pBook = new Book();
			//Collect all data within current table.
			for (HashMap<String, String> data : RecipientDataUtil.dataList) {
				if (RecipientDataUtil.isDataPrintable(data))
					pBook.append(new DrawEnvelope(data, font, fontAddSize, fontAddeeSize, pBook.getNumberOfPages()), pf);
			}
			
			pj.setPageable(pBook);
			pj.setJobName("Envlopes");
			try {
				for (int i = 0; i < pBook.getNumberOfPages(); i++) {
					pBook.setPage(i, pBook.getPrintable(i), pj.getPageFormat(aset));
				}
				//System.out.println(pBook.getPageFormat(0).getImageableHeight() / 72 + " " + pBook.getPageFormat(0).getImageableWidth() / 72);
				pj.print(aset);
			} catch (PrinterException e) {
			}
		}
	}
}
