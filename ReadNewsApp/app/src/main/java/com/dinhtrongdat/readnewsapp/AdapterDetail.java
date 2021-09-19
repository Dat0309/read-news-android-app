package com.dinhtrongdat.readnewsapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

public class AdapterDetail extends RecyclerView.Adapter<AdapterDetail.detailViewHolder> {

    List<Detail> mdata;
    final private AdapterDetail.ListItemClickListener mOnClickListener;

    public AdapterDetail(List<Detail> mdata, AdapterDetail.ListItemClickListener mOnClickListener) {
        this.mdata = mdata;
        this.mOnClickListener = mOnClickListener;
    }

    @Override
    public detailViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new detailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AdapterDetail.detailViewHolder holder, int position) {
        Detail detailHelper = mdata.get(position);
        try {
            Picasso.get().load(detailHelper.image).into(holder.image);
            holder.title.setText(detailHelper.getTitle());
        }catch (Exception e){

        }
    }

    @Override
    public int getItemCount() {
        return mdata.size();
    }

    public interface ListItemClickListener {
        void onDetailListClick(int clickedItemIndex);
    }

    public class detailViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView image;
        TextView title;
        public detailViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            image = itemView.findViewById(R.id.img_detail);
            title = itemView.findViewById(R.id.title_detail);
        }

        @Override
        public void onClick(View view) {
            int clickedPosition = getAdapterPosition();
            mOnClickListener.onDetailListClick(clickedPosition);
        }
    }
}
