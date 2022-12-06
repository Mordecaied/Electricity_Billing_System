package ebs.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

public class Paytm extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private String meter;
	private JButton backButton;
	JEditorPane editor;

	public Paytm(String meter) {
		this.meter = meter;
		editor = new JEditorPane();
		editor.setEditable(false);

		getBackButton();
		getEditor();
	}

	public JButton getBackButton() {
		backButton = new JButton("Back");
		backButton.setBackground(Color.BLACK);
		backButton.setForeground(Color.WHITE);
		backButton.setBounds(500, 20, 120, 25);
		backButton.addActionListener(this);
		editor.add(backButton);
		return backButton;
	}
	
	public void getEditor() {
		try {
			editor.setPage("https://paytm.com/electricity-bill-payment");
		} catch (Exception e) {
			editor.setContentType("text/html");
			editor.setText("<html>Could not Load</html>");
		}
		
		JScrollPane sp = new JScrollPane();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().add(sp);
		setPreferredSize(new Dimension(800,600));
		setSize(800,800);
		setLocation(250, 120);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.setVisible(false);
		new PayBill(meter).setVisible(true);

	}
	public static void main(String[] args) {
		new Paytm("").setVisible(true);

	}



}
