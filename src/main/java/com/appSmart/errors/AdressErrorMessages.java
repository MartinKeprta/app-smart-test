package com.appSmart.errors;

public enum AdressErrorMessages {

    NO_ERROR(""),
    ADRESS_MISSING("Please enter your street address"),
    ADRESS_OUTSIDE_DELIVERY_RANGE("This address is outside our delivery areas."),
    ADRESS_MISSING_STREET_NUMBER("Please enter the street number.");
    public final String text;
    AdressErrorMessages(String text) {
        this.text = text;
    }
}
