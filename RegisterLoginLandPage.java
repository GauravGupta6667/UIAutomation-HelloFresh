package com.hellofresh.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class RegisterLoginLandPage {

	WebDriver driver;

	@FindBy(xpath = "//*[@id=\"nav-navigation\"]/section/div/ul[2]/li/li[1]/a/span/span[1]")
	public WebElement firstName;

	@FindBy(xpath = "//*[@id=\"nav-navigation\"]/section/div/ul[2]/li/li[3]/a/img")
	public WebElement hellofreshoptions;

	@FindBy(xpath = "//*[@id=\"nav-navigation\"]/section/div/ul[2]/li/li[3]/div/div[3]/div/a")
	public WebElement helloFresOurRecipeBoxoption;

	@FindBy(xpath = "//*[@id=\"nav-navigation\"]/section/ul/li/a/img")
	public WebElement hellofreshlogo;

	public RegisterLoginLandPage(WebDriver driver) {
		System.out.println("page object creation----");
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void clickOnHelloFreshOptions(WebDriver driver) {
		try {

			hellofreshoptions.click();
		} catch (Exception e) {
			Assert.fail("Hello Fresh options not loaded", e);
		}

	}

	public void clickOnHelloFreshOurRecipeBoxOptions(WebDriver driver) {
		helloFresOurRecipeBoxoption.click();
	}

	public void clickOnHelloFreshLogo(WebDriver driver) {
		hellofreshlogo.click();
	}

	public void verifyHomePageLoaded(WebDriver driver, String fname) {

		Assert.assertEquals(firstName.getText(), fname);
		System.out.println("Loin landing Page Verified--------");
	}

}
