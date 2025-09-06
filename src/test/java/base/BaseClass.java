package base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.time.FastDateFormat;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass 
{
	public WebDriver driver;
	public FileInputStream fis;
	public Properties prop;
	public Logger logger;

	@BeforeClass
	//@Parameters("browser")
	public void setup(/*String browser*/) throws IOException  
	{
		/*if(browser.equalsIgnoreCase("chrome"))
		{
		driver = new ChromeDriver();
		}
		else if(browser.equalsIgnoreCase("firefox"))
		{
	    driver = new FirefoxDriver();
		}
		else
		{
			System.out.println("Browser not found");
			return;
		}*/
		logger = LogManager.getLogger(this.getClass());
		driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		fis = new FileInputStream(".\\src\\test\\resources\\config.properties");
		prop = new Properties();
		prop.load(fis);
		String url = prop.getProperty("url");
		driver.get(url);
	}
	
	
	@AfterClass
	public void tearDown()
	{
		driver.close();
	}
	
	public String captureScreen(String tname) throws IOException
    {
    	String timeStamp = FastDateFormat.getInstance("yyyy.MM.dd.HH.mm.ss").format(new Date());
        File scrfile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        
        String targetFilePath =System.getProperty("user.dir")+"/screenshots/"+tname+"_"+timeStamp+".jpg";
        FileUtils.copyFile(scrfile, new File(targetFilePath));
        
        return targetFilePath;
    }
}
