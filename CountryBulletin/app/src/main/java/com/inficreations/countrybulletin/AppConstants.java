package com.inficreations.countrybulletin;

import com.inficreations.countrybulletin.view.activity.MainActivity;

public class AppConstants {
    private static final String API_KEY = "9b7f6d4950614331a2895e9a2813b696";

    private static final String MAIN_URL = "https://newsapi.org/v2/";

    public static String getURL() {

        String url;
        if (MainActivity.categoryName.equalsIgnoreCase("highlights")) {
            url = MAIN_URL + "top-headlines?country=" + MainActivity.countryCode + "&apiKey=" + API_KEY;
        } else {
            url = MAIN_URL + "top-headlines?country=" + MainActivity.countryCode + "&category="
                    + MainActivity.categoryName + "&ap" +
                    "iKey=" + API_KEY;
        }
        return url;
    }

    public static String getSearchURL(String searchKey) {
        return MAIN_URL + "top-headlines?q=" + searchKey + "&apiKey=" + API_KEY;
    }
}
