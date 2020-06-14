package com.cognizant.tests;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.cognizant.businessFunctionality.CommonFunction;
import com.cognizant.pageObjects.FindRentals;
import com.cognizant.pageObjects.HolidayHomes;
import com.cognizant.pageObjects.HomePage;
import com.cognizant.utilities.DriverSetup;



public class TC13_SearchBarHolidayHomes extends DriverSetup
{
	CommonFunction commonFunction;
	public void searchFor()
	{
		//Choosing holiday homes option
		testCase.log(Status.INFO, "Selecting holiday homes");

		commonFunction.click(HomePage.txtboxLocation);
		
		commonFunction.click(HomePage.tabHolidayHomes);
	}

	public void checkHolidayHome()
	{
		boolean status=false;
		String strActualTitle=commonFunction.getElementValue(FindRentals.title);
		System.out.println(strActualTitle);
		if(strActualTitle.contains("holiday rental"))
		{
			status=true;
			testCase.log(Status.PASS, "Navigated correctly to holiday homes page");

		}
		else
			testCase.log(Status.FAIL, "Mismatched page is dispayed");
		
		Assert.assertEquals(true, status);

			
	}
	
	@Test
	public void fetchHolidayHomes() 
	{
		testCase=extentReport.createTest(this.getClass().getSimpleName()+" :Verifying holiady homes via search bar");

		PageFactory.initElements(driver, HomePage.class);
		PageFactory.initElements(driver, FindRentals.class);
		PageFactory.initElements(driver, HolidayHomes.class);

		commonFunction=new CommonFunction(driver);
		searchFor();
		checkHolidayHome();		
	}

}
