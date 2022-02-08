package com.appSmart;

import com.appSmart.errors.AdressErrorMessages;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.util.List;

import static com.codeborne.selenide.Selenide.*;

@Log4j2
public class LandingPage extends Page
{
    private SelenideElement landingPageAdressInput=$("div.AddressAutocomplete__input input");
    private SelenideElement landingPageAdressInputStatus=$("p.error");
    private SelenideElement landingPAgeAdressInputDropdownElements=$("");
    //Branch selection
    private List<SelenideElement> branchesList=$$("div.BranchCard");

    /**
     * Opens new landing page
     * @param open
     */
    public LandingPage(Boolean open) {
        super(open);
    }

    /**
     * Enter delivery adress with explicit checking for speecific error message
     * If no error is expected do not enter null as parameter but use ErrorMessage.NO_ERROR
     * @param adress
     * @return
     */
    public LandingPage enterDeliveryAdress(String adress){
        log.info("Entering delivery adress :"+adress);
        landingPageAdressInput.sendKeys(adress);
        sleep(1000);
        landingPageAdressInput.sendKeys(Keys.ARROW_DOWN);
        landingPageAdressInput.sendKeys(Keys.ENTER);
        return this;
    }

    /**
     * Validate expected error message versus recieved error message
     */
    public LandingPage validateDeliveryAdressDeliveryMessage(AdressErrorMessages errorMessage){

        log.info("Validating adress delivery for required error message.");
        //Step 1 : Check if we even have error message
        if(errorMessage!=AdressErrorMessages.NO_ERROR){

            landingPageAdressInputStatus.should(Condition.text(errorMessage.text),Duration.ofSeconds(10));
            log.info("Provided error message for adress delivery is expected");
            return this;
        }

        if(errorMessage==AdressErrorMessages.NO_ERROR){
            if(landingPageAdressInputStatus.has(Condition.visible)){
                log.error("No error expected.");
                log.error("Expected:"+errorMessage.text);
                log.error("Provided:");
                throw new IllegalStateException("We are not expecting error!");
            }
        }
        return this;
    }

    public RestaurantPage chooseBranch(String branchName){
        for(SelenideElement element:branchesList){
            if(element.find("h4").getOwnText().equals(branchName)){
                element.find("button").click();
                return new RestaurantPage();
            }
        }
        throw new NullPointerException("Branch "+branchName+ " does not exists!");
    }

    public RestaurantPage chooseBranch(int branchId){
        if(branchesList.get(branchId)!=null){
            branchesList.get(branchId).find("button").click();
            return new RestaurantPage();
        }
        throw new NullPointerException("Branch with ID "+branchId +" does not exists!");
    }
}
