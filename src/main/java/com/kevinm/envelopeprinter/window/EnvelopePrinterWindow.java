package com.kevinm.envelopeprinter.window;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JSplitPane;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import com.formdev.flatlaf.FlatIntelliJLaf;
import com.kevinm.envelopeprinter.properties.EnvelopeProperties;
import com.kevinm.envelopeprinter.ui.controls.JAddressBookPane;
import com.kevinm.envelopeprinter.ui.controls.JAddresseeFormPanel;
import com.kevinm.envelopeprinter.ui.controls.JPreviewScrollPane;
import com.kevinm.envelopeprinter.ui.controls.JTopMenuBar;
import com.kevinm.envelopeprinter.ui.controls.settings.JFontSettingsPanel;
import com.kevinm.envelopeprinter.ui.controls.settings.JPreviewSettingsPanel;

public class EnvelopePrinterWindow extends JFrame {

	private HashMap<String, Component> componentMap;

	public EnvelopePrinterWindow() {
		super("Envlope Printer");
		this.preInit();
		this.setName("envlope_printer");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setExtendedState(MAXIMIZED_BOTH);
		this.setMinimumSize(new Dimension(1000, 500));
		this.setJMenuBar(new JTopMenuBar());
		SpringLayout layout = new SpringLayout();
		this.setLayout(layout);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width, height;
		width = (int) screenSize.getWidth();
		height = (int) screenSize.getHeight();
		this.setSize(width, height);

		Container contentPane = this.getContentPane();

		final JFontSettingsPanel fontSettings = new JFontSettingsPanel();
		final JPreviewSettingsPanel previewSettings = new JPreviewSettingsPanel();
		final JAddressBookPane addressPane = new JAddressBookPane();
		final JAddresseeFormPanel formPanel = new JAddresseeFormPanel();
		final JPreviewScrollPane previewPane = new JPreviewScrollPane();
		final JSplitPane splitPane = new JSplitPane(SwingConstants.HORIZONTAL, addressPane, previewPane);
		splitPane.setDividerLocation(0.5);
		this.addComponent("font_setting_panel", fontSettings);
		this.addComponent("preview_setting_panel", previewSettings);
		this.addComponent("form_panel", formPanel);
		this.addComponent("split_pane", splitPane);
		this.createComponentMap();
		layout.putConstraint(SpringLayout.WEST, fontSettings, 0, SpringLayout.WEST, contentPane);
		layout.putConstraint(SpringLayout.NORTH, fontSettings, 0, SpringLayout.NORTH, contentPane);

		layout.putConstraint(SpringLayout.WEST, previewSettings, 0, SpringLayout.EAST, fontSettings);
		layout.putConstraint(SpringLayout.NORTH, previewSettings, 0, SpringLayout.NORTH, contentPane);

		layout.putConstraint(SpringLayout.NORTH, formPanel, 0, SpringLayout.SOUTH, fontSettings);
		layout.putConstraint(SpringLayout.EAST, formPanel, -3, SpringLayout.EAST, contentPane);
		layout.putConstraint(SpringLayout.SOUTH, formPanel, -3, SpringLayout.SOUTH, contentPane);

		layout.putConstraint(SpringLayout.NORTH, splitPane, 0, SpringLayout.SOUTH, fontSettings);
		layout.putConstraint(SpringLayout.WEST, splitPane, 3, SpringLayout.WEST, contentPane);
		layout.putConstraint(SpringLayout.EAST, splitPane, -3, SpringLayout.WEST, formPanel);
		layout.putConstraint(SpringLayout.SOUTH, splitPane, -3, SpringLayout.SOUTH, contentPane);

		this.setVisible(true);
	}

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
				EnvelopePrinterWindow window = new EnvelopePrinterWindow();
				JSplitPane splitPane = (JSplitPane) window.getComponentNamed("split_pane");
				JPreviewScrollPane scroll = (JPreviewScrollPane) splitPane.getBottomComponent();
				scroll.centerViewport();
				window.postInit();
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
	}

	private void preInit() {
		EnvelopeProperties.loadProperties();
	}

	private void postInit() {
		EnvelopeProperties.saveProperties();
	}

	private void addComponent(String name, Component component) {
		Container contentPane = this.getContentPane();
		component.setName(name);
		contentPane.add(component);
	}

	private void createComponentMap() {
		componentMap = new HashMap<String, Component>();
		for (Component component : this.getContentPane().getComponents())
			componentMap.put(component.getName(), component);
	}

	public Component getComponentNamed(String name) {
		if (!componentMap.containsKey(name))
			return null;
		return componentMap.get(name);
	}

}
