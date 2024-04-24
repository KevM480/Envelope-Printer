package com.kevinm.envelopeprinter.properties.annotation;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

public class PropertyAnnotationHandler {

	public static void loadProperties(Properties properties, Field[] fields) {
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

	public static void saveProperties(Properties properties, Field[] fields) {
		for (Field field : fields) {
			if (field.isAnnotationPresent(Property.class)) {
				Property property = field.getAnnotation(Property.class);
				try {
					properties.setProperty(property.key(), field.get(null) + "");
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		try {
			properties.store(new FileOutputStream("envelope.properties"), null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
