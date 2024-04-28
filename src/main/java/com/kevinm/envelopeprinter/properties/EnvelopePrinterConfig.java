package com.kevinm.envelopeprinter.properties;

import java.awt.Color;
import java.awt.Font;

import com.kevinm.envelopeprinter.properties.annotation.ConfigProperty;

public class EnvelopePrinterConfig implements ConfigProperties {

	public static Font senderFont;
	public static Font recieverFont;

	@ConfigProperty(key = "sender.fontName")
	public static String senderFontName = "Arial";
	@ConfigProperty(key = "sender.fontSize")
	public static Integer senderFontSize = 8;
	@ConfigProperty(key = "sender.fontColor")
	public static Integer senderFontColor = Color.BLACK.getRGB();

	@ConfigProperty(key = "receiver.fontName")
	public static String receiverFontName = "Arial";
	@ConfigProperty(key = "receiver.fontSize")
	public static Integer receiverFontSize = 8;
	@ConfigProperty(key = "receiver.fontColor")
	public static Integer receiverFontColor = Color.BLACK.getRGB();

	@ConfigProperty(key = "window.showPreview")
	public static Boolean showPreview = true;

	@Override
	public String getFilePath() {
		return "envelope_printer.properties";
	}

	private void propertiesUpdated() {
		senderFont = new Font(senderFontName, Font.PLAIN, senderFontSize);
		recieverFont = new Font(receiverFontName, Font.PLAIN, receiverFontSize);
	}

	@Override
	public void afterLoadingProperties() {
		propertiesUpdated();
	}

	@Override
	public void afterSavingProperties() {
		propertiesUpdated();
	}

}
