package com.juaracoding;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ShopDemoQA {
    public static void main(String[] args) {
        // Mulai Automisasi & Membuka Chrome
        String path = "D:\\SQA RPA Batch 10 - Juaracoding\\MyTools\\chromedriver-win64\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver",path);
        WebDriver driver = new ChromeDriver();
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Masuk Ke Website shop.demoqa
        driver.get("https://shop.demoqa.com");
        driver.manage().window().maximize();
        System.out.println("Open Browser, Open URL");

        // Masuk Tab My Account
        String titlePage = driver.findElement(By.className("woocommerce-store-notice__dismiss-link")).getText();
        if (titlePage.equalsIgnoreCase("Dismiss")){
            driver.findElement(By.className("woocommerce-store-notice__dismiss-link")).click();
        } else {

        }
        driver.findElement(By.linkText("My Account")).click();

        // Fitur Login
        js.executeScript("window.scrollBy(0,500)");
        driver.findElement(By.name("username")).sendKeys("Nurwahid");
        driver.findElement(By.name("password")).sendKeys("Nurwahid11");
        driver.findElement(By.name("login")).click();
        js.executeScript("window.scrollBy(0,500)");
        String loginPage = driver.findElement(By.className("woocommerce-MyAccount-content")).getText();

        // Fitur add 1 product to cart
        driver.findElement(By.className("custom-logo")).click();
        js.executeScript("window.scrollBy(0,1000)");
        driver.findElement(By.linkText("PINK DROP SHOULDER OVERSIZED T SHIRT")).click();
        js.executeScript("window.scrollBy(0,700)");
        driver.findElement(By.cssSelector("select#pa_color")).sendKeys("Pink");
        driver.findElement(By.cssSelector("select#pa_size")).sendKeys("37");
        driver.findElement(By.xpath("//button[@class='single_add_to_cart_button button alt wp-element-button']")).click();
        String productToCart = driver.findElement(By.className("woocommerce-message")).getText();

        // Assert menggunakan if fitur Login
        if (loginPage.equalsIgnoreCase("Hello Nurwahid (not Nurwahid? Log out)\n" +
                "From your account dashboard you can view your recent orders, manage your shipping and billing addresses, and edit your password and account details.")){
            System.out.println("Login berhasil");
        }else {
            System.out.println("Login Gagal");
        }

        // Assert menggunakan if add 1 product to cart
        if (productToCart.equalsIgnoreCase("View cart\n" +
                "“pink drop shoulder oversized t shirt” has been added to your cart.")){
            System.out.println("Product berhasil ditambahkan ke keranjang");
        } else {
            System.out.println("Produk gagal masuk ditambahkan ke keranjang");
        }

        // Menutup Browers
        delay();
        driver.quit();
        System.out.println("Quit Browers");

    }
    public static void delay(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
