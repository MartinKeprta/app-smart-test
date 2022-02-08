package landingPage;

import com.appSmart.LandingPage;
import org.testng.annotations.Test;

public class ProductCustomizationTest {

    @Test
    public void testFreeItemsLimitation(){
        new LandingPage(true)
                .chooseBranch("Enjoy+Pizza+Bremen")
                .selectCategory("Pizza")
                .pickCustomizableItem("Pizza Salami")
                .pickCustomizationOption("Gratis-Zutaten","Knoblauch",2,true)
                .assertAmmounOfIngerdientsInCategory(2)
                .pickCustomizationOption("Gratis-Zutaten","Oregano",2,true)
                .assertAmmounOfIngerdientsInCategory(2)
                .pickCustomizationOption("Gratis-Zutaten","scharf",1,true)
                .assertAmmountOfItemsInCategory(4);
    }

    @Test
    public void testAddAndRemoveFreeItems(){
        new LandingPage(true)
                .chooseBranch("Enjoy+Pizza+Bremen")
                .selectCategory("Pizza")
                .pickCustomizableItem("Pizza Salami")
                .pickCustomizationOption("Gratis-Zutaten","Knoblauch",2,true)
                .assertAmmounOfIngerdientsInCategory(2)
                .pickCustomizationOption("Gratis-Zutaten","Knoblauch",-2,true)
                .assertAmmountOfItemsInCategory(0);
    }

    @Test
    public void testPriceCalculationForIngredidients(){
        new LandingPage(true)
                .chooseBranch("Enjoy+Pizza+Bremen")
                .selectCategory("Pizza")
                .pickCustomizableItem("Pizza Salami")
                .pickCustomizationOption("Extrazutaten 1","Basilikum",2,false)
                .assertAmmounOfIngerdientsInCategory(2)
                .assertCost();
    }

    @Test
    public void testPriceCalculationForAddAndRemoveIngredients(){
        new LandingPage(true)
                .chooseBranch("Enjoy+Pizza+Bremen")
                .selectCategory("Pizza")
                .pickCustomizableItem("Pizza Salami")
                .pickCustomizationOption("Extrazutaten 1","Basilikum",2,false)
                .assertAmmounOfIngerdientsInCategory(2)
                .pickCustomizationOption("Extrazutaten 1","Ei",2,false)
                .assertAmmountOfItemsInCategory(0)
                .assertCost();
    }

    @Test
    public void testPriceCalculationForPrice(){
        new LandingPage(true)
                .chooseBranch("Enjoy+Pizza+Bremen")
                .selectCategory("Pizza")
                .pickCustomizableItem("Pizza Salami")
                .pickSize("26 cm")
                .assertCost();
    }


}
