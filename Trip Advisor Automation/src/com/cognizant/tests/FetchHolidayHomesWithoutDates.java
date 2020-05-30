package com.cognizant.tests;

import org.openqa.selenium.support.PageFactory;

import org.testng.annotations.Test;

import com.cognizant.businessFunctionality.*;
import com.cognizant.utilities.DriverSetup;

import com.cognizant.pageObjects.*;

/*
 * Test Scenario ID :TS21
 * Test Case ID :TC24
 */

public class FetchHolidayHomesWithoutDates extends DriverSetup
{
	public void searchFor()
	{
		//Choosing holidayhomes option
		commonFunction.click(HomePage.txtboxLocation);
		commonFunction.click(HomePage.tabHolidayHomes);
	}
	
	public void provideDetails()
	{
		//entering the location name
		commonFunction.setElementValue(FindRentals.txtboxLocationName, "Nairobi"); 

		//click on find rental button to search for holiday homes
		commonFunction.click(FindRentals.btnfindRental);
		
	}
	
	public void displayDetails()
	{
		//Display top 5 holiday homes based on specifications(Location,check-in,check-out dates)
		commonFunction.getHolidayHomeNames(HolidayHomes.lstHolidayHomeNames);
	}
	
	CommonFunction commonFunction;
	@Test
	public void fetchHolidayHomes() 
	{
		PageFactory.initElements(driver, HomePage.class);
		PageFactory.initElements(driver, FindRentals.class);
		PageFactory.initElements(driver, HolidayHomes.class);

		commonFunction=new CommonFunction(driver);
		searchFor();
		provideDetails();
		displayDetails();
				
	}

}
