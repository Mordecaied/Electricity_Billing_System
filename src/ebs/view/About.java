package ebs.view;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Scrollbar;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class About extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	JButton exitButton;
	JLabel aboutL;
	TextArea aboutTA;
	String aboutDesc;
	Font F= new Font("RALEWAY", Font.BOLD, 180);
	Font F1= new Font("RALEWAY", Font.BOLD, 16);
	Font F2= new Font("RALEWAY", Font.BOLD, 20);
	Container contentPane; 
	
	
	public About(){
		
		setLayout(null);
		buttonExit();
		setFont(F);	
	    createAboutArea();
	    contentPane= this.getContentPane();
	    aboutTA = new TextArea();
	    setBounds(700, 220, 500, 550);
	    setLayout(null);
	    setVisible(true);
	    
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void buttonExit() {
		exitButton = new JButton("Exit");
		add(exitButton);
		exitButton.setBounds(180, 430, 120, 20);
		exitButton.addActionListener(this);
	}
	
	public void createAboutArea() {
		aboutDesc = "                                About Projects                 \n  "
				+   "\nElectricity Billing System is a software based application "
				+   "Developed in Java programming language. The programs aims to serve "
				+   "the department of electricity by computerizing the billing system. "
				+   "It mainly focuses on the calculation of units consumed during the "
				+   "specified time and the money to be paid to electricity offices. "
				+   "This computerized system will make the overall billing system easy, "
				+   "accessible, comfortable and effective for consumers.\n\n";
		
		aboutTA = new TextArea(aboutDesc, 10, 40, Scrollbar.VERTICAL);
		aboutTA.setEditable(false);
		aboutTA.setBounds(20, 100, 450, 300);
		add(aboutTA);
		aboutTA.setFont(F1);
		
	    
	    aboutL = new JLabel("About Project");
	    add(aboutL);
	    aboutL.setBounds(170, 10, 180, 80);
	    aboutL.setForeground(Color.red);
	    aboutL.setFont(F2);
	}
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		dispose();
		
	}

	public static void main(String[] args) {
		new About().setVisible(true);

	}

	
}
