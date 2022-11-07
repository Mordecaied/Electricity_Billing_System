package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class testConnection {

		static Connection connection;
		static Statement statement;
		
		public testConnection() {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				connection = DriverManager.getConnection("jdbc:mysql:///ebs","root","wegoREAL23@@@");
				statement = connection.createStatement();
			}catch(Exception e){
				System.out.println(e);
			}
		}
		
		public static void testIt() {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				connection = DriverManager.getConnection("jdbc:mysql:///ebs","root","wegoREAL23@@@");
				statement = connection.createStatement();
			}catch(Exception e){
				System.out.println(e);
			}
			System.out.println("Connection succesfull");
		}


	public static void main(String[] args) {
		testIt();

	}

}
