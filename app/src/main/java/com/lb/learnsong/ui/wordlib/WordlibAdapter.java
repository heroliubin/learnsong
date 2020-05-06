package com.lb.learnsong.ui.wordlib;

import android.content.Context;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.lb.learnsong.R;
import com.lb.learnsong.bean.WordInfo;
import com.lb.learnsong.ui.lsview.BaseAdapter;

import java.util.List;

public class WordlibAdapter extends BaseAdapter<WordInfo,WordlibViewHolder> {

    public WordlibAdapter(List<WordInfo> list, Context context) {
        super(list, context);
    }

    @Override
    protected void bindbody(WordlibViewHolder holder, List<WordInfo> bodyList, int p) {
            holder.word.setText(bodyList.get(p).getTargetWord()+"   "+p);
            holder.wordtrans.setText(bodyList.get(p).getChineseWord());
    }

    @Override
    protected WordlibViewHolder getBodyHolder(ViewGroup parent) {
        return new WordlibViewHolder(inflater.inflate(R.layout.item_wordlib,parent,false));
    }


}
