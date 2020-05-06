package com.lb.learnsong.ui.lsview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lb.learnsong.R;
import com.lb.learnsong.ui.viewholder.SpaceViewHolder;

import java.util.List;

public abstract class BaseAdapter<T, H extends RecyclerView.ViewHolder> extends RecyclerView.Adapter {
    protected List<T> list;
    protected Context context;
    protected LayoutInflater inflater;

    public BaseAdapter(List<T> list, Context context) {
        this.list = list;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 0) {

            return getBodyHolder(parent);
        } else {
            return new SpaceViewHolder(inflater.inflate(R.layout.support_simple_spinner_dropdown_item,
                    parent, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == 0) {
            bindbody((H) holder, list, position);
        }else {
            holder.itemView.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        if (list == null) {
            return 0;
        }
        if (list.size() == 0) {
            return 0;
        }
        return list.size() + 1;
    }

    protected abstract void bindbody(H holder, List<T> bodyList, int p);


    protected abstract H getBodyHolder(ViewGroup parent);

    @Override
    public int getItemViewType(int position) {
        if (position > 0 && position == list.size()) {
            return 1;
        }
        return 0;
    }
}
