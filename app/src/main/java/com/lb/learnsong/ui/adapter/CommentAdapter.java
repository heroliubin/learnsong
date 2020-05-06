package com.lb.learnsong.ui.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.lb.learnsong.R;
import com.lb.learnsong.bean.CommentInfo;
import com.lb.learnsong.ui.lsview.BaseAdapter;
import com.lb.learnsong.ui.viewholder.CommentViewHolder;
import com.lb.learnsong.uiUtil.ImageLoad;

import java.util.List;

public class CommentAdapter extends BaseAdapter<CommentInfo, CommentViewHolder> {
    private ZanListener zanListener;

    public void setZanListener(ZanListener zanListener) {
        this.zanListener = zanListener;
    }

    public CommentAdapter(List<CommentInfo> list, Context context) {
        super(list, context);
    }

    @Override
    protected void bindbody(CommentViewHolder holder, List<CommentInfo> bodyList, int p) {

        holder.name.setText(bodyList.get(p).getNickname());
        holder.content.setText(bodyList.get(p).getContent());
        holder.time.setText(bodyList.get(p).getAddtime());
        holder.zan.setSelected(bodyList.get(p).isIspraise()==1);
        ImageLoad.LoadCircleImage(context,bodyList.get(p).getHeadimg(),holder.head);
        if (bodyList.get(p).getType()==2){
            holder.voiceBotton.setVisibility(View.VISIBLE);
        }
        holder.zan.setOnClickListener(v -> {
            if (zanListener!=null){
                if (v.isSelected()){
                    zanListener.zan(bodyList.get(p).getType()+"",bodyList.get(p).getCommentid(),"2");
                }else {
                    zanListener.zan(bodyList.get(p).getType()+"",bodyList.get(p).getCommentid(),"1");
                }
            }
        });
    }

    @Override
    protected CommentViewHolder getBodyHolder(ViewGroup parent) {
        return new CommentViewHolder(inflater.inflate(R.layout.comment_item,parent,false));
    }
    public interface ZanListener{
        void zan(String type,String typeid, String status);
    }
}
