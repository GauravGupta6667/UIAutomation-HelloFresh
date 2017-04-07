package com.hellofresh.utils;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

public class HelloFreshSetupUtil {

	public WebDriver driver = null;

	public static String URL = null;

	public static String COUNTRY = null;

	@Parameters({ "country" })
	@BeforeMethod()
	public void setUpAndIntializeBrowser(String country) {

		// driver=new FirefoxDriver();
		COUNTRY = country;

		String userdir = System.getProperty("user.dir");
		System.setProperty("webdriver.chrome.driver", userdir + "/drivers/chromedriver.exe");

		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		if (country.equals("UK")) {

			URL = "https://www.hellofresh.co.uk/tasty/";

		} else if (country.equals("US")) {

			URL = "https://www.hellofresh.com/tasty/";

		}
		System.out.println("the url selected is----" + URL);

		driver.get(URL);

	}

	@AfterMethod()
	public void tearDown() {

		driver.close();
		driver.quit();
	}

}
