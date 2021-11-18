package com.anahuac.calidad.funcionales;


import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.apache.commons.io.FileUtils;
import java.io.File;

public class GoogleTest {
	private WebDriver driver;
	private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();
	JavascriptExecutor js;
	
	
	@Before
	public void setUp() throws Exception {
		WebDriverManager.chromedriver().setup();
	    driver = new ChromeDriver();
	    baseUrl = "https://www.google.com/";
	    driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	    js = (JavascriptExecutor) driver;
	  }
	  
	@After
	public void tearDown() throws Exception {
		driver.quit();
	    String verificationErrorString = verificationErrors.toString();
	    if (!"".equals(verificationErrorString)) {
	      fail(verificationErrorString);
	    }
	 }
	  
	 @Test
	 public void testBusqueda() throws Exception {
	    driver.get("https://www.google.com/search?q=covid+2021&rlz=1C1GCEU_esMX967MX967&oq=covid+2021&aqs=chrome.0.69i59j0i512l9.3982j0j15&sourceid=chrome&ie=UTF-8");
	    driver.findElement(By.xpath("//div[@id='kp-wp-tab-overview']/div[7]/div/div[2]/div/div/div/div/div/div/div/div/a/h3")).click();
	    assertThat("Coronavirus – gob.mx", is(driver.getTitle()));
	 }

	 private boolean isElementPresent(By by) {
		 try {
			 driver.findElement(by);
			 return true;
		 } catch (NoSuchElementException e) {
			 return false;
		 }
	 }

	 private boolean isAlertPresent() {
		 try {
			 driver.switchTo().alert();
			 return true;
	    } catch (NoAlertPresentException e) {
	    	return false;
	    }
	 }

	 private String closeAlertAndGetItsText() {
		 try {
			 Alert alert = driver.switchTo().alert();
			 String alertText = alert.getText();
			 if (acceptNextAlert) {
				 alert.accept();
			 } else {
				 alert.dismiss();
			 }
			 return alertText;
		 } finally {
	      acceptNextAlert = true;
		 }
	 }
}

