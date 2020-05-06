package com.lb.learnsong.ui.home;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.lb.learnsong.R;
import com.lb.learnsong.bean.Lyrics;
import com.lb.learnsong.ui.lsview.BaseRefreshAdapter;
import com.lb.learnsong.uiUtil.ImageLoad;
import java.util.List;

public class HomeAdapter extends BaseRefreshAdapter<Lyrics, HomeViewHolder> {


    public HomeAdapter(Context context, List bodyList) {
        super(context, bodyList);
    }


    @Override
    protected void bindbody(HomeViewHolder holder, List<Lyrics>  bodyList, int p) {
        ImageLoad.LoadRadiusImage(context,bodyList.get(p).getCover(),holder.img,10);
        holder.lname.setText(bodyList.get(p).getLname());
        holder.sname.setText(bodyList.get(p).getSname());

    }

    @Override
    protected int getBodyId() {
        return R.layout.item_body;
    }

    @Override
    protected HomeViewHolder getBodyHolder(ViewGroup parent) {
        return new HomeViewHolder(inflater.inflate(getBodyId(),parent,false));
    }




}
