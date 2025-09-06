package pageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TextBoxPageObjects 
{

	 WebDriver ldriver;
	 JavascriptExecutor js;
	 
	 public TextBoxPageObjects(WebDriver rdriver) 
	 {
		ldriver = rdriver;
		PageFactory.initElements(ldriver, this);
	 }
	 
	 @FindBy(xpath="//span[text()='Text Box']")
	 WebElement text_box_link;
	 
	 @FindBy(xpath = "//input[@id='userName']")
	 WebElement username;
	 
	 @FindBy(xpath="//input[@id='userEmail']")
	 WebElement email;
	 
	 @FindBy(xpath="//*[@id='currentAddress']")
	 WebElement current_address;
	 
	 @FindBy(xpath = "//*[@id='permanentAddress']")
	 WebElement permanent_address;
	 
	 @FindBy(xpath = "//*[@id='submit']")
	 WebElement submit;
	 
	 @FindBy(xpath = "//p[@id='name']")
	 WebElement registered_name;
	 
	 public void click_text_box_link()
	 {
		 text_box_link.click();
	 }
	 
	 public void enter_username(String username)
	 {
		 js = (JavascriptExecutor)ldriver;
		 js.executeScript("arguments[0].scrollIntoView(true);", this.username);
		 this.username.sendKeys(username);
	 }
	 
	 public void enter_email(String email)
	 {
		 this.email.sendKeys(email);
	 }
	 
	 public void enter_current_address(String curaddress)
	 {
		 current_address.sendKeys(curaddress);
	 }
	 
	 public void enter_permanent_address(String peraddress)
	 {
		 permanent_address.sendKeys(peraddress);
	 }
	 
	 public void click_submit()
	 {
		 submit.click();
	 }
	 
	 public String return_name()
	 {
		 String name[] = registered_name.getText().trim().split(":");
		 return name[1];
	 }

}
