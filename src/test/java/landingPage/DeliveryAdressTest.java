package landingPage;

import com.appSmart.errors.AdressErrorMessages;
import com.appSmart.LandingPage;
import com.appSmart.Page;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

public class DeliveryAdressTest {



    @AfterEach
    public void close(){
        new Page(false).closeAndClear();
    }

    @Test
    public void testAdressOutsideDeliveryRange(){
        new LandingPage(true)
                .enterDeliveryAdress("Bremen")
                .enterDeliveryAdress("32")
                .validateDeliveryAdressDeliveryMessage(AdressErrorMessages.ADRESS_OUTSIDE_DELIVERY_RANGE);
    }

    @Test
    public void testAdressWithoutStreetNumber(){
        new LandingPage(true)
                .enterDeliveryAdress("Frankfurt am Main, Münchener Straße")
                .validateDeliveryAdressDeliveryMessage(AdressErrorMessages.ADRESS_MISSING_STREET_NUMBER);

    }


}
