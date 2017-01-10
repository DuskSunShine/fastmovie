package com.scy.fastmovie.customviews;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.VideoView;

/**
 * Created by TimiZhuo on 2017/1/9.
 */
public class MyVideoView extends VideoView {
    public MyVideoView(Context context) {
        super(context);
    }

    public MyVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width=MeasureSpec.getSize(widthMeasureSpec);
        int height=MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(width,height);

    }
}
