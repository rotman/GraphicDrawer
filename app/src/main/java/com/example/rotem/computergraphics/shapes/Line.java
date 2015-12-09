package com.example.rotem.computergraphics.shapes;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

/**
 * Created by rotem on 11/26/2015.
 */
public class Line extends Shape {
    public Point p1,p2;
    public Line(Point p1,Point p2,Paint paint) {
        super(paint);
        this.p1 = p1;
        this.p2 = p2;
    }




    @Override
    public void draw(Canvas canvas) {
        int tempX1 = p1.x;
        int tempX2 = p2.x;
        int tempY1 = p1.y;
        int tempY2 = p2.y;
        int dy = Math.abs(tempY2 - tempY1);
        int dx = Math.abs(tempX2 - tempX1);
        int sx = tempX1 < tempX2 ? 1 : -1;
        int sy = tempY1 < tempY2 ? 1 : -1;
        if (dx >= dy) {
            int p = 2 * dy - dx;
            while (tempX1 != tempX2) {
                //this is putPixel
                canvas.drawLine(tempX1, tempY1, tempX1 + 1, tempY1 + 1, mPaint);
                if (p > 0) {
                    tempY1 += sy;
                    p -= 2 * dx;
                }
                tempX1 += sx;
                p += 2 * dy;
            }
        }
        else if (dy > dx) {
            int p = 2 * dx - dy;
            while (tempY1 != tempY2) {
                //this is putPixel
                canvas.drawLine(tempX1,tempY1,tempX1+1,tempY1+1,mPaint);
                if (p > 0) {
                    tempX1 += sx;
                    p -= 2 * dy;
                }
                tempY1 += sy;
                p += 2 * dx;
            }
        }
    }
}
