package com.inficreations.countrybulletin.model;

public class NewsModel {
    private String newsTitle;
    private String newsDescription;
    private String newsURL;
    private String newsImageURL;
    private String newsPublishedAt;
    private String newsContent;

    public NewsModel() {
    }

    public NewsModel(String newsTitle, String newsDescription, String newsURL, String newsImageURL, String newsPublishedAt, String newsContent) {
        this.newsTitle = newsTitle;
        this.newsDescription = newsDescription;
        this.newsURL = newsURL;
        this.newsImageURL = newsImageURL;
        this.newsPublishedAt = newsPublishedAt;
        this.newsContent = newsContent;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public String getNewsDescription() {
        return newsDescription;
    }

    public void setNewsDescription(String newsDescription) {
        this.newsDescription = newsDescription;
    }

    public String getNewsURL() {
        return newsURL;
    }

    public void setNewsURL(String newsURL) {
        this.newsURL = newsURL;
    }

    public String getNewsImageURL() {
        return newsImageURL;
    }

    public void setNewsImageURL(String newsImageURL) {
        this.newsImageURL = newsImageURL;
    }

    public String getNewsPublishedAt() {
        return newsPublishedAt;
    }

    public void setNewsPublishedAt(String newsPublishedAt) {
        this.newsPublishedAt = newsPublishedAt;
    }

    public String getNewsContent() {
        return newsContent;
    }

    public void setNewsContent(String newsContent) {
        this.newsContent = newsContent;
    }
}
