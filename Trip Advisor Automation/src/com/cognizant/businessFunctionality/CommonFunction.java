package com.cognizant.businessFunctionality;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CommonFunction 
{
	public WebDriver driver;
	WebDriverWait wait;
	
	public CommonFunction(WebDriver driver)
	{
		this.driver=driver;
		wait = new WebDriverWait(driver, 20);
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

}
