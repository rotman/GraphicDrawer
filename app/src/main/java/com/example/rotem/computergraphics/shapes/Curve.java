package com.example.rotem.computergraphics.shapes;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.Log;

/**
 * Created by rotem on 11/26/2015.
 */
public class Curve extends Shape {
    private static final String TAG ="Curve" ;
    public Point p1,p2,p3,p4;
    public int lines;
    public final double[][] Mb = {{-1,3,-3,1},{3,-6,3,0},{-3,3,0,0},{1,0,0,0}};
    public static int numLines;

    public Curve(Point p1,Point p2,Point p3,Point p4,int lines,Paint paint) {
        super(paint);
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.p4 = p4;
        this.lines = lines;
    }

    @Override
    public void draw(Canvas canvas) {
        double[] Px = {p1.x,p2.x,p3.x,p4.x};
        double[] Py = {p1.y,p2.y,p3.y,p4.y};
        double [] xParams = matrixMultiply(Mb, Px);
        double [] yParams = matrixMultiply(Mb,Py);
        int lastX = 0;
        int lastY = 0;
        int xt,yt;
        float step = 1/(float)lines;
        for (float t=0; t<=1 ; t+=step) {
            if (t==0) { //the first point
                lastX = p1.x;
                lastY = p1.y;
                xt = (int)(xParams[0]*Math.pow(t, 3) + xParams[1]*Math.pow(t, 2) + xParams[2]*t + xParams[3]);
                yt = (int)(yParams[0]*Math.pow(t, 3) + yParams[1]*Math.pow(t, 2) + yParams[2]*t + yParams[3]);
                Line l = new Line(new Point(lastX,lastY),new Point(xt,yt),mPaint);
                l.draw(canvas);
            }
            else if (t==1) {//the last point
                xt = p4.x;
                yt = p4.y;
                Line l = new Line(new Point(lastX,lastY),new Point(xt,yt),mPaint);
                l.draw(canvas);
            }
            else {
                xt = (int)(xParams[0]*Math.pow(t, 3) + xParams[1]*Math.pow(t, 2) + xParams[2]*t + xParams[3]);
                yt = (int)(yParams[0]*Math.pow(t, 3) + yParams[1]*Math.pow(t, 2) + yParams[2] * t + yParams[3]);
                Line l = new Line(new Point(lastX,lastY),new Point(xt,yt),mPaint);
                l.draw(canvas);
                lastX = xt;
                lastY = yt;
            }
        }
    }

    public static double[] matrixMultiply(double[][] mb, double[] px) {
        double[] result = {0,0,0,0};
        for (int i=0;i<mb.length;i++) {
            for (int j=0;j<px.length;j++) {
                result[i] += mb[i][j]*px[j];
            }
        }
        return result;
    }

    public static double[] matrixMultiply2(double[] px,double[][] mb) {
        double[] result = {0,0,0,0};
        for (int i=0;i<mb.length;i++) {
            for (int j=0;j<px.length;j++) {
                result[i] += mb[i][j]*px[j];
            }
        }
        return result;
    }
}
