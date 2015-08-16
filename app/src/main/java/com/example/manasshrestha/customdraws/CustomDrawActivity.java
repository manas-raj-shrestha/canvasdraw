package com.example.manasshrestha.customdraws;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by ManasShrestha on 8/15/15.
 */
public class CustomDrawActivity extends Activity {
    Button btnRed,btnBlue,btnGreen;
    DrawView drawView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_draw_activity);

        btnBlue = (Button) findViewById(R.id.btn_blue);
        btnRed = (Button) findViewById(R.id.btn_red);
        btnGreen = (Button) findViewById(R.id.btn_green);
        drawView = (DrawView) findViewById(R.id.draw_view);

        setOnClickListners();
    }

    private void setOnClickListners() {
        btnRed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawView.setColor(Color.RED);
            }
        });


        btnGreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawView.setColor(Color.GREEN);
            }
        });

        btnBlue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawView.setColor(Color.BLUE);
            }
        });
    }
}
