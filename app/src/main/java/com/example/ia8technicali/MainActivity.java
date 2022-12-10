package com.example.ia8technicali;

import static android.graphics.Color.parseColor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;

//Built with Extensive Help from Tutorial on geeksforgeeks.org/how-to-create-a-paint-application-in-android/
public class MainActivity extends AppCompatActivity {
    private DoodleView paint;
    private Button eraser,black,yellow,blue,red,green,purple;
    private SeekBar size;
    private ImageView pencil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        paint = (DoodleView) findViewById(R.id.DoodleView);
        size = (SeekBar) findViewById(R.id.sizeSelectors);
        pencil = (ImageView) findViewById(R.id.penRed);
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
                pencil.setVisibility(View.GONE);
            }
        });

        yellow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                paint.setColor(parseColor("#ffff00"));

            }
        });

        blue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                paint.setColor(parseColor("#0000ff"));
            }
        });

        red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                paint.setColor(parseColor("#ff0000"));
            }
        });

        green.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                paint.setColor(parseColor("#008000"));
            }
        });

        purple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                paint.setColor(parseColor("#800080"));
            }
        });
    }
}

//public class doodleView extends View {



//}