package base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.time.FastDateFormat;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
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
	@Parameters({"browser","os"})
	public void setup(String browser,String os) throws IOException  
	{
		fis = new FileInputStream(".\\src\\test\\resources\\config.properties");
		prop = new Properties();
		prop.load(fis);
		
		String env = prop.getProperty("env");
		System.out.println(env+" "+browser+" "+os);
		
		if(env.equalsIgnoreCase("remote"))
		{
			DesiredCapabilities dc = new DesiredCapabilities();
			if(os.equalsIgnoreCase("windows"))
			{
				dc.setPlatform(Platform.WIN11);
			}
			else if(os.equalsIgnoreCase("linux"))
			{
				dc.setPlatform(Platform.LINUX);
			}
			else
			{
				System.out.println("Os id not found");
				return;
			}
			
			if(browser.equalsIgnoreCase("chrome"))
			{	
				dc.setBrowserName("chrome");
			}
			else if(browser.equalsIgnoreCase("firefox"))
			{
				dc.setBrowserName("firefox");
			}else
			{
				System.out.println("browser is not found");
				return;
			}
			
			driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),dc);
		}
		else
		{
		if(browser.equalsIgnoreCase("chrome"))
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
		}
		}
		logger = LogManager.getLogger(this.getClass());
		//driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
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
