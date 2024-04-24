package com.kevinm.envelopeprinter.properties;

import java.awt.Font;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.kevinm.envelopeprinter.properties.annotation.Property;
import com.kevinm.envelopeprinter.properties.annotation.PropertyAnnotationHandler;

public class EnvelopeProperties {
	private static Properties properties = new Properties();

	public static Font senderFont;
	public static Font recieverFont;

	@Property(key = "sender.fontName")
	public static String senderFontName = "Arial";
	@Property(key = "sender.fontSize")
	public static Integer senderFontSize = 8;

	@Property(key = "receiver.fontName")
	public static String receiverFontName = "Arial";
	@Property(key = "receiver.fontSize")
	public static Integer receiverFontSize = 8;

	static {
		try (InputStream input = new FileInputStream("envelope.properties")) {
			if (input != null)
				properties.load(input);
		} catch (IOException e) {
			saveProperties();
		}

	}

	public static void loadProperties() {
		PropertyAnnotationHandler.loadProperties(properties, EnvelopeProperties.class.getClass().getFields());
	}

	public static void saveProperties() {
		PropertyAnnotationHandler.saveProperties(properties, EnvelopeProperties.class.getClass().getFields());
	}
}
