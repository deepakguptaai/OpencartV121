package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass {
	
	//Pasted into BaseClass
	/*public WebDriver driver;
	
	@BeforeClass
	public void setup() throws InterruptedException
	{
		driver=new ChromeDriver();
		driver.get("https://tutorialsninja.com/demo/");
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		Thread.sleep(4000);
	}
	
	@AfterClass
	public	void tearDown() 
	{
	driver.quit();
	}*/
	
	@Test(groups= {"Regression","Master"})
	public void verify_account_registration ()
	{
		logger.info("*******Starting TC001_AccountRegistrationTest******");
		
		try
		{
		HomePage hp=new HomePage(driver);
		hp.ClickMyAccount();
		logger.info("Clicked on MyAccount Link");
		
		hp.ClickRegister();
		logger.info("Clicked on Rigister Link");
		
		AccountRegistrationPage regpage=new AccountRegistrationPage(driver);
		
		logger.info("Providing Customer Details...");
		regpage.setFirstName(randomString().toUpperCase());
		regpage.setLastName(randomString().toUpperCase());
		regpage.setEMail(randomString()+"@gmail.com");//randomly generated the email
		regpage.setTelephone(randomNumber());
		
		String password=randomAlphanumeric();
		
		regpage.setPassword(password);
		regpage.setconfirmPassword(password);
		regpage.setPrivacyPolicy();
		regpage.clickContinue();
		
		logger.info("Validating expected message");
		String confmsg=regpage.getConfirmationMsg();
		
		if(confmsg.equals("Your Account Has Been Created!"))
		{
			Assert.assertTrue(true);
		}
		else
		{
			logger.error("Test Failed...");
			logger.debug("Debug log...");
			Assert.assertTrue(false);
		}
		Assert.assertEquals(confmsg, "Your Account Has Been Created!");
		}
		catch(Exception e)
		{
			
			Assert.fail();
		}
		
		logger.info("*******Finished TC001_AccountRegistrationTest******");
	}
	
	// Pasted into BaseClass
	/*public String randomString()
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
	*/

}
