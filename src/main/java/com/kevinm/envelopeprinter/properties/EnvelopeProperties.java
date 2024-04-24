package com.kevinm.envelopeprinter.properties;

import java.awt.Font;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

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

	public EnvelopeProperties() {
		try (InputStream input = new FileInputStream("envelope.properties")) {
			if (input != null)
				properties.load(input);
		} catch (IOException e) {
			this.saveProperties();
		}
	}

	public void loadProperties() {
		Field[] fields = this.getClass().getFields();
		for (Field field : fields) {
			if (field.isAnnotationPresent(Property.class)) {
				Property property = field.getAnnotation(Property.class);
				field.setAccessible(true);
				try {
					Object obj = field.getType().getConstructor(String.class).newInstance(properties.get(property.key()));
					field.set(null, obj);
				} catch (IllegalArgumentException | IllegalAccessException | InstantiationException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void saveProperties() {
		Field[] fields = this.getClass().getFields();
		for (Field field : fields) {
			if (field.isAnnotationPresent(Property.class)) {
				Property property = field.getAnnotation(Property.class);
				try {
					properties.setProperty(property.key(), field.get(null) + "");
				} catch (IllegalArgumentException | IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		try {
			properties.store(new FileOutputStream("envelope.properties"), null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
