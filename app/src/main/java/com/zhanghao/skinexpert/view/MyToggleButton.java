package com.zhanghao.skinexpert.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by RockGao on 2016/12/27.
 */

public class MyToggleButton extends View {

    public MyToggleButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        initBitmap();
    }

    private void initBitmap() {

    }

    public MyToggleButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
