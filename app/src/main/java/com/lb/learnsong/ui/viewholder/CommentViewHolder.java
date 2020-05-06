package com.lb.learnsong.ui.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.lb.learnsong.R;
import com.lb.learnsong.ui.lsview.VoiceBotton;

public class CommentViewHolder extends ViewHolder {
    public TextView name,content,time,report;
    public ImageView head,zan;
    public VoiceBotton voiceBotton;
    public CommentViewHolder(@NonNull View itemView) {
        super(itemView);
        name=itemView.findViewById(R.id.name);
        content=itemView.findViewById(R.id.comment_content);
        time=itemView.findViewById(R.id.comment_time);
        report=itemView.findViewById(R.id.comment_report);
        head=itemView.findViewById(R.id.head);
        zan=itemView.findViewById(R.id.zanimg);
        voiceBotton=itemView.findViewById(R.id.voiceview);
    }
}
