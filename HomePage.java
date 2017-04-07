package com.hellofresh.pageobject;

import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import com.hellofresh.utils.HelloFreshSetupUtil;

public class HomePage extends HelloFreshSetupUtil {

	WebDriver driver;

	@FindBy(id = "banner-view-plans-button")
	public WebElement viewOurPlans;

	@FindBy(id = "login-button")
	public WebElement logInBUtton;

	@FindBy(id = "register-user-link")
	public WebElement registerUserLink;

	@FindBy(id = "gender-input")
	public WebElement genderSelectBox;

	@FindBy(id = "first-name-input")
	public WebElement firstName;

	@FindBy(id = "last-name-input")
	public WebElement lastName;

	@FindBy(id = "email-input")
	public WebElement email;

	@FindBy(id = "password-input")
	public WebElement password;

	@FindBy(id = "month-input")
	public WebElement birthmonth;

	@FindBy(id = "day-input")
	public WebElement birthday;

	@FindBy(id = "year-input")
	public WebElement birthyear;

	@FindBy(id = "register-button")
	public WebElement registerBUtton;

	@FindBy(id = "email-input")
	public WebElement emailFieldToLogin;

	@FindBy(id = "password-input")
	public WebElement passwordFieldToLogin;

	@FindBy(id = "submit-login-button")
	public WebElement logIn;

	public HomePage(WebDriver driver) {
		System.out.println("page object creation----");
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void verifyHomePageLoaded(WebDriver driver) {
		try {
			System.out.println("before verify page load");
			if (COUNTRY.equals("US")) {
				Assert.assertEquals(viewOurPlans.getText(), "View our plans");
			} else {
				Assert.assertEquals(viewOurPlans.getText(), "View Our Plans");
				System.out.println("Home Page Verified");
			}
		} catch (Exception e) {
			Assert.fail("Home Page not loaded", e);
		}
	}

	public void clickLoginButton(WebDriver driver) {
		try {
			logInBUtton.click();
		} catch (Exception e) {
			Assert.fail("Login button not Clickable", e);
		}
	}

	public void clickRegisterUserlink(WebDriver driver) throws InterruptedException {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.visibilityOf(registerUserLink));
			registerUserLink.click();

			System.out.println("clicked register user link------");

		} catch (Exception e) {
			Assert.fail("User not able to register", e);
		}
	}

	public void registerUser(Map<String, String> registerusermap, String random) throws Exception {
		try {
			Select select = new Select(driver.findElement(By.id("gender-input")));
			select.selectByVisibleText(registerusermap.get("gender"));

			firstName.sendKeys(registerusermap.get("firstname") + random);
			System.out.println("***************" + firstName);

			lastName.sendKeys(registerusermap.get("lastname") + random);
			email.sendKeys(registerusermap.get("email") + random + "@gmail.com");
			password.sendKeys(registerusermap.get("password"));
			birthmonth.sendKeys(registerusermap.get("birthmonth"));
			birthday.sendKeys(registerusermap.get("birthday"));
			birthyear.sendKeys(registerusermap.get("birthyear"));

			registerBUtton.submit();

		} catch (Exception e) {
			Assert.fail("Registraion details incorrect", e);
		}
	}

	public void loginAsRegisteredUser(String useremail, String password) {
		try {

			System.out.println("email-----" + useremail);
			System.out.println("password----" + password);
			emailFieldToLogin.sendKeys(useremail);

			passwordFieldToLogin.sendKeys(password);

			logIn.submit();
		} catch (Exception e) {
			Assert.fail("Login failed", e);
		}
	}

}
