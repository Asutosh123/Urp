package pageObjects;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import stepDefinitions.Hooks;
import utilities.BrowserBot;


public class LoginPage extends BrowserBot {

	public WebDriver ldriver;

	public LoginPage(WebDriver rdriver) {
		super(rdriver);
		ldriver = rdriver;
		PageFactory.initElements(rdriver, this);
	}
	
	String sss;

	@FindBy(id = "username")
	@CacheLookup
	WebElement txtEmail;
 
	@FindBy(id = "password")
	@CacheLookup
	WebElement txtPassword;

	@FindBy(xpath = "//input[@type='submit'][@value='Login']")
	@CacheLookup
	WebElement btnLogin;

	@FindBy(xpath = "//a[text()='Logout']")
	@CacheLookup
	WebElement lnkLogout;

	public void setUserName(String uname) {
		
		clear(txtEmail);
		type(txtEmail, uname);	
	}
	
	public void setPassword(String pwd) {
		waitForVisible(txtPassword);
		clear(txtPassword);
		type(txtPassword, pwd);
	}

	public void clickLogin() throws Exception {
		click(btnLogin);
	}
	
	public void clickLogout() throws Exception {
		waitForVisible(lnkLogout);
		clickByJS(lnkLogout);
		
	}


}