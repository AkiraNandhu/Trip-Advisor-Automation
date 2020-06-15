package com.cognizant.tests.testScenario5;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.cognizant.businessFunctionality.CommonFunction;
import com.cognizant.businessFunctionality.DatePicker;
import com.cognizant.pageObjects.FindRentals;
import com.cognizant.pageObjects.HolidayHomes;
import com.cognizant.pageObjects.HomePage;
import com.cognizant.utilities.DriverSetup;
import com.cognizant.utilities.ExcelUtilities;

public class TC51_FetchHolidaysHomesAllFilters extends DriverSetup
{
	CommonFunction commonFunction;
	DatePicker datePicker;
	
	@DataProvider(name="TripAdvisor data")
	public Object[][] getExcelData() throws FileNotFoundException, IOException
	{
		Object[][] data=ExcelUtilities.getExcelData("TripAdvisor_Data");
		return data;
	}

	
	public void searchFor()
	{
		//Choosing holidayhomes option
		testCase.log(Status.INFO, "Clicking Holiday homes");
		commonFunction.click(HomePage.txtboxLocation);
		commonFunction.click(HomePage.tabHolidayHomes);	
	}
	
	public void provideDetails(String locationName,double noOfDays)
	{
		int noOfDay=(int)noOfDays;
		//entering the location name
		commonFunction.setElementValue(FindRentals.txtboxLocationName, locationName);
		
		testCase.log(Status.INFO, "Providing location name,check-in & check-out  dates");
		
		//choosing chechIn and checkOut date
		datePicker.ClickTomorrowCheckInDate();
		
		datePicker.ClickCheckOutDate(noOfDay);

		//click on find rental button to search for holiday homes
		commonFunction.click(FindRentals.btnfindRental);
		
	}
	
	public void choosingSuitability(String suitability)
	{
		WebElement suitabilityElement=null;
		if(suitability.equalsIgnoreCase("Kid friendly"))
			suitabilityElement=HolidayHomes.chkKidSuitability;
		else if(suitability.equalsIgnoreCase("Elder access"))
			suitabilityElement=HolidayHomes.chkElderSuitability;
		else if(suitability.equalsIgnoreCase("Pet friendly"))
			suitabilityElement=HolidayHomes.chkPetSuitability;
	
		commonFunction.click(suitabilityElement);	
			
	}
	
	public void choosingAmenities(String amenities)
	{
		WebElement amenitiesElement=null;
		if(amenities.equalsIgnoreCase("Wi-fi"))
			amenitiesElement=HolidayHomes.chkWifiAmenities;
		else if(amenities.equalsIgnoreCase("Internet"))
			amenitiesElement=HolidayHomes.chkInternetAmenities;
		else if(amenities.equalsIgnoreCase("Elevator/Lift access"))
		{
			WebElement element=driver.findElement(By.className("_3ncH7U-p"));
			JavascriptExecutor jse=(JavascriptExecutor)driver;
			jse.executeScript("arguments[0].scrollIntoView();",element);
			System.out.println("hi........");
			commonFunction.click(HolidayHomes.btnShowMore);
			amenitiesElement=HolidayHomes.chkLiftAmenities;
		}
		
		commonFunction.click(amenitiesElement);
	}
	
	public void checkGuestCount(double guestCount)
	{
		int noOfGuest=(int)guestCount;
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
	
	public void displayDetails() throws InterruptedException
	{
		
		//Display top 5 holiday homes based on specifications(Location,check-in,check-out dates)
		testCase.log(Status.INFO, "Displays holiday homes");
		commonFunction.getHolidayHomeNames(HolidayHomes.lstHolidayHomeNames);
		commonFunction.getHolidayHomeNames(HolidayHomes.lstHolidayHomePrice);

	}
	
	public void choosingSortby()
	{
		commonFunction.click(HolidayHomes.btnSortby);
		commonFunction.click(HolidayHomes.rdoTravellerRating);

	}
	
	
	
	@Test(dataProvider="TripAdvisor data",groups= {"HolidayHomes"})
	public void fetchHolidayHomes(String locationName,String check_in,Double noOfDays,Double guestCount,String sortType,String suitability,String amenities,Double amount) throws InterruptedException 
	{
		testCase=extentReport.createTest(this.getClass().getSimpleName()+" :Fetching Holiday homes with aLl filters");
		PageFactory.initElements(driver, HomePage.class);
		PageFactory.initElements(driver, FindRentals.class);
		PageFactory.initElements(driver, HolidayHomes.class);
		
		commonFunction=new CommonFunction(driver);
		datePicker=new DatePicker(driver);
		
		
		searchFor();
		
		provideDetails(locationName,noOfDays);
		checkGuestCount(guestCount);
	
		choosingSuitability(suitability);
		//choosingSortby();
		choosingAmenities(amenities);
		
		
		displayDetails();
		
	}
	
	

}
