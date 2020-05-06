package com.lb.learnsong.ui.lsview;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.Nullable;

import com.lb.learnsong.R;
import com.lb.learnsong.uiUtil.ScreenUtil;

public class BackView extends View {
    private int mwidth ;
    private int mheight ;

    private Paint mpaint;
    private int mcolor;
    private int starx, stary,
            endtopx, endtopy,
            endbottomx, endbottomy;


    public BackView(Context context) {

        this(context,null);
    }

    public BackView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);

    }

    public BackView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs);

    }

    public void init(Context context,AttributeSet attrs) {
        TypedArray typedArray=context.obtainStyledAttributes(attrs, R.styleable.BackView);
        mcolor=typedArray.getColor(R.styleable.BackView_peletecolor,getResources().getColor(R.color.title_black));
        typedArray.recycle();
        mwidth = ScreenUtil.dip2px(context, 48);
        mheight = mwidth;
        mpaint = new Paint();
        starx = ScreenUtil.dip2px(context, 12);
        stary = ScreenUtil.dip2px(context, 24);
        endtopx = ScreenUtil.dip2px(context, 24);
        endtopy = starx;
        endbottomx = endtopx;
        endbottomy = ScreenUtil.dip2px(context, 36);;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(mwidth,mheight);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.drawColor(Color.TRANSPARENT);
        mpaint.setStrokeWidth(4);
        mpaint.setColor(mcolor);
        canvas.drawLine(starx, stary, endtopx, endtopy, mpaint);
        canvas.drawLine(starx, stary, endbottomx, endbottomy, mpaint);
    }
    public void setColor(int color){
        boolean inv=false;
        if (color!=mcolor){
            mcolor=color;
            inv=true;
        }
        if (inv){
            invalidate();
        }
    }
}
