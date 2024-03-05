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

public class EntrataWebsiteTests {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void setUp() {
        // Set up WebDriver
        System.setProperty("webdriver.chrome.driver", "/path/to/chromedriver");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
        // Navigate to entrata.com
        driver.get("https://www.entrata.com");
    }

    @After
    public void tearDown() {
        // Close the WebDriver instance
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testNavigationToProductsPage() {
        // Click on Products link
        WebElement productsLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Products")));
        productsLink.click();
        // Verify if the URL contains 'products'
        assertTrue(driver.getCurrentUrl().toLowerCase().contains("products"));
    }

    @Test
    public void testSearchFunctionality() {
        // Enter search query
        WebElement searchInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("search")));
        searchInput.sendKeys("property management software");
        searchInput.submit();
        // Verify search results
        WebElement searchResults = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("search-results")));
        assertTrue(searchResults.getText().toLowerCase().contains("property management software"));
    }

    @Test
    public void testDemoRequestFormSubmission() {
        // Click on Request a Demo link
        WebElement demoLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Request a Demo")));
        demoLink.click();
        // Fill out the form
        WebElement nameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("DemoRequestName")));
        nameInput.sendKeys("John Doe");
        WebElement emailInput = driver.findElement(By.id("DemoRequestEmail"));
        emailInput.sendKeys("john.doe@example.com");
        WebElement phoneInput = driver.findElement(By.id("DemoRequestPhone"));
        phoneInput.sendKeys("1234567890");
        WebElement companyInput = driver.findElement(By.id("DemoRequestCompany"));
        companyInput.sendKeys("ABC Company");
        // Submit the form
        WebElement submitButton = driver.findElement(By.cssSelector("button[type='submit']"));
        submitButton.click();
        // Verify success message
        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("demo-success")));
        assertEquals("Thank you for your interest! We'll be in touch soon.", successMessage.getText());
    }

    @Test
    public void testFooterLinks() {
        // Click on each footer link and verify navigation
        String[] footerLinks = {"About", "Products", "Services", "Blog", "Careers", "Contact Us"};
        for (String linkText : footerLinks) {
            WebElement footerLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText(linkText)));
            footerLink.click();
            assertEquals("https://www.entrata.com/" + linkText.toLowerCase(), driver.getCurrentUrl().toLowerCase());
            // Navigate back to homepage
            driver.navigate().back();
        }
    }

    @Test
    public void testDynamicContent() {
        // Verify that dynamic content is present on the homepage
        WebElement dynamicContent = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("dynamic-content")));
        assertTrue(dynamicContent.isDisplayed());
        assertTrue(dynamicContent.getText().contains("Dynamic content"));
    }
}
