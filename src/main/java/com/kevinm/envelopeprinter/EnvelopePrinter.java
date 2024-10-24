package com.kevinm.envelopeprinter;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.swing.JSplitPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import com.formdev.flatlaf.FlatIntelliJLaf;
import com.kevinm.envelopeprinter.components.ComponentFinderUtils;
import com.kevinm.envelopeprinter.properties.ConfigPropertiesHandler;
import com.kevinm.envelopeprinter.properties.EnvelopePrinterConfig;
import com.kevinm.envelopeprinter.ui.components.JPreviewScrollPane;
import com.kevinm.envelopeprinter.window.EnvelopePrinterWindow;

public class EnvelopePrinter {

	private static ConfigPropertiesHandler configPropertiesHandler;
	private static EnvelopePrinterWindow root;
	private static boolean startFlag;

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				preInit();
				root = new EnvelopePrinterWindow();
				ComponentFinderUtils.setRoot(root.getContentPane());
				root.createComponents();
				postInit();
				/*
				 * PrintService service = PrintServiceLookup.lookupDefaultPrintService(); Media
				 * med[] = (Media[]) service.getSupportedAttributeValues(Media.class, null,
				 * null); for (Media d : med) { if (d instanceof MediaSizeName name) { MediaSize
				 * s = MediaSize.getMediaSizeForName(name);
				 * System.out.println(s.getSize(MediaSize.INCH)[0] + " " +
				 * s.getSize(MediaSize.INCH)[1] + " " + d); } }
				 */

			}
		});

		// saves properties on close
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
			public void run() {
				configPropertiesHandler.writeProperties();
			}
		}, "Shutdown-thread"));

		// Will be used for backing up list of addresses
		Executors.newScheduledThreadPool(1).scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {

			}
		}, 1, 1, TimeUnit.MINUTES);
	}

	public static EnvelopePrinterWindow getRoot() {
		return root;
	}

	/**
	 * Returns whether or not the Window has finished building.
	 * 
	 * @return true if it is building the Window.
	 *         <p>
	 *         false if it has finished building the window.
	 */
	public static boolean getIsBuilding() {
		return startFlag;
	}

	public static ConfigPropertiesHandler getPropertiesHandler() {
		return configPropertiesHandler;
	}

	/**
	 * Runs before creating the JFrame window
	 */
	private static void preInit() {
		try {
			FlatIntelliJLaf.setup();
			UIManager.put("ScrollBar.showButtons", true);
			UIManager.put("ScrollBar.width", 16);
		} catch (Exception ignored) {
		}
		configPropertiesHandler = new ConfigPropertiesHandler(new EnvelopePrinterConfig());
		configPropertiesHandler.loadProperties();
		startFlag = true;
	}

	/**
	 * Runs after creating the JFrame window
	 */
	private static void postInit() {
		startFlag = false;
		JSplitPane splitPane = ComponentFinderUtils.get(JSplitPane.class, "split_pane_vertical.split_pane_horizontal");
		JPreviewScrollPane scroll = (JPreviewScrollPane) splitPane.getBottomComponent();
		scroll.centerViewport(true);
		scroll.grabFocus();

	}
}
