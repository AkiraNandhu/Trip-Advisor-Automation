package com.cognizant.tests.testScenario6;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.cognizant.businessFunctionality.CommonFunction;
import com.cognizant.pageObjects.CruiseDetails;
import com.cognizant.pageObjects.CruisesSelection;
import com.cognizant.pageObjects.HomePage;
import com.cognizant.utilities.DriverSetup;

public class TC61_CruiseButtonChecking extends DriverSetup
{
	
	CommonFunction commonFunction;
	public void checkCruise()
	{
		//Choosing Cruise option
		testCase.log(Status.INFO, "Clicking Cruises");

		commonFunction.click(HomePage.txtboxLocation);
		commonFunction.click(HomePage.tabCruises);
		boolean status=false;
		if(HomePage.tabCruises.isDisplayed() && HomePage.tabCruises.isEnabled())
		{
			status=true;
			testCase.log(Status.PASS, "Cruise tab is accesible");

		}
		else
		{
			testCase.log(Status.FAIL, "Cruise tab is not accesible");

		}
		Assert.assertEquals(true,status);
		
	}
	
	@Test(groups= {"SmokeTest"})
	public void fetchCruises() 
	{
		
		PageFactory.initElements(driver, HomePage.class);
		

		testCase=extentReport.createTest(this.getClass().getSimpleName()+" :Checking Cruise button");

		commonFunction=new CommonFunction(driver);
		
		checkCruise();
		
		
	}

}
