package com.experitest.auto;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import com.experitest.appium.SeeTestClient;
import org.openqa.selenium.remote.DesiredCapabilities;

public class BaseTest {

	protected DesiredCapabilities dc = new DesiredCapabilities();
	protected Properties cloudProperties = new Properties();
	public SeeTestClient seetest;


	public void init(String deviceQuery) throws Exception {
		initCloudProperties();
		String adhocDeviceQuery = System.getenv("deviceQuery");
		if (adhocDeviceQuery != null) {
			System.out.println("[INFO] Redirecting test to the current device.");
			deviceQuery = adhocDeviceQuery;
		}
		dc.setCapability("deviceQuery", deviceQuery);
		dc.setCapability("reportDirectory", "reports");
		dc.setCapability("reportFormat", "xml");
		String accessKey = getProperty("accessKey", cloudProperties);
		if (accessKey != null && !accessKey.isEmpty()) {
			dc.setCapability("accessKey", accessKey);
		} else {
			dc.setCapability("user", getProperty("username", cloudProperties));
			dc.setCapability("password", getProperty("password", cloudProperties));
		}
		// In case your user is assign to a single project leave empty,
		// otherwise please specify the project name
//		dc.setCapability("project", getProperty("project", cloudProperties));
	}

	protected String getProperty(String property, Properties props) {
		if (System.getProperty(property) != null) {
			return System.getProperty(property);
		} else if (System.getenv().containsKey(property)) {
			return System.getenv(property);
		} else if (props != null) {
			return props.getProperty(property);
		}
		return null;
	}

	private void initCloudProperties() throws FileNotFoundException, IOException {
		FileReader fr = new FileReader("cloud.properties");
		cloudProperties.load(fr);
		fr.close();
	}
}
