package com.kevinm.envelopeprinter.properties.annotation;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

public class PropertyAnnotationHandler {

	private Properties inputProperties;
	private String filePath;
	private Field[] fields;

	public PropertyAnnotationHandler(Class<?> containingClass, String filePath) {
		this.inputProperties = new Properties();
		this.filePath = filePath;
		try (InputStream input = new FileInputStream(filePath)) {
			inputProperties.load(input);
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.fields = containingClass.getFields();
	}

	public void loadProperties() {
		for (Field field : fields) {
			if (field.isAnnotationPresent(Property.class)) {
				Property property = field.getAnnotation(Property.class);
				field.setAccessible(true);
				try {
					if (inputProperties.containsKey(property.key())) {
						Object value = inputProperties.get(property.key());
						Object obj = field.getType().getConstructor(String.class).newInstance(value);
						field.set(null, obj);
					}
				} catch (IllegalArgumentException | IllegalAccessException | InstantiationException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void saveProperties() {
		for (Field field : fields)
			setProperty(field);
		try {
			inputProperties.store(new FileOutputStream(this.filePath), null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void setProperty(Field field) {
		if (field.isAnnotationPresent(Property.class)) {
			Property property = field.getAnnotation(Property.class);
			try {
				inputProperties.setProperty(property.key(), field.get(null) + "");
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}

}
