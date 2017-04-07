package com.hellofresh.test;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import org.testng.annotations.Test;
import com.hellofresh.pageobject.HomePage;
import com.hellofresh.pageobject.RecipeBoxAndCheckOutPage;
import com.hellofresh.pageobject.RegisterLoginLandPage;
import com.hellofresh.utils.HelloFreshSetupUtil;
import com.hellofresh.utils.JsonReader;

public class RegisterUserAndLogIn extends HelloFreshSetupUtil {

	private String email = null;
	private String password = null;

	@Test(priority = 1)
	public void registerUser() {

		Random randomnumber = new Random();
		int number = randomnumber.nextInt(1000);

		String random = String.format("%d", number);
		try {
			// test register user
			HomePage homepage = new HomePage(driver);

			homepage.verifyHomePageLoaded(driver);

			homepage.clickLoginButton(driver);

			Map<String, String> registerusermap = new HashMap<String, String>();
			registerusermap = JsonReader.getRegisterUserJsonData();

			email = registerusermap.get("email") + random;
			password = registerusermap.get("password");
			homepage.clickRegisterUserlink(driver);

			homepage.registerUser(registerusermap, random);

			RegisterLoginLandPage loggedInpage = new RegisterLoginLandPage(driver);

			loggedInpage.verifyHomePageLoaded(driver, registerusermap.get("firstname") + random);

			System.out.println("email------" + email);

			System.out.println("password-----" + password);

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test(priority = 2)
	public void logInAndAddToBasket() {
		try {
			HomePage homepage = new HomePage(driver);

			homepage.verifyHomePageLoaded(driver);

			homepage.clickLoginButton(driver);

			System.out.println(email);

			System.out.println(password);

			homepage.loginAsRegisteredUser(email + "@gmail.com", password);

			Thread.sleep(10000);

			RegisterLoginLandPage registerLoginLandPage = new RegisterLoginLandPage(driver);

			registerLoginLandPage.clickOnHelloFreshLogo(driver);

			registerLoginLandPage.clickOnHelloFreshOptions(driver);

			registerLoginLandPage.clickOnHelloFreshOurRecipeBoxOptions(driver);

			RecipeBoxAndCheckOutPage recipeBoxAndCheckOutPage = new RecipeBoxAndCheckOutPage(driver);

			recipeBoxAndCheckOutPage.verifyRecipeBoxAndCheckOutPageLoaded(driver);

			recipeBoxAndCheckOutPage.clickClassicBoxViewBoxAndAddBoxToCart();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
