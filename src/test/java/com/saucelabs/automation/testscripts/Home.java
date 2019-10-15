package com.saucelabs.automation.testscripts;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.Test;

import com.saucelabs.automation.constants.FileConstants;
import com.saucelabs.automation.helper.SeleniumHelper;
import com.saucelabs.automation.helper.ValidaterHelper;
import com.saucelabs.automation.testbase.TestBase;
import com.saucelabs.automation.utils.PropertiesReader;

public class Home extends TestBase {
	SeleniumHelper help=new SeleniumHelper();
	PropertiesReader propread=new PropertiesReader();
	ValidaterHelper validate=new ValidaterHelper();
	
	@Test
	public void pricingMethod() throws IOException, InterruptedException {
		logger=extentObject.startTest("pricing");
		Properties prop=propread.KeyValueLoader(FileConstants.HOME);
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		help.mouseOver(prop.getProperty("loc.menu.pricing"), driver);
		help.clickElement(driver, prop.getProperty("loc.menu.pricing"));
		if(!driver.findElement(help.matchElement(prop.getProperty("loc.menu.pricing"))).isSelected())
		{
			String anualbefore=validate.textOfElement(driver, prop.getProperty("loc.anualy.pricing"));
			System.out.println(anualbefore);
			if(anualbefore.contentEquals("Billed annually")) {
				help.clickElement(driver, prop.getProperty("loc.annually.button"));
				String anualafter=validate.textOfElement(driver, prop.getProperty("loc.anualy.pricing"));
				System.out.println(anualafter);
			}	
		}
		
	}

}
