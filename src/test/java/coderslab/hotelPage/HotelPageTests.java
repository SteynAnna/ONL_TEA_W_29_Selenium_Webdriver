package coderslab.hotelPage;

import hotelPage.pageobjects.HomePage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class HotelPageTests {

    private WebDriver driver;

    @BeforeEach
    void setUp() {
        System.setProperty("webdriver.chrome.driver",
                "src/main/resources/drivers/chromedriver");

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @Test
    void clickToSignIn(){
        hotelPage.pageobjects.HomePage homePage = new HomePage(driver);
        homePage.openPage();
        homePage.clickToSignIn();
    }

    @AfterEach
    void tearDown(){
        driver.quit();
    }
}

