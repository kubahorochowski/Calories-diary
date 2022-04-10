package com.dietaryApp.pages;

import com.dietaryApp.helpers.SeleniumHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class DietPage {


    @FindBy(xpath="//app-diet-item")
    private List<WebElement> dietsElements;

    @FindBy(xpath="//*[contains(text(), 'Sprawdź dodane produkty')]")
    private WebElement checkDiet;

    @FindBy(xpath="//input[@name='product_name']")
    private WebElement productNameInput;

    private SeleniumHelper helper;
    private WebDriver driver;

    public DietPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.helper = new SeleniumHelper(driver);
        this.driver = driver;
    }

    public void checkIfDisplayed() {
        helper.waitForElementToBeDisplayed(checkDiet);
    }

    public Integer checkForDiets() {
        return dietsElements.size();
    }

    public void addProduct(String name, String weight) throws InterruptedException {
        productNameInput.clear();
        productNameInput.sendKeys(name);
        WebElement productBtn = driver.findElement(By.xpath("//button[@name='productButton']"));
        productBtn.click();
        WebElement weightInput = driver.findElement(By.id("productWeight"));
        helper.waitForElementToBeDisplayed(weightInput);
        weightInput.clear();
        weightInput.sendKeys(weight);
        Thread.sleep(1000);
        driver.findElement(By.id("submitWeight")).click();
        Thread.sleep(1000);
    }

    public void deleteDiet(int index) {
        List<WebElement> dietList = driver.findElements(By.xpath("//app-diet-item//i"));
        if (index <= dietList.size()) {
            dietList.get(index-1).click();
        }
    }

}
