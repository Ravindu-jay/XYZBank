package com.mytest.flightbook;


import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class BankManager {
    WebDriver driver;

    // customer's first name list
    List<String> firstNames;

    // customer's last name list
    List<String> lastNames;


    // customer's post codes
    List<String> postCodes;

    @BeforeTest
    public void config() {
        driver = new ChromeDriver();

        firstNames = new ArrayList<>();
        firstNames.add("Christopher");
        firstNames.add("Frank");
        firstNames.add("Christopher");
        firstNames.add("Connely");
        firstNames.add("Jackson");
        firstNames.add("Minka");
        firstNames.add("Jackson");

        lastNames = new ArrayList<>();
        lastNames.add("Connely");
        lastNames.add("Christopher");
        lastNames.add("Minka");
        lastNames.add("Jackson");
        lastNames.add("Frank");
        lastNames.add("Jackson");
        lastNames.add("Connely");

        postCodes = new ArrayList<>();
        postCodes.add("L789C349");
        postCodes.add("A897N450");
        postCodes.add("M098Q585");
        postCodes.add("L789C349");
        postCodes.add("L789C349");
        postCodes.add("A897N450");
        postCodes.add("L789C349");
    }

    @Test
    public void addCustomers() {
        // login as a bank manager
        driver.get("https://www.globalsqa.com/angularJs-protractor/BankingProject/#/login");
        driver.manage().window().maximize();
        implicitlyWait(20);
        getElement("//button[text()='Bank Manager Login']").click();
        implicitlyWait(20);
        getElement("//button[@ng-class='btnClass1']").click();

        // add customers using a for loop
        for (int i = 0; i < firstNames.size(); i++) {
            if (i > 0) {
                implicitlyWait(100);
            }
            getElement("//input[@ng-model='fName']").sendKeys(firstNames.get(i));
            getElement("//input[@ng-model='lName']").sendKeys(lastNames.get(i));
            getElement("//input[@ng-model='postCd']").sendKeys(postCodes.get(i));
            getElement("//button[text()='Add Customer']").click();
            driver.switchTo().alert().accept();
            System.out.println(firstNames.get(i) + " " + lastNames.get(i) + " added");
        }
    }

    @Test
    public void verifyCustomers() {

        // navigate to Customer tab
        implicitlyWait(20);
        getElement("//button[@ng-class='btnClass3']").click();

        for (int i = 0; i < firstNames.size(); i++) {
            WebElement element = getElement("//td[contains(text()," + firstNames.get(i)
                    + ")]/..//td[contains(text()," + lastNames.get(i)
                    + ")]/..//td[contains(text()," + postCodes.get(i) + ")]");
            if (element != null) {
                System.out.println(firstNames.get(i) + " " + lastNames.get(i) + " found");
            }
        }
    }

    @Test
    public void deleteCustomers() {

        getElement("//td[contains(text(),'Christopher')]/..//td[contains(text(),'Connely')]/..//td[contains(text(),'L789C349')]/..//button[contains(text(),'Delete')]").click();
        implicitlyWait(100);

        System.out.println ("Record deleted");

        //getElement("//td[contains(text(),'Jackson')]/.." +
         //       "//td[contains(text(),'Frank')]/.." +
        //        "//td[contains(text(),'L789C349')]/.." +
        //        "//button[contains(text(),'Delete')]").click();
       // implicitlyWait(100);

     //   System.out.println ("Record deleted");


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
