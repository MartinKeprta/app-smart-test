package landingPage;

import com.appSmart.LandingPage;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.sleep;

public class PizzaCustomizationTest {

    //Create pizza with free indgredients
    @Test
    public void selectBranch(){
        new LandingPage(true)
                .chooseBranch("Enjoy+Pizza+Bremen")
                .selectCategory("Pizza")
                .pickCustomizableItem("Pizza Salami")
                    .pickCustomizationOption("Gratis-Zutaten","Knoblauch",1,true)
                     .assertAmmountOfItemsInCategory(1)
                     .assertAmmounOfIngerdientsInCategory(1)
                    .pickCustomizationOption("Gratis-Zutaten","Oregano",1,true)
                    .pickCustomizationOption("Gratis-Zutaten","scharf",1,true)
                     .assertAmmountOfItemsInCategory(3);
    }
}
