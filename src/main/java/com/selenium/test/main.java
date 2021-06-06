package com.selenium.test;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileWriter;
import java.util.List;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;


public class main {
    public static void ebay(WebDriver driver){
        driver.get("https://www.ebay.com/");
        driver.findElement(By.id("gh-ac")).sendKeys("iphone");
        driver.findElement(By.id("gh-btn")).click();

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");

        List<WebElement> listings = driver.findElements(By.xpath("//h3[@class='s-item__title']"));
        List<WebElement> prices = driver.findElements(By.className("s-item__price"));

        try {
            File file = new File("ebay_output.txt");
            if(file.createNewFile()){
                System.out.println("File created: " + file.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e){
            System.out.println("An error has occurred.");
            e.printStackTrace();
        }
        try {
            FileWriter writer = new FileWriter("ebay_output.txt");

            WebElement listing;
            WebElement price;

            for(int i = 0; i < listings.size(); i++){
                listing = listings.get(i);
                price = prices.get(i);
                writer.write(listing.getText() + " - " + price.getText() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("An error has occurred.");
            e.printStackTrace();
        }

    }

    public static void target(WebDriver driver){
        String url = "https://www.target.com/";

        JavascriptExecutor js = (JavascriptExecutor) driver;

        driver.get(url);
        driver.findElement(By.id("search")).sendKeys("iphone");
        driver.findElement(By.id("search")).submit();
        System.out.println("Search submitted.");

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@data-test='product-title']")));

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.findElement(By.xpath("//body")).click();


        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");

        List<WebElement> listings = driver.findElements(By.xpath("//a[@data-test='product-title']"));
        List<WebElement> prices = driver.findElements(By.xpath("//div[@data-test='current-price']/span"));
        System.out.println("Listings and prices obtained");

        try {
            File file = new File("target_output.txt");
            if(file.createNewFile()){
                System.out.println("File created: " + file.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e){
            System.out.println("An error has occurred.");
            e.printStackTrace();
        }
        try {
            FileWriter writer = new FileWriter("target_output.txt");

            WebElement listing;
            WebElement price;

            for(int i = 0; i < listings.size(); i++){
                listing = listings.get(i);
                price = prices.get(i);
                writer.write(listing.getText() + " - " + price.getText() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("An error has occurred.");
            e.printStackTrace();
        }


    }


    public static void main(String[] args){
        System.setProperty("webdriver.chrome.driver", "C:/Users/imaso/Documents/Techbee Training/chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        ebay(driver);
        target(driver);




    }
}
