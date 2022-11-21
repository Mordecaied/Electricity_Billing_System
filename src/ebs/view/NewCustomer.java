package ebs.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import ebs.database.Conn;

public class NewCustomer extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	JPanel newCustomerPanel;
	JLabel nameLabel;
	JLabel meterLabel;
	JLabel addressLabel;
	JLabel stateLabel;
	JLabel cityLabel;
	JLabel emailLabel;
	JLabel phoneNumberLabel;
	JLabel newCustomerImageLabel;
	JLabel generateMeterNumberJLabel;
	
	JTextField nameTF;
	JTextField meterTF;
	JTextField addressTF;
	JTextField stateTF;
	JTextField cityTF;
	JTextField emailTF;
	JTextField phoneNumberTF;
	
	JButton nextButton;
	JButton cancelButton;	
	
	public NewCustomer() {
		setLocation(600, 200);
		setSize(700, 600);
        setLayout(new BorderLayout());
        
		add(getNewCustomerPanel(),"Center");
		add(getNewCustomerImage(),"West");
		getContentPane().setBackground(Color.WHITE);
		
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
	
	public JPanel getNewCustomerPanel() {
		newCustomerPanel = new JPanel();
		newCustomerPanel.setLayout(null);
		newCustomerPanel.setBackground(Color.WHITE);
		newCustomerPanel.setBackground( new Color(152,0,204));
		
		getNewCustomerLabel();
		getNameField();
        getMeter();
        getAddress();
        getStateField();
        getCity();
        getEmail();
        getPhoneNumber();
        getNextButton();
        getCancelButton();
		return newCustomerPanel;
	}
	
	public void getNewCustomerLabel() {
		JLabel title = new JLabel("New Customer");
		title.setBounds(180, 10, 200, 26);
		title.setFont(new Font("Tahoma", Font.PLAIN, 24));
	    newCustomerPanel.add(title);	

	}
	
	public void getNameField() {
		nameLabel = new JLabel("Customer Name");
		nameLabel.setBounds(100, 80, 100, 20);
	    newCustomerPanel.add(nameLabel);
	    
	    nameTF = new JTextField();
	    nameTF.setBounds(240, 80, 200, 20);
	    newCustomerPanel.add(nameTF);  
	}

	public void getMeter() {
		meterLabel = new JLabel("Meter No");
		meterLabel.setBounds(100, 120, 100, 20);
		newCustomerPanel.add(meterLabel);
	    
	    meterTF = new JTextField();
	    meterTF.setBounds(240, 120, 200, 20);
	    newCustomerPanel.add(meterTF); 
	    generateMeterNumber();
	}

	public void getAddress() {
		addressLabel = new JLabel("Address");
		addressLabel.setBounds(100, 160, 100, 20);
		newCustomerPanel.add(addressLabel);
	    
	    addressTF = new JTextField();
	    addressTF.setBounds(240, 160, 200, 20);
	    newCustomerPanel.add(addressTF); 
	}
	
    public void getCity() {
		cityLabel = new JLabel("City");
		cityLabel.setBounds(100, 200, 100, 20);
		newCustomerPanel.add(cityLabel);
	    
	    cityTF = new JTextField();
	    cityTF.setBounds(240, 200, 200, 20);
	    newCustomerPanel.add(cityTF); 
	}
	public void getStateField() {	
		stateLabel = new JLabel("State");
		stateLabel.setBounds(100, 240, 100, 20);
		newCustomerPanel.add(stateLabel);
	    
	    stateTF = new JTextField();
	    stateTF.setBounds(240, 240, 200, 20);
	    newCustomerPanel.add(stateTF); 
	}
	public void getEmail() {
		emailLabel = new JLabel("Email");
		emailLabel.setBounds(100, 280, 100, 20);
		newCustomerPanel.add(emailLabel);
	    
	    emailTF = new JTextField();
	    emailTF.setBounds(240, 280, 200, 20);
	    newCustomerPanel.add(emailTF); 
	}
	public void getPhoneNumber() {
		phoneNumberLabel = new JLabel("Phone Number");
		phoneNumberLabel.setBounds(100, 320, 100, 20);
		newCustomerPanel.add(phoneNumberLabel);
	    
	    phoneNumberTF = new JTextField();
	    phoneNumberTF.setBounds(240, 320, 200, 20);
	    newCustomerPanel.add(phoneNumberTF); 
	}
	public void getNextButton() {
		nextButton = new JButton("Next");
		nextButton.setBounds(170, 390, 100, 25);
		nextButton.setBackground(Color.BLACK);
		nextButton.setForeground(Color.WHITE);
		nextButton.addActionListener(this);
		newCustomerPanel.add(nextButton);
	}
	public void getCancelButton() {
		cancelButton = new JButton("Cancel");
		cancelButton.setBounds(300, 390, 100, 25);
		cancelButton.setBackground(Color.BLACK);
		cancelButton.setForeground(Color.WHITE);
		cancelButton.addActionListener(this);
		newCustomerPanel.add(cancelButton);	
	}
	public JLabel getNewCustomerImage() {
		ImageIcon imageIconRaw= new ImageIcon(ClassLoader.getSystemResource("Icons/signupUser.png"));
		Image scaledImage = imageIconRaw.getImage().getScaledInstance(150, 300, Image.SCALE_SMOOTH);
		ImageIcon finalImageIcon = new ImageIcon(scaledImage);
		return newCustomerImageLabel = new JLabel(finalImageIcon);
	}
	
	public JTextField generateMeterNumber() {
		Random ran = new Random();
		long first = (ran.nextLong() % 1000000);
		meterTF.setText("" + Math.abs(first));
		return meterTF;
	}

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		if (actionEvent.getSource() == nextButton) {
			String name = nameTF.getText();
			String meter = meterTF.getText();
			String address = addressTF.getText();
			String state = stateTF.getText();
			String city = cityTF.getText();
			String email = emailTF.getText();
			String phone = phoneNumberTF.getText();
			
			String customerQuery = "insert into customer values('"+name+"','"+meter+"','"+address+"','"+city+"','"+state+"','"+email+"','"+phone+"')";
			String loginQuery = "insert into login values('"+meter+"', '', '', '', '')";
			try {
				Conn con = new Conn();
				con.statement.executeUpdate(customerQuery);
				con.statement.executeUpdate(loginQuery);
				JOptionPane.showMessageDialog(null, "Custmer Details added succesfully");
				new MeterInfo(meter).setVisible(true);
				
			} catch (Exception ex) {
				ex.printStackTrace();
				}
			}else if (actionEvent.getSource() == cancelButton) {
				this.setVisible(false);
			}
		
	}

	public static void main(String[] args) {
		new NewCustomer().setVisible(true);
	}


}
