package com.lb.learnsong.ui.lsview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.lb.learnsong.R;

import java.util.List;

public abstract class BaseRefreshAdapter<T,H extends RecyclerView.ViewHolder> extends RecyclerView.Adapter {
    protected final LayoutInflater inflater;
    protected Context context;
    private Itemclicklisenter itemclicklisenter;

    public void setItemclicklisenter(Itemclicklisenter itemclicklisenter) {
        this.itemclicklisenter = itemclicklisenter;
    }

    //数据
    protected List<T> bodyList;
    public static final int TYPE_BODY = 1;
    public static final int TYPE_FOOT = 2;
    RecyclerView.ViewHolder holder;

    public BaseRefreshAdapter(Context context, List<T> bodyList) {
        inflater =LayoutInflater.from(context);
        this.context = context;
        this.bodyList = bodyList;
    }

    @Override
    public int getItemCount() {
        return bodyList.size()+1;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType){
            case TYPE_BODY:
                holder = getBodyHolder(parent);
                break;
            case TYPE_FOOT:
                holder = new FootViewHolder(inflater.inflate(R.layout.item_foot,parent,false));
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(getItemViewType(position)==TYPE_BODY){
            bindbody((H)holder,bodyList,position );
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (itemclicklisenter!=null){
                        itemclicklisenter.click(position);
                    }
                }
            });
        }else{
            bindfoot((FootViewHolder) holder);
        }
    }


    class FootViewHolder extends RecyclerView.ViewHolder {
        public TextView foottext;

        public FootViewHolder(View itemView) {
            super(itemView);
            foottext=itemView.findViewById(R.id.tv_foot);
        }
    }
    @Override
    public int getItemViewType(int position) {
        int viewType = -1;

        if (position == bodyList.size() ) {
            viewType = TYPE_FOOT;
        } else {
            viewType = TYPE_BODY;
        }
        return viewType;
    }
    protected void bindfoot(FootViewHolder holder){

    }
    protected abstract void bindbody(H holder, List<T> bodyList, int p);

    protected abstract int getBodyId();
    protected abstract H getBodyHolder(ViewGroup parent);

}
