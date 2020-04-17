package ru.marsel.flat_rent;

import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

public class Autotests {

    private static WebDriver driver;

    @BeforeClass
    public static void setUp() {
        System.setProperty("webdriver.chrome.driver", "D:\\IdeaProjects\\flat_rent\\drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void openTest() {
        driver.get("http://localhost:8080/");
        driver.findElement(By.xpath("//*[@id=\"app\"]/div/main/div/div/div/div[1]/div/div/div[1]/div[1]")).click();
        driver.findElement(By.xpath("//*[@id=\"list-24\"]/div[3]")).click();
        driver.findElement(By.xpath("//*[@id=\"app\"]/div[2]/main/div/div/div/div[2]/button")).click();
        assertTrue(driver.getCurrentUrl().contains("announcements"));
    }

    @Test
    public void registrationTest() {
        driver.get("http://localhost:8080/");
        driver.findElement(By.xpath("//*[@id=\"app\"]/div/header/div/button")).click();
        driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div/div/div[1]/div/div[2]/div/div[3]")).click();
        driver.findElement(By.xpath("//*[@id=\"input-54\"]")).sendKeys("Марсель");
        driver.findElement(By.xpath("//*[@id=\"input-57\"]")).sendKeys("test@mail.ru");
        driver.findElement(By.xpath("//*[@id=\"input-60\"]")).sendKeys("123456");
        driver.findElement(By.xpath("//*[@id=\"input-63\"]")).sendKeys("88005553535");
        driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div/div/div[2]/div/div[2]/div/div/div/div[5]/button")).click();
        assertTrue(driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div/div/div[2]/div/div[2]/div/div/div/div[5]/div")).isDisplayed());
    }

    @Test
    public void loginTest() {
        driver.get("http://localhost:8080/");
        login();
        assertTrue(driver.findElement(By.xpath("//*[@id=\"app\"]/div/header/div/div[3]/button[1]")).isDisplayed());
    }

    private void login() {
        driver.findElement(By.xpath("//*[@id=\"app\"]/div/header/div/button")).click();
        driver.findElement(By.xpath("//*[@id=\"input-46\"]")).sendKeys("test@mail.ru");
        driver.findElement(By.xpath("//*[@id=\"input-49\"]")).sendKeys("123456");
        driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div/div/div[2]/div/div[1]/div/div/form/div[3]/button")).click();
    }

    @Test
    public void addAnnouncementTest() throws InterruptedException {
        driver.get("http://localhost:8080/");
        if (driver.findElements(By.xpath("//*[@id=\"app\"]/div/header/div/div[3]/button[1]")).isEmpty()) {
            login();
        }
        driver.findElement(By.xpath("//*[@id=\"app\"]/div/header/div/div[3]/button[1]")).click();
        driver.findElement(By.xpath("//*[@id=\"input-39\"]")).sendKeys("3");
        driver.findElement(By.xpath("//*[@id=\"input-43\"]")).sendKeys("96");
        driver.findElement(By.xpath("//*[@id=\"input-47\"]")).sendKeys("5");
        driver.findElement(By.xpath("//*[@id=\"input-51\"]")).sendKeys("16");
        driver.findElement(By.xpath("//*[@id=\"input-55\"]")).sendKeys("Вахитовский");
        driver.findElement(By.xpath("//*[@id=\"input-59\"]")).sendKeys("Кремлевская");
        driver.findElement(By.xpath("//*[@id=\"input-63\"]")).sendKeys("35");
        driver.findElement(By.xpath("//*[@id=\"input-67\"]")).sendKeys("3200000");
        driver.findElement(By.xpath("//*[@id=\"input-71\"]")).sendKeys("Описание");
        driver.findElement(By.xpath("//*[@id=\"app\"]/div/main/div/div/div/div/div[10]/div/button")).click();
        Thread.sleep(2500);
        System.out.println(driver.getCurrentUrl());
    }
}
