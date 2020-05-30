package com.cognizant.businessFunctionality;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.cognizant.pageObjects.FindRentals;

public class DatePicker 
{
	
	CommonFunction commonFunction;
	public WebDriver driver;
	WebDriverWait wait;
	int noOfDays;
	
	public DatePicker(WebDriver driver)
	{
		this.driver=driver;
		wait = new WebDriverWait(driver, 20);
		commonFunction=new CommonFunction(driver);
		
	}

	public int SetCheckOutDate(int Days)
	{
		int days=Integer.parseInt(commonFunction.getElementValue(FindRentals.dtpVisibleCheckOut));
		//int days=Integer.parseInt(homePage.GetElementValue(VisibleCheckOut));
		noOfDays=Days;
		return days+Days;
	}
	
	public void ClickTomorrowCheckInDate() 
	{
		PageFactory.initElements(driver,FindRentals.class);
		Date date = new Date();
		Calendar c = Calendar.getInstance();
		

		c.setTime(date);
		c.add(Calendar.DATE, 1);
 
		String strMonth = c.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH);
				
		//String tommorowsDate = new SimpleDateFormat("MM/dd/yyyy").format(c.getTime());
		
		commonFunction.click(FindRentals.dtpCheckIn);

		String strUIMonth=commonFunction.getElementValue(FindRentals.dtpCheckInTitle);
		
		strUIMonth=strUIMonth.substring(0,strUIMonth.indexOf(" "));
		 
		if (strMonth.equalsIgnoreCase(strUIMonth)) 
		{
			int size= commonFunction.getListsize(FindRentals.dtpAvailableDates);
	
			if(size!=0 )
			{
				commonFunction.click(FindRentals.dtpTomorrowCheckInDate);
			}
				
		}
 		else
		{
 			commonFunction.click(FindRentals.dtpbtnNext);
 					
			for (int i = 0; i < FindRentals.dtpNextMonthDates.size(); i++) 
			{
				// Get the day from the date
				if (FindRentals.dtpNextMonthDates.get(i).getText().trim().equalsIgnoreCase("1"))
				{
					
					FindRentals.dtpNextMonthDates.get(i).click();
					break;
				}
				
			}
			
		}
	}
	public void ClickCheckOutDate() 
	{
		int days=SetCheckOutDate(5);
		int size= commonFunction.getListsize(FindRentals.dtpAvailableDates);

		String path="";
		
		if(size>=noOfDays)
		{
			path="//span[contains(text(),'"+days+"') and contains(@class,'rsdc-cell rsdc-day')]";
			 
			 WebElement CheckOutDate=driver.findElement(By.xpath(path));
			 commonFunction.click(CheckOutDate);
		}
		else
		{
			int differenceDates=noOfDays-size;
			 commonFunction.click(FindRentals.dtpbtnNext);
 			System.out.println("differenceDates :"+differenceDates);
			
			wait.until(ExpectedConditions.visibilityOfAllElements(FindRentals.dtpNextMonthDates));
				
			path="//span[contains(text(),'"+differenceDates+"') and contains(@class,'rsdc-cell rsdc-day')]";
			WebElement CheckOutDate=driver.findElement(By.xpath(path));
			 commonFunction.click(CheckOutDate);
		
			
		}
		
	}
	

}
