package page_objects;

import command_providers.ActOn;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class RealApr extends NavigationBar{
    public static final Logger LOGGER = LogManager.getLogger(RealApr.class);

    private final By CalculatorTab = By.xpath("//*[@id='CalcForm']//label[text()='Calculator']");
    private final By HomeValue = By.name("HomeValue");
    private final By DownPaymentInDollar = By.id("DownPaymentSel0");
    private final By DownPayment = By.name("DownPayment");
    private final By InterestRate = By.name("Interest");
    private final By CalculateButton = By.name("calculate");
    private final By ActualAprValue = By.xpath("//*[@id='analysisDiv']/table/tbody/tr/td/strong[contains(text(),'Actual APR')]/../../td[2]/strong");

    public RealApr(WebDriver driver){
        super(driver);
    }

    // Wait for the calculator tab to exist
    public RealApr waitForPageToLoad(){
        LOGGER.debug("Wait for the Real Apr Page to load");
        ActOn.wait(driver, CalculatorTab).WaitForElementToBeVisible();
        return this;
    }


    //Enter Home price
    public RealApr typeHomeValue(String value){
        LOGGER.debug("Entered Home Value is "+ value);
        ActOn.element(driver,HomeValue).setValue(value);
        return this;
    }

    //Click on DownPayment In Dollar
    public RealApr clickDownPaymentInDollar(){
        LOGGER.debug("Clicked on Down Payment In Dollar");
        ActOn.element(driver, DownPaymentInDollar).click();
        return this;
    }

    //Enter DownPayment
    public RealApr typeDownPayment(String value){
        LOGGER.debug("Entered Down Payment is " + value);
        ActOn.element(driver,DownPayment).setValue(value);
        return this;
    }

    //Enter Interest Rate
    public RealApr typeInterestRate(String value){
        LOGGER.debug("Entered Interest Rate is " + value);
        ActOn.element(driver,InterestRate).setValue(value);
        return this;
    }

    // Click on Calculate button
    public RealApr clickonCalculateButton(){
        LOGGER.debug("Clicked on Calculate Button");
        ActOn.element(driver, CalculateButton).click();
        return this;
    }

    // Validate Real APR value
    public RealApr validateRealAprValue(String expectedValue){
        String aprRate = ActOn.element(driver,ActualAprValue).getTextValue();
        LOGGER.debug("Validating the Real Apr Value" + expectedValue);
        Assert.assertEquals(aprRate, expectedValue);
        return this;
    }
}

