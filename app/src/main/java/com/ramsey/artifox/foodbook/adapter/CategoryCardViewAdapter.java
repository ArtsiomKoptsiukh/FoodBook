package com.ramsey.artifox.foodbook.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ramsey.artifox.foodbook.R;
import com.ramsey.artifox.foodbook.model.FoodCategory;

import java.util.ArrayList;
import java.util.List;


public class CategoryCardViewAdapter extends RecyclerView.Adapter<CategoryCardViewAdapter.ViewHolder> {

    List<FoodCategory> mItems;

    public CategoryCardViewAdapter(Context context) {
        super();
        mItems = new ArrayList<FoodCategory>();
        FoodCategory footCategory = new FoodCategory();
        footCategory.setName("Супы");
        footCategory.setThumbnail(R.drawable.ic_soup);
        mItems.add(footCategory);

        footCategory = new FoodCategory();
        footCategory.setName(context.getString(R.string.rolls));
        footCategory.setThumbnail(R.drawable.ic_rolls);
        mItems.add(footCategory);

        footCategory = new FoodCategory();
        footCategory.setName(context.getString(R.string.salads));
        footCategory.setThumbnail(R.drawable.ic_salad);
        mItems.add(footCategory);

        footCategory = new FoodCategory();
        footCategory.setName(context.getString(R.string.warms));
        footCategory.setThumbnail(R.drawable.ic_warm);
        mItems.add(footCategory);


        footCategory = new FoodCategory();
        footCategory.setName(context.getString(R.string.snacks));
        footCategory.setThumbnail(R.drawable.ic_snack);
        mItems.add(footCategory);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.category_card_view, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        FoodCategory nature = mItems.get(i);
        viewHolder.tvFootCategory.setText(nature.getName());
        viewHolder.imgThumbnail.setImageResource(nature.getThumbnail());
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView imgThumbnail;
        public TextView tvFootCategory;


        public ViewHolder(View itemView) {
            super(itemView);
            imgThumbnail = (ImageView)itemView.findViewById(R.id.img_thumbnail);
            tvFootCategory = (TextView)itemView.findViewById(R.id.tv_foot_category);
        }
    }
}
