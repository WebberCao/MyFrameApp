package com.webber.myframeapp.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.IdRes;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RadioGroup;

import com.webber.myframeapp.R;


/**
 * 定制radiogroup 选中的radiobutton背景变色
 *
 * Created by opggl on 10/18/2017.
 */

public class CustomRadioGroup extends RadioGroup implements RadioGroup.OnCheckedChangeListener {

    public interface ItemSelectChangeListener {
        void onSelectChanged(RadioGroup group, int checkedId);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        if (itemSelectChangeListener != null) {
            itemSelectChangeListener.onSelectChanged(group, checkedId);
            postInvalidate();
        }
    }

    Context mContext;

    ItemSelectChangeListener itemSelectChangeListener;

    View  selectedView;
    Rect  selectedRect;
    Paint selectPaint;
    Paint contentBGPaint;

    int defaultSelectColor    = 0x21a57e;
    int defaultContentBGColor = 0x2169a4;
    int selectedColor;
    int contentBGColor;

    public CustomRadioGroup(Context context) {
        super(context);
        mContext = context;
        init(null);
    }

    public CustomRadioGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        setOnCheckedChangeListener(this);
        selectPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        contentBGPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        selectPaint.setColor(defaultSelectColor);
        contentBGPaint.setColor(defaultContentBGColor);
        if (attrs == null) {
            return;
        }
        TypedArray ta = mContext.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomRadioGroup, 0, 0);
        int n = ta.getIndexCount();
        for (int i = 0; i <= n; i++) {
            int attr = ta.getIndex(i);
            switch (attr) {
                //注意获取属性的方式为 styleable的名称_属性名称
                case R.styleable.CustomRadioGroup_selectedColor:
                    selectedColor = ta.getColor(attr, defaultSelectColor);
                    break;
                case R.styleable.CustomRadioGroup_contentBGColor:
                    contentBGColor = ta.getColor(attr, defaultContentBGColor);
                    break;
            }
        }
        ta.recycle();
        selectPaint.setColor(selectedColor);
        contentBGPaint.setColor(contentBGColor);
        selectedRect = new Rect();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }

    private void onSeletView(int checkedId) {
        selectedView = findViewById(checkedId);
        if (selectedView == null)
            return;
        selectedRect.top = (int)(getPaddingTop() * 0.4);
        selectedRect.left = selectedView.getLeft() + getPaddingTop();
        selectedRect.bottom = selectedView.getBottom();
        selectedRect.right = selectedView.getRight() - getPaddingTop();
    }

    public void setItemSelectChangeListener(ItemSelectChangeListener listener) {
        itemSelectChangeListener = listener;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        onSeletView(getCheckedRadioButtonId());
        canvas.drawRect(0, getPaddingTop(), getWidth(), getHeight(), contentBGPaint);
        canvas.drawRect(selectedRect, selectPaint);
    }

}
