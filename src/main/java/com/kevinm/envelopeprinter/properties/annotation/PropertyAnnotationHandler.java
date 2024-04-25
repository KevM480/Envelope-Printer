package com.kevinm.envelopeprinter.properties.annotation;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

import com.kevinm.envelopeprinter.properties.PropertiesConfig;

public class PropertyAnnotationHandler {

	private Properties inputProperties;
	private PropertiesConfig propertiesConfig;
	private Field[] fields;

	public PropertyAnnotationHandler(PropertiesConfig propertiesConfig) {
		this.inputProperties = new Properties();
		this.propertiesConfig = propertiesConfig;
		try (InputStream input = new FileInputStream(propertiesConfig.getFilePath())) {
			inputProperties.load(input);
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.fields = propertiesConfig.getFields();
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
		propertiesConfig.afterLoadingProperties();
	}

	public void saveProperties() {
		propertiesConfig.beforeSavingProperties();
		for (Field field : fields)
			setProperty(field);
		try {
			inputProperties.store(new FileOutputStream(propertiesConfig.getFilePath()), null);
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
