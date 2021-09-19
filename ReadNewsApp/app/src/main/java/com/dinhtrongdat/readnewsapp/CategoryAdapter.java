package com.dinhtrongdat.readnewsapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;

import java.util.ArrayList;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.cateviewHolder> {
    List<Category> mdata;
    CategoryCallback callBack;

    public CategoryAdapter(List<Category> mdata, CategoryCallback callBack) {
        this.mdata = mdata;
        this.callBack = callBack;
    }

    @Override
    public cateviewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_category,parent,false);
        return new cateviewHolder(view);
    }

    @Override
    public void onBindViewHolder(CategoryAdapter.cateviewHolder holder, int position) {
        Category categoryHelper = mdata.get(position);
        Glide.with(holder.itemView.getContext()).load(mdata.get(position).getDrawableResource())
                .transforms(new CenterCrop(), new RoundedCorners(16)).into(holder.imgCate);
        holder.txtCate.setText(categoryHelper.getTitle());
        
    }

    @Override
    public int getItemCount() {
        return mdata.size();
    }

    public class cateviewHolder extends RecyclerView.ViewHolder {
        ImageView imgCate,imgCateBack;
        TextView txtCate;
        public cateviewHolder( View itemView) {
            super(itemView);
            imgCate = itemView.findViewById(R.id.img_cate);
            txtCate = itemView.findViewById(R.id.txt_Title_cate);
            imgCateBack = itemView.findViewById(R.id.img_cateBack);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    callBack.onCategoryClick(getAdapterPosition(),imgCateBack,imgCate,txtCate);
                }
            });
        }
    }

}
