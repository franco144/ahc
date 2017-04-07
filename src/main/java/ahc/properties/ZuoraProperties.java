package ahc.properties;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ZuoraProperties {
	private final String propertyFilename = "service.properties";
	
	public ZuoraProperties() {
		load();
	}
	
	public void load() {
		// open the file
		try (BufferedReader reader = new BufferedReader(new FileReader(propertyFilename) )){
			String line = null;
			
			while( (line = reader.readLine()) != null  ) {
				if(line.startsWith("#"))
					continue;

				parseLine(line);
			}
		} catch (IOException e1) {
			e1.printStackTrace();
			System.out.println("Expecting file "+ propertyFilename);
			return;
		}
	}
	
	public String getRestUser() {
		return restUser;
	}

	public String getRestPassword() {
		return restPassword;
	}

	private void parseLine(String line) {
		//i.e. name_property = value
		if(line.contains("user"))
			restUser = line.split("=")[1].trim();
		if(line.contains("password"))
			restPassword = line.split("=")[1].trim();
	}

	private String restUser;
	private String restPassword;
}
