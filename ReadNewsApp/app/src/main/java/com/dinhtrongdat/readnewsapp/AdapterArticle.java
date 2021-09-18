package com.dinhtrongdat.readnewsapp;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterArticle extends RecyclerView.Adapter<AdapterArticle.ArticleViewHolder> {
    ArrayList<Article> articleLocation;
    final private ListItemClickListener mOnClickListener;

    public AdapterArticle(ArrayList<Article> articleLocation,ListItemClickListener mOnClickListener) {
        this.mOnClickListener = mOnClickListener;
        this.articleLocation = articleLocation;
    }

    @Override
    public ArticleViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_card, parent, false);
        return new ArticleViewHolder(view);
    }

    @Override
    public void onBindViewHolder( AdapterArticle.ArticleViewHolder holder, int position) {
        Article articleHelper = articleLocation.get(position);
        holder.image.setImageResource(articleHelper.getImage());
        holder.relative.setBackground(articleHelper.getColor());
    }

    @Override
    public int getItemCount() {
        return articleLocation.size();
    }

    public interface ListItemClickListener {
        void onArticleListClick(int clickedItemIndex);
    }

    public class ArticleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView image;
        RelativeLayout relative;

        public ArticleViewHolder( View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            image = itemView.findViewById(R.id.article_image);
            relative = itemView.findViewById(R.id.background_color);
        }

        @Override
        public void onClick(View view) {
            int clickedPosition = getAdapterPosition();
            mOnClickListener.onArticleListClick(clickedPosition);
        }
    }
}
