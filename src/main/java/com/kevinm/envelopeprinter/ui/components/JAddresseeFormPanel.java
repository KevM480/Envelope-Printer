package com.kevinm.envelopeprinter.ui.components;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.HashMap;
import java.util.Map;

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
		this.setMinimumSize(new Dimension(300, 0));

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

		final JLabeledTextField city = new JLabeledTextField("City: ", 20);
		city.setName("city");
		addressPanel.add(city);

		final JLabeledTextField state = new JLabeledTextField("State: ", 20);
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

	public enum State {

		ALABAMA("Alabama", "AL"), ALASKA("Alaska", "AK"), AMERICAN_SAMOA("American Samoa", "AS"), ARIZONA("Arizona", "AZ"), ARKANSAS("Arkansas", "AR"), CALIFORNIA("California", "CA"),
		COLORADO("Colorado", "CO"), CONNECTICUT("Connecticut", "CT"), DELAWARE("Delaware", "DE"), DISTRICT_OF_COLUMBIA("District of Columbia", "DC"),
		FEDERATED_STATES_OF_MICRONESIA("Federated States of Micronesia", "FM"), FLORIDA("Florida", "FL"), GEORGIA("Georgia", "GA"), GUAM("Guam", "GU"), HAWAII("Hawaii", "HI"), IDAHO("Idaho", "ID"),
		ILLINOIS("Illinois", "IL"), INDIANA("Indiana", "IN"), IOWA("Iowa", "IA"), KANSAS("Kansas", "KS"), KENTUCKY("Kentucky", "KY"), LOUISIANA("Louisiana", "LA"), MAINE("Maine", "ME"),
		MARYLAND("Maryland", "MD"), MARSHALL_ISLANDS("Marshall Islands", "MH"), MASSACHUSETTS("Massachusetts", "MA"), MICHIGAN("Michigan", "MI"), MINNESOTA("Minnesota", "MN"),
		MISSISSIPPI("Mississippi", "MS"), MISSOURI("Missouri", "MO"), MONTANA("Montana", "MT"), NEBRASKA("Nebraska", "NE"), NEVADA("Nevada", "NV"), NEW_HAMPSHIRE("New Hampshire", "NH"),
		NEW_JERSEY("New Jersey", "NJ"), NEW_MEXICO("New Mexico", "NM"), NEW_YORK("New York", "NY"), NORTH_CAROLINA("North Carolina", "NC"), NORTH_DAKOTA("North Dakota", "ND"),
		NORTHERN_MARIANA_ISLANDS("Northern Mariana Islands", "MP"), OHIO("Ohio", "OH"), OKLAHOMA("Oklahoma", "OK"), OREGON("Oregon", "OR"), PALAU("Palau", "PW"), PENNSYLVANIA("Pennsylvania", "PA"),
		PUERTO_RICO("Puerto Rico", "PR"), RHODE_ISLAND("Rhode Island", "RI"), SOUTH_CAROLINA("South Carolina", "SC"), SOUTH_DAKOTA("South Dakota", "SD"), TENNESSEE("Tennessee", "TN"),
		TEXAS("Texas", "TX"), UTAH("Utah", "UT"), VERMONT("Vermont", "VT"), VIRGIN_ISLANDS("Virgin Islands", "VI"), VIRGINIA("Virginia", "VA"), WASHINGTON("Washington", "WA"),
		WEST_VIRGINIA("West Virginia", "WV"), WISCONSIN("Wisconsin", "WI"), WYOMING("Wyoming", "WY"), UNKNOWN("Unknown", "");

		/**
		 * The state's name.
		 */
		private String name;

		/**
		 * The state's abbreviation.
		 */
		private String abbreviation;

		/**
		 * The set of states addressed by abbreviations.
		 */
		private static final Map<String, State> STATES_BY_ABBR = new HashMap<String, State>();

		/* static initializer */
		static {
			for (State state : values()) {
				STATES_BY_ABBR.put(state.getAbbreviation(), state);
			}
		}

		/**
		 * Constructs a new state.
		 *
		 * @param name         the state's name.
		 * @param abbreviation the state's abbreviation.
		 */
		State(String name, String abbreviation) {
			this.name = name;
			this.abbreviation = abbreviation;
		}

		/**
		 * Returns the state's abbreviation.
		 *
		 * @return the state's abbreviation.
		 */
		public String getAbbreviation() {
			return abbreviation;
		}

		/**
		 * Gets the enum constant with the specified abbreviation.
		 *
		 * @param abbr the state's abbreviation.
		 * @return the enum constant with the specified abbreviation.
		 * @throws SunlightException if the abbreviation is invalid.
		 */
		public static State valueOfAbbreviation(final String abbr) {
			final State state = STATES_BY_ABBR.get(abbr);
			if (state != null) {
				return state;
			} else {
				return UNKNOWN;
			}
		}

		public static State valueOfName(final String name) {
			final String enumName = name.toUpperCase().replaceAll(" ", "_");
			try {
				return valueOf(enumName);
			} catch (final IllegalArgumentException e) {
				return State.UNKNOWN;
			}
		}

		@Override
		public String toString() {
			return name;
		}
	}
}
