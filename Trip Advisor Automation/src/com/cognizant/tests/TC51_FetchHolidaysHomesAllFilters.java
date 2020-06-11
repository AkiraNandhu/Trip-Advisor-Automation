package com.cognizant.tests;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
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
		
		/*System.out.println("loc :"+data[1][0].getClass());
		System.out.println("c in :"+data[1][1].getClass());
		System.out.println("days :"+data[1][2].getClass());
		System.out.println("gues :"+data[1][3].getClass());

		System.out.println("sort :"+data[1][4].getClass());

		System.out.println("sui :"+data[1][5].getClass());
		System.out.println("ame :"+data[1][6].getClass());
		System.out.println("amount :"+data[1][7].getClass());
		
		System.out.println("From driver :"+data[3][3]);*/
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
	
	public void displayDetails() throws InterruptedException
	{
		
		//Display top 5 holiday homes based on specifications(Location,check-in,check-out dates)
		testCase.log(Status.INFO, "Displays holiday homes");
		commonFunction.getHolidayHomeNames(HolidayHomes.lstHolidayHomeNames);
		
	}
	
	public void choosingSortby()
	{
		commonFunction.click(HolidayHomes.btnSortby);
		commonFunction.click(HolidayHomes.rdoTravellerRating);

	}
	
	
	
	@Test(dataProvider="TripAdvisor data")
	public void fetchHolidayHomes(String locationName,String check_in,Double noOfDays,Double guestCount,String sortType,String suitability,String amenities,Double amount) throws InterruptedException 
	{
		testCase=extentReport.createTest("Fetching Holiday homes with check-in & check-out  dates");
		PageFactory.initElements(driver, HomePage.class);
		PageFactory.initElements(driver, FindRentals.class);
		PageFactory.initElements(driver, HolidayHomes.class);
		
		commonFunction=new CommonFunction(driver);
		datePicker=new DatePicker(driver);
		
		System.out.println("I am in test case with dates");
		
		searchFor();
		
		provideDetails(locationName,noOfDays);
		
	
		choosingSuitability(suitability);
		//choosingSortby();
		choosingAmenities(amenities);
		
		
		displayDetails();
		
	}
	
	

}
