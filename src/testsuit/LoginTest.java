package testsuit;

import browserfactory.BaseTest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LoginTest extends BaseTest {

    //base Url
    String baseUrl = "https://demo.nopcommerce.com/";


    @Before
    public void setupIt() {
        openBrowser(baseUrl);

    }

    @Test
    public void userShouldNavigateToLoginPageSuccessfully() {

        //Find log in link and click log in
        WebElement longinLink = driver.findElement(By.linkText("Log in"));
        longinLink.click();

        // This is from requirement
        String expectedMessage = "Welcome, Please Sign In!";

        //Find the welcome text elements and get the text
        WebElement actualMessageElement = driver.findElement(By.xpath("//h1[contains(text(),'Welcome, Please Sign In!')]"));
        String actualMessage = actualMessageElement.getText();

        // verification of the message
        Assert.assertEquals("Not navigate to login page", expectedMessage, actualMessage);
    }


    @Test
    public void userShouldLoginSuccessfullyWithValidCredentials() {
        //Find log in link and click log in
        WebElement longinLink = driver.findElement(By.xpath("//a[contains(text(),'Log in')]"));
        longinLink.click();

        //find email field and enter email address
        WebElement emailField = driver.findElement(By.id("Email"));
        emailField.sendKeys("rlobo123@gmail.com");

        //find password field amd enter password
        WebElement passwordField = driver.findElement(By.id("Password"));
        passwordField.sendKeys("rlobo123");

        //find login button and click it
        WebElement loginButton = driver.findElement(By.xpath("//button[contains(text(),'Log in')]"));
        loginButton.click();

        //find log out text
        String expectedText = "Log out";

        // * Verify the ‘Log out’ text is display
        WebElement actualTextElement = driver.findElement(By.linkText("Log out"));
        String actualText = actualTextElement.getText();
        Assert.assertEquals(expectedText, actualText);
    }


    @Test
    public void verifyTheErrorMessage() {
        //Find log in link and click log in
        WebElement loginLink = driver.findElement(By.linkText("Log in"));
        loginLink.click();

        //find email field and enter email address
        WebElement emailField = driver.findElement(By.id("Email"));
        emailField.sendKeys("rlobo123@gmail.com");

        //find password field amd enter password
        WebElement passwordField = driver.findElement(By.name("Password"));
        passwordField.sendKeys("rlobo123");

        // Expcted error message
        String expectedMessage = "Login was unsuccessful. Please correct the errors and try again.\n" +
                "No customer account found";

        //find login button and click it
        WebElement loginbutton = driver.findElement(By.xpath("//button[contains(text(),'Log in')]"));
        loginbutton.click();

        WebElement actualMessageElement = driver.findElement(By.xpath("//body/div[6]/div[3]/div[1]/div[1]/div[1]/div[2]/div[1]/div[2]/form[1]/div[1]"));
        String actualMessage = actualMessageElement.getText();

        // Verify the error message
        Assert.assertEquals("Not navigate to login page", expectedMessage, actualMessage);


    }


    @After
    public void tearDown() {
        closeBrowser();
    }


}
