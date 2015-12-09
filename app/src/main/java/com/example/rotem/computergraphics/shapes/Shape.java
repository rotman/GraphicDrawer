package com.example.rotem.computergraphics.shapes;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by rotem on 11/26/2015.
 */
public abstract class Shape {
    public Paint mPaint;
    public Shape(Paint p) {
        mPaint = p;
    }
    public abstract void draw(Canvas canvas);
}
