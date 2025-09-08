package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseClass;
import pageObjects.TextBoxPageObjects;

public class TC001_Text_Box_Handling extends BaseClass
{
     TextBoxPageObjects text_box_page_object;
	
	 @Test
	 public void text_box_handling()
	 {
		 logger.info("Test started");
		 text_box_page_object = new TextBoxPageObjects(driver);
		 text_box_page_object.click_text_box_link();
		 text_box_page_object.enter_username("abcd");
		 text_box_page_object.enter_email("abcd@gmail.com");
		 text_box_page_object.enter_current_address("satara");
		 text_box_page_object.enter_permanent_address("satara");
		 text_box_page_object.click_submit();
		 String registered_name = text_box_page_object.return_name();
		 Assert.assertEquals(registered_name,"abcd");
		 logger.info("Test completed");
	 }
}
