package com.browser.browserrestservices.restvervice;

public class Folder {

    private final String name;
    private final long size;


    public Folder(String name, long size) {
        this.name = name;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public long getSize() {
        return size;
    }


}
