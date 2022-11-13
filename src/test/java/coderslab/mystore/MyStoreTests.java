package coderslab.mystore;

import myStore.pageobjects.AccountPage;
import myStore.pageobjects.HomePage;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static utils.DataFaker.createRandomEmail;

class MyStoreTests {

    private WebDriver driver;
    private final String EMAIL = "michal.dobrzycki@coderslab.pl";
    private final String PASSWORD = "CodersLab";

    @BeforeEach
    void setUp(){
        System.setProperty("webdriver.chrome.driver",
                "src/main/resources/drivers/chromedriver");

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @Test
    void subscriberToNewsletterTest(){
        HomePage homePage = new HomePage(driver); // teworzenie PageObject'u
        homePage.openPage(); // otwieranie strony
        homePage.subscribeToNewsletter(createRandomEmail()); // subskrybcja do newsletter
        String actualAlertText = homePage.getSubscriberAlertText(); // wyciąganie tekstu z powiadomienia
        String expectedText = "successfully subscribed";
        Assertions.assertThat(actualAlertText).as("Alert Text").contains(expectedText); // veryfikacja tekstu
    }

    @Test
    void loginUserWithValidCredentials(){
        HomePage homePage = new HomePage(driver);
        homePage.openPage();
        mystore.pageobjects.LoginPage loginPage = homePage.clickSignIn(); // new LoginPage(driver); - Zwracamy obiekt nowej strony
        AccountPage accountPage = loginPage.loginUser(EMAIL, PASSWORD); // new AccountPage(driver); - Zwracamy obiekt nowej strony
        String expectedText = "INFORMATION";
        String actualText = accountPage.getIdentityLinkText();
        Assertions.assertThat(actualText).contains(expectedText); // veryfikacja tekstu
        String expectedAccountName = "Automated Tester";
        String actualAccountName = accountPage.getAccountNameText();
        Assertions.assertThat(actualAccountName).as("Account name").isEqualTo(expectedAccountName);
    }

    @Test
    void loginUserPAgeFactoryTest(){
        myStore.pagefactory.HomePage homePage = new myStore.pagefactory.HomePage(driver);
        homePage.openPage();
        myStore.pagefactory.LoginPage loginPage = homePage.clickSingIn();
        myStore.pagefactory.AccountPage accountPage = loginPage.loginUser(EMAIL, PASSWORD);
        String expectedText = "INFORMATION";
        String actualText = accountPage.getIdentityLinkText();
        Assertions.assertThat(actualText).contains(expectedText); // veryfikacja tekstu
        String expectedAccountName = "Automated Tester";
        String actualAccountName = accountPage.getAccountNameText();
        Assertions.assertThat(actualAccountName).as("Account name").isEqualTo(expectedAccountName);
    }


    @AfterEach
    void tearDown(){
        driver.quit();
    }
}