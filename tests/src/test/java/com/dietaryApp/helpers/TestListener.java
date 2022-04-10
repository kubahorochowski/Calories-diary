package com.dietaryApp.helpers;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

public class TestListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("On test start");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("On test success");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        try {
            System.out.println("On test failure");
            SeleniumHelper.takeScreeenshot(DriverFactory.getDriver(DriverType.CHROME));
        } catch (IOException | NoSuchDriverException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("On test skipped");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        System.out.println("On test failure but within seccess percentage");
    }

    @Override
    public void onStart(ITestContext iTestContext){
        System.out.println("On start");
    }

    @Override
    public void onFinish(ITestContext iTestContext){
        System.out.println("On finish");
    }

}
