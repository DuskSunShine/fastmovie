package com.scy.fastmovie.customviews;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;
import android.widget.ScrollView;

/**
 * Created by TimiZhuo on 2017/1/6.
 */
public class MyGridView extends GridView {
    public MyGridView(Context context) {
        super(context);
    }

    public MyGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int h=Integer.MAX_VALUE>>2;
        int height=MeasureSpec.makeMeasureSpec(h,MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec,height);
    }
}
