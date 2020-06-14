package com.cognizant.tests;


import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.cognizant.businessFunctionality.*;
import com.cognizant.utilities.DriverSetup;

import com.cognizant.pageObjects.*;

/*
 * Test Scenario ID :TS11
 * Test Case ID :TC14,TC15,TC16
 */

public class TC14_VerifyWithDifferentLocationName extends DriverSetup
{
	
	String[] data= {"Nairobi","Chennai","chn"};
	
	@DataProvider(name="LocationName data")
	public String[] locationDataProvider()
	{
		return data;
	}
	
	
	public void provideDetails(String locationName) throws InterruptedException
	{
		//Entering the location name
		testCase.log(Status.INFO, "Entering the location name");
		//div[@id='HEADING']/h1
		commonFunction.click(HomePage.txtboxLocation);
		commonFunction.click(HomePage.tabHolidayHomes);
			
		commonFunction.setElementValue(FindRentals.txtboxLocationName, locationName);
		commonFunction.click(FindRentals.btnfindRental);
		String strLocationName=commonFunction.getElementValue(HolidayHomes.locationTitle);

		
		boolean status=false;
		if(strLocationName.contains(locationName))
		{
			status=true;
			testCase.log(Status.PASS, "Diplayed correct information based on the  search");
		}
		else
			testCase.log(Status.FAIL, "Diplayed Incorrect information");
		
		Assert.assertEquals(true,status);
	}
	
	public void displayDetails() throws InterruptedException
	{
		String strName = commonFunction.getElementValue(FindRentals.locationName);
		Assert.assertEquals(strName, "Nairobi Houses");
		
		if(strName.equalsIgnoreCase("Nairobi Houses"))
			testCase.log(Status.PASS, "Diplayed correct information based on the searched location");
		else
			testCase.log(Status.FAIL, "Diplayed Incorrect information");
		
		System.out.println("The HolidayHomes corresponding to Nairobi are displayed\n");
		//Display top 5 holiday homes based on specifications(Location,check-in,check-out dates)
		commonFunction.getHolidayHomeNames(HolidayHomes.lstHolidayHomeNames);
		
		
	}
	
	CommonFunction commonFunction;
	
	@Test(dataProvider="LocationName data")
	public void fetchHolidayHomes(String locationName) throws InterruptedException 
	{
		testCase=extentReport.createTest(this.getClass().getSimpleName()+" :Verifying selected location details are displayed");

		PageFactory.initElements(driver, HomePage.class);
		PageFactory.initElements(driver, FindRentals.class);
		PageFactory.initElements(driver, HolidayHomes.class);

		commonFunction=new CommonFunction(driver);
		provideDetails(locationName);
		
		displayDetails();		
	}


}
