package landingPage;

import com.appSmart.LandingPage;
import io.qameta.allure.Description;
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
                .assertAmmountOfItemsInCategory();
    }

    @Test
    @Description("Adds and removes free items from category and verifies the limit")
    public void testAddAndRemoveFreeItems(){
        new LandingPage(true)
                .chooseBranch("Enjoy+Pizza+Bremen")
                .selectCategory("Pizza")
                .pickCustomizableItem("Pizza Salami")
                .pickCustomizationOption("Gratis-Zutaten","Knoblauch",2,true)
                .assertAmmounOfIngerdientsInCategory(2)
                .pickCustomizationOption("Gratis-Zutaten","Knoblauch",-2,true)
                .assertAmmountOfItemsInCategory();
    }

    @Test
    @Description("Adds and removes free items from category and verifies the limit")
    public void testAddItemsOverLimit(){
        new LandingPage(true)
                .chooseBranch("Enjoy+Pizza+Bremen")
                .selectCategory("Pizza")
                .pickCustomizableItem("Pizza Salami")
                .pickCustomizationOption("Gratis-Zutaten","Knoblauch",10,true)
                .assertAmmounOfIngerdientsInCategory(4)
                .assertAmmountOfItemsInCategory();
    }



    @Test
    @Description("Adds ingredients to category and verifies if price is correctly calculated")
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
    @Description("Adds and removes ingredients from category and verifies if price is correctly calculated")
    public void testPriceCalculationForAddAndRemoveIngredients(){
        new LandingPage(true)
                .chooseBranch("Enjoy+Pizza+Bremen")
                .selectCategory("Pizza")
                .pickCustomizableItem("Pizza Salami")
                .pickCustomizationOption("Extrazutaten 1","Basilikum",2,false)
                .assertAmmounOfIngerdientsInCategory(2)
                .pickCustomizationOption("Extrazutaten 1","Ei",2,false)
                .assertAmmountOfItemsInCategory()
                .assertCost();
    }
    @Test
    @Description("Test price calculation based on pizza size")
    public void testPriceCalculationForPrice(){
        new LandingPage(true)
                .chooseBranch("Enjoy+Pizza+Bremen")
                .selectCategory("Pizza")
                .pickCustomizableItem("Pizza Salami")
                .pickSize("26 cm")
                .assertCost();
    }


}
