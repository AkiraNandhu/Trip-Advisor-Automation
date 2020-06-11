package com.cognizant.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class ExcelUtilities
{
	static XSSFWorkbook Myworkbook;
	static XSSFSheet sheet;
	static XSSFRow row;
	static XSSFCell cell;

	
	public static Object [][] getExcelData(String sheetName) throws FileNotFoundException ,IOException
	{
		//Get the excel file path
		//Using the sheet name passed to this method, read the data and store it in a object array.
		//Store only the column value in the array.
		
		String filePath=System.getProperty("user.dir")+"\\src\\com\\cognizant\\utilities\\Excel_Data.xlsx";


		//String path="C:\\Users\\New\\CTS Workspace\\miniproject\\src\\project\\Excel_Data.xlsx";
		Object [][] excelData;
		FileInputStream Xcelfile = new FileInputStream(filePath);
		Myworkbook = new XSSFWorkbook(Xcelfile);
		sheet = Myworkbook.getSheet(sheetName);
		
		int rowCount=sheet.getPhysicalNumberOfRows();
		int columnCount=sheet.getRow(1).getPhysicalNumberOfCells();
		
		System.out.println("Rows :"+rowCount+" col :"+columnCount);
		excelData=new Object[rowCount-1][columnCount];
		int count=0;
		for(int i=1;i<rowCount;i++)
		{
			if(count<rowCount)
			{
				row=sheet.getRow(i);
				//System.out.println("Row :"+i+row);
				for(int j=0;j<columnCount;j++)
				{
					cell=row.getCell(j);
					
					//System.out.println("col :"+j+cell);    //XSSFCell.CELL_TYPE_
					
					switch(cell.getCellType())
					{
						case NUMERIC:
							excelData[count][j]=cell.getNumericCellValue();
							break;
						case STRING:
							excelData[count][j]=cell.getStringCellValue();
							break;
							
					}
				
				}
				count++;
			
				
				//System.out.println("data :"+i+ " : "+j+": "+excelData[i][j]);
			}
			 
		}
  	 
       Myworkbook.close();
       Xcelfile.close();
       return excelData;
			
	}

}
