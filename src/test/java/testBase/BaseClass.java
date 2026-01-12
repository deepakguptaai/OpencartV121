package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;//Log4j
import org.apache.logging.log4j.Logger;//Log4j
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {

public static WebDriver driver;//variable
public Logger logger;//variable for Log4j
public Properties p;
	
	@BeforeClass(groups= {"Sanity","Regression","Master"})
	@Parameters({"os","browser"})
	public void setup(String os, String br) throws InterruptedException, IOException
	{ 
		//Loading config.properties file
		FileReader file=new FileReader("./src//test//resources//config.properties");
		p=new Properties();// object created
		p.load(file);
		
		
		logger=LogManager.getLogger(this.getClass());//log4j2
		
		if(p.getProperty("execution_env").equalsIgnoreCase("remote"))
			{
			 DesiredCapabilities capabalities=new DesiredCapabilities();
			 capabalities.setPlatform(Platform.WIN11);
			 
			 //os
			 if(os.equalsIgnoreCase("windows"))
			 {
				 capabalities.setPlatform(Platform.WIN11);
			 }
			 else if(os.equalsIgnoreCase("linx"))
			 {
				 capabalities.setPlatform(Platform.LINUX);
			 }
			 else if(os.equalsIgnoreCase("mac"))
			 {
				 capabalities.setPlatform(Platform.MAC);
			 }
			 else
			 {
				 System.out.println("No matching os");
			 }
			 
			 
			 //browser
			 switch(br.toLowerCase()) {
			 case "chrome": capabalities.setBrowserName("chrome");break;
			 case "edge": capabalities.setBrowserName("MicrosoftEdge");break;
			 case "firefox": capabalities.setBrowserName("firefox");break;
			 default:System.out.println("No matching browese"); return;
			 }
			 
			 
			 driver=new RemoteWebDriver(new URL("http://192.168.0.103:4444/wd/hub"),capabalities);
			 
		}
		if(p.getProperty("execution_env").equalsIgnoreCase("local"))
		
		
		switch(br.toLowerCase())///Local evn
		{
		case "chrome": driver=new ChromeDriver(); break;
		case "edge":   driver=new EdgeDriver(); break;
		case "firefox":driver=new FirefoxDriver(); break;
		default:System.out.println("Invalid browser name.."); return;
		
		}
		
		//driver=new ChromeDriver();
		//driver.get("https://tutorialsninja.com/demo/");
		driver.get(p.getProperty("appURL1"));// reading url from config.properties file
		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		Thread.sleep(10000);
	}
	
	@AfterClass(groups= {"Sanity","Regression","Master"})
	public	void tearDown() 
	{
	driver.quit();
	}
	
	public String randomString()
	{
		String generatedstring=RandomStringUtils.randomAlphabetic(6);
		return generatedstring;
	}
	
	public String randomNumber()
	{
		String generatedstring=RandomStringUtils.randomNumeric(10);
		return generatedstring;
	}
	
	public String randomAlphanumeric()
	{
		String generatedstring=RandomStringUtils.randomNumeric(3);
		String generatednumber=RandomStringUtils.randomAlphanumeric(3);
		return (generatedstring+"@"+generatednumber);
	}
	
	public String captureScreen(String tname) throws IOException {

	    String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

	    TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
	    File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

	    String targetFilePath = System.getProperty("user.dir") + "\\screenshots\\"+ tname + "_" + timeStamp + ".png";
	    File targetFile = new File(targetFilePath);

	    sourceFile.renameTo(targetFile);

	    return targetFilePath;
	}
	
	
	
}
