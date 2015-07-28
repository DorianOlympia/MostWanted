package com.example.dawid.mostwanted.model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Toast;

import com.example.dawid.mostwanted.controller.MainActivity;

import java.io.File;
import java.io.Serializable;

/**
 * Created by Dawid on 2015-07-22.
 */
public class ListItemData implements Serializable{
    private boolean isDefaultPhoto;
    private File photo;
    private String name;
    private int owes;
    private String telephone;

    public ListItemData(String nm, String num, File ph, int owe){
        setName(nm);
        setOwes(owe);
        setTelephone(num);
        photo = ph;
    }

    public File getPhotoFile(){return photo;}

    public Bitmap getPhoto() {
        return (photo == null)? null : MainActivity.TrimmBitmap(BitmapFactory.decodeFile(photo.getAbsolutePath()));
    }

    public void setPhoto(File photo) {
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
