package com.anahuac.calidad.CRUDFuncionales;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
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
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class mernCRUDTest {
	private WebDriver driver;
	private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();
	JavascriptExecutor js;
	
	
	@Before
	public void setUp() throws Exception {
		WebDriverManager.chromedriver().setup();
	    driver = new ChromeDriver();
	    baseUrl = "https://mern-crud.herokuapp.com/";
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
	 
	
	 // Add person 
	 @Test
	 public void testmernAdd() throws Exception {
		// Get the base URL
		driver.get("https://mern-crud.herokuapp.com/");
		// Action click on Add New
	    driver.findElement(By.xpath("//div[@id='root']/div/div[2]/button")).click();//"//div[@id='root']/div/div[2]/button"
	    // Set name field
	    driver.findElement(By.name("name")).click();
	    driver.findElement(By.name("name")).clear();
	    driver.findElement(By.name("name")).sendKeys("Leo G");
	    // Set email field 
	    driver.findElement(By.name("email")).click();
	    driver.findElement(By.name("email")).clear();
	    driver.findElement(By.name("email")).sendKeys("gleo8181@gmail.com");
	    // Set age field
	    driver.findElement(By.name("age")).click();
	    driver.findElement(By.name("age")).clear();
	    driver.findElement(By.name("age")).sendKeys("20");
	    // Set gender field
	    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Gender'])[2]/following::div[1]")).click();
	    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Male'])[1]/following::span[1]")).click();
	    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Woah!'])[1]/following::button[1]")).click();
	    // Make a pause in order for the server to catch up with the code
	    pause(5000);
	    // Verify presence of texts
	    String tag = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/form/div[4]/div/div")).getText();
	    assertThat("Nice one!", is(tag));
	 }
	 
	 // Delete person tests
	 @Test
	 public void testmernDelete() throws Exception {
		// Get the base URL
		driver.get("https://mern-crud.herokuapp.com/");
	    // Action on Delete 
	    driver.findElement(By.xpath("/html/body/div/div/div[2]/table/tbody/tr[1]/td[5]/button[2]")).click(); 
	    // Action on Yes 
	    driver.findElement(By.xpath("/html/body/div[2]/div/div[3]/button[1]")).click(); 
	    // Make a pause in order for the server to catch up with the code
	    pause(5000);
	    String FirstUser = driver.findElement(By.xpath("/html/body/div/div/div[2]/table/tbody/tr/td[1]")).getText();
	    assertThat("Leo Garcia", is(not(FirstUser)));

	 }
	 
	 // Update person tests
	 @Test
	 public void testmernUpdate() throws Exception {
		// Get the base URL
		driver.get("https://mern-crud.herokuapp.com/");
	    // Action on Edit
	    driver.findElement(By.xpath("/html/body/div/div/div[2]/table/tbody/tr[1]/td[5]/button[1]")).click(); 
	    // Update email field 
	    driver.findElement(By.name("email")).clear();
	    driver.findElement(By.name("email")).click();
	    driver.findElement(By.name("email")).clear();
	    pause(2000);
	    driver.findElement(By.name("email")).sendKeys("jajajaj@gmail.com");
	    // Save the update
	    driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/form/button")).click();
	    // Make a pause in order for the server to catch up with the code
	    pause(5000);
	    // Verify presence of texts
	    String tag = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/form/div[4]/div/div")).getText();
	    assertThat("Nice one!", is(tag));

	 }
	 
	// Search person fields
	 @Test
	 public void testmernSearch() throws Exception {
		 // Expected values
		 String Name = "Leo G";
		 String Email = "gleo@gmail.com";
		 String Edad = "20";
		 String Genero = "m"; 
		 
		 // Get the base URL
		 driver.get("https://mern-crud.herokuapp.com/");
		 
		 // Retrieve values of the table
		 String RetrievedName = driver.findElement(By.xpath("/html/body/div/div/div[2]/table/tbody/tr[1]/td[1]")).getText();
		 String RetrievedEmail = driver.findElement(By.xpath("/html/body/div/div/div[2]/table/tbody/tr[1]/td[2]")).getText(); 
		 String RetrievedEdad = driver.findElement(By.xpath("/html/body/div/div/div[2]/table/tbody/tr[1]/td[3]")).getText(); 
		 String RetrievedGenero = driver.findElement(By.xpath("/html/body/div/div/div[2]/table/tbody/tr[1]/td[4]")).getText(); 
	    
	    // Make a pause in order for the server to catch up with the code
	    pause(5000);
	    // Verify presence of texts
	    assertThat(Name, is(RetrievedName));
	    assertThat(Email, is(RetrievedEmail));
	    assertThat(Edad, is(RetrievedEdad));
	    assertThat(Genero, is(RetrievedGenero));

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

