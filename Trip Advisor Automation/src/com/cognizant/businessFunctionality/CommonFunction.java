package com.cognizant.businessFunctionality;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.cognizant.pageObjects.HolidayHomes;

public class CommonFunction 
{
	public WebDriver driver;
	WebDriverWait wait;
	JavascriptExecutor jse;
	
	public CommonFunction(WebDriver driver)
	{
		this.driver=driver;
		wait = new WebDriverWait(driver, 20);
		jse=(JavascriptExecutor)driver;

	}
	
	public void setElementValue(WebElement webElement,String value)
	{
		webElement.sendKeys(value);		
	}
	
	
	
	public String getElementValue(WebElement webElement)
	{
		wait.until(ExpectedConditions.visibilityOf(webElement));
		return webElement.getText();	
	}
	
	public void click(WebElement webElement)
	{
		wait.until(ExpectedConditions.elementToBeClickable(webElement));
		webElement.click();
	}
	
	public int getListsize(List<WebElement> webelement) 
	{
		
		return webelement.size();
	}
	public void getHolidayHomeNames(List<WebElement> webelement)
	{
		int j;
		System.out.println("Top 5 Holiday Homes based on your wish");
		for(int i=0;i<5;i++)
		{
			j=i+1;
			System.out.println(j+" :"+webelement.get(i).getText());
		}
		
	}
	
	public Boolean checkElement(WebElement webElement) {
		Boolean check=webElement.isEnabled();
		return check;
		
	}
	
	public  String[] getChosenFilters(List<WebElement> webelement)
	{
		jse.executeScript("arguments[0].scrollIntoView(true);", HolidayHomes.filters);
		wait.until(ExpectedConditions.visibilityOfAllElements(webelement));
	
		int size=webelement.size();
		String filtersData[]=new String[size];
		for(int i=0;i<size;i++)
		{
			filtersData[i]=webelement.get(i).getText();
			
			System.out.println(i+" :"+webelement.get(i).getText());
		}
		
		return filtersData;
		
	}
	
	public boolean checkingGuest(List<WebElement> element,int noOfGuest)
	{
		System.out.println("Top 5 Holiday Homes based on your wish");
		wait.until(ExpectedConditions.visibilityOfAllElements(HolidayHomes.lstHolidayHomeNames));
		String actualSleeps;
		boolean status=false;
		for(int i=0;i<5;i++)
		{
			
			String actualText=element.get(i).getText();
			actualSleeps=actualText.substring(actualText.length() -1);
			int noOfSleeps=(Integer.parseInt(actualSleeps));
			//System.out.println(" xxx :"+actualText+" :"+actualSleeps+" :"+noOfSleeps);

			if(noOfGuest<=noOfSleeps)
			{
				status=true;
				System.out.println(getElementValue(HolidayHomes.lstHolidayHomeNames.get(i)));
				
			}
			else
				break;
			
			

		}
		return status;
		
	}

}
