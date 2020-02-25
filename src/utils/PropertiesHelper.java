package utils;

import java.io.InputStream;
import java.util.Properties;

public class PropertiesHelper {

	private static String url;
	private static String user;
	private static String pass;

	static {
		Properties props = new Properties();
		try {
			InputStream is = PropertiesHelper.class.getClassLoader().getResourceAsStream("project.properties");
			props.load(is);
			url = props.getProperty("DB_URL");
			user = props.getProperty("DB_USER");
			pass = props.getProperty("DB_PASS");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String getUrl() {
		return url;
	}

	public static String getUser() {
		return user;
	}

	public static String getPass() {
		return pass;
	}

}
