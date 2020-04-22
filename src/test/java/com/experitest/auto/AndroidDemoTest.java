package com.experitest.auto;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;

import com.experitest.appium.SeeTestClient;


public class AndroidDemoTest extends BaseTest {
	protected AndroidDriver<AndroidElement> driver = null;
	protected final String DEFAULT_QUERY = "@os='android'";
	Application app;
	@BeforeMethod
	public void setUp1() throws Exception {
		app = new Application();


	}

//	@Test
//	@Parameters({ "deviceQuery" })
//	public void login(@Optional(DEFAULT_QUERY) String deviceQuery) throws Exception {
//
//		app.init(deviceQuery, "Login");
//		app.login("company", "company");
//		app.logout();
//	}
//	@Test
//	@Parameters({ "deviceQuery" })
//	public void makePayment(@Optional(DEFAULT_QUERY) String deviceQuery) throws Exception {
//
//		app.init(deviceQuery, "Make Payment");
//		app.login("company", "company");
//		app.makePayment("123456", "guy", 10, "USA");
//		app.logout();
//	}

	@Test
	@Parameters({ "deviceQuery" })
	public void removeElementFromBucket(@Optional(DEFAULT_QUERY) String deviceQuery) throws Exception {
		app.init(deviceQuery, "Remove Element");


		Given: {
			// init
			app.login("company", "company");

			ApplicationApi api = new ApplicationApi();
			api.login("company", "company");


			// clear bucket
			for(Product product: api.getBucket()){
				api.removeBucketElement(product.getId());
			}

			// have 4 product in the bucket
			api.addProduct("P1", 1);
			api.addProduct("P2", 2);
			api.addProduct("P3", 3);
			api.addProduct("P4", 4);
		}

		When: {
			// One product is removed
			app.removeProduct("P4");
		}
		Then: {
			Assert.assertEquals(app.getProductsCount(), 3, "Wrong number of product. Expected 3");
		}

		app.logout();
	}

	@AfterMethod
	public void tearDown(){
		app.close();
	}
	
}
