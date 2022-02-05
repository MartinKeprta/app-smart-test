package com.appSmart;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.conditions.localstorage.Item;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public class ItemCustomizationPage extends Page{

    private SelenideElement itemTitle=$("h2.modal-title div");
    private ElementsCollection itemCustomizationOptions=$$("[data-testId=extra-group]");
    private ElementsCollection itemCustomizationOptionsIngredients=$$("[data-testId=extra-ingredient]");
    private double itemCostTotal=0;
    private SelenideElement itemSelectedCustomizationOption;
    private SelenideElement itemSelectedCustomizationIngredient;
    public ItemCustomizationPage(){

    }

    public ItemCustomizationPage(String productTitle){
        itemTitle.should(Condition.text(productTitle));
    }
    //This is for Pizza only!
    public ItemCustomizationPage pickSize(String size){
        return this;
    }
    public ItemCustomizationPage pickCustomizationOption(String customizationCategory,String customizationIngredient,int ammount,Boolean free){

        boolean addItem=true;
        if(ammount<0){
            addItem=false;
            ammount=ammount*-1;
        }
        //First find selected option
        sleep(5000);
        for(SelenideElement customizationOption:itemCustomizationOptions){
            itemSelectedCustomizationOption=customizationOption;
            if(customizationOption.find(".product-options__accordion__header").getText().contains(customizationCategory)){

                //If customization are not displayed we are going to open them
                if(!customizationOption.find(".product-options__wrap").exists()){
                    customizationOption.find(".product-options__accordion__arrow").click();
                    sleep(5000);
                }
                //Pick customization option
                for(SelenideElement ingredient:itemCustomizationOptionsIngredients){
                    itemSelectedCustomizationIngredient=ingredient;
                    System.out.println(ingredient.find("div.ingredients-text").text());
                    if(ingredient.find("div.ingredients-text").text().contains(customizationIngredient)){
                        for(int i=0;i<ammount;i++){
                            if(addItem){
                                ingredient.find("[data-testId=extra-plus]").click();
                                sleep(500);
                                if(!free){
                                    modifyPrice(ingredient.find("div.zero-price").getText(),true);
                                }
                            }else {
                                ingredient.find("[data-testId=extra-minus]").click();
                                sleep(500);
                                if(!free){
                                    modifyPrice(ingredient.find("div.zero-price").getText(),false);
                                }
                            }



                        }
                        //Expected point of return
                        return this;
                    }

                }
                //If cycle ended without returning it means no customization option was found and accoording exception will be thrown
                throw new NullPointerException("Unable to find customization option:"+customizationCategory);
            }
        }

        throw new NullPointerException("Unable to find customization category:"+customizationCategory);
    }
    //This method can be called only after adding / removing items from selected category
    public ItemCustomizationPage assertAmmountOfItemsInCategory(int expectedAmmount){
        Integer[] minMax=extractMinMaxFromItem(itemSelectedCustomizationOption.find("div.title").getText());
        int currentItemsCurrent=minMax[0];
        int currentItemsMaximum=minMax[1];
        //I mean... there is no way that this should happen... but for all cases:-D
        if(currentItemsCurrent>currentItemsMaximum){
            throw new IllegalStateException("User was able to insert more item then allowed into category");
        }
        if(currentItemsCurrent<0){
            throw new IllegalStateException("User was able somehow god knows how insert negative value of items into category");
        }

        return this;
    }

    public ItemCustomizationPage assertAmmounOfIngerdientsInCategory(int expectedAmmount){
        Integer currentAmmountOfIngredientsInCategory=extractAmmountFromItem(itemSelectedCustomizationIngredient.find("div.zero-count").getText());
        if(expectedAmmount!=currentAmmountOfIngredientsInCategory){
            throw new IllegalStateException("Incorrect total ammount of ingredients");
        }
        return this;
    }

    /**
     * Adds to total price of item and parses number to double
     * @param number
     */
    private void modifyPrice(String number,Boolean add){
        modifyPrice(extractCostFromItem(number),add);
    }

    /**
     * Adds to total price of item
     * @param number
     */
    private void modifyPrice(Double number,Boolean add){
        if(add){
            this.itemCostTotal=this.itemCostTotal+number;
        }else {
            this.itemCostTotal=this.itemCostTotal-number;
        }
        System.out.println("Total price for item now is "+number);
    }
}
