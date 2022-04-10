package com.dietaryApp.pages;

import com.dietaryApp.helpers.SeleniumHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductPage {

    @FindBy(id = "displayProducts")
    private WebElement displayAllProdBtn;

    @FindBy(xpath = "//*[contains(text(), 'produkt')]")
    private WebElement pageTitle;

    @FindBy(xpath="//*[contains(text(), 'Aktualizuj')]")
    private WebElement updateBtn;


    private SeleniumHelper helper;
    private WebDriver driver;

    public ProductPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.helper = new SeleniumHelper(driver);
        this.driver = driver;
    }

    public void checkIfDisplayed() {
        helper.waitForElementToBeDisplayed(pageTitle);
    }

    public void displayAllProducts() throws InterruptedException {
        displayAllProdBtn.click();
        Thread.sleep(3000);
        helper.waitForElementToBeDisplayed(driver.findElement(By.xpath("//ul/li[9]")));
    }

    public void updateTestProduct(String value) {
        WebElement testProdElLi = driver.findElement(By.xpath("//ul[@class='productsList']/li//*[contains(text(),'test')]/../../.."));
        testProdElLi.findElement(By.xpath(".//i")).click();
        WebElement submitBtn = driver.findElement(By.id("submit"));
        helper.waitForElementToBeDisplayed(submitBtn);
        driver.findElement(By.id("kcal")).clear();
        driver.findElement(By.id("kcal")).sendKeys(value);
        submitBtn.click();
    }

    public boolean checkKcalOfTestProduct(String kcal) {
        WebElement testProdElLi = driver.findElement(By.xpath("//ul[@class='productsList']/li//*[contains(text(),'test')]/../../.."));
        String kcalValue = testProdElLi.findElement(By.xpath(".//div[@id='kcalVal']/p")).getText();
        System.out.println(kcal);
        System.out.println(kcalValue);
        return kcal.equals(kcalValue);

    }

}
