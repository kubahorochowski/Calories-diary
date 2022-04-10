package com.dietaryApp.tests;

import com.dietaryApp.helpers.DriverFactory;
import com.dietaryApp.helpers.DriverType;
import com.dietaryApp.helpers.NoSuchDriverException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class BaseSeleniumTest {

    protected static WebDriver driver;

    @BeforeMethod
    public static void setUp() throws NoSuchDriverException {
        System.out.println("Before");
        String driverPath = "C:\\Users\\kubah\\Desktop\\Inz\\tests\\src\\main\\resources\\executables.drivers\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", driverPath);
        driver = DriverFactory.getDriver(DriverType.CHROME);
        driver.get("http://localhost:4200/home");
    }

    @AfterMethod
    public static void tearDown() {
        System.out.println("After");
        driver.quit();
        DriverFactory.resetDriver();
    }

}
