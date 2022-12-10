package com.example.ia8technicali;
import static android.graphics.Color.parseColor;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;
import java.util.ArrayList;

//Built with Extensive Help from Tutorial on geeksforgeeks.org/how-to-create-a-paint-application-in-android/
public class DoodleView  extends View{
    private static final float TOUCH_TOLERANCE = 4;
    private float mX,mY;
    private Path mPath;
    private Paint mPaint;

    //Stroke Variables: ArrayList stores all stokes created by User
    private ArrayList<Stroke> paths = new ArrayList<>();
    private int currentColor; //Obvious
    private int strokeWidth; //Obvious
    private Bitmap mBitmap; //Bitmap seems to be the Griding of Pixels that make up the canvas
    private Canvas mCanvas; //Obvious
    private Paint mBitmapPaint = new Paint(Paint.DITHER_FLAG); //^^

    //
    public DoodleView(Context context){
        this(context,null);
    }

    //Condensed with use of the Nullable import... will see if this breaks the tutorial
    public DoodleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        mBitmap = Bitmap.createBitmap(1500,1675,Bitmap.Config.ARGB_8888); //Stores each pixel as 4 bytes...?
        mCanvas = new Canvas(mBitmap); //Uses Bitmap to create the canvas
        currentColor = parseColor("#000000");
        strokeWidth = 20;
        //Smoothening Functions
        mPaint.setAntiAlias(true); //Assuming prevents artifacting
        mPaint.setDither(true);  //Assuming prevents harsh edges
        mPaint.setColor(parseColor("#000000")); //Obvious
        mPaint.setStyle(Paint.Style.STROKE); //Sets the Geometry of the object being drawn with
        mPaint.setStrokeJoin(Paint.Join.ROUND); //??? Assuming has to do with the combination of the Stroke shape
        mPaint.setStrokeCap(Paint.Cap.ROUND); //??? Assuming has to do with the termination of a stroke
        mPaint.setAlpha(255); //Opacity
    }

    public void setColor(int color) {
        currentColor = color;
    }

    public void setStrokeWidth(int width){
        strokeWidth = width;
    }

    public void undo(){
        if(paths.size() != 0){
            paths.remove(paths.size()-1);
            invalidate(); //Generally means 'redraw on screen' -> calls onDraw()
        }
    }

    public Bitmap save(){
        return mBitmap;
    }

    @Override
    protected void onDraw(Canvas canvas){
        //save the current state of the canvas before to draw the background of the canvas?
        canvas.save();

        int backgroundColor = Color.WHITE;
        mCanvas.drawColor(backgroundColor);

        //We iterate over the list of paths and draw each path on the canvas
        for (Stroke fp : paths){
            mPaint.setColor(fp.color);
            mPaint.setStrokeWidth(fp.strokeWidth);
            mCanvas.drawPath(fp.path, mPaint);
        }
        canvas.drawBitmap(mBitmap, 0,0,mBitmapPaint);
        canvas.restore();
    }

    private void touchStart(float x, float y){
        mPath = new Path();
        Stroke fp = new Stroke(currentColor, strokeWidth, mPath);
        paths.add(fp);
        mPath.reset(); //From experience, will prevent connecting to an old path
        mPath.moveTo(x,y);//Sets new start point
        mX=x;
        mY=y;
    }

    // in this method we check
    // if the move of finger on the
    // screen is greater than the
    // Tolerance we have previously defined,
    // then we call the quadTo() method which
    // actually smooths the turns we create,
    // by calculating the mean position between
    // the previous position and current position
    //Fanciest Method included from the tutorial, will test with and without to see the difference
    private void touchMove(float x, float y) {
        float dx = Math.abs(x - mX);
        float dy = Math.abs(y - mY);

        if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
            mPath.quadTo(mX, mY, (x + mX) / 2, (y + mY) / 2);
            mX = x;
            mY = y;
        }
    }

    //Just finishes up the current stroke with the x and y that the lifting up of the finger happened at
    //The start function will handle the separation of the two strokes
    private void touchUp(){
        mPath.lineTo(mX,mY);
    }

    //Based off of the event coming into the function, determines what action should happen of the above methods
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                touchStart(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                touchMove(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                touchUp();
                invalidate();
                break;
        }
        return true;
    }
}
