package com.dietaryApp.tests;

import com.dietaryApp.helpers.TestListener;
import com.dietaryApp.pages.DietPage;
import com.dietaryApp.pages.HomePage;
import com.dietaryApp.pages.LoginPage;
import com.dietaryApp.pages.ProductPage;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.Date;


@Listeners(TestListener.class)
public class DietaryAppTest extends BaseSeleniumTest {

    String login = "test";


    @Test
    public void addDiet() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        DietPage dietPage = new DietPage(driver);

        homePage.checkIfDisplayed();
        homePage.goToLoginPage();

        loginPage.checkIfDisplayed();
        loginPage.login(login, "test");

        homePage.checkIfDisplayed();
        homePage.goToDietPage();

        dietPage.checkIfDisplayed();
        int numberOfElements = dietPage.checkForDiets();
        dietPage.addProduct("jajko", "20");
        int numberOfElementsAfterAdd = dietPage.checkForDiets();
        Assert.assertEquals(numberOfElementsAfterAdd, numberOfElements+1);
        dietPage.deleteDiet(1);
        int numberOfElementsAfterDelete = dietPage.checkForDiets();
        Assert.assertEquals(numberOfElementsAfterDelete, numberOfElements);

    }

    @Test
    public void updateProduct() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        ProductPage productPage = new ProductPage(driver);

        String kcal = String.valueOf(((new Date().getTime()) % 10000));

        homePage.checkIfDisplayed();
        homePage.goToLoginPage();

        loginPage.checkIfDisplayed();
        loginPage.login(login, "test");

        homePage.checkIfDisplayed();
        homePage.goToProductsPage();

        productPage.checkIfDisplayed();
        productPage.displayAllProducts();
        productPage.updateTestProduct(kcal);
        productPage.displayAllProducts();
        Assert.assertTrue(productPage.checkKcalOfTestProduct(kcal));

    }

}
