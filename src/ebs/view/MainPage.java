package ebs.view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

public class MainPage extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	String meter;
	String person;
	JMenuBar menuBar;
	JMenu masterMenu;
	
	JMenuItem newCustomer;
	JMenuItem customerDetails;
	JMenuItem depositDetails;
	JMenuItem calculateBill;
	
	JMenu infoMenu;
	JMenuItem updateInfo;
	JMenuItem viewInfo;
	
	JMenu userMenu;
	JMenuItem payBills;
	JMenuItem billDetails;
	
	JMenu reportMenu;
	JMenuItem generateBill;
	
	JMenu utilityMenu;
	JMenuItem calender;
	JMenuItem calculator;
	
	JMenu logoutMenu;
	JMenuItem logoutMenuItem;
    
	public static final Font F_MONO = new Font("monospaced", Font.PLAIN, 12);
	
	MainPage(String meter, String person) {
		super("Electricity Billing System");
		this.meter = meter;
		this.person = person;
		setSize(1920, 1030);
		setUpMainPaiBackground();
		
		setJMenuBar(setUpMenuBar());
		setFont(new Font("Senserif", Font.BOLD, 16));
		setLayout(new FlowLayout());
		setVisible(false);
	}
	
	private void setUpMainPaiBackground() {
		ImageIcon mainPageBackgroundIcon = new ImageIcon(ClassLoader.getSystemResource(""));
		Image mainImageScaled = mainPageBackgroundIcon.getImage().getScaledInstance(1900, 950, Image.SCALE_SMOOTH);
		ImageIcon mainImageIconFinal = new ImageIcon(mainImageScaled);
		JLabel imageLabel = new JLabel(mainImageIconFinal);
		add(imageLabel);	
	}
/*--------------MENU BAR-----------------------*/	
	private JMenuBar setUpMenuBar() {
		menuBar = new JMenuBar();
		if (person.equals("Admin")) {
			menuBar.add(masterMenu());
		}else {
			menuBar.add(informationMenu());
		    menuBar.add(userMenu());
		    menuBar.add(reportMenu());
		}
		menuBar.add(utilityMenu());
		menuBar.add(logoutMenu());
		return menuBar;
	}
/*--------------MASTER MENU-----------------------*/	
	private JMenu masterMenu() {
		masterMenu = new JMenu("Master");
		masterMenu.setForeground(Color.BLUE);
		
		masterMenu.add(newCustomerMenuItem());
		masterMenu.add(customerDetailsMenuItem());
		masterMenu.add(depositDetailsMenuItem());
		masterMenu.add(calculateBillsMenuItem());
		return masterMenu;	
	}

	private JMenuItem newCustomerMenuItem() {
		newCustomer = new JMenuItem("New Customer");
		newCustomer.setFont(F_MONO);
		newCustomer.setBackground(Color.WHITE);	
		setImage(newCustomer, "Icons/signupUser.png");
     	setUpKeys(newCustomer, 'N', KeyEvent.VK_N);
		return newCustomer;	
	}

	private JMenuItem customerDetailsMenuItem() {
		customerDetails = new JMenuItem("Customer Details");
		customerDetails.setFont(F_MONO);
		customerDetails.setBackground(Color.WHITE);	
		setImage(customerDetails, "Icons/signupUser.png");
     	setUpKeys(customerDetails, 'C', KeyEvent.VK_C);	
     	return customerDetails;
	}

	private JMenuItem depositDetailsMenuItem() {
		depositDetails = new JMenuItem("Deposit Details");	
		depositDetails.setFont(F_MONO);
		depositDetails.setBackground(Color.WHITE);	
		setImage(depositDetails, "Icons/signupUser.png");
     	setUpKeys(depositDetails, 'D', KeyEvent.VK_D);
		return depositDetails;
	}

	private JMenuItem calculateBillsMenuItem() {
		calculateBill = new JMenuItem("Calculate Bill");
		calculateBill.setFont(F_MONO);
		calculateBill.setBackground(Color.WHITE);	
		setImage(calculateBill, "Icons/signupUser.png");
     	setUpKeys(calculateBill, 'B', KeyEvent.VK_B);
		return calculateBill;
	}
	
/*--------------INFORMATION MENU-----------------------*/
	private JMenu informationMenu() {
		infoMenu = new JMenu("Information");
		infoMenu.setForeground(Color.RED);
		infoMenu.add(updateInformationMenuItem());
		infoMenu.add(viewInfomationMenuItem());
		return infoMenu;	
	}

	private JMenuItem updateInformationMenuItem() {
		updateInfo = new JMenuItem("Update Information");
		updateInfo.setFont(F_MONO);
		updateInfo.setBackground(Color.WHITE);	
		setImage(updateInfo, "Icons/signupUser.png");
     	setUpKeys(updateInfo, 'U', KeyEvent.VK_U);
		return updateInfo;	
	}

	private JMenuItem viewInfomationMenuItem() {
		viewInfo = new JMenuItem("View Information");
		viewInfo.setFont(F_MONO);
		viewInfo.setBackground(Color.WHITE);	
		setImage(viewInfo, "Icons/signupUser.png");
     	setUpKeys(viewInfo, 'V', KeyEvent.VK_V);
		return viewInfo;	
	}
	
/*--------------USER MENU-----------------------*/
	private JMenu userMenu() {
		userMenu = new JMenu("User");
		userMenu.setForeground(Color.RED);
		userMenu.add(payBillsMenuItem());
		userMenu.add(billDetailsMenuItem());
		return userMenu;		
	}
	
	private JMenuItem payBillsMenuItem() {
		payBills = new JMenuItem("Pay Bills");
		payBills.setFont(F_MONO);
		payBills.setBackground(Color.WHITE);	
		setImage(payBills, "Icons/signupUser.png");
	 	setUpKeys(payBills, 'P', KeyEvent.VK_P);
		return payBills;
		}

	private JMenuItem billDetailsMenuItem() {
		billDetails = new JMenuItem("Bill Details");
		billDetails.setFont(F_MONO);
		billDetails.setBackground(Color.WHITE);	
		setImage(billDetails, "Icons/signupUser.png");
	 	setUpKeys(billDetails, 'L', KeyEvent.VK_L);
		return billDetails;
		}

/*--------------REPORT MENU-----------------------*/
	private JMenu reportMenu() {
		reportMenu = new JMenu("Report");
		reportMenu.setForeground(Color.BLUE);
		reportMenu.add(generateBillMenuItem());
		return reportMenu;		
	}
	private JMenuItem generateBillMenuItem() {
		generateBill = new JMenuItem("Generate Bill");
		generateBill.setFont(F_MONO);
		generateBill.setBackground(Color.WHITE);	
		setImage(generateBill, "Icons/signupUser.png");
	 	setUpKeys(generateBill, 'G', KeyEvent.VK_G);
		return generateBill;
		}

/*--------------UTILITY MENU-----------------------*/
	private JMenu utilityMenu() {
		utilityMenu = new JMenu("Utility");
		utilityMenu.setForeground(Color.RED);
		utilityMenu.add(calenderMenuItem());
		utilityMenu.add(calculatorMenuItem());
		utilityMenu.add(webBrowserMenuItem());
		return utilityMenu;
	}
	
	private JMenuItem calenderMenuItem() {
		calender = new JMenuItem("Calender");
		calender.setFont(F_MONO);
		calender.setBackground(Color.WHITE);	
		setImage(calender, "Icons/signupUser.png");
	 	setUpKeys(calender, 'A', KeyEvent.VK_A);
		return calender;
		}

	private JMenuItem calculatorMenuItem() {
		calculator = new JMenuItem("Calculator");
		calculator.setFont(F_MONO);
		calculator.setBackground(Color.WHITE);	
		setImage(calculator, "Icons/signupUser.png");
	 	setUpKeys(calculator, 'R', KeyEvent.VK_R);
		return calculator;
		}
	
	private JMenuItem webBrowserMenuItem() {
		generateBill = new JMenuItem("Web Browser");
		generateBill.setFont(F_MONO);
		generateBill.setBackground(Color.WHITE);	
		setImage(generateBill, "Icons/signupUser.png");
	 	setUpKeys(generateBill, 'W', KeyEvent.VK_W);
		return generateBill;
		}

	/*--------------Logout MENU-----------------------*/
	private JMenu logoutMenu() {
		logoutMenu = new JMenu("Logout");
		logoutMenu.setForeground(Color.RED);
	    logoutMenu.add(logoutMenuItem());	
		return logoutMenu;	
	}

	private JMenuItem logoutMenuItem() {
		logoutMenuItem = new JMenuItem("Logout");
		logoutMenuItem.setFont(F_MONO);
		logoutMenuItem.setBackground(Color.WHITE);	
		setImage(logoutMenuItem, "Icons/signupUser.png");
	 	setUpKeys(logoutMenuItem, 'W', KeyEvent.VK_W);
		return logoutMenuItem;
	}
	
	public void setImage(JMenuItem menuItem, String imageResource) {
		ImageIcon imageIconRaw= new ImageIcon(ClassLoader.getSystemResource(imageResource));
		Image scaledImage = imageIconRaw.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		menuItem.setIcon(new ImageIcon(scaledImage));
		return;
	}
	
	public void setUpKeys(JMenuItem menuItem, Character Mnemonic, int keyEvent ) {
		menuItem.setMnemonic(Mnemonic);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(keyEvent, ActionEvent.CTRL_MASK));
		return;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		String msg = actionEvent.getActionCommand();
		switch (msg) {
		case "Customer Details":
			new CustomerDetails().setVisible(true);;
			break;
		case "New Customer":
			new NewCustomer().setVisible(true);
			break;
		case "Calculate Bill":
			new CalculateBill().setVisible(true);;
			break;
		case "Pay Bill":
			new PayBill(meter).setVisible(true);;
			break;
		case "Calander":
			try {
				Runtime.getRuntime().exec("notepad.exe");
			} catch (Exception e) {}
			break;
		case "Calculator":
			try {
				Runtime.getRuntime().exec("calc.exe");
			} catch (Exception e) {}
			break;
		case "Web Broswer":
			try {
				Runtime.getRuntime().exec("C:\\Program Files (x86)\\Microsoft\\Edge\\Applicationmsedge.exe");
			} catch (Exception e) {}
			break;
		case "Logout":
			this.setVisible(false);
			break;
		case "Generate Bill":
			new GenerateBill().setVisible(true);
			break;
		case "Deposit Details":
			new DepositDetails().setVisible(true);;
			break;
		case "View Information":
			new ViewInformation(meter).setVisible(true);
			break;
		case "Update Information":
			new UpdateInformation(meter).setVisible(true);;
			break;
		case "Bill Details":
			new BillDetails(meter).setVisible(true);
			break;
		default:
			break;
		}
		
	}	
	

	
	public static void main(String[] args) {
		new MainPage("", "").setVisible(true);

	}

}
