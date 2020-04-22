package com.experitest.auto;

import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.experitest.appium.SeeTestClient;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;

public class IOSDemoTest extends BaseTest {
	protected IOSDriver<IOSElement> driver = null;
	//private SeeTestClient seetest;


	@BeforeMethod
	@Parameters("deviceQuery")
	public void setUp(@Optional("@os='ios'") String deviceQuery) throws Exception {
		init(deviceQuery);
		// Init application / device capabilities
		//dc.setCapability(MobileCapabilityType.APP, "cloud:com.experitest.ExperiBank");
		//dc.setCapability(IOSMobileCapabilityType.BUNDLE_ID, "com.experitest.ExperiBank");
		dc.setCapability(MobileCapabilityType.APP, "cloud:com.experitest.ExperiBank");
		dc.setCapability(IOSMobileCapabilityType.BUNDLE_ID, "com.experitest.ExperiBank");
		dc.setCapability("appVersion", "3180");
		
		dc.setCapability("testName", "IOSDemoTest");
		driver = new IOSDriver<>(new URL(getProperty("url",cloudProperties) + "/wd/hub"), dc);
		seetest = new SeeTestClient(driver);

	}

	@Test
	public void test() {
		SeeTestClient seetest = new SeeTestClient(driver);

		driver.findElement(in.Repo.obj("login.usernameTextField")).sendKeys("company");
		driver.findElement(in.Repo.obj("login.passwordTextField")).sendKeys("company");
		new WebDriverWait(driver, 30).until(ExpectedConditions.presenceOfElementLocated(in.Repo.obj("login.loginButton")));
		seetest.startPerformanceTransaction("");
		driver.findElement(in.Repo.obj("login.loginButton")).click();
		
		WebDriverWait wait = new WebDriverWait(driver, 20, 100);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='pBar']")));
		seetest.endPerformanceTransaction("WEB.Transaction");
		driver.findElement(in.Repo.obj("main.logoutButton")).click();
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
