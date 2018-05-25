package com.solarexsoft.radarview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.List;

/**
 * Created by houruhou on 2018/5/23.
 */

public class RadarView extends View {
    private static final String TAG = RadarView.class.getSimpleName();

    private List<RadarData> mData;

    private int count;
    private float angle;
    private float radius;
    private float maxValue = 100f;
    private Paint mainPaint;
    private Paint valuePaint;
    private Paint textPaint;

    private int mainColor = 0xff888888;
    private int valueColor = 0xff79d4fd;
    private int textColor = 0xff808080;

    private float mainLineWidth = 0.5f;
    private float valueLineWidth = 1f;
    private float valuePointRadius = 2f;
    private float textSize = 14f;

    private int mWidth,mHeight;

    public RadarView(Context context) {
        this(context, null);
    }

    public RadarView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RadarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mainPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mainPaint.setColor(mainColor);
        mainPaint.setStyle(Paint.Style.STROKE);

        valuePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        valuePaint.setColor(valueColor);
        valuePaint.setStyle(Paint.Style.FILL_AND_STROKE);
        
        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setColor(textColor);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        radius = Math.min(h, w)/2*0.6f;
        mWidth = w;
        mHeight = h;
        postInvalidate();
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(mWidth/2, mHeight/2);
//        canvas.drawCircle(0, 0, radius, mainPaint);
        if (isDataValid()) {
            drawSpiderWeb(canvas);
            drawText(canvas);
            drawRegion(canvas);
        }
    }

    private void drawRegion(Canvas canvas) {
        valuePaint.setStrokeWidth(Utils.dp2px(getContext(), valueLineWidth));;
        Path path = new Path();
        valuePaint.setAlpha(255);
        path.reset();
        for (int i = 0; i < count; i++) {
            double percent = mData.get(i).getPercent()*1f/maxValue;
            float x = (float)(radius*Math.sin(angle*i)*percent);
            float y = (float)(radius*Math.cos(Math.PI + angle*i)*percent);
            if (i == 0) {
                path.moveTo(x, y);
            } else {
                path.lineTo(x, y);
            }
            canvas.drawCircle(x, y, Utils.dp2px(getContext(), valuePointRadius), valuePaint);
        }
        path.close();
        valuePaint.setStyle(Paint.Style.STROKE);
        canvas.drawPath(path, valuePaint);
        valuePaint.setAlpha(128);
        valuePaint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawPath(path, valuePaint);
    }

    private void drawText(Canvas canvas) {
        textPaint.setTextSize(Utils.dp2px(getContext(), textSize));
        Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
        float fontHeight = fontMetrics.descent - fontMetrics.ascent;
        for (int i = 0; i < count; i++) {
            float x = (float)((radius+fontHeight*2)*Math.sin(angle*i));
            float y = (float)((radius+fontHeight*2)*Math.cos(Math.PI + angle*i));
            String title = mData.get(i).getTitle();
            float dis = textPaint.measureText(title);
            canvas.drawText(title, x-dis/2, y, textPaint);
        }
    }

    private void drawSpiderWeb(Canvas canvas) {
        mainPaint.setStrokeWidth(Utils.dp2px(getContext(), mainLineWidth));
        Path webPath = new Path();
        Path linePath = new Path();
        float r = radius / (count - 1);
        for (int i = 0; i < count; i++) {
            float curR = r * i;
//            canvas.drawCircle(0, 0, curR, mainPaint);
            webPath.reset();
            for (int j = 0; j < count; j++) {
                float x = (float) (curR * Math.sin(angle*j));
                float y = (float) (curR * Math.cos(Math.PI + angle*j));
                if (j == 0) {
                    webPath.moveTo(x, y);
                } else {
                    webPath.lineTo(x, y);
                }
                Log.d(TAG, "(i,j,x,y) =(" +i+","+j+","+x+","+y+")");
                if (i == count - 1) {
                    linePath.reset();
                    linePath.moveTo(0, 0);
                    linePath.lineTo(x, y);
                    canvas.drawPath(linePath, mainPaint);
                }
            }
            webPath.close();
            canvas.drawPath(webPath, mainPaint);
        }
    }

    private boolean isDataValid() {
        return mData != null && mData.size() >= 3;
    }

    public void setData(List<RadarData> data) {
        if (data == null || data.size() < 3) {
            throw new RuntimeException("The number of data must be greater than 3");
        } else {
            mData = data;
            count = mData.size();
            angle = (float)(Math.PI * 2 / count);
            invalidate();
        }
    }

}
