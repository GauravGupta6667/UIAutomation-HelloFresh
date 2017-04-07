package com.hellofresh.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import com.hellofresh.utils.HelloFreshSetupUtil;

public class RecipeBoxAndCheckOutPage extends HelloFreshSetupUtil {

	WebDriver driver;

	@FindBy(xpath = "//*[@id=\"food-boxes\"]/div/div[1]/div/h1")
	public WebElement ourRecipeBoxesText;

	@FindBy(xpath = "//*[@id=\"product-detail\"]/div/div/div[3]/div/div/div/section/div/h4/hf-total/span/span")
	public WebElement classicBoxPriceBeforeCheckout;

	@FindBy(id = "add-to-cart-button")
	public WebElement continuAndAddToCartBUtton;

	@FindBy(id = "classic-box-product-detail-button")
	public WebElement classicBoxViewBox;
	
	@FindBy(id = "classic-menu-product-detail-button")
	public WebElement USClassicBoxViewBx;

	@FindBy(xpath = "//*[@id=\"product-detail\"]/div/div/div[2]/hf-product-detail-card/div/div/div/h3[2]")
	public WebElement boxSelectedBeforeCheckOutAndContinue;

	@FindBy(xpath = "//*[@id=\"ginger\"]/div[2]/div[2]/div/div[2]/div/hf-order-summary-detailed/section/span/div/div[1]/strong")
	public WebElement boxSelecionOnCheckoutPage;

	@FindBy(xpath = "//*[@id=\"ginger\"]/div[2]/div[2]/div/div[2]/div/div[3]/div[2]/p/strong")
	public WebElement priceOfBoxOnCheckoutPge;

	public RecipeBoxAndCheckOutPage(WebDriver driver) {
		System.out.println("page object creation----");
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void verifyRecipeBoxAndCheckOutPageLoaded(WebDriver driver) {
		try {

			if (COUNTRY.equals("US")) {
				Assert.assertEquals(ourRecipeBoxesText.getText(), "Our Meal Plans");
			} else {
				Assert.assertEquals(ourRecipeBoxesText.getText(), "Our Recipe Boxes");
				System.out.println(" RecipeBoxAndCheckOut Page Verified--------");
			}
		} catch (Exception e) {
			Assert.fail("Checkout page not loaded", e);
		}
	}

	public void clickClassicBoxViewBoxAndAddBoxToCart() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			if(COUNTRY.equals("UK")){
				wait.until(ExpectedConditions.visibilityOf(classicBoxViewBox));
				if (driver.findElements(By.xpath("//*[@id='cookiesDisclaimer']/div/button")).size() > 0) {
					driver.findElement(By.xpath("//*[@id='cookiesDisclaimer']/div/button")).click();
				}
				classicBoxViewBox.click();	
			}
			else {
				wait.until(ExpectedConditions.visibilityOf(USClassicBoxViewBx));
				if (driver.findElements(By.xpath("//*[@id='cookiesDisclaimer']/div/button")).size() > 0) {
					driver.findElement(By.xpath("//*[@id='cookiesDisclaimer']/div/button")).click();
				}
				USClassicBoxViewBx.click();
			}

			String priceOfBoxBeforeCheckout = classicBoxPriceBeforeCheckout.getText();

			String boxSelectedBeforeCheckOutContinue = boxSelectedBeforeCheckOutAndContinue.getText();

			System.out.println("box selectiono---" + boxSelectedBeforeCheckOutContinue);

			continuAndAddToCartBUtton.click();

			Assert.assertEquals(boxSelecionOnCheckoutPage.getText(), boxSelectedBeforeCheckOutContinue);

			Assert.assertEquals(priceOfBoxOnCheckoutPge.getText(), priceOfBoxBeforeCheckout);

		} catch (Exception e) {
			Assert.fail("Not able to add the item to Cart");
		}
	}

}
