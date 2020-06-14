package com.cognizant.tests;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.cognizant.businessFunctionality.CommonFunction;
import com.cognizant.businessFunctionality.DatePicker;
import com.cognizant.pageObjects.FindRentals;
import com.cognizant.pageObjects.HolidayHomes;
import com.cognizant.pageObjects.HomePage;
import com.cognizant.utilities.DriverSetup;

public class TC41_SortByFilter extends DriverSetup
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
		testCase.log(Status.INFO, "Providing location name");

		commonFunction.setElementValue(FindRentals.txtboxLocationName, "Nairobi"); 
		
		//Choosing check-in check-out date
		datePicker.ClickTomorrowCheckInDate();
		
		datePicker.ClickCheckOutDate(6);
		//click on find rental button to search for holiday homes
		commonFunction.click(FindRentals.btnfindRental);
		
	}
	
	public void choosingSortby()
	{
		testCase.log(Status.INFO, "Selecting sortby feature");

		commonFunction.click(HolidayHomes.btnSortby);
		commonFunction.click(HolidayHomes.rdoTravellerRating);
		testCase.log(Status.INFO, "sort by traveller rating is choosen succesfully ");

		
	}
	
	@Test
	public void fetchHolidayHomes()
	{
		testCase=extentReport.createTest(this.getClass().getSimpleName()+" :Checking Sortby Filter option");

		PageFactory.initElements(driver, HomePage.class);
		PageFactory.initElements(driver, FindRentals.class);
		PageFactory.initElements(driver, HolidayHomes.class);
		

		commonFunction=new CommonFunction(driver);
		datePicker=new DatePicker(driver);
		searchFor();
		provideDetails();
		choosingSortby();
		
				
	}

}
