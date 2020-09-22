package com.azsm.reeduacare.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.azsm.reeduacare.R;
import com.azsm.reeduacare.model.BannerSliderModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class BannerSliderAdapter extends PagerAdapter {

    List<BannerSliderModel> bannerSliders;
    Context context;

    public BannerSliderAdapter(List<BannerSliderModel> bannerSliders, Context context) {
        this.bannerSliders = bannerSliders;
        this.context = context;
    }


    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {


        View view= LayoutInflater.from(container.getContext()).inflate(R.layout.banner_slider_layout,container,false);
        ImageView bannerImage=view.findViewById(R.id.banner_image);
        Picasso.with(context).load(bannerSliders.get(position).getImage()).fit().centerCrop()
                .placeholder(R.drawable.placeholder)
                .into(bannerImage);
        container.addView(view,0);
        return view;

    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

        container.removeView((View)object);
    }

    @Override
    public int getCount() {
        return bannerSliders.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {

        return view==object;
    }
}
