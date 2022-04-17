package com.printer.window;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class SenderAddressWindow {
	
	private JFrame frame;
	private JTextField textField;
	
	/** Launch the application. */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SenderAddressWindow window = new SenderAddressWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/** Create the application. */
	public SenderAddressWindow() {
		initialize();
	}
	
	/** Initialize the contents of the frame. */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 535, 358);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(90, 26, 192, 19);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Sender's Name");
		lblNewLabel.setBounds(10, 29, 70, 13);
		frame.getContentPane().add(lblNewLabel);
	}
}
