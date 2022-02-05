package com.appSmart;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.By;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.codeborne.selenide.Selenide.*;

@Getter
@Setter
public class Page {
    public static String mainUrl="https://hermes-dev.devteam.win/-bremen-2/";
    private static final Pattern costFromItem = Pattern.compile("[\\d]*\\.?[\\d]+(e[-+][\\d]+)?");
    private static final Pattern costMinMaxFromItem = Pattern.compile("(\\d+)");

    public Page(Boolean open){
        if(open) open(mainUrl);

    }

    public void closeAndClear() {
        clearBrowserCookies();
        clearBrowserLocalStorage();
    }

    public Page(){

    }

    public Double extractCostFromItem(String item){
        Matcher m = costFromItem.matcher(item);
        if(m.find()){
            return Double.valueOf(m.group(0));
        }

        throw new IllegalStateException("Number not found or in invalid state");
    }

    public Integer[] extractMinMaxFromItem(String itemText){
        Matcher m = costMinMaxFromItem.matcher(itemText);
        if(m.find()){
            return new Integer[]{(Integer.valueOf(m.group(0))),Integer.valueOf(m.group(1))};
        }
        throw new IllegalStateException("Number not found or in invalid state");
    }

    public Integer extractAmmountFromItem(String itemText){
        System.out.println(itemText);
        Matcher m = costFromItem.matcher(itemText);
        if(m.find()){
           return Integer.valueOf(m.group(0));
        }
        throw new IllegalStateException("Number not found or in invalid state");
    }

}
