package com.cognizant.tests;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.cognizant.businessFunctionality.CommonFunction;
import com.cognizant.pageObjects.FindRentals;
import com.cognizant.pageObjects.HolidayHomes;
import com.cognizant.pageObjects.HomePage;
import com.cognizant.utilities.DriverSetup;

public class TC31_CheckGuestButton extends DriverSetup 
{
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
		//click on find rental button to search for holiday homes
		commonFunction.click(FindRentals.btnfindRental);
		
	}
	public void checkCount() 
	{
		testCase.log(Status.INFO, "Checking Guestcount button");

		commonFunction.click(HolidayHomes.txtGuest);
		Boolean test=commonFunction.checkElement(HolidayHomes.btnGuestCount);
		System.out.print(test);
	}
	
	
	CommonFunction commonFunction;
	@Test
	public void fetchHolidayHomes()
	{
		testCase=extentReport.createTest(this.getClass().getSimpleName()+" :Checking Guest Count button");

		PageFactory.initElements(driver, HomePage.class);
		PageFactory.initElements(driver, FindRentals.class);
		PageFactory.initElements(driver, HolidayHomes.class);
		

		commonFunction=new CommonFunction(driver);
		searchFor();
		provideDetails();
		checkCount();
				
	}


}
