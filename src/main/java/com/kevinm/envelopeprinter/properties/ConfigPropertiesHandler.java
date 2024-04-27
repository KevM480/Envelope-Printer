package com.kevinm.envelopeprinter.properties;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Properties;

import com.kevinm.envelopeprinter.properties.annotation.ConfigProperty;

public class ConfigPropertiesHandler {

	private Properties inputProperties;
	private ConfigProperties propertiesConfig;
	HashMap<String, Field> fields = new HashMap<>();

	public ConfigPropertiesHandler(ConfigProperties propertiesConfig) {
		this.inputProperties = new Properties();
		this.propertiesConfig = propertiesConfig;
		this.fields = propertiesConfig.getFields();
		try (InputStream input = new FileInputStream(propertiesConfig.getFilePath())) {
			inputProperties.load(input);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Reads the properties off of the properties file and sets the fields that
	 * match the property's key
	 */
	public void loadProperties() {
		for (Entry<String, Field> entry : fields.entrySet()) {
			entry.getValue().setAccessible(true);
			try {
				if (inputProperties.containsKey(entry.getKey())) {
					Object value = inputProperties.get(entry.getKey());
					Object obj = entry.getValue().getType().getConstructor(String.class).newInstance(value);
					entry.getValue().set(null, obj);
				}
			} catch (IllegalArgumentException | IllegalAccessException | InstantiationException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
				e.printStackTrace();
			}

		}
		propertiesConfig.afterLoadingProperties();
	}

	/**
	 * Writes all the properties in the Properties config to the properties file
	 */
	public void writeProperties() {
		try {
			inputProperties.store(new FileOutputStream(propertiesConfig.getFilePath()), null);
		} catch (IOException e) {
			e.printStackTrace();
		}
		propertiesConfig.afterSavingProperties();
	}

	/**
	 * Parses through the fields to set the property to the value of the field
	 */
	public void setProperties() {
		for (Entry<String, Field> entry : fields.entrySet())
			setProperty(entry.getValue());

	}

	/**
	 * Finds the field that has the property key provided and sets it with the new
	 * value
	 * 
	 * @param popertyKey The property key of the field you want to set
	 * 
	 * @param value      The value you want the field to be set too
	 */
	public void setProperty(String popertyKey, Object value) {
		if (fields.containsKey(popertyKey)) {
			Field field = fields.get(popertyKey);
			field.setAccessible(true);
			try {
				field.set(null, value);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Sets the field's value to the Properties config
	 * 
	 * @param field The field that needs to be set.
	 */
	private void setProperty(Field field) {
		ConfigProperty property = field.getAnnotation(ConfigProperty.class);
		if (property != null) {
			try {
				inputProperties.setProperty(property.key(), field.get(null) + "");
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}

}
