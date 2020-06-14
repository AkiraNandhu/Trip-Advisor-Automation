package com.cognizant.tests;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.cognizant.businessFunctionality.CommonFunction;
import com.cognizant.pageObjects.CruiseDetails;
import com.cognizant.pageObjects.CruisesSelection;

import com.cognizant.pageObjects.HomePage;
import com.cognizant.utilities.DriverSetup;
import com.cognizant.utilities.ExcelUtilities;

public class TC64_FetchCruiseDetails extends DriverSetup
{
	CommonFunction commonFunction;
	@DataProvider(name="Cruise data")
	public Object[][] getExcelData() throws FileNotFoundException, IOException
	{
		Object[][] data=ExcelUtilities.getExcelData("Cruise_Data");
		return data;
	}
	
	public void searchFor()
	{
		//Choosing Cruise option
		testCase.log(Status.INFO, "Clicking Cruises");

		commonFunction.click(HomePage.txtboxLocation);
		commonFunction.click(HomePage.tabCruises);
		
	}
	
	public void chooseCruises(String cruiseLine,String cruiseShip)
	{
		testCase.log(Status.INFO, "Choosing Cruise Line and Ship");

		commonFunction.click(CruisesSelection.drpCruiseLine);
		CruisesSelection.chooseCruiseOption(cruiseLine);
		
		commonFunction.click(CruisesSelection.drpCruiseShip);
		CruisesSelection.chooseCruiseOption(cruiseShip);
		
		testCase.log(Status.INFO, "Clicking Search button and started searching");

		commonFunction.click(CruisesSelection.btnSearch);
		
		testCase.log(Status.INFO, "Verifying Title");

		ArrayList<String> tabs=new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		
		String title=commonFunction.getElementValue(CruiseDetails.heading);
		
		if(title.contains(cruiseShip))
			testCase.log(Status.PASS, "Navigated to correct cruise ship details Page");

		else
			testCase.log(Status.FAIL, "Irrelevant data shown");
		
		
		
	}
	
	public void displayDetails()
	{
		
		testCase.log(Status.INFO, "Display Cruise Details");
		System.out.println("Display Cruise details");

		System.out.println(commonFunction.getElementValue(CruiseDetails.cruiseDetails)); 
		testCase.log(Status.PASS, "Displayed Cruise Details");

			
	}


	@Test(dataProvider="Cruise data")
	public void fetchCruises(String cruiseLine,String cruiseShip) 
	{
		
		System.out.println("Fetching Holiday homes without dates");
		PageFactory.initElements(driver, HomePage.class);
		PageFactory.initElements(driver, CruisesSelection.class);
		PageFactory.initElements(driver, CruiseDetails.class);

		testCase=extentReport.createTest(this.getClass().getSimpleName()+" :Fetching Cruise details");

		commonFunction=new CommonFunction(driver);
		
		searchFor();
		
		chooseCruises(cruiseLine,cruiseShip);
		
		displayDetails();
		
	}
}
