package com.kevinm.envelopeprinter;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.swing.JSplitPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import com.formdev.flatlaf.FlatIntelliJLaf;
import com.kevinm.envelopeprinter.properties.ConfigPropertiesHandler;
import com.kevinm.envelopeprinter.properties.EnvelopePrinterConfig;
import com.kevinm.envelopeprinter.ui.controls.JPreviewScrollPane;
import com.kevinm.envelopeprinter.window.EnvelopePrinterWindow;

public class EnvelopePrinter {

	private static ConfigPropertiesHandler configPropertiesHandler;

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					FlatIntelliJLaf.setup();
					UIManager.put("ScrollBar.showButtons", true);
					UIManager.put("ScrollBar.width", 16);
				} catch (Exception ignored) {
				}
				EnvelopePrinter envelopePrinter = new EnvelopePrinter();
				EnvelopePrinterConfig printerConfig = new EnvelopePrinterConfig();
				configPropertiesHandler = new ConfigPropertiesHandler(printerConfig);
				envelopePrinter.preInit();
				EnvelopePrinterWindow window = new EnvelopePrinterWindow();
				JSplitPane splitPane = (JSplitPane) window.getComponentNamed("split_pane");
				JPreviewScrollPane scroll = (JPreviewScrollPane) splitPane.getBottomComponent();
				scroll.centerViewport();
				/*
				 * PrintService service = PrintServiceLookup.lookupDefaultPrintService(); Media
				 * med[] = (Media[]) service.getSupportedAttributeValues(Media.class, null,
				 * null); for (Media d : med) { if (d instanceof MediaSizeName name) { MediaSize
				 * s = MediaSize.getMediaSizeForName(name);
				 * System.out.println(s.getSize(MediaSize.INCH)[0] + " " +
				 * s.getSize(MediaSize.INCH)[1] + " " + d); } }
				 */

				envelopePrinter.postInit();
			}
		});

		// saves properties on close
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
			public void run() {
				configPropertiesHandler.writeProperties();
			}
		}, "Shutdown-thread"));

		// saves properties every minute
		Executors.newScheduledThreadPool(1).scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				configPropertiesHandler.writeProperties();
			}
		}, 1, 1, TimeUnit.MINUTES);
	}

	public static ConfigPropertiesHandler getPropertiesHandler() {
		return configPropertiesHandler;
	}

	/**
	 * Runs before creating the JFrame window
	 */
	private void preInit() {
		configPropertiesHandler.loadProperties();
	}

	/**
	 * Runs after creating the JFrame window
	 */
	private void postInit() {
	}
}
