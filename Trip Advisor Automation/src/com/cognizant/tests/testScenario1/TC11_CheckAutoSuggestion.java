package com.cognizant.tests.testScenario1;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.cognizant.businessFunctionality.*;
import com.cognizant.utilities.DriverSetup;

import com.cognizant.pageObjects.*;

/*
 * Test Scenario ID :TS11
 * Test Case ID :TC11
 */

public class TC11_CheckAutoSuggestion extends DriverSetup 
{
	CommonFunction commonFunction;

	public void provideDetails() throws InterruptedException
	{
		//Entering the location name
		
		testCase.log(Status.INFO, "Entering the location name");
		commonFunction.setElementValue(HomePage.txtboxLocation, "Nairobi"); 
		Thread.sleep(4000);

		//click on find rental button to search for holiday homes

		testCase.log(Status.INFO, "Displaying autosuggestion location name");
		commonFunction.click(HomePage.autoSuggestedLocation);
		Thread.sleep(4000);	
		String strLocationName=commonFunction.getElementValue(FindRentals.locationCheck);
		
		Assert.assertEquals(strLocationName,"Nairobi");
		
		if(strLocationName.equalsIgnoreCase("Nairobi"))
			testCase.log(Status.PASS, "Diplayed correct information based on the auto search");
		else
			testCase.log(Status.FAIL, "Diplayed Incorrect information");
		
	}
	
	
	
	@Test(groups= {"SmokeTest"})
	public void fetchHolidayHomes() throws InterruptedException 
	{
		testCase=extentReport.createTest("Verifying autosuggestion location search box");
		PageFactory.initElements(driver, HomePage.class);
		PageFactory.initElements(driver, FindRentals.class);

		commonFunction=new CommonFunction(driver);
		provideDetails();
		
	}


}