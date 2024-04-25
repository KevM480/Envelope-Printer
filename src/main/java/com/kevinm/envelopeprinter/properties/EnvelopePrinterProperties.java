package com.kevinm.envelopeprinter.properties;

import java.awt.Color;

import com.kevinm.envelopeprinter.properties.annotation.Property;

public class EnvelopePrinterProperties implements PropertiesConfig {

	@Property(key = "sender.fontName")
	public static String senderFontName = "Arial";
	@Property(key = "sender.fontSize")
	public static Integer senderFontSize = 8;
	@Property(key = "sender.fontColor")
	public static Integer senderFontColor = Color.BLACK.getRGB();

	@Property(key = "receiver.fontName")
	public static String receiverFontName = "Arial";
	@Property(key = "receiver.fontSize")
	public static Integer receiverFontSize = 8;
	@Property(key = "receiver.fontColor")
	public static Integer receiverFontColor = Color.BLACK.getRGB();

	@Property(key = "window.showPreview")
	public static Boolean showPreview = true;

	@Override
	public String getFilePath() {
		return "envelope_printer.properties";
	}

	@Override
	public void afterLoadingProperties() {

	}

	@Override
	public void beforeSavingProperties() {

	}

}
