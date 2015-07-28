package com.example.dawid.mostwanted.model;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.dawid.mostwanted.R;
import com.example.dawid.mostwanted.controller.MainActivity;

import java.io.File;
import java.net.URI;


/**
 * Created by Dawid on 2015-07-24.
 */
public class AddPersonDialog extends DialogFragment implements View.OnClickListener {

    public interface AddPersonDialogListener{
        void onAddPersonButtonClicked(File photo, String name, String owes, String phone);
        String getNextName();
    }

    private AddPersonDialogListener listener;
    private EditText etName;
    private EditText etOwes;
    private EditText etPhone;
    private ImageButton ibPhoto;

    private File file;
    private boolean isPhotoMade;

    public AddPersonDialog(){}

    public AddPersonDialog(AddPersonDialogListener listener){
        this.listener = listener;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        isPhotoMade = false;
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View v = getActivity().getLayoutInflater().inflate(R.layout.add_person_layout, null);
        builder.setView(v);
        builder.setTitle("Add a debter");
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Bitmap image = null;
                if(isPhotoMade){
                    image = ((BitmapDrawable)ibPhoto.getDrawable()).getBitmap();
                }
                listener.onAddPersonButtonClicked(file, etName.getText().toString(), etOwes.getText().toString(), etPhone.getText().toString());
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        etName = (EditText) v.findViewById(R.id.etName);
        etOwes = (EditText) v.findViewById(R.id.etOwes);
        etPhone = (EditText)v.findViewById(R.id.etPhone);
        ibPhoto = (ImageButton)v.findViewById(R.id.ibPhoto);
        ibPhoto.setOnClickListener(this);

        return builder.create();
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), listener.getNextName());
        Uri tempUri = Uri.fromFile(file);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, tempUri);
        intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
        startActivityForResult(intent, 0);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 0){
            switch(resultCode){
                case Activity.RESULT_OK:
                    ibPhoto.setImageBitmap(MainActivity.TrimmBitmap(BitmapFactory.decodeFile(file.getAbsolutePath())));
                    ibPhoto.setScaleType(ImageView.ScaleType.FIT_START);
                    ibPhoto.setAdjustViewBounds(true);
                    isPhotoMade = true;
                    break;
            }
        }
    }
}
