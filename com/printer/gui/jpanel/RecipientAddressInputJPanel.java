package com.printer.gui.jpanel;

import java.awt.Dimension;
import java.awt.Font;
import java.util.HashMap;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import com.printer.gui.listener.update.RecipientUpdateListener;
import com.printer.util.RecipientDataUtil;
import com.printer.window.MainWindow;

public class RecipientAddressInputJPanel extends JPanel {
	
	private static final long serialVersionUID = 4734260666112646901L;
	
	private final Font standardFont = new Font("Tahoma", Font.PLAIN, 13);
	
	private static JTextField textFieldFlName = new JTextField();
	private static JTextField textFieldCOName = new JTextField();
	private static JTextField textFieldAddress_1 = new JTextField();
	private static JTextField textFieldAddress_2 = new JTextField();
	private static JTextField textFieldCity = new JTextField();
	private static JTextField textFieldState = new JTextField();
	private static JTextField textFieldZip = new JTextField();
	
	private static JLabel lblApartmentSuiteUnit = new JLabel("Apartment, suite, unit, building, floor, etc.");
	private static JLabel lblRecipient = new JLabel("Recipient's Address:");
	private static JLabel lblFullName = new JLabel("Full Name:");
	private static JLabel lblCompanyName = new JLabel("Company Name:");
	private static JLabel lblAddressLine_1 = new JLabel("Address Line 1:");
	private static JLabel lblAddressLine_2 = new JLabel("Address Line 2:");
	private static JLabel lblCity = new JLabel("City:");
	private static JLabel lblStateprovinceregion = new JLabel("State/Province/Region:");
	private static JLabel lblZip = new JLabel("ZIP:");
	private static JLabel lblCountry = new JLabel("Country:");
	private static JLabel lblStreetAddressPo = new JLabel("Street address, P.O. box, Company name, c/o");
	private static String[] countries = new String[] { "Don't Add To Envelope", "Afghanistan", "Albania", "Algeria", "American Samoa", "Andorra", "Angola", "Anguilla", "Antarctica", "Antigua and Barbuda", "Argentina", "Armenia", "Aruba", "Australia", "Austria", "Azerbaijan", "Bahamas", "Bahrain", "Bangladesh", "Barbados", "Belarus", "Belgium", "Belize", "Benin", "Bermuda", "Bhutan", "Bolivia", "Bosnia and Herzegowina", "Botswana", "Bouvet Island", "Brazil", "British Indian Ocean Territory", "Brunei Darussalam", "Bulgaria", "Burkina Faso", "Burundi", "Cambodia", "Cameroon", "Canada", "Cape Verde", "Cayman Islands", "Central African Republic", "Chad", "Chile", "China", "Christmas Island", "Cocos (Keeling) Islands", "Colombia", "Comoros", "Congo", "Congo, the Democratic Republic of the", "Cook Islands", "Costa Rica", "Cote d'Ivoire", "Croatia (Hrvatska)", "Cuba", "Cyprus", "Czech Republic", "Denmark", "Djibouti", "Dominica", "Dominican Republic", "East Timor", "Ecuador", "Egypt", "El Salvador", "Equatorial Guinea", "Eritrea", "Estonia", "Ethiopia", "Falkland Islands (Malvinas)", "Faroe Islands", "Fiji", "Finland", "France", "France Metropolitan", "French Guiana", "French Polynesia", "French Southern Territories", "Gabon", "Gambia", "Georgia", "Germany", "Ghana", "Gibraltar", "Greece", "Greenland", "Grenada", "Guadeloupe", "Guam", "Guatemala", "Guinea", "Guinea-Bissau", "Guyana", "Haiti", "Heard and Mc Donald Islands", "Holy See (Vatican City State)", "Honduras", "Hong Kong", "Hungary", "Iceland", "India", "Indonesia", "Iran (Islamic Republic of)", "Iraq", "Ireland", "Israel", "Italy", "Jamaica", "Japan", "Jordan", "Kazakhstan", "Kenya", "Kiribati", "Korea, Democratic People's Republic of", "Korea, Republic of", "Kuwait", "Kyrgyzstan", "Lao, People's Democratic Republic", "Latvia", "Lebanon", "Lesotho", "Liberia", "Libyan Arab Jamahiriya", "Liechtenstein", "Lithuania", "Luxembourg", "Macau", "Macedonia, The Former Yugoslav Republic of", "Madagascar", "Malawi", "Malaysia", "Maldives", "Mali", "Malta", "Marshall Islands", "Martinique", "Mauritania", "Mauritius", "Mayotte", "Mexico", "Micronesia, Federated States of", "Moldova, Republic of", "Monaco", "Mongolia", "Montserrat", "Morocco", "Mozambique", "Myanmar", "Namibia", "Nauru", "Nepal", "Netherlands", "Netherlands Antilles", "New Caledonia", "New Zealand", "Nicaragua", "Niger", "Nigeria", "Niue", "Norfolk Island", "Northern Mariana Islands", "Norway", "Oman", "Pakistan", "Palau", "Panama", "Papua New Guinea", "Paraguay", "Peru", "Philippines", "Pitcairn", "Poland", "Portugal", "Puerto Rico", "Qatar", "Reunion", "Romania", "Russian Federation", "Rwanda", "Saint Kitts and Nevis", "Saint Lucia", "Saint Vincent and the Grenadines", "Samoa", "San Marino", "Sao Tome and Principe", "Saudi Arabia", "Senegal", "Seychelles", "Sierra Leone", "Singapore", "Slovakia (Slovak Republic)", "Slovenia", "Solomon Islands", "Somalia", "South Africa", "South Georgia and the South Sandwich Islands", "Spain", "Sri Lanka", "St. Helena", "St. Pierre and Miquelon", "Sudan", "Suriname", "Svalbard and Jan Mayen Islands", "Swaziland", "Sweden", "Switzerland", "Syrian Arab Republic", "Taiwan, Province of China", "Tajikistan", "Tanzania, United Republic of", "Thailand", "Togo", "Tokelau", "Tonga", "Trinidad and Tobago", "Tunisia", "Turkey", "Turkmenistan", "Turks and Caicos Islands", "Tuvalu", "Uganda", "Ukraine", "United Arab Emirates", "United Kingdom", "United States", "United States Minor Outlying Islands", "Uruguay", "Uzbekistan", "Vanuatu", "Venezuela", "Vietnam", "Virgin Islands (British)", "Virgin Islands (U.S.)", "Wallis and Futuna Islands", "Western Sahara", "Yemen", "Yugoslavia", "Zambia", "Zimbabwe" };
	private static JComboBox<String> comboBoxCountry = new JComboBox<String>(countries);
	
	public RecipientAddressInputJPanel() {
		JScrollPane scrollPane = new JScrollPane();
		RecipientUpdateListener recipListener = new RecipientUpdateListener();
		this.setPreferredSize(new Dimension(790, 347));
		scrollPane.setViewportView(this);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		this.setLayout(null);
		
		lblRecipient.setFont(standardFont);
		lblRecipient.setBounds(10, 10, 178, 22);
		this.add(lblRecipient);
		
		lblFullName.setFont(standardFont);
		lblFullName.setBounds(158 - 69, 50, 69, 16);
		this.add(lblFullName);
		
		textFieldFlName.setBounds(160, 48, 235, 22);
		textFieldFlName.setColumns(10);
		textFieldFlName.setName("FullName");
		textFieldFlName.getDocument().addDocumentListener(recipListener);
		this.add(textFieldFlName);
		
		lblCompanyName.setFont(standardFont);
		lblCompanyName.setBounds(400, 50, 137, 16);
		this.add(lblCompanyName);
		
		textFieldCOName.setBounds(515, 48, 235, 22);
		textFieldCOName.setColumns(10);
		textFieldCOName.setName("CompanyName");
		textFieldCOName.getDocument().addDocumentListener(recipListener);
		this.add(textFieldCOName);
		
		lblAddressLine_1.setFont(standardFont);
		lblAddressLine_1.setBounds(158 - 100, 85, 100, 16);
		this.add(lblAddressLine_1);
		
		textFieldAddress_1.setColumns(10);
		textFieldAddress_1.setBounds(160, 83, 551, 22);
		textFieldAddress_1.setName("Address1");
		textFieldAddress_1.getDocument().addDocumentListener(recipListener);
		this.add(textFieldAddress_1);
		
		lblAddressLine_2.setFont(standardFont);
		lblAddressLine_2.setBounds(158 - 100, 135, 100, 16);
		this.add(lblAddressLine_2);
		
		textFieldAddress_2.setColumns(10);
		textFieldAddress_2.setBounds(160, 133, 551, 22);
		textFieldAddress_2.setName("Address2");
		textFieldAddress_2.getDocument().addDocumentListener(recipListener);
		this.add(textFieldAddress_2);
		
		lblCity.setFont(standardFont);
		lblCity.setBounds(158 - 29, 184, 29, 16);
		this.add(lblCity);
		
		textFieldCity.setColumns(10);
		textFieldCity.setBounds(160, 182, 213, 22);
		textFieldCity.setName("City");
		textFieldCity.getDocument().addDocumentListener(recipListener);
		this.add(textFieldCity);
		
		lblStateprovinceregion.setFont(standardFont);
		lblStateprovinceregion.setBounds(158 - 149, 219, 149, 16);
		this.add(lblStateprovinceregion);
		
		textFieldState.setColumns(10);
		textFieldState.setBounds(160, 217, 187, 22);
		textFieldState.setName("State");
		textFieldState.getDocument().addDocumentListener(recipListener);
		this.add(textFieldState);
		
		lblZip.setFont(standardFont);
		lblZip.setBounds(158 - 27, 254, 27, 16);
		this.add(lblZip);
		
		textFieldZip.setColumns(10);
		textFieldZip.setBounds(160, 252, 142, 22);
		textFieldZip.setName("Zip");
		textFieldZip.getDocument().addDocumentListener(recipListener);
		this.add(textFieldZip);
		
		lblCountry.setFont(standardFont);
		lblCountry.setBounds(158 - 56, 290, 56, 16);
		this.add(lblCountry);
		
		comboBoxCountry.setBounds(160, 288, 300, 22);
		comboBoxCountry.setSelectedItem("Don't Add To Envelope");
		comboBoxCountry.setName("Country");
		comboBoxCountry.addActionListener(recipListener);
		this.add(comboBoxCountry);
		
		lblStreetAddressPo.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblStreetAddressPo.setBounds(160, 104, 268, 16);
		this.add(lblStreetAddressPo);
		
		lblApartmentSuiteUnit.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblApartmentSuiteUnit.setBounds(160, 155, 233, 16);
		this.add(lblApartmentSuiteUnit);
	}
	
	public static void update() {
		MainWindow mainGui = MainWindow.getMainGui();
		HashMap<String, String> data = RecipientDataUtil.dataList.get(mainGui.table.getSelectedRow());
		
		textFieldFlName.setText(data.get("FullName"));
		textFieldCOName.setText(data.get("CompanyName"));
		textFieldAddress_1.setText(data.get("Address1"));
		textFieldAddress_2.setText(data.get("Address2"));
		textFieldCity.setText(data.get("City"));
		textFieldState.setText(data.get("State"));
		textFieldZip.setText(data.get("Zip"));
		
		if (data.get("Country") == null)
			comboBoxCountry.setSelectedItem("Don't Add To Envelope");
		else
			comboBoxCountry.setSelectedItem(data.get("Country"));
	}
}
