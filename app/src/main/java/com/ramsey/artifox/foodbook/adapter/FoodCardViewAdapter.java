package com.ramsey.artifox.foodbook.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ramsey.artifox.foodbook.R;
import com.ramsey.artifox.foodbook.model.Food;
import com.ramsey.artifox.foodbook.utils.ImageLoader;

import java.util.List;


public class FoodCardViewAdapter extends RecyclerView.Adapter<FoodCardViewAdapter.FootViewHolder> {

    private List<Food> mFoots;

    ImageView image;
    int loader;
    String image_url;
    Context mContext;


    public FoodCardViewAdapter(List<Food> foots, Context context) {
        mFoots = foots;
        mContext = context;
    }

    @Override
    public FootViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext()).inflate(R.layout.foot_card_view, parent, false);
        return new FootViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FootViewHolder holder, int position) {
        Food foot = mFoots.get(position);
        holder.footName.setText(foot.getName());
        holder.footWeight.setText(String.valueOf(foot.getWeight()));
        holder.footPrice.setText(String.valueOf(foot.getPrice()));

        loader = R.drawable.ic_loader;
        image = holder.footImage;
        image_url = foot.getImageId();

        ImageLoader imgLoader = new ImageLoader(mContext);
        imgLoader.DisplayImage(image_url, loader, image);

    }

    @Override
    public int getItemCount() {
        return mFoots.size();
    }

    public static class FootViewHolder extends RecyclerView.ViewHolder {

        public ImageView footImage;
        public TextView footName;
        public TextView footWeight;
        public TextView footPrice;

        public FootViewHolder(View itemView) {
            super(itemView);

            footImage = (ImageView) itemView.findViewById(R.id.foot_image);
            footName = (TextView) itemView.findViewById(R.id.foot_name);
            footWeight = (TextView) itemView.findViewById(R.id.foot_weight);
            footPrice = (TextView) itemView.findViewById(R.id.foot_price);
        }
    }

}

