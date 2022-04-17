package com.printer.util;

import java.awt.GraphicsEnvironment;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FontUtil {
	/** List of preset font sizes. */
	public static final ArrayList<Integer> listOfSizes = new ArrayList<Integer>(Arrays.asList(8, 9, 10, 11, 12, 14, 16, 18, 20, 22, 24, 26, 28, 36));
	
	/** @return
	 *         List of all fonts that are a part of the computer */
	public static List<String> getFonts() {
		List<String> fontNm = new ArrayList<String>();
		String fonts[] = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
		for (int i = 0; i < fonts.length; i++) {
			fontNm.add(fonts[i]);
		}
		return fontNm;
	}
	
}
