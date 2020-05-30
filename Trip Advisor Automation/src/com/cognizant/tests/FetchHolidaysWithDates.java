package com.cognizant.tests;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.cognizant.businessFunctionality.CommonFunction;
import com.cognizant.businessFunctionality.DatePicker;
import com.cognizant.pageObjects.FindRentals;
import com.cognizant.pageObjects.HolidayHomes;
import com.cognizant.pageObjects.HomePage;
import com.cognizant.utilities.DriverSetup;

public class FetchHolidaysWithDates extends DriverSetup
{
	CommonFunction commonFunction;
	DatePicker datePicker;

	
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
		
		//choosing chechIn and checkOut date
		datePicker.ClickTomorrowCheckInDate();
		datePicker.ClickCheckOutDate();

		//click on find rental button to search for holiday homes
		commonFunction.click(FindRentals.btnfindRental);
		
	}
	
	public void displayDetails()
	{
		//Display top 5 holiday homes based on specifications(Location,check-in,check-out dates)
		commonFunction.getHolidayHomeNames(HolidayHomes.lstHolidayHomeNames);
		
	}
	
	
	@Test
	public void fetchHolidayHomes() 
	{
		PageFactory.initElements(driver, HomePage.class);
		PageFactory.initElements(driver, FindRentals.class);
		PageFactory.initElements(driver, HolidayHomes.class);
		
		System.out.println("I am in test case with dates");

		commonFunction=new CommonFunction(driver);
		datePicker=new DatePicker(driver);
		
		searchFor();
		provideDetails();
		displayDetails();
		
		
	}
	
	

}
