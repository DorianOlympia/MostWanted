package com.example.dawid.mostwanted.model;

import android.graphics.Bitmap;

/**
 * Created by Dawid on 2015-07-22.
 */
public class ListItemData {
    private Bitmap photo;
    private String name;
    private int owes;
    private String telephone;

    public ListItemData(String nm, String num, Bitmap ph, int owe){
        setName(nm);
        setOwes(owe);
        setPhoto(ph);
        setTelephone(num);
    }

    public Bitmap getPhoto() {
        return photo;
    }

    public void setPhoto(Bitmap photo) {
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOwes() {
        return owes;
    }

    public void setOwes(int owes) {
        this.owes = owes;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
