package com.cognizant.tests;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.cognizant.businessFunctionality.CommonFunction;
import com.cognizant.businessFunctionality.DatePicker;
import com.cognizant.pageObjects.FindRentals;
import com.cognizant.pageObjects.HolidayHomes;
import com.cognizant.pageObjects.HomePage;
import com.cognizant.utilities.DriverSetup;


public class TC32_GuestCountVerification extends DriverSetup 
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
		
		testCase.log(Status.INFO, "Choosing check-in check-out dates");

		//Choosing check-in check-out date
		datePicker.ClickTomorrowCheckInDate();
		
		datePicker.ClickCheckOutDate(6);
		//click on find rental button to search for holiday homes
		commonFunction.click(FindRentals.btnfindRental);
		
	}
	
	
	
	public void checkGuestCount(int noOfGuest)
	{
		noOfGuest=noOfGuest-2;
		commonFunction.click(HolidayHomes.txtGuest);
		for(int i=1;i<=noOfGuest;i++)
			commonFunction.click(HolidayHomes.btnGuestCount);
		
		System.out.println(commonFunction.getElementValue(HolidayHomes.txtGuest));
	
		
		boolean result=commonFunction.checkingGuest(HolidayHomes.matchSleeps,noOfGuest);
		
		Assert.assertEquals(true, result);
		if(result)
			testCase.log(Status.PASS, "Displayed with corect guest count");
		else
			testCase.log(Status.FAIL, "Mismatch data displayed");


		
	}

	@Test
	public void fetchHolidayHomes() throws InterruptedException
	{
		testCase=extentReport.createTest(this.getClass().getSimpleName()+" :Checking guest count in holiday homes");

		PageFactory.initElements(driver, HomePage.class);
		PageFactory.initElements(driver, FindRentals.class);
		PageFactory.initElements(driver, HolidayHomes.class);
		

		commonFunction=new CommonFunction(driver);
		datePicker=new DatePicker(driver);

		searchFor();
		provideDetails();
		checkGuestCount(4);
		
				
	}

}
