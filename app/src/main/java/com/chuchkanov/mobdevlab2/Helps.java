package com.chuchkanov.mobdevlab2;

public class Helps {
    private String text; // название

    private int flagResource; // ресурс флага

    public Helps(String text, int flag){

        this.text=text;
        this.flagResource=flag;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }



    public int getFlagResource() {
        return this.flagResource;
    }

    public void setFlagResource(int flagResource) {
        this.flagResource = flagResource;
    }
}
