import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EntrataWebsiteTests1
{
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void setUp()
    {
        // Set up WebDriver
        System.setProperty("webdriver.chrome.driver", "/path/to/chromedriver");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
        // Navigate to entrata.com
        driver.get("https://www.entrata.com");
    }

    @After
    public void tearDown()
    {
        // Close the WebDriver instance
        if (driver != null) 
        {
            driver.quit();
        }
    }

    @Test
    public void testNavigationToProductsPage()
    {
        // Click on Products link
        WebElement productsLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Products")));
        productsLink.click();
        // Verify if the URL contains 'products'
        assertTrue(driver.getCurrentUrl().toLowerCase().contains("products"));
    }

    @Test
    public void testSearchFunctionality()
    {
        // Enter search query
        WebElement searchInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("search")));
        searchInput.sendKeys("property management software");
        searchInput.submit();
        // Verify search results
        WebElement searchResults = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("search-results")));
        assertTrue(searchResults.getText().toLowerCase().contains("property management software"));
    }
}
