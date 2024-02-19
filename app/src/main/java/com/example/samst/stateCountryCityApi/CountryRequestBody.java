package com.example.samst.stateCountryCityApi;

public class CountryRequestBody {
    private String country;

    public CountryRequestBody(String country) {
        this.country = country;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
