package com.inficreations.countrybulletin.model;

import java.util.List;

public class CountryModel {
    private String countryCode;
    private String countryName;
    private String countryFlag;
    private List<String> category;

    public CountryModel() {
    }

    public CountryModel(String countryCode, String countryName, String countryFlag, List<String> category) {
        this.countryCode = countryCode;
        this.countryName = countryName;
        this.countryFlag = countryFlag;
        this.category = category;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryFlag() {
        return countryFlag;
    }

    public void setCountryFlag(String countryFlag) {
        this.countryFlag = countryFlag;
    }

    public List<String> getCategory() {
        return category;
    }

    public void setCategory(List<String> category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "CountryModel{" +
                "countryCode='" + countryCode + '\'' +
                ", countryName='" + countryName + '\'' +
                ", countryFlag='" + countryFlag + '\'' +
                ", category=" + category +
                '}';
    }
}
