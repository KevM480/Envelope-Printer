package com.kevinm.envelopeprinter.properties;

import java.lang.reflect.Field;
import java.util.HashMap;

import com.kevinm.envelopeprinter.properties.annotation.ConfigProperty;

public interface ConfigProperties {

	public String getFilePath();

	public default HashMap<String, Field> getFields() {
		HashMap<String, Field> fields = new HashMap<>();
		for (Field field : this.getClass().getFields()) {
			if (field.isAnnotationPresent(ConfigProperty.class))
				fields.put(field.getAnnotation(ConfigProperty.class).key(), field);
		}
		return fields;
	}

	public void afterLoadingProperties();

	public void afterSavingProperties();

}
