package ebs.database;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Properties;

public class Conn {
	public Connection connection;
	public Statement statement;
	
	String driverClass = "";
	String url = "";
	String username = "";
	String password = "";
	
	public Conn() {
		try {
			
			Properties prop = loadPropertiesFile();

			driverClass = prop.getProperty("MYSQLJDBC.driver");
			url = prop.getProperty("MYSQLJDBC.url");
			username = prop.getProperty("MYSQLJDBC.username");
			password = prop.getProperty("MYSQLJDBC.password");
			
			Class.forName(driverClass);
			connection = DriverManager.getConnection(url, username, password);
			statement = connection.createStatement();
			
			if (connection != null) {
				System.out.println("connection created successfully using properties file");
			}

			else {
				System.out.println(" unable to create connection");
			}
		}catch(Exception e){
			System.out.println(e);
		}
	}
	
	public static Properties loadPropertiesFile() throws Exception {

		Properties prop = new Properties();
		InputStream in = new FileInputStream("config.properties");
		prop.load(in);
		in.close();
		return prop;
	}

}
