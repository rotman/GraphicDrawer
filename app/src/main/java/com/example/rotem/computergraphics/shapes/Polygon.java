package com.example.rotem.computergraphics.shapes;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by rotem on 11/26/2015.
 */
public class Polygon extends Shape{
    private static final String TAG ="Polygon" ;
    public Point pc,p;
    public int sides;
    public static int numSides;


    public Polygon(Point pc,Point p,Paint paint) {
        super(paint);
        this.pc = pc;
        this.p = p;
        sides = numSides;
    }

    @Override
    public void draw(Canvas canvas) {
        if (sides < 3) {
            return;
        }
        ArrayList<Point> points = new ArrayList<>();
        // calculates the radius with distance formula
        int r = (int)Math.sqrt(Math.pow((p.x-pc.x),2) + Math.pow((p.y-pc.y),2));
        //get the polygon vertexes
        double theta = 2*Math.PI/sides;
        for (int i=0;i<sides;i++) {
            int x = (int)(pc.x + r*Math.cos(i*theta));
            int y = (int)(pc.y + r*Math.sin(i*theta));
            points.add(new Point(x,y));
        }

        //draw the polygon
        int i;
        for (i = 0;i<points.size()-1;i++) {
            Line l = new Line(points.get(i),points.get(i+1),mPaint);
            l.draw(canvas);
        }
        //draw line from the last point to the first point
        Line l = new Line(points.get(i),points.get(0),mPaint);
        l.draw(canvas);

    }



}
