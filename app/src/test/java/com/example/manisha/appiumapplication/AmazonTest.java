package com.example.manisha.appiumapplication;
import io.appium.java_client.android.AndroidDriver;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class AmazonTest {
    private static AndroidDriver driver;

	public static void main(String[] args) throws MalformedURLException {
		File classpathRoot = new File(System.getProperty("user.dir"));
		File appDir = new File(classpathRoot, "/Apps/Amazon/");
		File app = new File(appDir, "in.amazon.mShop.android.shopping.apk");

		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
		capabilities.setCapability("deviceName", "Xiaomi MI 5");
		capabilities.setCapability("platformVersion", "7.0");
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("app", app.getAbsolutePath());
		capabilities.setCapability("appPackage", "in.amazon.mShop.android.shopping");
		capabilities.setCapability("appActivity", "com.amazon.mShop.home.HomeActivity");

		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);

		// Click on Shop by Deparment link
		driver.findElementById("in.amazon.mShop.android.shopping:id/web_home_shop_by_department_label").click();
		// Click on Main menu
		driver.findElementByClassName("android.widget.ImageView").click();
		// Click on Home link under Main menu
		driver.findElement(By.name("Home")).click();
		// Click on Sign In link on the Home Screen
		driver.findElementByName("Sign inHello. Link").click();

		// Entering UserName using Parent node strategy
		WebElement parentElement = driver.findElement(By.name("Amazon Sign In"));
		List<WebElement> childElements = parentElement.findElements(By.className("android.view.View"));
		WebElement mainElement = childElements.get(4);
		mainElement.findElement(By.className("android.widget.EditText")).sendKeys("Your_UserName");
		// Entering Password using Xpath & Sibling strategy
		driver.findElementByXPath("//android.view.View[@content-desc='Password']/following-sibling::android.view.View/android.widget.EditText").sendKeys("Your_Password");
		// Click on Sign In button
		driver.findElement(By.name("Sign in")).click();
		// This is to kill the Android driver
		driver.quit();

	}
}
