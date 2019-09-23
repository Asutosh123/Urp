package stepDefinitions;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.junit.BeforeClass;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;
import org.testng.annotations.AfterClass;

import com.cucumber.listener.Reporter;
import com.google.common.io.Files;

import baseTest.BaseClass;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Hooks extends BaseClass {
	// TestContext testContext;

	@Before
	/**
	 * Delete all cookies at the start of each scenario to avoid shared state
	 * between tests
	 */
	public void openBrowser() throws Exception {
		openBrowser("Chrome");
	}
	

	@After(order = 0)
	public void embedScreenshot(Scenario scenario) throws Exception {
		takeScreenShotFail();

		if (scenario.isFailed()) {
			try {
				final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
				scenario.embed(screenshot, "image/png");
			} catch (WebDriverException wde) {
				System.err.println(wde.getMessage());
			} catch (ClassCastException cce) {
				cce.printStackTrace();
			}
		}
		driver.quit();
		
	}

	@After(order = 1)
	public void afterScenario(Scenario scenario) {
		if (scenario.isFailed()) {
			Date d = new Date();
			String screenshotFile = d.toString().replace(":", "_").replace(" ", "_") + ".png";
			try {
				// This takes a screenshot from the driver at save it to the specified location
				File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

				// Building up the destination path for the screenshot to save
				// Also make sure to create a folder 'screenshots' with in the cucumber-report
				// folder
				File destinationPath = new File(
						System.getProperty("user.dir") + "/target/cucumber-reports/screenshots/" + screenshotFile);

				// Copy taken screenshot from source location to destination location
				Files.copy(scrFile, destinationPath);

				// This attach the specified screenshot to the test
				Reporter.addScreenCaptureFromPath(destinationPath.toString());
			} catch (IOException e) {
			}
		}
	}


}