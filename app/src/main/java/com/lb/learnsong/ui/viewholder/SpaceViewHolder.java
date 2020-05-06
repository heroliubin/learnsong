package com.lb.learnsong.ui.viewholder;

import android.view.View;
import android.widget.Space;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lb.learnsong.R;

public class SpaceViewHolder extends RecyclerView.ViewHolder {
    Space space;
    public SpaceViewHolder(@NonNull View itemView) {
        super(itemView);
        space=itemView.findViewById(R.id.view_result_space_space);
    }
}
