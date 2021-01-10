package com.haytech.haytechstyles.multibutton;

public class ItemModel {
    private String title;
    private long id;

    public ItemModel() {
    }

    public ItemModel(String title, long id) {
        this.title = title;
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
