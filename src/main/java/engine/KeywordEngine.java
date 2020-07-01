package engine;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import base.Base;

public class KeywordEngine {
	
	public Base base;
	public WebDriver driver;
	public Properties prop;
	public WebElement element;

	public static Workbook book;//this is an Interface, book is reference
	
	public static Sheet sheet; //this is should be imported from poi.ss.usermodel
	
	public final String SCENARIO_SHEET_PATH = "C:\\New Eclipse Workspace 29th May 2020\\KeyDrivenNaveen\\src\\main\\java\\scenarios\\R82020.xlsx";
	
	public void startExecution(String sheetName) 
	{
		//Now Interaction with Excel Sheet. 1. Workbook 2. Sheet
		/*String locatorName=null;
		String locatorValue=null;*/
		FileInputStream file=null;
		
		//Dont use throws declaration, instead, use surround by try catch.
		try {
			file=new FileInputStream(SCENARIO_SHEET_PATH);
/*			FileInputStream file1=new FileInputStream(new File(SCENARIO_SHEET_PATH));*/
		//Now, WorkbookFactory;
		}
		catch(FileNotFoundException e) 
		{
			e.printStackTrace();
			}
		try {
			book=WorkbookFactory.create(file);
			/*XSSFWorkbook wb=new XSSFWorkbook(file);*/
		} catch (IOException e) {
			e.printStackTrace();
		}
		sheet=book.getSheet(sheetName);
		//Now, we have to read the excel. For this, we use For Loop
		int k=0;//This k is generated for Cell, k=0 is pointing Column A in the workbook
		for(int i=0; i<sheet.getLastRowNum(); i++) 
		{
			try {
				String locatorType = sheet.getRow(i + 1).getCell(k + 1).toString().trim();
				String locatorValue = sheet.getRow(i + 1).getCell(k + 2).toString().trim();
				String action = sheet.getRow(i + 1).getCell(k + 3).toString().trim();
				String value = sheet.getRow(i + 1).getCell(k + 4).toString().trim();		//this is for 'METHODS Column in the sheet'
				//Once we read all the column values, now we have to write Switch Statement as below
				//this is very important in keyword driven
				//First Switch Statement:
				switch (action) {
				//case 1
				case "openBrowser":
					//here we are going to access open browser method from Base class
					/*what to do access method from Base Class;
					1. First create one reference in class level;
					public Base base;//here it will ask to import from Base, just import
					2. Create one object for the base:
					base =new Base();
					3. Now we can access the method from Base Class
					base.*/
					base = new Base();
					
					prop=base.init_properties();
					
					if(value.isEmpty()||value.equals("NA"))
					{
					driver=base.init_driver(prop.getProperty("browser"));
					Thread.sleep(2000);
					}
					else 
					{
						driver=base.init_driver(value);
						Thread.sleep(2000);
					}
					break;
				//Now next row
				//case 2:	
				case "openUrl":
					if(value.isEmpty()||value.equals("NA")) 
					{
						driver.get(prop.getProperty("url"));
						Thread.sleep(2000);
					}
					else 
					{
						driver.get(value);
						Thread.sleep(3000);
					}
					break;
				//case 3:
				case "quit":
				driver.quit();
				/*break;*/
				default://if no case is written, by default it will come to this.
				break;
				}
		//-----------------------------------------------------------------------------
	   //-----------------------------------------------------------------------------
		//Second Switch Statement:
				
				switch (locatorType) {
				case "id":
					element=driver.findElement(By.id(locatorValue));
					
					if(action.equalsIgnoreCase("sendKeys")) 
					{
						element.clear();
						element.sendKeys(value);
						Thread.sleep(3000);
					}else if(action.equalsIgnoreCase("click")) 
					{
						element.click();
						Thread.sleep(3000);
					}
/*					else if(action.equalsIgnoreCase("click")) 
					{
						element.click();
						Thread.sleep(3000);
					}
*/					locatorType=null;
					break;
					
				case "xpath":
					element=driver.findElement(By.xpath(locatorValue));
					
					if(action.equalsIgnoreCase("click")) 
					{
						/*element.clear();*/
						element.click();
						Thread.sleep(3000);
					}
					else if(action.equalsIgnoreCase("sendKeys")) 
					{
						element.clear();
						element.sendKeys(value);
						Thread.sleep(3000);
					}
					/*else if(action.equalsIgnoreCase("click")) 
					{
						element.clear();
						element.click();
						Thread.sleep(3000);
					}*/
					locatorType=null;
					break;
					
					/*case "linkText":
					element=driver.findElement(By.linkText(locatorValue));
					element.click();
					locatorName=null;
					break;*/
					default:
					break;
		//startExecution
				}
		}//I HAVE GIVEN TRY CATCH FOR THE WHOLE BLOCK, BUT IT IS SHOWING ERROR SIR
		
		catch(Exception e) 
		{
			
		}
	}
	}
}