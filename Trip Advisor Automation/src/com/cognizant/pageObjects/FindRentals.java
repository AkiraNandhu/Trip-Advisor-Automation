package com.cognizant.pageObjects;



import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class FindRentals 
{
	WebDriver driver;
	

	@FindBy(xpath="//input[@class='typeahead_input' or contains(@placeholder,'Where do you want to go?')]")
	public static WebElement txtboxLocationName;
	
	@FindBy(xpath="//span[contains(text(),'Check In')]")
	public static WebElement dtpCheckIn;
	
	@FindBy(xpath="//span[contains(@class,'month-title')]")
	public static WebElement dtpCheckInTitle;
	
	@FindBy(xpath="//span[contains(@class,'rsdc-day rsdc-today') or contains(@class,'rsdc-day rsdc-startrange')]/following-sibling::span")
	public static List<WebElement> dtpAvailableDates;
	
	@FindBy(xpath="//span[contains(@class,'rsdc-day rsdc-today')]/following-sibling::span[1]")
	public static WebElement dtpTomorrowCheckInDate;
	
	@FindBy(xpath="//div[contains(@class,'rsdc-next rsdc-nav ui_icon single-chevron-right-circle')]")
	public static WebElement dtpbtnNext;
	
	@FindBy(xpath="//span[contains(@class,'month')]/span[contains(@class,'rsdc-day')]/following-sibling::span")
	public static List<WebElement> dtpNextMonthDates;
	
	@FindBy(xpath="//span[contains(text(),'Check Out')]")
	public static  WebElement dtpCheckOut;
	
	@FindBy(xpath="//span[contains(@class,'rsdc-startrange')]")
	public static WebElement dtpVisibleCheckOut;
	
	@FindBy(xpath="//span[contains(text(),'Find holiday rentals')]")
	public static WebElement btnfindRental;
		
	
	/*public FindRentals(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);		
	}
	*/
	
	
	

	
	
}
