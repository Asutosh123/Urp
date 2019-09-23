package baseTest;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;
import pageObjects.LoginPage;

public class BaseClass {

	public static WebDriver driver;
	public static Properties prop;
	public static String ppt;
	public LoginPage lp;
	
	static Logger logger = Logger.getLogger(BaseClass.class.getName());
	
	static{
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_hhmmss");
        System.setProperty("current.date", dateFormat.format(new Date()));
    }
	//private static final Logger logger = LogManager.getLogger(LoginPage.class.getName());

	public void openBrowser(String bType) {
		try {
            prop = new Properties();
            FileInputStream inputStream = new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/Properties/config.properties");
            prop.load(inputStream);

            PropertyConfigurator.configure(System.getProperty("user.dir") + "/src/test/resources/log4j.properties");
            logger = Logger.getLogger(BaseClass.class.getName());

        } catch (FileNotFoundException Ex) {
            logger.info("File not found: " + Ex.getMessage());

        } catch (IOException Ex) {
            logger.info("Exception occurred: " + Ex.getMessage());
        
		}

		if (bType.equals("Mozilla")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if (bType.equals("Chrome")) {
			WebDriverManager.chromedriver().setup();
			String downloadFilepath = System.getProperty("user.dir") + "/src/test/resources/DownloadedFile";
			HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
			chromePrefs.put("profile.default_content_settings.popups", 0);
			chromePrefs.put("download.default_directory", downloadFilepath);
			ChromeOptions options = new ChromeOptions();
			options.addArguments("disable-popup-blocking");
			options.setExperimentalOption("prefs", chromePrefs);
			logger.info("Trying to Launch" + bType + "Browser");
			driver = new ChromeDriver(options);
			// chromeOptions.addArguments("--headless");
			logger.info("Sucessfully Launched to " + bType + "Browser");

		}
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		 driver.manage().deleteAllCookies();

	}
	
	


	public void initProp() {
		if (prop == null)
			prop = new Properties();
		try {
			FileInputStream fs = new FileInputStream(
					System.getProperty("user.dir") + "//src//test//resources//Properties//path.properties");
			prop.load(fs);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void waitForPageToLoad() {
		wait1(1);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String state = (String) js.executeScript("return document.readyState");

		while (!state.equals("complete")) {
			wait1(2);
			state = (String) js.executeScript("return document.readyState");
		}
	}

	public void navigateTo(String url) throws Exception {
		driver.get(url);
		logger.info("Navigating to "+ url );
	}

	public void navigateTo1(String url) throws Exception {
		driver.get(url);
	}

	public static String InsidentDate = getFormattedDate("dd-MM-yyyy");

	public void takeScreenShotPass() {
		// fileName of the screenshot
		Date d = new Date();
		String screenshotFile = d.toString().replace(":", "_").replace(" ", "_") + ".png";
		// store screenshot in that file
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(scrFile,
					new File(System.getProperty("user.dir") + "//screenshots//PassedScreenShot" + screenshotFile));
		} catch (IOException e) {
			// TODO Auto-generated catcsh block
			e.printStackTrace();
		}
		// put screenshot file in reports

	}

	public void takeScreenShotFail() {
		// fileName of the screenshot
		Date d = new Date();
		String screenshotFile = d.toString().replace(":", "_").replace(" ", "_") + ".png";
		// store screenshot in that file
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(scrFile,
					new File(System.getProperty("user.dir") + "//screenshots//FailedScreenshot//" + screenshotFile));
		} catch (IOException e) {
			// TODO Auto-generated catcsh block
			e.printStackTrace();
		}
		// put screenshot file in reports
	}

	public final String Regdate = getFormattedDate("dd-MM-yyyy");

	public void prinText(String p) {
		System.out.println(p);

	}

	public void wait1(int timeToWaitInSec) {
		try {
			Thread.sleep(timeToWaitInSec * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void setClipboardData(String string) {
		// String Selection is a class that can be used for copy and paste operations.
		StringSelection stringSelection = new StringSelection(string);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
	}

	public static void uploadFileUsingRobot(String fileLocation) throws Exception {
		try {
			// Setting clipboard with file location
			Thread.sleep(2000);
			setClipboardData(fileLocation);
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(2000);
		} catch (Exception exp) {
			exp.printStackTrace();

		}
	}

	public void robotclass() throws AWTException {
		StringSelection ss = new StringSelection(prop.getProperty("genrelpdf"));
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);

		// native key strokes for CTRL, V and ENTER keys
		Robot robot = new Robot();

		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);

	}

	public void robotclass1() throws AWTException {
		StringSelection ss = new StringSelection(prop.getProperty("pdffile"));
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);

		// native key strokes for CTRL, V and ENTER keys
		Robot robot = new Robot();

		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);

	}

	public static String getFormattedDate(String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date uDate = new Date();
		return sdf.format(uDate);
	}

	public static String getFormattedDate(String format, int year) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.YEAR, year);
		return sdf.format(c.getTime());
	}

	String passportExpiryDt = getFormattedDate("dd-MM-yyyy", 5);

	public void navigate(String urlKey) {
		driver.get(prop.getProperty(urlKey));
		logger.info("Sucessfully Entered  " + urlKey + " Urlof the application");
	}

	public static String randmname() {

		int leftLimit = 97; // letter 'a'
		int rightLimit = 122; // letter 'z'
		int targetStringLength = 6;
		Random random = new Random();
		StringBuilder buffer = new StringBuilder(targetStringLength);
		for (int i = 0; i < targetStringLength; i++) {
			int randomLimitedInt = leftLimit + (int) (random.nextFloat() * (rightLimit - leftLimit + 1));
			buffer.append((char) randomLimitedInt);
		}
		String wrkername1 = buffer.toString().toUpperCase();
		System.out.println(wrkername1);
		return wrkername1;
	}

	public static String pnumber = ("" + System.currentTimeMillis()).substring(6, 12);

	public static String pptnumber() {
		Random random = new Random();
		int num = random.nextInt(100000);
		String formatted = "PASS" + String.format("%06d", num);
		return formatted;

	}

	// @AfterMethod
	public void takeScreenShotOnFailure(ITestResult testResult) throws IOException {
		Date d = new Date();
		String screenshotFile = d.toString().replace(":", "_").replace(" ", "_") + ".png";

		if (testResult.getStatus() == ITestResult.FAILURE) {
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File("errorScreenshots\\" + testResult.getName() + "-"
					+ Arrays.toString(testResult.getParameters()) + screenshotFile));
		}
	}

}
