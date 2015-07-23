package com.example.dawid.mostwanted.controller;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dawid.mostwanted.R;
import com.example.dawid.mostwanted.model.ListItemChangedListener;
import com.example.dawid.mostwanted.model.ListItemData;

import java.util.List;

/**
 * Created by Dawid on 2015-07-22.
 */
public class MWListAdapter extends RecyclerView.Adapter<MWListAdapter.MWViewHolder> {
    private List<ListItemData> data;
    private Context ctx;
    private LayoutInflater inflater;
    private ListItemChangedListener listener;
    private final int VIEW_FOOTER = 0;
    private final int VIEW_ITEM = 1;

    public MWListAdapter(Context c, List<ListItemData> data, ListItemChangedListener lst){
        this.data = data;
        ctx = c;
        inflater = LayoutInflater.from(c);
        listener = lst;
    }

    @Override
    public MWViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == VIEW_ITEM) {
            MWViewHolder resVH;
            View res = inflater.inflate(R.layout.wanted_list_item, parent, false);
            resVH = new MWViewHolder(res, VIEW_ITEM);
            return resVH;
        }
        else{
            MWViewHolder resVH;
            View res = inflater.inflate(R.layout.wanted_list_footer, parent, false);
            resVH = new MWViewHolder(res, VIEW_FOOTER);
            return resVH;
        }
    }

    @Override
    public void onBindViewHolder(MWViewHolder holder, int position) {

        if(position < data.size()) {
            ListItemData tmp = data.get(position);
            holder.setName(tmp.getName());
            holder.setTvNum(tmp.getTelephone());
            holder.setTvOwes(tmp.getOwes());
            holder.setIvPhoto(tmp.getPhoto());
        }
    }

    @Override
    public int getItemCount() {
        return data.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        return (position == data.size())? VIEW_FOOTER : VIEW_ITEM;
    }

    public class MWViewHolder extends RecyclerView.ViewHolder {
        //ITEM
        CardView debtorView;
        TextView tvName;
        TextView tvOwes;
        TextView tvNum;
        ImageView ivPhoto;
        //FOOTER
        ImageButton ivAddButton;

        private final int VIEW_FOOTER = 0;
        private final int VIEW_ITEM = 1;

        public MWViewHolder(View itemView, int viewType) {
            super(itemView);
            if(viewType == VIEW_ITEM) {
                tvName = (TextView) itemView.findViewById(R.id.tvName);
                tvOwes = (TextView) itemView.findViewById(R.id.tvMoney);
                tvNum = (TextView) itemView.findViewById(R.id.tvPhone);
                ivPhoto = (ImageView) itemView.findViewById(R.id.ivPhoto);
                debtorView = (CardView) itemView.findViewById(R.id.cvDebtor);
                debtorView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        listener.onListItemRemove(getAdapterPosition());
                        return true;
                    }
                });
            }
            else{
                ivAddButton = (ImageButton)itemView.findViewById(R.id.ibAdd);
                ivAddButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listener.onListItemAdd();
                    }
                });
            }
        }

        public void setName(String nm){
            if(tvName != null)tvName.setText(nm);
        }

        public void setTvOwes(int money){
            if(tvOwes != null)tvOwes.setText(String.valueOf(money));
        }

        public void setTvNum(String nm){
            if(tvNum != null)tvNum.setText(nm);
        }

        public void setIvPhoto(Bitmap photo){
            if(ivPhoto != null)ivPhoto.setImageBitmap(photo);
        }
    }
}
