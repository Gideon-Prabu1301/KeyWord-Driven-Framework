package tests;

import org.testng.annotations.Test;

public class DummyParent {
	
	public DummyChild instance= new DummyChild();
	
	@Test
	public void compareHere() 
	{
		String name2="WilliamPowel";
		String otherClass= instance.compareHere();
		System.out.println("otherClass is: "+otherClass);
		
		if(name2.equals(otherClass)) 
		{
			System.out.println("PASS");
		}
		else
		{
			System.out.println("FAIL");
		}
	}

}
