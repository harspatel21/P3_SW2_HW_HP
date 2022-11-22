package testsuite;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import utilities.Utility;

public class LoginTest extends Utility {
    String baseUrl = "http://the-internet.herokuapp.com/login";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }

    @Test
    public void userShouldLoginSuccessfullyWithValidCredentials() {

        // Find the email field element and send the email
        sendTextToElement(By.name("username"), "tomsmith");

        // Find the password field element and send the password
        sendTextToElement(By.name("password"), "SuperSecretPassword!");

        // Find the login button and click on it
        clickOnElement(By.xpath("//button[@type='submit']"));

        //Expected Result
        String expectedMessage = "Secure Area";

        // Find the Secure Area text element and get the text
        String actualMessage = getTextFromElement(By.xpath("//h2[text()=' Secure Area']"));

        // Validate actual and expected message
        Assert.assertEquals("Not navigate to Secure Area", expectedMessage, actualMessage);

    }

    @Test
    public void verifyTheUsernameErrorMessage() {
        // Find the email field element and send the email
        sendTextToElement(By.name("username"), "tomsmith1");

        // Find the password field element and send the password
        sendTextToElement(By.name("password"), "SuperSecretPassword!");

        // Find the login button and click on it
        clickOnElement(By.xpath("//button[@type='submit']"));

        //Expected Result
        String expectedErrorTextMessage = "Your username is invalid!";
        //finding path and using substring method to trim message.
        String actualErrorTextMessage = getTextFromElement(By.xpath("//div[@id='flash-messages']")).substring(0, 25);
        Assert.assertEquals("Unexpected error happen", expectedErrorTextMessage, actualErrorTextMessage);
    }

    @Test
    public void verifyThePasswordErrorMessage() {
        // Find the email field element and send the email
        sendTextToElement(By.name("username"), "tomsmith");

        // Find the password field element and send the password
        sendTextToElement(By.name("password"), "SuperSecretPassword");

        // Find the login button and click on it
        clickOnElement(By.xpath("//button[@type='submit']"));

        //Expected Result
        String expectedMessage = "Your password is invalid!";

        //finding path and using substring method to trim message.
        String actualMessage = getTextFromElement(By.xpath("//body/div/div/div[@class='flash error']")).substring(0, 25);
        // Validate actual and expected message
        Assert.assertEquals("Not navigate to Secure Area", expectedMessage, actualMessage);

    }

    @After

    public void closeBrowser() {
        //to close all associated window where selenium is running test.
        driver.quit();
    }


}
