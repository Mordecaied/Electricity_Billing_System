package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Conn {
	public Connection connection;
	public Statement statement;
	public Conn() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql:///ebs","root","wegoREAL23@@@");
			statement = connection.createStatement();
		}catch(Exception e){
			System.out.println(e);
		}
	}

}
