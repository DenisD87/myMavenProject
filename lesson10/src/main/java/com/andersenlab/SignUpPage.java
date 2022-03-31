package com.andersenlab;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SignUpPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By emailField = By.xpath("//input[@id='signupform-email']");
    private By emailBlock = By.xpath("//input[@id='signupform-email']/parent::div");
    private By registerButton = By.xpath("//button[@id='signup_btn']");
    private By emailErrorField = By.xpath("//input[@id='signupform-email']/following-sibling::p");

    SignUpPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public SignUpPage typeEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
        return this;
    }

    public SignUpPage registerWithoutLogin(String email) {
        this.typeEmail(email);
        driver.findElement(registerButton).click();
        if (!email.matches("\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,6}")) {
            wait.until(ExpectedConditions.attributeContains(emailBlock, "class", "form-group field-signupform-email required has-error"));
        }
        return new SignUpPage(driver);
    }

    public String getTextError() {
        return driver.findElement(emailErrorField).getText();
    }
}
