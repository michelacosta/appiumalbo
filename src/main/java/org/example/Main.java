package org.example;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URI;
import java.time.Duration;

public class Main {
    private static AndroidDriver driver;
    private WebDriverWait waitDriver;

    public static void main(String[] args){
        setup();
        WebDriverWait waitDriver = new WebDriverWait(driver, Duration.ofSeconds(5));
        try {
            WebElement el = driver.findElement(AppiumBy.xpath("//android.widget.Button[@resource-id=\"android:id/button1\"]"));
            el.click();

            WebElement el4 = driver.findElement(AppiumBy.xpath("//android.widget.Button[@content-desc=\"Add Contact\"]"));
            el4.click();

            waitDriver.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"com.example.android.contactmanager:id/contactNameEditText\"]")));

            WebElement el5 = driver.findElement(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"com.example.android.contactmanager:id/contactNameEditText\"]"));
            el5.sendKeys("Test");

            WebElement el6 = driver.findElement(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"com.example.android.contactmanager:id/contactPhoneEditText\"]"));
            el6.sendKeys("111111");

            WebElement el7 = driver.findElement(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"com.example.android.contactmanager:id/contactEmailEditText\"]"));
            el7.sendKeys("test@test.com");

            WebElement el8 = driver.findElement(AppiumBy.xpath("//android.widget.Button[@content-desc=\"Save\"]"));
            el8.click();

            System.out.println(driver.getPageSource());
        } finally {
            driver.quit();
        }
    }

    public static void setup() {
        UiAutomator2Options uiAuto = new UiAutomator2Options()
                .setApp("/Users/michelacosta/Documents/ContactManager.apk")
                .setDeviceName("Pixel 7 Pro API 29")
                .setPlatformName(String.valueOf(Platform.ANDROID))
                .setPlatformVersion("10");

        try {
            driver = new AndroidDriver(URI.create("http://localhost:4723").toURL(), uiAuto);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}