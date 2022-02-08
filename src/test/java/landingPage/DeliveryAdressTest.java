package landingPage;

import com.appSmart.errors.AdressErrorMessages;
import com.appSmart.LandingPage;
import com.appSmart.Page;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Attachment;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class DeliveryAdressTest {

    @Test
    @Description("Check required validation message if user will provide valid adress outside permited range")
    @Severity(SeverityLevel.CRITICAL)
    public void testAdressOutsideDeliveryRange(){
        new LandingPage(true)
                .enterDeliveryAdress("Bremen")
                .enterDeliveryAdress("32")
                .validateDeliveryAdressDeliveryMessage(AdressErrorMessages.ADRESS_OUTSIDE_DELIVERY_RANGE);
    }

    @Test
    @Description("Check required validation message if user will not provide adress street number")
    @Severity(SeverityLevel.CRITICAL)
    public void testAdressWithoutStreetNumber(){
        new LandingPage(true)
                .enterDeliveryAdress("Frankfurt am Main, Münchener Straße")
                .validateDeliveryAdressDeliveryMessage(AdressErrorMessages.ADRESS_MISSING_STREET_NUMBER);

    }

    //This test is for purpose to demonstrate how tests are handled if they fail
    @Test
    @Description("Check entering of valid delivery adress")
    @Severity(SeverityLevel.CRITICAL)
    public void testCorrectAdressWithinDeliveryRange(){
        new LandingPage(true)
                .enterDeliveryAdress("Frankfurt am Main, Münchener Straße")
                .validateDeliveryAdressDeliveryMessage(AdressErrorMessages.NO_ERROR);

    }

    @Attachment(value = "{name}", type = "image/png")
    private byte[] takeScreenShot(String name) throws IOException {
        return ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }

    @AfterMethod
    protected void screenShotIfFail(ITestResult result) throws IOException {
        if (!result.isSuccess()) {
            takeScreenShot(result.getName());
        }
    }


}
