package com.example.ia8technicali;

import static android.graphics.Color.parseColor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;

//Built with Extensive Help from Tutorial on geeksforgeeks.org/how-to-create-a-paint-application-in-android/
public class MainActivity extends AppCompatActivity {
    private DoodleView paint;
    private Button eraser,black,yellow,blue,red,green,purple;
    private SeekBar size;
    private ImageView penBlack, penYellow, penBlue, penRed, penGreen, penPurple;
    private TextView sizeDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sizeDisplay = (TextView) findViewById(R.id.sizeDisplay);
        paint = (DoodleView) findViewById(R.id.DoodleView);
        size = (SeekBar) findViewById(R.id.sizeSelectors);
        penBlack = (ImageView) findViewById(R.id.penBlack);
        penYellow = (ImageView) findViewById(R.id.penYellow);
        penBlue = (ImageView) findViewById(R.id.penBlue);
        penRed = (ImageView) findViewById(R.id.penRed);
        penGreen = (ImageView) findViewById(R.id.penGreen);
        penPurple = (ImageView) findViewById(R.id.penPurple);
        eraser = (Button) findViewById(R.id.eraser);
        black = (Button) findViewById(R.id.black_button);
        yellow = (Button) findViewById(R.id.yellow_button);
        blue = (Button) findViewById(R.id.blue_button);
        red = (Button) findViewById(R.id.red_button);
        green = (Button) findViewById(R.id.green_button);
        purple = (Button) findViewById(R.id.purple_button);

        black.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                paint.setColor(parseColor("#000000"));
                resetPen();
                penBlack.setVisibility(View.VISIBLE);
            }
        });

        yellow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                paint.setColor(parseColor("#ffff00"));
                resetPen();
                penYellow.setVisibility(View.VISIBLE);
            }
        });

        blue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                paint.setColor(parseColor("#0000ff"));
                resetPen();
                penBlue.setVisibility(View.VISIBLE);
            }
        });

        red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                paint.setColor(parseColor("#ff0000"));
                resetPen();
                penRed.setVisibility(View.VISIBLE);

            }
        });

        green.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                paint.setColor(parseColor("#008000"));
                resetPen();
                penGreen.setVisibility(View.VISIBLE);
            }
        });

        purple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                paint.setColor(parseColor("#800080"));
                resetPen();
                penPurple.setVisibility(View.VISIBLE);
            }
        });

        eraser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                paint.undo();
            }
        });

        size.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,boolean fromUser) {
                // TODO Auto-generated method stub
                sizeDisplay.setText(String.valueOf(progress));
                paint.setStrokeWidth(progress*5);
            }
        });
    }

    private void resetPen() {
        penBlack.setVisibility(View.GONE);
        penBlue.setVisibility(View.GONE);
        penGreen.setVisibility(View.GONE);
        penYellow.setVisibility(View.GONE);
        penPurple.setVisibility(View.GONE);
        penRed.setVisibility(View.GONE);
    }


}

//public class doodleView extends View {



//}