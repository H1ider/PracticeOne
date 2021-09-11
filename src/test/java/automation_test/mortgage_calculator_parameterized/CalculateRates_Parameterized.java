package automation_test.mortgage_calculator_parameterized;

import automation_test.mortgage_calculator.CalculateRates;
import command_providers.ActOn;
import data_providers.DataProviderClass;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page_objects.Home;
import utilities.ReadConfigFiles;

public class CalculateRates_Parameterized {
    WebDriver driver;
    public static final Logger LOGGER = LogManager.getLogger(CalculateRates_Parameterized.class);

    @BeforeMethod
    public void  browserInitialization(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        LOGGER.info("---------- Start Test (CalculateRates)---------");
        String url = ReadConfigFiles.getPropertyValues("MortgageCalculatorURL");
        ActOn.browser(driver).openBrowser(url);
        LOGGER.info("Browser is successfully initiated with the url " + url);

    }

    @Test(dataProvider = "RealAprRates", dataProviderClass = DataProviderClass.class)
    public void CalculateRealApr(String homePrice, String downPayment, String interestRate, String aprRate){

        new Home(driver)
                .mouseHoverToRates()
                .navigateToRealApr()
                .typeHomeValue(homePrice)
                .clickDownPaymentInDollar()
                .typeDownPayment(downPayment)
                .typeInterestRate(interestRate)
                .clickonCalculateButton()
                .validateRealAprValue(aprRate);
    }

    @AfterMethod
    public void testCleanup(){
        ActOn.browser(driver).closeBrowser();
        LOGGER.info("Browser is closed");
        LOGGER.info("== End Test (CalculateRates)==");
    }
}
