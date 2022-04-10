package com.dietaryApp.pages;

import com.dietaryApp.helpers.SeleniumHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    @FindBy(xpath = "//*[text()='Logowanie']")
    private WebElement pageTitle;

    @FindBy(xpath = "//input[@name='user_name']")
    private WebElement loginInput;

    @FindBy(xpath = "//input[@name='user_password']")
    private WebElement passwordInput;

    @FindBy(xpath = "//button/span[contains(text(), 'ZALOGUJ')]")
    private WebElement submitBtn;

    private SeleniumHelper helper;

    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.helper = new SeleniumHelper(driver);
    }

    public void checkIfDisplayed() {
        helper.waitForElementToBeDisplayed(pageTitle);
    }

    public void login(String login, String password) {
        loginInput.clear();
        loginInput.sendKeys(login);
        passwordInput.clear();
        passwordInput.sendKeys(password);
        submitBtn.click();
    }

}
