package com.kevinm.envelopeprinter.ui.components;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import com.kevinm.envelopeprinter.ui.components.layouts.WrapLayout;

public class JAddresseeFormPanel extends JPanel {

	public JAddresseeFormPanel() {
		this.setBorder(BorderFactory.createLineBorder(getForeground()));
		this.setMinimumSize(new Dimension(305, 0));

		SpringLayout springLayout = new SpringLayout();

		WrapLayout layout = new WrapLayout(FlowLayout.LEADING);
		final JPanel recipientPanel = new JPanel();
		Border border = BorderFactory.createLineBorder(getForeground());
		TitledBorder recipientTitledBorder = new TitledBorder(border, "Recipient", TitledBorder.LEFT, TitledBorder.TOP);
		recipientPanel.setBorder(recipientTitledBorder);
		this.add(recipientPanel);

		final JLabeledTextField firstName = new JLabeledTextField("First Name: ", 10);
		firstName.setName("first_name");
		recipientPanel.add(firstName);

		final JLabeledTextField lastName = new JLabeledTextField("Last Name: ", 10);
		lastName.setName("last_name");
		recipientPanel.add(lastName);

		final JLabeledTextField company = new JLabeledTextField("Company: ", 18);
		company.setName("company");
		recipientPanel.add(company);

		recipientPanel.setLayout(layout);

		final JPanel addressPanel = new JPanel();
		TitledBorder addressTitledBorder = new TitledBorder(border, "Address", TitledBorder.LEFT, TitledBorder.TOP);
		addressPanel.setBorder(addressTitledBorder);
		this.add(addressPanel);

		final JLabeledTextField address = new JLabeledTextField("Address: ", 20);
		address.setName("address");
		addressPanel.add(address);

		final JLabeledTextField city = new JLabeledTextField("City: ", 15);
		city.setName("city");
		addressPanel.add(city);
		final JLabeledStateComboBox state = new JLabeledStateComboBox();
		state.setName("state");
		addressPanel.add(state);

		final JLabeledTextField zip = new JLabeledTextField("Zip Code: ", 6);
		zip.setName("zip");
		addressPanel.add(zip);
		addressPanel.setLayout(layout);

		springLayout.putConstraint(SpringLayout.NORTH, recipientPanel, 0, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.EAST, recipientPanel, 0, SpringLayout.EAST, this);
		springLayout.putConstraint(SpringLayout.WEST, recipientPanel, 0, SpringLayout.WEST, this);

		springLayout.putConstraint(SpringLayout.NORTH, addressPanel, 0, SpringLayout.SOUTH, recipientPanel);
		springLayout.putConstraint(SpringLayout.EAST, addressPanel, 0, SpringLayout.EAST, this);
		springLayout.putConstraint(SpringLayout.WEST, addressPanel, 0, SpringLayout.WEST, this);

		this.setLayout(springLayout);
	}

	public class JLabeledTextField extends JPanel {

		public JLabeledTextField(String title, int columns) {
			super();
			BoxLayout layout = new BoxLayout(this, BoxLayout.X_AXIS);
			this.setLayout(layout);
			final JLabel label = new JLabel(title);
			label.setName("title");
			final JTextField field = new JTextField(columns);
			field.setName("text_field");
			this.add(label);
			this.add(field);
		}
	}

	public class JLabeledStateComboBox extends JPanel {

		public JLabeledStateComboBox() {
			super();
			BoxLayout layout = new BoxLayout(this, BoxLayout.X_AXIS);
			this.setLayout(layout);
			final JLabel label = new JLabel("State: ");
			label.setName("title");
			final JStatesComboBox state = new JStatesComboBox();
			state.setName("state_box");
			this.add(label);
			this.add(state);
		}
	}

}
