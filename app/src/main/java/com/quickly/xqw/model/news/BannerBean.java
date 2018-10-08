package com.quickly.xqw.model.news;

import java.util.ArrayList;
import java.util.List;

public class BannerBean {

    private List<String> urls;

    public BannerBean() {
        this.urls = new ArrayList<>();
        urls.add("http://image.luosen365.com/016e8bbee88a4ce0bf9daca6cc3261e4.jpg");
        urls.add("http://image.luosen365.com/home_banner_01.jpg");
        urls.add("http://image.luosen365.com/home_banner_02.jpg");
        urls.add("http://image.luosen365.com/home_banner_03.jpg");
        urls.add("http://image.luosen365.com/home_banner_04.jpg");
    }

    public List<String> getUrls() {
        return urls;
    }
}