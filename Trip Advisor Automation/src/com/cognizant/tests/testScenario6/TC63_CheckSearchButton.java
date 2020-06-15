package com.cognizant.tests.testScenario6;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.cognizant.businessFunctionality.CommonFunction;
import com.cognizant.pageObjects.CruisesSelection;
import com.cognizant.pageObjects.HomePage;
import com.cognizant.utilities.DriverSetup;

public class TC63_CheckSearchButton extends DriverSetup
{
	CommonFunction commonFunction;
	public void chooseCruise()
	{
		//Choosing Cruise option
		testCase.log(Status.INFO, "Clicking Cruises");

		commonFunction.click(HomePage.txtboxLocation);
		commonFunction.click(HomePage.tabCruises);
		
		
	}
	
	public void CheckCruiseLineAndShip()
	{
		testCase.log(Status.INFO, "Verifying cruise Line,shipand search button is accessible");

		boolean status=false;
		
		if(CruisesSelection.drpCruiseLine.isDisplayed() && CruisesSelection.drpCruiseLine.isEnabled())
		{
			if((CruisesSelection.btnSearch.isEnabled()==false))
				status=true;

				commonFunction.click(CruisesSelection.drpCruiseLine);
				CruisesSelection.chooseCruiseOption("AmaWaterways");
				if(CruisesSelection.drpCruiseShip.isDisplayed() && CruisesSelection.drpCruiseShip.isEnabled())
				{
					commonFunction.click(CruisesSelection.drpCruiseShip);
					CruisesSelection.chooseCruiseOption("AmaCerto");
					if(CruisesSelection.btnSearch.isEnabled())
					{
						status=true;
						testCase.log(Status.PASS, "Cruise Line,Ship and serach button is accesible");
					}
					else
						testCase.log(Status.FAIL, "Serach button not accessible after choosing Cruise Line and Ship");


				}
				else
					testCase.log(Status.FAIL, "Cruise Ship is not accesible after selecting Cruise LIne");

		}
		else
			testCase.log(Status.FAIL, "Cruise Line tab is not accesible");
		
		Assert.assertEquals(true,status);
	}
	
	@Test(groups= {"SmokeTest"})
	public void fetchCruises() 
	{
		
		PageFactory.initElements(driver, HomePage.class);
		PageFactory.initElements(driver, CruisesSelection.class);

		testCase=extentReport.createTest(this.getClass().getSimpleName()+" :Checking search button ");

		commonFunction=new CommonFunction(driver);
		
		chooseCruise();
		CheckCruiseLineAndShip();
		
		
	}

}
