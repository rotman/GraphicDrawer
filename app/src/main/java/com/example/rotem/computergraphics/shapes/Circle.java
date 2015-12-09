package com.example.rotem.computergraphics.shapes;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.Log;

/**
 * Created by rotem on 11/26/2015.
 */
public class Circle extends Shape {
    public Point pc,p;
    private static final String TAG ="Circle" ;

    public Circle(Point pc,Point p,Paint paint) {
        super(paint);
        this.pc = pc;
        this.p = p;
    }

    @Override
    public void draw(Canvas canvas) {
        //Bresenheim ALGORITHM for circle

        // calculates the radius with distance formula
        int r = (int)Math.sqrt(Math.pow((p.x-pc.x),2) + Math.pow((p.y-pc.y),2));
        
        int x = 0;
        int y = r;
        int p = 3-2*r;
        while (x<y) {
            //for all the quarters
            //this is putPixel
            canvas.drawLine(pc.x + x, pc.y + y, pc.x + x+1, pc.y + y+1, mPaint);
            canvas.drawLine(pc.x-x, pc.y+y, pc.x-x+1, pc.y+y+1, mPaint);
            canvas.drawLine(pc.x + x, pc.y - y, pc.x+x+1, pc.y-y+1, mPaint);
            canvas.drawLine(pc.x - x, pc.y - y,pc.x-x+1, pc.y-y+1, mPaint);
            canvas.drawLine(pc.x + y, pc.y + x, pc.x+y+1, pc.y+x+1, mPaint);
            canvas.drawLine(pc.x - y, pc.y + x, pc.x-y+1, pc.y+x+1, mPaint);
            canvas.drawLine(pc.x + y, pc.y - x, pc.x+y+1, pc.y-x+1, mPaint);
            canvas.drawLine(pc.x-y, pc.y-x, pc.x-y+1, pc.y-x+1, mPaint);
            if (p < 0) {
                p = p+(4*x)+6;
            }
            else {
                p = p+4*(x-y)+10;
                y--;
            }
            x++;
        }

    }
}
