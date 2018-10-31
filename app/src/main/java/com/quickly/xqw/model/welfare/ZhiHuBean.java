package com.quickly.xqw.model.welfare;

public class ZhiHuBean {

    private int id;
    private String image;



    private String title;
    public ZhiHuBean() {
    }

    public ZhiHuBean(String image, int id, String title) {
        this.image = image;
        this.id = id;
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
