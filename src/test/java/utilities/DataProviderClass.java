package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviderClass 
{
	
	/*@org.testng.annotations.DataProvider(name="register_data")
	public String[][] getData()
	{
		String data[][]= {
				{"abcd1","abcd1@gmail.com","satara","satara"},
				{"abcd2","abcd2@gmail.com","satara","satara"},
				{"abcd3","abcd3@gmail.com","satara","satara"},
				{"abcd4","abcd4@gmail.com","satara","satara"},
				{"abcd5","abcd5@gmail.com","satara","satara"}
		};
		
		return data;
	}*/
	
	/*@DataProvider(name="register_data1")
	public String[][] getdata1() throws IOException
	{
		FileInputStream fis = new FileInputStream(".\\testData\\test_data.xlsx");
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheetAt(0);
		
	     System.out.println(sheet.getLastRowNum());
	     System.out.println(sheet.getRow(0).getLastCellNum());
	     
	     int row_count=sheet.getLastRowNum();
	     int col_count=sheet.getRow(0).getLastCellNum();
	     String data[][] = new String[row_count][col_count];
	     
	     for(int i=1;i<=row_count;i++)
	     {
	    	 for(int j=0; j<col_count; j++)
	    	 {
	    		 //System.out.print(sheet.getRow(i).getCell(j));
	    		 data[i-1][j] = sheet.getRow(i).getCell(j).getStringCellValue();
	    		 System.out.print(data[i-1][j]);
	    	 }
	    	 System.out.println();
	     }
		
	     //System.out.println(Arrays.toString(data));
		
		return data;
		
	}*/

    @DataProvider(name="register_data2")
	public String[][] getData() throws IOException
	{
		XLUtility xl = new XLUtility(".\\testData\\test_data.xlsx");
		int row_count=xl.getRowCount("Sheet1");
		System.out.println(row_count);
		int col_count=xl.getCellCount("Sheet1", 0);
		System.out.println(col_count);
		String data[][] = new String[row_count][col_count];
		
		for(int i=1;i<=row_count;i++)
		{
			for(int j=0;j<col_count;j++)
			{
				data[i-1][j]=xl.getCellData("Sheet1", i, j);
			}
		}
		
		return data;
	}
}
