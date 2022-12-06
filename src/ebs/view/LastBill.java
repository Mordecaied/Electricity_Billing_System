package ebs.view;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import ebs.database.Conn;


public class LastBill extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JLabel generateBillLabel;
	private JTextArea generateTA;
	private JButton generateButton;
	private Choice chooseMeter;
	private JPanel generateBillJPanel;


	public LastBill() {
		setSize(500, 900);
		setLocation(350, 40);
		setLayout(new BorderLayout());

		getBillPanel();
		getGenerateBill();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}


	private void getBillPanel() {
		generateBillJPanel = new JPanel();
		generateBillLabel = new JLabel("Generate Bill");


		generateBillJPanel.add(getChooseMonth());
		generateBillJPanel.add(generateBillLabel);
		add(generateBillJPanel, "North");
	}

	public Choice getChooseMonth() {
		chooseMeter = new Choice();
		chooseMeter.add("1001");
		chooseMeter.add("1002");
		chooseMeter.add("1003");
		chooseMeter.add("1004");
		chooseMeter.add("1005");
		chooseMeter.add("1006");
		chooseMeter.add("1007");
		chooseMeter.add("1008");
		chooseMeter.add("1009");
		chooseMeter.add("1010");

		return chooseMeter;
	}

	public void getGenerateBill(){
		//TODO add year chosen
		generateTA = new JTextArea(50,15);
		JScrollPane sp = new JScrollPane(generateTA);
		generateTA.setFont(new Font("Senserif", Font.ITALIC, 18));
		add(sp, "Center");

		generateButton = new JButton("Generate Bill");
		generateButton.addActionListener(this);
		add(generateButton, "South");
	}
	@Override
	public void actionPerformed(ActionEvent ae) {
		try {
			Conn conn = new Conn();
			String query = "SELECT * FROM emp WHERE meter_number = ?";
			PreparedStatement pstmt = conn.connection.prepareStatement(query);
			pstmt.setString(1, chooseMeter.getSelectedItem());
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				generateTA.append("\n   Customer Name:"+rs.getString("name"));
				generateTA.append("\n   Meter Number:  "+rs.getString("meter"));
				generateTA.append("\n   Address:            "+rs.getString("address"));
				generateTA.append("\n   State:                 "+rs.getString("state"));
				generateTA.append("\n   City:                   "+rs.getString("city"));
				generateTA.append("\n   Email:                "+rs.getString("email"));
				generateTA.append("\n   Phone Number: "+rs.getString("phone"));
				generateTA.append("\n---------------------------------------------------------------");
				generateTA.append("\n");

			}

			generateTA.append("Details of the Last Bills\n\n\n");
			query = "SELECT * FROM bill WHERE meter_number= ?";
			pstmt.setString(1, chooseMeter.getSelectedItem());
			rs = pstmt.executeQuery();

			while (rs.next()) {
				generateTA.append("        "+rs.getString("month") + "           " + rs.getString("amount") + "\n");

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	public static void main(String[] args) {
		new LastBill().setVisible(true);
	}
}
