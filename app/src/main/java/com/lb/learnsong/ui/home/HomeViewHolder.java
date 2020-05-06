package com.lb.learnsong.ui.home;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.lb.learnsong.R;

public class HomeViewHolder extends RecyclerView.ViewHolder {
    public ImageView img;
    public TextView lname,sname;
    public HomeViewHolder(View itemView) {
        super(itemView);
        img=itemView.findViewById(R.id.home_item_img);
        lname=itemView.findViewById(R.id.home_item_lname);
        sname=itemView.findViewById(R.id.home_item_sname);
    }
}
