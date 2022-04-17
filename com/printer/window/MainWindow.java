package com.printer.window;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.printer.drawing.DrawExample;
import com.printer.gui.jpanel.RecipientAddressInputJPanel;
import com.printer.gui.listener.action.MenuItemActionListener;
import com.printer.gui.listener.event.FontSelectorItemEventListener;
import com.printer.gui.table.RecipientJTable;
import com.printer.util.FontUtil;

@SuppressWarnings("serial")
public class MainWindow extends JFrame {
	
	public JPopupMenu popMenu = new JPopupMenu();
	public JMenuItem taskList;
	
	public JMenuItem newFile;
	public JMenuItem open;
	public JMenuItem save;
	public JMenuItem saveAs;
	public JMenuItem print;
	public JMenuItem fontList;
	public JMenuBar mb = new JMenuBar();
	public JMenu menu = new JMenu("File");
	public JMenu printer = new JMenu("Print");
	public JMenu edit = new JMenu("Edit");
	
	public JPanel envolopeExamplePanel = new JPanel();
	public Choice fontTypeChoice = new Choice();
	public JComboBox<Integer> fontSizeComboBox = new JComboBox<Integer>();
	public JComboBox<Integer> fontSizeAddresseeComboBox = new JComboBox<Integer>();
	
	public JPanel recipScrollPanel = new RecipientAddressInputJPanel();
	
	private final JToolBar toolBar = new JToolBar();
	private final JLabel fontLabel = new JLabel("Font: ");
	private final Component horizontalStrut = Box.createHorizontalStrut(1);
	public final JTable table = new RecipientJTable();
	
	private static DrawExample graphic;
	//Used to info from swing objects
	private static MainWindow instance;
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				MainWindow mainGui = new MainWindow();
				mainGui.createGui();
			}
		});
	}
	
	public static MainWindow getMainGui() {
		return instance;
	}
	
	public MainWindow() {
		instance = this;
		getContentPane().setBackground(Color.LIGHT_GRAY);
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		this.setTitle("Address List");
	}
	
	/** Creates the main GUI seen at launch. */
	private void createGui() {
		toolBar.setFloatable(false);
		fontList = new JMenuItem("");
		
		JScrollPane scrollPane = new JScrollPane();
		
		JToolBar toolBar_1 = new JToolBar();
		toolBar_1.setPreferredSize(new Dimension(13, 24));
		toolBar_1.setFloatable(false);
		
		scrollPane.setViewportView(table);
		
		// Menu Bar Start
		newFile = new JMenuItem("New");
		open = new JMenuItem("Open");
		save = new JMenuItem("Save");
		saveAs = new JMenuItem("Save As");
		print = new JMenuItem("Print");
		
		MenuItemActionListener menuActionListener = new MenuItemActionListener();
		newFile.addActionListener(menuActionListener);
		open.addActionListener(menuActionListener);
		print.addActionListener(menuActionListener);
		saveAs.addActionListener(menuActionListener);
		save.addActionListener(menuActionListener);
		
		save.setOpaque(true);
		
		menu.add(newFile);
		menu.add(open);
		menu.add(save);
		menu.add(saveAs);
		
		printer.add(print);
		toolBar.add(mb);
		
		mb.add(menu);
		mb.add(printer);
		mb.add(edit);
		mb.add(horizontalStrut);
		mb.add(fontLabel);
		
		FontSelectorItemEventListener fontListener = new FontSelectorItemEventListener();
		
		for (String fontTp : FontUtil.getFonts()) {
			fontTypeChoice.add(fontTp);
		}
		fontTypeChoice.addItemListener(fontListener);
		fontTypeChoice.setFocusable(false);
		fontTypeChoice.setPreferredSize(new Dimension(100, 0));
		fontTypeChoice.select(2);
		mb.add(fontTypeChoice);
		
		for (Integer size : FontUtil.listOfSizes) {
			fontSizeComboBox.addItem(size);
			fontSizeAddresseeComboBox.addItem(size);
		}
		fontSizeComboBox.setFocusable(false);
		fontSizeComboBox.setFocusTraversalPolicyProvider(true);
		fontSizeComboBox.addItemListener(fontListener);
		fontSizeComboBox.setEditable(true);
		fontSizeComboBox.setPreferredSize(new Dimension(0, 20));
		fontSizeComboBox.setSelectedIndex(5);
		toolBar_1.add(fontSizeComboBox);
		
		fontSizeAddresseeComboBox.setFocusable(false);
		fontSizeAddresseeComboBox.setFocusTraversalPolicyProvider(true);
		fontSizeAddresseeComboBox.addItemListener(fontListener);
		fontSizeAddresseeComboBox.setEditable(true);
		fontSizeAddresseeComboBox.setPreferredSize(new Dimension(0, 20));
		fontSizeAddresseeComboBox.setSelectedIndex(4);
		toolBar_1.add(fontSizeAddresseeComboBox);
		
		int fontAddSize = (int) fontSizeComboBox.getSelectedItem();
		int fontAddeeSize = (int) fontSizeAddresseeComboBox.getSelectedItem();
		String font = (String) fontTypeChoice.getSelectedItem();
		
		graphic = new DrawExample(font, fontAddSize, fontAddeeSize);
		
		envolopeExamplePanel.setLayout(new BorderLayout(0, 0));
		envolopeExamplePanel.add(graphic, BorderLayout.CENTER);
		
		scrollPane.setPreferredSize(new Dimension(600, 600));
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout.createSequentialGroup().addComponent(toolBar, GroupLayout.PREFERRED_SIZE, 333, GroupLayout.PREFERRED_SIZE).addComponent(toolBar_1, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)).addGroup(groupLayout.createSequentialGroup().addComponent(envolopeExamplePanel, GroupLayout.PREFERRED_SIZE, 800, GroupLayout.PREFERRED_SIZE).addGap(8).addComponent(recipScrollPanel, GroupLayout.DEFAULT_SIZE, 642, Short.MAX_VALUE)).addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 1449, Short.MAX_VALUE));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout.createSequentialGroup().addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false).addComponent(toolBar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(toolBar_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)).addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 321, Short.MAX_VALUE).addGap(7).addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false).addComponent(recipScrollPanel, GroupLayout.PREFERRED_SIZE, 347, GroupLayout.PREFERRED_SIZE).addComponent(envolopeExamplePanel, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 347, GroupLayout.PREFERRED_SIZE))));
		
		getContentPane().setLayout(groupLayout);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1620, 751);
		this.setVisible(true);
		
	}
}
