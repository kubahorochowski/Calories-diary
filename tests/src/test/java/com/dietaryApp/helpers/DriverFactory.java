package com.dietaryApp.helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.GeckoDriverService;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerDriverService;

import java.io.File;

public class DriverFactory {

    private static WebDriver driverInstance;

    public static WebDriver getDriver(DriverType driverType) throws NoSuchDriverException {
        if(driverInstance == null){
            driverInstance = getSpecificDriver(driverType);
            driverInstance.manage().window().maximize();
        }
        return driverInstance;
    }

    private static WebDriver getSpecificDriver(DriverType driverType) throws NoSuchDriverException {
        switch(driverType){
            case IE:
                File ieExe = new File("C:\\Users\\kubah\\Desktop\\Inz\\tests\\src\\main\\resources\\executables.drivers\\IEDriverServer.exe");
                InternetExplorerDriverService ieService = new InternetExplorerDriverService.Builder().usingDriverExecutable(ieExe).usingAnyFreePort().build();
                driverInstance = new InternetExplorerDriver(ieService);
                break;
            case CHROME:
                File chromeExe = new File("C:\\Users\\kubah\\Desktop\\Inz\\tests\\src\\main\\resources\\executables.drivers\\chromedriver.exe");
                ChromeDriverService chromeService = new ChromeDriverService.Builder().usingDriverExecutable(chromeExe).usingAnyFreePort().build();
                driverInstance = new ChromeDriver(chromeService);
                break;
            case FIREFOX:
                File firefoxExe = new File("C:\\Users\\kubah\\Desktop\\Inz\\tests\\src\\main\\resources\\executables.drivers\\geckodriver.exe");
                GeckoDriverService firefoxService = new GeckoDriverService .Builder().usingDriverExecutable(firefoxExe).usingAnyFreePort().build();
                driverInstance = new FirefoxDriver(firefoxService);
                break;
            default:
                System.out.println("Brak drivera podanego typu");
                throw new NoSuchDriverException();
        }
        return driverInstance;
    }

    public static void resetDriver() {
        driverInstance = null;
    }
}
