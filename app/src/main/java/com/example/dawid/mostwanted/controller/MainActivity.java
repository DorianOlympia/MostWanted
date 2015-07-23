package com.example.dawid.mostwanted.controller;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.dawid.mostwanted.R;
import com.example.dawid.mostwanted.model.ListItemChangedListener;
import com.example.dawid.mostwanted.model.ListItemData;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements ListItemChangedListener {

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
    }

    @Override
    public void onListItemAdd() {
        data.add(new ListItemData("Random Guy", "609 109 477", BitmapFactory.decodeResource(getResources(),R.drawable.example_guy), 2000));
        mwAdapter.notifyDataSetChanged();
    }

    @Override
    public void onListItemRemove(int layoutPosition) {
        data.remove(layoutPosition);
        mwAdapter.notifyItemRemoved(layoutPosition);
    }


}
