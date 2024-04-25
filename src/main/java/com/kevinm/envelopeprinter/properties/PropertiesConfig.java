package com.kevinm.envelopeprinter.properties;

import java.lang.reflect.Field;

public interface PropertiesConfig {
	public String getFilePath();

	public default Field[] getFields() {
		return this.getClass().getFields();
	}

	public void afterLoadingProperties();

	public void beforeSavingProperties();
}
