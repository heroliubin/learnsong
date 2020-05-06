package com.lb.learnsong.ui.wordlib;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lb.learnsong.R;

public class WordlibViewHolder extends RecyclerView.ViewHolder {
    public TextView word,wordtrans;
    public WordlibViewHolder(@NonNull View itemView) {
        super(itemView);
        word=itemView.findViewById(R.id.word);
        wordtrans=itemView.findViewById(R.id.word_trans);
    }
}
