package com.cognizant.tests.testScenario2;

import org.openqa.selenium.support.PageFactory;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.cognizant.businessFunctionality.*;
import com.cognizant.utilities.DriverSetup;

import com.cognizant.pageObjects.*;

/*
 * Test Scenario ID :TS21
 * Test Case ID :TC22
 */


public class TC22_FetchHolidayHomesWithoutDates extends DriverSetup
{
	CommonFunction commonFunction;
	
	
	public void searchFor()
	{
		//Choosing holidayhomes option
		testCase.log(Status.INFO, "Clicking Holiday homes");

		commonFunction.click(HomePage.txtboxLocation);
		commonFunction.click(HomePage.tabHolidayHomes);
	}
	
	public void provideDetails()
	{
		//entering the location name
		testCase.log(Status.INFO, "Providing location details");

		commonFunction.setElementValue(FindRentals.txtboxLocationName, "Nairobi"); 

		//click on find rental button to search for holiday homes
		commonFunction.click(FindRentals.btnfindRental);
		
	}
	
	public void displayDetails()
	{
		//Display top 5 holiday homes based on specifications(Location,check-in,check-out dates)
		testCase.log(Status.INFO, "Displays holiday homes");

		commonFunction.getHolidayHomeNames(HolidayHomes.lstHolidayHomeNames);
	}
	
	
	
	@Test(groups= {"HolidayHomes"})
	public void fetchHolidayHomes() throws InterruptedException 
	{
		System.out.println("Fetching Holiday homes without dates");
		PageFactory.initElements(driver, HomePage.class);
		PageFactory.initElements(driver, FindRentals.class);
		PageFactory.initElements(driver, HolidayHomes.class);
		
		testCase=extentReport.createTest("Fetching Holiday homes without dates");

		commonFunction=new CommonFunction(driver);
		
		searchFor();
		
		provideDetails();
		
		displayDetails();
		
		
	}

}
