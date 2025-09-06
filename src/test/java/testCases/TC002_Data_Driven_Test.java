package testCases;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.BaseClass;
import pageObjects.TextBoxPageObjects;
import utilities.DataProviderClass;

public class TC002_Data_Driven_Test extends BaseClass
{
	  TextBoxPageObjects text_box_page_object;   
	
      /*@Test(dataProvider = "register_data" ,dataProviderClass = DataProviderClass.class)
      public void register_test(String username, String email, String cur_address, String per_address)
      {
    	  text_box_page_object = new TextBoxPageObjects(driver);
    	  text_box_page_object.click_text_box_link();
    	  text_box_page_object.enter_username(username);
    	  text_box_page_object.enter_email(email);
    	  text_box_page_object.enter_current_address(cur_address);
    	  text_box_page_object.enter_permanent_address(per_address);
    	  text_box_page_object.click_submit();
    	  String reg_name=text_box_page_object.return_name();
    	  Assert.assertEquals(reg_name,username);
    	  driver.get(prop.getProperty("url"));
      }  */
      
     /* @Test(dataProvider = "register_data1", dataProviderClass = DataProviderClass.class)
      public void register_test1(String username, String email, String cur_address, String per_address)
      {
    	  text_box_page_object = new TextBoxPageObjects(driver);
    	  text_box_page_object.click_text_box_link();
    	  text_box_page_object.enter_username(username);
    	  text_box_page_object.enter_email(email);
    	  text_box_page_object.enter_current_address(cur_address);
    	  text_box_page_object.enter_permanent_address(per_address);
    	  text_box_page_object.click_submit();
    	  String reg_name=text_box_page_object.return_name();
    	  Assert.assertEquals(reg_name,username);
    	  driver.get(prop.getProperty("url"));
      }*/
	  
	  @Test(dataProvider = "register_data2", dataProviderClass = DataProviderClass.class)
      public void register_test2(String username, String email, String cur_address, String per_address)
      {
		  logger.info("Test started");
    	  text_box_page_object = new TextBoxPageObjects(driver);
    	  text_box_page_object.click_text_box_link();
    	  text_box_page_object.enter_username(username);
    	  text_box_page_object.enter_email(email);
    	  text_box_page_object.enter_current_address(cur_address);
    	  text_box_page_object.enter_permanent_address(per_address);
    	  text_box_page_object.click_submit();
    	  String reg_name=text_box_page_object.return_name();
    	  Assert.assertEquals(reg_name,username);
    	  driver.get(prop.getProperty("url"));
    	  logger.info("Test completed");
      }
}
