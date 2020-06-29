package base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Base {
	//1) creating reference for common driver
	
	public WebDriver driver;
	public Properties prop;//import properties from "import.java.util"
	
	//2) Creating First method to initialize driver
	public WebDriver init_driver(String browserName) 
	{
		if(browserName.equals("chrome")) 
		{
			System.setProperty("webdriver.chrome.driver", "C:\\New Eclipse Workspace 29th May 2020\\KeyDrivenNaveen\\Drivers\\chromedriver.exe");
			if(prop.getProperty("headless").equals("yes")) 
			{
				//headless mode;
				ChromeOptions options=new ChromeOptions();
				options.addArguments("--headless");
				driver=new ChromeDriver(options);
			}
			else
			{
				driver=new ChromeDriver();
			}
		}
		return driver;
	}
	//3) Creating second method for Properties files
	public Properties init_properties()
	{
		prop=new Properties();
		try {
		FileInputStream ip= new FileInputStream("C:\\New Eclipse Workspace 29th May 2020\\KeyDrivenNaveen\\src\\main\\java\\config\\config.properties");
		prop.load(ip);
		}
		catch(FileNotFoundException e) 
		{
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prop;
	}
}