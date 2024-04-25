package com.kevinm.envelopeprinter;

import javax.swing.JSplitPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import com.formdev.flatlaf.FlatIntelliJLaf;
import com.kevinm.envelopeprinter.properties.EnvelopePrinterProperties;
import com.kevinm.envelopeprinter.properties.PropertiesConfig;
import com.kevinm.envelopeprinter.properties.annotation.PropertyAnnotationHandler;
import com.kevinm.envelopeprinter.ui.controls.JPreviewScrollPane;
import com.kevinm.envelopeprinter.window.EnvelopePrinterWindow;

public class EnvelopePrinter {

	public static PropertiesConfig propertiesConfig = new EnvelopePrinterProperties();
	private static PropertyAnnotationHandler propertiesHandler = new PropertyAnnotationHandler(propertiesConfig);

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
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
			public void run() {
				propertiesHandler.saveProperties();
			}
		}, "Shutdown-thread"));
	}

	/**
	 * Runs before creating the JFrame window
	 */
	private void preInit() {
		propertiesHandler.loadProperties();
	}

	/**
	 * Runs after creating the JFrame window
	 */
	private void postInit() {
	}
}
