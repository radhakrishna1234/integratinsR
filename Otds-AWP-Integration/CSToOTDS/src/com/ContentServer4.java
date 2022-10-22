package com;

import java.io.*;  
import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class ContentServer4 {
	
	@Test
	public void main1()  throws Exception{
		
		
		Properties csproperties = new Properties();
		csproperties.load(new FileInputStream("../config.properties"));
		String csBaseUrl = csproperties.getProperty("CSURL");
		String csAdmin = csproperties.getProperty("csAdmin");
		String cspass =  csproperties.getProperty("cspass");
		String csWebPass = csproperties.getProperty("csWebPass");
		String otdsServerURL = csproperties.getProperty("server");
		String isClassicCS = csproperties.getProperty("isClassicCS");
		
	   System.out.println("==== >>>> pass "+ csWebPass);
		
		String tenant = csproperties.getProperty("tenantName");
		if(!tenant.trim().equals(""))
			otdsServerURL = otdsServerURL+"/otdstenant/"+tenant;
		
		FileReader fr=new FileReader("../key.txt");    
        BufferedReader br=new BufferedReader(fr); 
        String key = br.readLine();		
        System.out.println("==== >>>> key"+ key);
        fr.close();
		
		WebDriver driver=new HtmlUnitDriver();
		// System.setProperty("webdriver.chrome.driver", "C:\\Users\\rgudimet\\Desktop\\integrations\\Otds-AWP-Integration\\CSToOTDS\\chromedriver.exe");
		 //WebDriver driver = new ChromeDriver();
		 //driver.manage().window().maximize();
		//((HtmlUnitDriver) driver).setJavascriptEnabled(true);
		if(isClassicCS.equals("true"))
			driver.get(csBaseUrl+"/OTCS/cs.exe?func=admin.adminuserlogin");
		else
		driver.get(csBaseUrl+"/cs/cs?func=admin.adminuserlogin");
		Thread.sleep(1000);
		System.out.println(" curent URL "+driver.getCurrentUrl());
		//System.out.println(" curent URL "+driver.getPageSource());
		WebDriverWait wait = new WebDriverWait(driver, 120);
	/*	try {
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[@id='Password_labelledBy']/parent::div/following-sibling::div[1]/input[@id='Password']")));
		
		driver.findElement(By.xpath("//label[@id='Password_labelledBy']/parent::div/following-sibling::div[1]/input[@id='Password']")).sendKeys(csWebPass);
		Thread.sleep(10000);
		driver.findElement(By.id("loginbutton")).click();
		System.out.println(" === >>> Web Admin login success ");
		} catch(Exception e) {
			
			System.out.println(" === >>> Web Admin login not displayed ");
			
		}*/
		System.out.println(" curent URL "+driver.getCurrentUrl());
		System.out.println(" curent URL "+driver.getTitle());
		Thread.sleep(1000);		
		wait.until(ExpectedConditions.elementToBeClickable(By.id("username")));
		driver.findElement(By.id("username")).sendKeys(csAdmin);
		driver.findElement(By.id("password")).sendKeys(cspass);
		driver.findElement(By.id("loginbutton")).click();
		System.out.println(" === >>> Admin login success ");
		Thread.sleep(3000);
		if(isClassicCS.equals("true"))
			driver.get(csBaseUrl+"/OTCS/cs.exe?func=otdsintegration.settings");
		else		
			driver.get(csBaseUrl+"/cs/cs?func=otdsintegration.settings");
		Thread.sleep(6000);
		wait.until(ExpectedConditions.elementToBeClickable(By.name("webService")));
		driver.findElement(By.name("webService")).clear();
		driver.findElement(By.name("resourceID")).clear();
		driver.findElement(By.name("webService")).sendKeys(otdsServerURL);
		Thread.sleep(100);
		driver.findElement(By.id("loginURL")).clear();
        driver.findElement(By.name("resourceID")).sendKeys(key);
        driver.findElement(By.name("WebAdminPassword")).sendKeys(csWebPass);
        driver.findElement(By.name("adminsettings_save_btn")).click();
        Thread.sleep(1000);
        System.out.println(" === >>> Save changes ");
       
        try {
			WebDriverWait wait3 = new WebDriverWait(driver, 60);
        	wait3.until(ExpectedConditions.elementToBeClickable(By.id("restartServerButton")));
        	driver.findElement(By.id("restartServerButton")).click();
        	System.out.println(" === >>> Restart content server required");
        	wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("restartMsgSpinner")));
        	WebDriverWait wait1 = new WebDriverWait(driver, 240);
        	wait1.until(ExpectedConditions.invisibilityOfElementLocated(By.id("restartMsgSpinner")));
        	wait.until(ExpectedConditions.elementToBeClickable(By.id("continueButton")));
        	driver.findElement(By.id("continueButton")).click();
        	System.out.println(" === >>> Completed Restart content server required");
        }catch(Exception e) {
        	System.out.println("Restart of content serve not required");
        }
        
        
        
	
	}

}
