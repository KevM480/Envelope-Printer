package com.kevinm.envelopeprinter.properties;

import java.awt.Color;
import java.awt.Font;

import com.kevinm.envelopeprinter.properties.annotation.Property;
import com.kevinm.envelopeprinter.properties.annotation.PropertyAnnotationHandler;

public class EnvelopePrinterProperties {

	private static PropertyAnnotationHandler propertiesHandler;

	public static Font senderFont;
	public static Font recieverFont;

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

	public EnvelopePrinterProperties() {
		propertiesHandler = new PropertyAnnotationHandler(this.getClass(), "envelope_printer.properties");
	}

	public void loadProperties() {
		propertiesHandler.loadProperties();
	}

	public void saveProperties() {
		propertiesHandler.saveProperties();
	}
}
