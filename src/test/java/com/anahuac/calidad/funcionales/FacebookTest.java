package com.anahuac.calidad.funcionales;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FacebookTest {
	private WebDriver driver;
	private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();
	JavascriptExecutor js;
	
	
	@Before
	public void setUp() throws Exception {
		WebDriverManager.chromedriver().setup();
	    driver = new ChromeDriver();
	    baseUrl = "https://es-la.facebook.com/";
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
	 public void testWrongPassword() throws Exception {
	    driver.get(baseUrl); 
	    driver.findElement(By.name("email")).clear();
	    driver.findElement(By.name("email")).sendKeys("sdfd");
	    driver.findElement(By.name("pass")).clear();
	    driver.findElement(By.name("pass")).sendKeys("4567890");
	    driver.findElement(By.name("login")).click();
	    pause(1000);
	    String AlertMessage = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[1]/div/div[2]/div[2]/form/div/div[1]/div[2]")).getText();
	    assertThat("El correo electrónico o número de celular que ingresaste no está conectado a una cuenta. Encuentra tu cuenta e inicia sesión.", is(AlertMessage));
	 }
	 
	 @Test
	 public void testSuccesfullLogIn() throws Exception {
	    driver.get(baseUrl); 
	    driver.findElement(By.name("email")).clear();
	    driver.findElement(By.name("email")).sendKeys("gleo2519@gmail.com");
	    driver.findElement(By.name("pass")).clear();
	    driver.findElement(By.name("pass")).sendKeys("347kvy347");
	    driver.findElement(By.name("login")).click();
	    pause(1000);
	    assertThat("Facebook", is(driver.getTitle()));
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
	 
	 private void pause(long mils) {
		  try {
			  Thread.sleep(mils);
		  }catch(Exception e){
			  e.printStackTrace();
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
