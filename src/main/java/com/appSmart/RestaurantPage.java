package com.appSmart;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;

import java.time.Duration;
import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class RestaurantPage extends Page{
    SelenideElement branchSelector=$("[data-testid=branch-selector]");
    ElementsCollection productCategory=$$("#category-menu-desktop-hermes-theme a");
    ElementsCollection items=$$("[data-testid=product");
    //Category characteristics
    SelenideElement categoryName=$("div.product-desktop-main-container div.image-mode-title-text");
    SelenideElement categoryDescription=$("div.product-desktop-main-container div.image-mode-description-text");

    public RestaurantPage(Boolean open) {
        super(open);
    }

    public RestaurantPage(){
        branchSelector.should(Condition.appear, Duration.ofSeconds(5));
    }

    public RestaurantPage verifyBranchName(String requiredBranchName){
        Assertions.assertEquals(branchSelector.getOwnText(),requiredBranchName);
        return this;
    }

    public RestaurantPage selectCategory(String categoryName){
        for(SelenideElement element:productCategory){
            if(element.getText().equals(categoryName)){
               element.click();
               return this;
            }
        }
        throw new NullPointerException("Cannot find category with name : "+categoryName);
    }

    public RestaurantPage selectCategory(int categoryId){
        for(SelenideElement element:productCategory){
            if(Integer.valueOf(element.attr("href").split("/cat/")[1]).equals(categoryId)){
                element.click();
                return this;
            }
        }
        throw new NullPointerException("Cannot find category with ID : "+categoryId);
    }

    public ItemCustomizationPage pickCustomizableItem(String name){
        for(SelenideElement element:items){
            if(element.find("h5[data-testid=product-title]").getText().equals(name)){
                element.click();
                return new ItemCustomizationPage();
            }
        }
        throw new NullPointerException("Cannot find product with name: "+name);
    }

    public ItemCustomizationPage pickCustomizableItem(int name){
        for(SelenideElement element:items){
            if(Integer.valueOf(element.find("div.number p").getText()).equals(name)){
                element.click();
                return new ItemCustomizationPage();
            }
        }
        throw new NullPointerException("Cannot find product with name: "+name);
    }

}
