package context;

import java.util.Properties;
import java.util.Random;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.InputStream;
public class TestBaseAPI {
	protected String server = LoadProperties().getProperty("url");
	protected String accessToken = LoadProperties().getProperty("token");
	
	protected static RequestSpecification req_spec = null;
	protected static Response resp = null;
	public Properties LoadProperties() {
		try {
		InputStream inStream = getClass().getClassLoader().getResourceAsStream("config.properties");
		Properties prop = new Properties();		
		prop.load(inStream);		
		return prop;
		} catch (Exception e) {
			System.out.println("File Not Found Exception thrown for config.properties file.");
			return null;
		}
		
}
	//To get random Key
		public String GetRandomString(int n) {
			// lower limit for LowerCase Letters 
			int lowerLimit = 97; 

			// lower limit for LowerCase Letters 
			int upperLimit = 122; 

			Random random = new Random(); 

			// Create a StringBuffer to store the result 
			StringBuffer r = new StringBuffer(n); 

			for (int i = 0; i < n; i++) { 

				// take a random value between 97 and 122 
				int nextRandomChar = lowerLimit 
						+ (int)(random.nextFloat() 
								* (upperLimit - lowerLimit + 1)); 

				// append a character at the end of bs 
				r.append((char)nextRandomChar); 
			} 

			// return the resultant string 
			return r.toString(); 
		} 
}