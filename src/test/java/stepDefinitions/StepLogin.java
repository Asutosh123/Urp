package stepDefinitions;

import org.openqa.selenium.WebDriver;

import baseTest.BaseClass;
import cucumber.api.java.en.Given;
import pageObjects.LoginPage;



public class StepLogin extends BaseClass {

	WebDriver driver;
	public StepLogin() {
		driver = Hooks.driver;
		lp=new LoginPage(driver);
	}
	
	

@Given("^User Launch Chrome browser$")
public void user_Launch_Chrome_browser() throws Throwable {
	navigate("appurl");
	lp.setUserName("marybang");
	lp.setPassword("password");
	lp.clickLogin();
    
}

	
	
	
	
}
