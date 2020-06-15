package com.cognizant.tests.testScenario1;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.cognizant.businessFunctionality.*;
import com.cognizant.utilities.DriverSetup;

import com.cognizant.pageObjects.*;

/*
 * Test Scenario ID :TS11
 * Test Case ID :TC12
 */


public class TC12_VerifyHolidayHomesViaMoreTab extends DriverSetup
{
	public void searchFor()
	{
		//Choosing holiday homes option
		testCase.log(Status.INFO, "Selecting holiday homes");

		commonFunction.click(HomePage.btnMore);
		
		commonFunction.click(HomePage.tabMoreHolidayHomes);
		
		commonFunction.setElementValue(HomePage.txtLocation, "Nairobi");
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

	
	CommonFunction commonFunction;
	
	@Test(groups= {"SmokeTest"})
	public void fetchHolidayHomes() 
	{
		testCase=extentReport.createTest(this.getClass().getSimpleName()+" :Verifying Holiday homes via more tab");

		PageFactory.initElements(driver, HomePage.class);
		PageFactory.initElements(driver, FindRentals.class);
		PageFactory.initElements(driver, HolidayHomes.class);

		commonFunction=new CommonFunction(driver);
		
		
		searchFor();
		checkHolidayHome();
				
	}


}
