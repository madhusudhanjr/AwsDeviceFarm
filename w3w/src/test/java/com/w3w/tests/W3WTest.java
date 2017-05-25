package com.w3w.tests;

import java.io.File;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.w3w.pageobjects.HomePage;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;

public class W3WTest {

	public static AppiumDriver<MobileElement> m_driver;
	public HomePage m_homePage = null;

	/**
	 * Run before each method
	 * 
	 * @throws MalformedURLException
	 */
	@BeforeMethod(alwaysRun = true)
	public void setUp(Method method) throws MalformedURLException {

		System.out.println("\n******Test Case Execution Satrted*******");

		DesiredCapabilities capabilities = new DesiredCapabilities();

		/**
		 * Below Capabilities to be added for executing tests locally
		 * 
		 * capabilities.setCapability("automationName", "XCUITest");
		 * capabilities.setCapability("platformName", "iOS");
		 * capabilities.setCapability("deviceName", "iPhone 6s");
		 * capabilities.setCapability("platformVersion", "10.0");
		 * capabilities.setCapability("app", "/Users/itc/Downloads/w3w.app");
		 * capabilities.setCapability("newCommandTimeout", 600);
		 * capabilities.setCapability("launchTimeout", 90000);
		 * capabilities.setCapability("autoAcceptAlerts", true);
		 */

		URL appiumURL = new URL("http://127.0.0.1:4723/wd/hub");
		m_driver = new IOSDriver<MobileElement>(appiumURL, capabilities);

		System.out.println("Intialize HomePage Object");
		m_homePage = new HomePage(m_driver);
	}

	@Test
	public void homePageTest() throws Exception {

		System.out.println("******Starting Test Case :: homePageTest");

		System.out.println("Handle Alert If exists");
		m_homePage.clickAlertOkBtn();
		m_homePage.clickAlertAllowkBtn();

		System.out.println("Verify Home Page buttons");
		int size = m_homePage.getHomePageButtons();
		Assert.assertTrue((size > 0), "Home Page Buttons NOT found!!");
		System.out.println("Home Page buttons verified successfully");
	}

	/**
	 * Run After each test method
	 */
	@AfterMethod(alwaysRun = true)
	public void tearDown(Method method) {

		System.out.println("Capture screenshot");
		takeScreenshot(method.getName());

		System.out.println("Driver Quit!");
		m_driver.quit();

		System.out.println("\n******Test Case Execution Completed!!*******");
	}

	/**
	 * Take Screenshot
	 * 
	 * @param name
	 *            file name
	 * @return true if successful
	 */
	private boolean takeScreenshot(final String name) {

		String screenshotDirectory = System.getProperty("appium.screenshots.dir");
		File screenshot = m_driver.getScreenshotAs(OutputType.FILE);
		return screenshot.renameTo(new File(screenshotDirectory, String.format("%s.png", name)));
	}
}
