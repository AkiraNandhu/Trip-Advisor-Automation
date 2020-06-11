package com.cognizant.tests;



import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.aventstack.extentreports.Status;
import com.cognizant.businessFunctionality.CommonFunction;
import com.cognizant.businessFunctionality.DatePicker;
import com.cognizant.pageObjects.FindRentals;
import com.cognizant.pageObjects.HolidayHomes;
import com.cognizant.pageObjects.HomePage;
import com.cognizant.utilities.DriverSetup;


public class TC21_FetchHolidaysWithDates extends DriverSetup
{
	CommonFunction commonFunction;
	DatePicker datePicker;
	

	
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
		commonFunction.setElementValue(FindRentals.txtboxLocationName, "Nairobi");
		
		testCase.log(Status.INFO, "Providing location name,check-in & check-out  dates");
		
		//choosing chechIn and checkOut date
		datePicker.ClickTomorrowCheckInDate();
		
		datePicker.ClickCheckOutDate(5);

		//click on find rental button to search for holiday homes
		commonFunction.click(FindRentals.btnfindRental);
		
	}
	public void checkDateChosen()
	{
		String data[]=commonFunction.getChosenFilters(HolidayHomes.chosenFilters);
		boolean status=false;
		if (data.length==1)
			status=true;
		System.out.println(" filters :"+status+data.length);
		Assert.assertEquals(true, status);
		
		if(status)
			testCase.log(Status.PASS, "Date  are chosen");
		else
			testCase.log(Status.FAIL, "Dates are not chosen");
	}
	
	
	
	public void displayDetails() 
	{
		
		//Display top 5 holiday homes based on specifications(Location,check-in,check-out dates)
		testCase.log(Status.INFO, "Displays holiday homes");
		commonFunction.getHolidayHomeNames(HolidayHomes.lstHolidayHomeNames);
		
	}
	
	
	
	public void fetchHolidayHomes() 
	{
		testCase=extentReport.createTest("Fetching Holiday homes with check-in & check-out  dates");
		PageFactory.initElements(driver, HomePage.class);
		PageFactory.initElements(driver, FindRentals.class);
		PageFactory.initElements(driver, HolidayHomes.class);
		
		commonFunction=new CommonFunction(driver);
		datePicker=new DatePicker(driver);
		
		System.out.println("I am in test case with dates");
		
		searchFor();
		
		provideDetails();
		
		checkDateChosen();
		
		displayDetails();
		
	}
	
	

}
