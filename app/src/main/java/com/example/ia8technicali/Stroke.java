package com.example.ia8technicali;
import android.graphics.Path;

//Built with Extensive Help from Tutorial on geeksforgeeks.org/how-to-create-a-paint-application-in-android/
public class Stroke {
    public int color;
    public int strokeWidth;
    public Path path;
    public Stroke(int color, int strokeWidth, Path path){
        this.color=color;
        this.strokeWidth=strokeWidth;
        this.path=path;
    }
}
