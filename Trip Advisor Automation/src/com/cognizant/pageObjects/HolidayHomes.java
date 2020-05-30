package com.cognizant.pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HolidayHomes
{
	WebDriver driver;
	
	@FindBy(xpath="//*[contains(@class,'vr-srp-propertyCard-pieces-PropertyTitleSection__propertyTitle--351Sr')]")
	public static List<WebElement> lstHolidayHomeNames;
	
	/*public HolidayHomes(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);		
	}
	*/
	

}
