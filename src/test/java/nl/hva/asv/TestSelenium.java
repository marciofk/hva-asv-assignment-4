package nl.hva.asv;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;

public class TestSelenium {

    @Test
    public void firstSearchResultIsRelatedTest() throws IOException {

        // for windows or linux machines, update the chromedriver into the resources folder
        // https://chromedriver.storage.googleapis.com/index.html?path=2.38/
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver");

        // Start a new Chrome browser instance and maximize the browser window
        ChromeDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Navigate to the Amazon.com home page
        driver.get("https://www.amazon.com/");

        // Type "Software testing" in the search window
        driver.findElement(By.id("twotabsearchtextbox")).sendKeys("Software Testing");

        // Click on the search button
        driver.findElement(By.xpath("//input[@value='Go']")).click();

        // Take a screenshot of the screen
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);

        // Copy the file to the target folder
        FileUtils.copyFile(scrFile, new File("target/screenshot.png"));

        // Select the first item in the list of search results
        driver.findElement(By.xpath("(//div[@id='resultsCol']//a[contains(@class,'access-detail-page')])[1]")).click();

        // Check that the page title contains the term "Software Testing"
        Assert.assertTrue(driver.getTitle().contains("Software Testing"));

        // Close the browser
        driver.quit();
    }
}
