package tests;

import java.io.IOException;

import org.testng.annotations.Test;

import engine.KeywordEngine;

public class LoginTest {
	//here we have to call the method 'startExecution' from "KeywordEngine.java" class, the below steps are to acccess this method
	public KeywordEngine keyWordEngine;//KeywordEngine class, keyWordEngine reference;
	@Test
	public void loginTest() 
	{
		keyWordEngine= new KeywordEngine();
		keyWordEngine.startExecution("189633_Ashok");
		/*keyWordEngine = new KeywordEngine();
		keyWordEngine.startExecution("login");*/
	}
}