package com.scy.fastmovie.customviews;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by TimiZhuo on 2016/11/17.
 */
public class MyTextView extends TextView {
    public MyTextView(Context context) {
        super(context);
    }

    public MyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean isFocused() {
        return true;
    }
}
