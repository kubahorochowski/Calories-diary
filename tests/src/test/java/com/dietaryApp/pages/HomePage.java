package com.dietaryApp.pages;

import com.dietaryApp.helpers.SeleniumHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    @FindBy(xpath = "//a[text()='Logowanie']")
    private WebElement navLogin;

    @FindBy(xpath = "//a[text()='Moja dieta']")
    private WebElement navDiet;

    @FindBy(xpath = "//a[text()='Produkty']")
    private WebElement navProducts;

    @FindBy(xpath = "//h1[text()='Dziennik kalorii']")
    private WebElement appTitle;

    private SeleniumHelper helper;
    private WebDriver driver;

    public HomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.helper = new SeleniumHelper(driver);
        this.driver = driver;
    }

    public void checkIfDisplayed(){
        helper.waitForElementToBeDisplayed(appTitle);
    }

    public void goToLoginPage() {
        navLogin.click();
    }

    public void goToDietPage() {
        navDiet.click();
    }

    public void goToProductsPage() {
        navProducts.click();
    }

}
