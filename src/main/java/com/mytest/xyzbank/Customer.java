package com.mytest.xyzbank;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Customer {

   WebDriver driver;

   @BeforeTest
    public void config(){
       driver = new ChromeDriver();
   }

   @Test
    public void customerTransaction(){
       int balance = 0;
       int amount = 0;
       // login as a Customer
       driver.get("https://www.globalsqa.com/angularJs-protractor/BankingProject/#/login");
       driver.manage().window().maximize();
       implicitlyWait(50);
       getElement("//button[text()='Customer Login']").click();
       implicitlyWait(50);
       getElement("//select[@id='userSelect']").click();
       getElement("//option[contains(text(),'Hermoine Granger')]").click();
       implicitlyWait(50);
       getElement("//button[text()='Login']").click();

       System.out.println("Logged");
       implicitlyWait(100);
       getElement("//select[@id='accountSelect']").click();
       implicitlyWait(100);
       getElement("//option[contains(text(),'1003')]").click();
       implicitlyWait(100);

       // 50000 cred
       amount = 50000;
       balance = balance + amount;
       getElement("//button[contains(text(),'Deposit')]").click();
       implicitlyWait(1000);
       getElement("//input[@type='number']").click();
       implicitlyWait(100);
       getElement("//input[@type='number']").sendKeys("50000");
       implicitlyWait(1000);
       getElement("//input[@type='number']/../..//button[contains(text(),'Deposit')]").click();
       System.out.println(amount + " Credited and Account balance is " + balance);
       // verify balance in the balance section
       getElement("//strong[contains(text(), " + balance + ")]");

       // 3000 deb
       amount = 3000;
       balance = balance - amount;
       implicitlyWait(15000);
       getElement("//button[normalize-space()='Withdrawl']").click();
       implicitlyWait(15000);
       getElement("//label[contains(text(),'Amount to be Withdrawn :')]/..//input[@type='number']").click();
       implicitlyWait(40000);
       getElement("//input[@type='number']").sendKeys("3000");
       implicitlyWait(20000);
       getElement("//label[contains(text(),'Amount to be Withdrawn :')]/../..//button[@type='submit']").click();
       System.out.println(amount + " Debited and Account balance is " + balance);
       // verify balance in the balance section
       getElement("//strong[contains(text(), " + balance + ")]");

       // 2000 deb
       amount = 2000;
       balance = balance - amount;
       implicitlyWait(1000);
       getElement("//label[contains(text(),'Amount to be Withdrawn :')]/..//input[@type='number']").click();
       implicitlyWait(10000);
       getElement("//input[@type='number']").sendKeys("2000");
       implicitlyWait(10000);
       getElement("//label[contains(text(),'Amount to be Withdrawn :')]/../..//button[@type='submit']").click();
       implicitlyWait(20000);
       System.out.println(amount+" Debited and balance is " + balance);
       // verify balance in the balance section
       getElement("//strong[contains(text(), " + balance + ")]");

       // 5000 cred
       amount = 5000;
       balance = balance + amount;
       implicitlyWait(20000);
       getElement("//button[contains(text(),'Deposit')]").click();
       implicitlyWait(20000);
       getElement("//input[@type='number']").click();
       implicitlyWait(20000);
       getElement("//input[@type='number']").sendKeys("5000");
       getElement("//input[@type='number']/../..//button[contains(text(),'Deposit')]").click();
       implicitlyWait(20000);
       System.out.println(amount+" Credited and balance is " + balance);
       // verify balance in the balance section
       getElement("//strong[contains(text(), " + balance + ")]");

       // 10000 deb
       amount = 10000;
       balance = balance - amount;
       implicitlyWait(20000);
       getElement("//button[normalize-space()='Withdrawl']").click();
       implicitlyWait(20000);
       getElement("//label[contains(text(),'Amount to be Withdrawn :')]/..//input[@type='number']").click();
       implicitlyWait(20000);
       getElement("//input[@type='number']").sendKeys("10000");
       implicitlyWait(20000);
       getElement("//label[contains(text(),'Amount to be Withdrawn :')]/../..//button[@type='submit']").click();
       System.out.println(amount+" Debited and balance is " + balance);
       // verify balance in the balance section
       getElement("//strong[contains(text(), " + balance + ")]");

       // 15000 deb
       amount = 15000;
       balance = balance - amount;
       implicitlyWait(20000);
       getElement("//label[contains(text(),'Amount to be Withdrawn :')]/..//input[@type='number']").click();
       implicitlyWait(20000);
       getElement("//input[@type='number']").sendKeys("15000");
       implicitlyWait(20000);
       getElement("//label[contains(text(),'Amount to be Withdrawn :')]/../..//button[@type='submit']").click();
       System.out.println(amount+" Debited and balance is " + balance);
       // verify balance in the balance section
       getElement("//strong[contains(text(), " + balance + ")]");

       // 1500 cred
       amount = 1500;
       balance = balance + amount;
       implicitlyWait(20000);
       getElement("//button[contains(text(),'Deposit')]").click();
       implicitlyWait(20000);
       getElement("//input[@type='number']").click();
       implicitlyWait(20000);
       getElement("//input[@type='number']").sendKeys("1500");
       getElement("//input[@type='number']/../..//button[contains(text(),'Deposit')]").click();
       implicitlyWait(20000);
       System.out.println(amount+" Credited and balance is " + balance);
       // verify balance in the balance section
       getElement("//strong[contains(text(), " + balance + ")]");
   }

    public WebElement getElement(String xpath) {
        for (int x = 0; !driver.findElement(By.xpath(xpath)).isDisplayed() && x < 4; x++) {
            try {
                new WebDriverWait(driver, Duration.ofSeconds(50)).until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
            } catch (ElementNotInteractableException e) {
                System.out.println("waiting");
            } catch (Exception ee) {
                ee.printStackTrace();
            }
        }
        return driver.findElement(By.xpath(xpath));
    }

    public void implicitlyWait(int second) {
        try {
            driver.manage().timeouts().implicitlyWait(second, TimeUnit.SECONDS);
        } catch (NoSuchElementException e) {
            System.out.println("Failed : implicitlyWait(int second)");
        }
    }
}

