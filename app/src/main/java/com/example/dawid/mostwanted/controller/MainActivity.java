package com.example.dawid.mostwanted.controller;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.widget.Toast;

import com.example.dawid.mostwanted.R;
import com.example.dawid.mostwanted.model.AddPersonDialog;
import com.example.dawid.mostwanted.model.ListItemChangedListener;
import com.example.dawid.mostwanted.model.ListItemData;

import java.io.Serializable;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements ListItemChangedListener, AddPersonDialog.AddPersonDialogListener {

    private RecyclerView recList;
    private ArrayList<ListItemData> data;
    private MWListAdapter mwAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recList = (RecyclerView)findViewById(R.id.mainRecyclerView);
        recList.setHasFixedSize(true);
        LinearLayoutManager recListLinLay = new LinearLayoutManager(this);
        recListLinLay.setOrientation(LinearLayoutManager.HORIZONTAL);
        recList.setLayoutManager(recListLinLay);
        data = new ArrayList<>();
        mwAdapter = new MWListAdapter(getApplicationContext(), data, this);
        recList.setAdapter(mwAdapter);

        ItemTouchHelper.SimpleCallback recListSimpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.UP | ItemTouchHelper.DOWN) {

            @Override
            public float getSwipeThreshold(RecyclerView.ViewHolder viewHolder) {
                return 0.7f;
            }


            @Override
            public int getSwipeDirs(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                if(viewHolder.getAdapterPosition() == data.size())return 0;
                return super.getSwipeDirs(recyclerView, viewHolder);
            }

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                onListItemRemove(viewHolder.getAdapterPosition());
            }
        };
        new ItemTouchHelper(recListSimpleCallback).attachToRecyclerView(recList);
    }

    @Override
    public void onListItemAdd() {
        AddPersonDialog dialog = new AddPersonDialog(this);
        dialog.show(getFragmentManager(), "TAG");
    }

    @Override
    public void onListItemRemove(int layoutPosition) {
        data.remove(layoutPosition);
        mwAdapter.notifyItemRemoved(layoutPosition);
    }

    @Override
    public void onAddPersonButtonClicked(Bitmap photo, String name, String owes, String phone) {
        if(photo == null){
            photo = BitmapFactory.decodeResource(getResources(),R.drawable.no_pic);
        }
        if(name.equals(""))name = "N/A";
        if(owes.equals(""))owes = "0";
        if(phone.equals(""))phone = "N/A";
        data.add(new ListItemData(name, phone, photo, Integer.parseInt(owes)));
        mwAdapter.notifyDataSetChanged();
    }

    public static Bitmap TrimmBitmap(Bitmap source)
    {
        return Bitmap.createBitmap(
                source,
                source.getWidth()/2 - source.getHeight()/2,
                0,
                source.getHeight(),
                source.getHeight()
        );
    }


}
