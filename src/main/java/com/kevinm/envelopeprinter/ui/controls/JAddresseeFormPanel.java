package com.kevinm.envelopeprinter.ui.controls;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class JAddresseeFormPanel extends JPanel {
	public JAddresseeFormPanel() {
		this.setBorder(BorderFactory.createLineBorder(getForeground()));
		this.setMinimumSize(new Dimension(300, 0));
		final JLabeledTextField recipient = new JLabeledTextField("Recipient");
		recipient.setName("recipient");
		recipient.setPreferredSize(new Dimension(100, recipient.getHeight()));
		FlowLayout layout = new FlowLayout(FlowLayout.LEADING);
		this.setLayout(layout);
		this.add(recipient);
	}

	public class JLabeledTextField extends JPanel {

		public JLabeledTextField(String title) {
			super();
			BoxLayout layout = new BoxLayout(this, BoxLayout.X_AXIS);
			final JLabel label = new JLabel(title);
			label.setName("title");
			final JTextField field = new JTextField();
			field.setName("text_field");
			this.add(label);
			this.add(field);
		}

		@Override
		public void setPreferredSize(Dimension preferredSize) {
			try {
				// ComponentHierarchy.get(this).get("text_field").getComponent().setPreferredSize(preferredSize);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
