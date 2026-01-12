package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage  {
	
	public HomePage(WebDriver driver)
	{
		super(driver);
	}


@FindBy(xpath="//span[normalize-space()='My Account']") //span[normalize-space()='My Account']
WebElement lnkMyAccount;

@FindBy(xpath="//a[normalize-space()='Register']")	//a[normalize-space()='Register']	
WebElement lnkRegister;	

@FindBy(linkText="Login")	
WebElement linkLogin;	

	
	
public void ClickMyAccount()
{
	
	lnkMyAccount.click();


}
public void ClickRegister()
{
	
	lnkRegister.click();
}

public void ClickLogin()
{
	linkLogin.click();
}
	
}
