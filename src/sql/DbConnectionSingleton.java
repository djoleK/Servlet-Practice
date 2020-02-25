package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import utils.PropertiesHelper;

public class DbConnectionSingleton {

	private static DbConnectionSingleton SINGLE_INSTANCE = null;

	private Connection connection;
	private String url;
	private String username;
	private String password;

	private DbConnectionSingleton() throws SQLException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.err.println("Database Connection Creation Failed" + e.getMessage());
		}
		
		url = PropertiesHelper.getUrl();
		username = PropertiesHelper.getUser();
		password = PropertiesHelper.getPass();
		connection = DriverManager.getConnection(url,username, password);
		
	}

	public static DbConnectionSingleton getInstance() throws SQLException {
		if (SINGLE_INSTANCE == null) {
			synchronized (DbConnectionSingleton.class) {
				if (SINGLE_INSTANCE == null) {
					SINGLE_INSTANCE = new DbConnectionSingleton();
				}
			}
		}
		return SINGLE_INSTANCE;
	}

	public Connection getConnection() {
		return connection;
	}

	public String getUrl() {
		return url;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

}
