package com.example.manasshrestha.customdraws;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by ManasShrestha on 8/15/15.
 */
public class DrawView extends View {
    Path path;
    Paint mPaint;
    Context context;
    Canvas canvas;
    private float mX;
    private float mY;
    ArrayList<Path> paintArrayList = new ArrayList<>();
    ArrayList<Integer> colorArrayList = new ArrayList<>();
    int actionUp = 0;
    int draw = 0;
    float bitmapX = 100;
    float bitmapY = 100;

    public DrawView(Context context) {
        super(context);
    }

    public DrawView(Context context, AttributeSet attrs) {
        super(context, attrs);
        path = new Path();
        mPaint = new Paint();
        mPaint.setColor(Color.GREEN);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeWidth(10);
        this.context = context;
    }

    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);

        if (actionUp == 1) {

            for (int i = 0; i < paintArrayList.size(); i++) {
                Log.e("loop", i + "");
                mPaint.setColor(colorArrayList.get(i));
                canvas.drawPath(paintArrayList.get(i), mPaint);
            }
        } else {
            if (draw == 0) {
                Bitmap b = BitmapFactory.decodeResource(getResources(), R.drawable.charlizard);
                b = Bitmap.createScaledBitmap(b, 100, 100, false);
                canvas.drawBitmap(b, bitmapX, bitmapY, new Paint());
            }

            canvas.drawPath(path, mPaint);
        }

    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        Log.e("X Y", event.getX() + " " + event.getY());

        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                if (draw == 1) {
                    Log.e("ACTION UP", event.getX() + " " + event.getY());

                    paintArrayList.add(path);
                    colorArrayList.add(mPaint.getColor());
                    actionUp = 1;
                    invalidate();
                }
                break;
            case MotionEvent.ACTION_DOWN:
                if (draw == 1) {
                    Log.e("ACTION DOWN", event.getX() + " " + event.getY());
                    startTouch(event.getX(), event.getY());
                    actionUp = 0;
                    invalidate();
                } else {
                    bitmapX = event.getX();
                    bitmapY = event.getY();
                    invalidate();
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (draw == 1) {
                    Log.e("ACTION MOVE", event.getX() + " " + event.getY());
                    onMove(event.getX(), event.getY());
                    actionUp = 0;
                    invalidate();
                }
                break;

        }

        return true;
    }

    public void startTouch(float x, float y) {
        path = new Path();
        path.moveTo(x, y);
        mX = x;
        mY = y;

    }

    public void onMove(float x, float y) {
        float dx = Math.abs(x - mX);
        float dy = Math.abs(y - mY);

        if (dx >= 5 || dy >= 5) {

            path.quadTo(mX, mY, (x + mX) / 2, (y + mY) / 2);
            mX = x;
            mY = y;

        }
    }

    public void setColor(int color) {
        mPaint.setColor(color);
    }
}