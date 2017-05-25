package com.w3w.pageobjects;

import java.util.List;

import org.openqa.selenium.WebElement;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.iOSFindBy;

/**
 * HomePage Class contains info on WebElements and its Operations
 * 
 * @author Tester
 */
public class HomePage extends BasePage {

	@iOSFindBy(xpath = "//*[@name='OK']")
	List<MobileElement> alertOKBtn;

	@iOSFindBy(xpath = "//*[@name='Allow']")
	List<MobileElement> alertAllowBtn;

	@iOSFindBy(xpath = "//*[@type='XCUIElementTypeButton']")
	List<MobileElement> homePageBtns;

	public HomePage(AppiumDriver driver) {

		super(driver);
	}

	/**
	 * This method clicks on "OK" button of Alert, if exists.
	 */
	public void clickAlertOkBtn() {

		System.out.println("Click Alert OK Button");
		waitInSeconds(2);

		for (MobileElement alert : alertOKBtn) {

			if (alert.isDisplayed()) {
				alert.click();
				break;
			}
		}
	}

	/**
	 * This method clicks on "Allow" button of Alert, if exists.
	 */
	public void clickAlertAllowkBtn() {

		System.out.println("Click Alert Allow Button");
		waitInSeconds(2);

		for (MobileElement alert : alertAllowBtn) {

			if (alert.isDisplayed()) {
				alert.click();
				break;
			}
		}
	}

	/**
	 * This method gets and prints all the buttons of HomePage.
	 */
	public int getHomePageButtons() {

		System.out.println("Finding All Buttons on Home Page");
		waitInSeconds(2);

		if (homePageBtns.isEmpty()) {
			
			homePageBtns = driver.findElementsByXPath("//*[@type='UIAButton']");
		}
		
		System.out.println("Source Code::" + driver.getPageSource());
		System.out.println("Buttons Size::" + homePageBtns + "  ****  " +  homePageBtns.size());
		
		for (WebElement buttonEle : homePageBtns) {

			System.out.println("Button Found:: " + buttonEle.getAttribute("name"));

		}

		return homePageBtns.size();
	}

	private void waitInSeconds(long sec) {

		try {
			Thread.sleep(sec * 1000);
		} catch (Exception e) {
			System.out.println("Error!!");
		}
	}

}
