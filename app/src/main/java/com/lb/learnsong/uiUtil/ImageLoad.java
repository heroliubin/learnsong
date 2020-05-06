package com.lb.learnsong.uiUtil;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.palette.graphics.Palette;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.lb.learnsong.R;

import java.io.File;
import java.util.List;

public class ImageLoad {
    public static void LoadImage(Context context, String url, ImageView imageView) {

        Glide.with(context).load(url).placeholder(R.mipmap.personal_head_default).into(imageView);
    }

    public static void LoadImageFile(Context context, String file, ImageView imageView) {
        File filepath = new File(file);
        Glide.with(context).load(filepath).into(imageView);
    }

    public static void LoadRadiusImage(Context context, String url, ImageView imageView, int radius) {
        //设置图片圆角角度
        RoundedCorners roundedCorners = new RoundedCorners(radius);
        RequestOptions options = RequestOptions.bitmapTransform(roundedCorners);
        Glide.with(context).load(url).apply(options).into(imageView);
    }

    public static void LoadRadiusImagefile(Context context, String file, ImageView imageView, int radius) {
        //设置图片圆角角度
        File filepath = new File(file);
        RoundedCorners roundedCorners = new RoundedCorners(radius);
        RequestOptions options = RequestOptions.bitmapTransform(roundedCorners);
        Glide.with(context).load(filepath).apply(options).into(imageView);
    }

    public static void LoadCircleImage(Context context, String url, ImageView imageView) {
        Glide.with(context).load(url).circleCrop().placeholder(R.mipmap.personal_head_default).into(imageView);
    }
    /**
     * 设置图片主色调
     *
     * @param bitmap
     * @return
     */
    public static void setPaletteColor(Bitmap bitmap, List<TextView> view) {
        if (bitmap == null) {
            return ;
        }

        Palette.from(bitmap).maximumColorCount(10).generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(@NonNull Palette palette) {
                Palette.Swatch s = palette.getDominantSwatch();//独特的一种
                Palette.Swatch s1 = palette.getVibrantSwatch();       //获取到充满活力的这种色调
                Palette.Swatch s2 = palette.getDarkVibrantSwatch();    //获取充满活力的黑
                Palette.Swatch s3 = palette.getLightVibrantSwatch();   //获取充满活力的亮
                Palette.Swatch s4 = palette.getMutedSwatch();           //获取柔和的色调
                Palette.Swatch s5 = palette.getDarkMutedSwatch();      //获取柔和的黑
                Palette.Swatch s6 = palette.getLightMutedSwatch();    //获取柔和的亮
                if (s1!= null) {
                    for (int i=0;i<view.size();i++){
                        view.get(i).setTextColor(s1.getBodyTextColor());
                        if (i!=view.size()-1){
                            view.get(i).setBackgroundColor(s1.getRgb());
                        }
                    }
                }

            }
        });

    }

}
