package com.example.manasshrestha.customdraws;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by ManasShrestha on 8/15/15.
 */
public class CustomPieChart extends View implements Runnable {
    Paint mPiePaint;
    Context context;
    float arcAngle = 0;
    float startAngle = 0;
    float arcAngle2 = 360;
    float startAngle2 = 0;
    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            invalidate();
            return false;
        }
    });

    public CustomPieChart(Context context) {
        super(context);
        init();
        this.context = context;
    }

    public CustomPieChart(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
        this.context = context;
    }

    private void init() {
        mPiePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPiePaint.setStyle(Paint.Style.STROKE);
        mPiePaint.setStrokeWidth(15);
        mPiePaint.setTextSize(12);
        mPiePaint.setColor(Color.GREEN);

        Thread thread = new Thread(this);
        thread.start();


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Log.e("onDraw", "Draw");

        mPiePaint.setColor(Color.parseColor("#10000000"));
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, 400, mPiePaint);
        mPiePaint.setColor(Color.parseColor("#10000000"));
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, 250, mPiePaint);

        mPiePaint.setStyle(Paint.Style.FILL);
        mPiePaint.setColor(Color.YELLOW);
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, 150, mPiePaint);

        RectF oval = new RectF();
        oval.set(getWidth() / 2 - 400, getHeight() / 2 - 400, getWidth() / 2 + 400, getHeight() / 2 + 400);
        mPiePaint.setColor(Color.BLUE);
        mPiePaint.setStrokeCap(Paint.Cap.ROUND);
        mPiePaint.setStyle(Paint.Style.STROKE);
        canvas.drawArc(oval, startAngle, arcAngle, false, mPiePaint);

        RectF oval2 = new RectF();
        oval.set(getWidth() / 2 - 250, getHeight() / 2 - 250, getWidth() / 2 + 250, getHeight() / 2 + 250);
        mPiePaint.setColor(Color.BLUE);
        mPiePaint.setStrokeCap(Paint.Cap.ROUND);
        mPiePaint.setStyle(Paint.Style.STROKE);
        canvas.drawArc(oval, startAngle2, arcAngle2, false, mPiePaint);

        Paint mTextPaint = new Paint();
        mTextPaint.setColor(Color.BLACK);
        mTextPaint.setTextSize(50);
        String text = "Start/Stop";
        canvas.drawText(text, (getWidth() / 2) - (text.length() * 10), getHeight() / 2, mTextPaint);

        Paint myPaint = new Paint();
        myPaint.setColor(Color.rgb(0, 0, 0));
        myPaint.setStrokeWidth(10);
        myPaint.setStyle(Paint.Style.STROKE);
        myPaint.setStrokeWidth(10);
        canvas.drawRect(getWidth() / 2 - 450, getHeight() / 2 - 450, getWidth() / 2 + 450, getHeight() / 2 + 450, myPaint);

        Bitmap b= BitmapFactory.decodeResource(getResources(), R.drawable.charlizard);
        b = Bitmap.createScaledBitmap(b,200,200,false);
        myPaint.setColor(Color.RED);
        canvas.drawBitmap(b, getWidth()/2 - b.getWidth()/2, getHeight()/2 - b.getHeight()/2, myPaint);
    }

    @Override
    public void run() {
        while (arcAngle < 360) {
            arcAngle = (float) (arcAngle + 1);
            startAngle = (float) (startAngle - 0.5);

            arcAngle2 = (float) (arcAngle2 - 1);
            startAngle2 = (float) (startAngle2 +0.5);
//            startAngle = startAngle - 20;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            handler.sendEmptyMessage(0);
        }
    }
}
