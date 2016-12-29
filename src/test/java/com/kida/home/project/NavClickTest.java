package com.kida.home.project;

import static org.junit.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class NavClickTest {
	private WebDriver driver;
	private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();

	@Before
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
		baseUrl = "http://localhost:8080/KHome/";
		driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
	}

	@Test
	public void test() throws Exception {
		driver.get(baseUrl);
		driver.findElement(By.id("experiment")).click();
		driver.findElement(By.id("sAnyunnoOgz8r3vH")).click();
		driver.findElement(By.id("home")).click();
		driver.findElement(By.id("KCwW8Q4E3na4tDzB")).click();
		driver.findElement(By.id("home")).click();
		driver.findElement(By.id("Tx6NK9qm3kvw6ha2")).click();
		driver.findElement(By.id("home")).click();
		// driver.findElement(By.linkText("布控预警查询优化")).click();
		driver.findElement(By.id("home")).click();
		driver.findElement(By.id("logs")).click();
		driver.findElement(By.id("experiment")).click();
		driver.findElement(By.id("ydoqCkk5ONwmVOE6")).click();
		driver.findElement(By.id("home")).click();
		driver.findElement(By.id("humSoKEwE7Wknftk")).click();
		driver.findElement(By.id("home")).click();
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
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
