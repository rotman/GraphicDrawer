package com.example.rotem.computergraphics;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import com.afollestad.materialdialogs.MaterialDialog;
import com.example.rotem.computergraphics.shapes.Circle;
import com.example.rotem.computergraphics.shapes.Curve;
import com.example.rotem.computergraphics.shapes.Line;
import com.example.rotem.computergraphics.shapes.Polygon;
import java.util.ArrayList;

/**
 * Created by rotem on 11/25/2015.
 */
public class CanvasView extends View{

    private static final String TAG = "CanvasView";
    private Context context;
    private Paint mPaint;
    public ArrayList<Point> points = new ArrayList<>();
    public ArrayList<Point> feedBackPoints = new ArrayList<>();
    public enum Shape {
        LINE,CIRCLE,POLYGON,CURVE,NONE
    }
    public static Shape shape;
    public ArrayList<com.example.rotem.computergraphics.shapes.Shape> shapes = new ArrayList<>();

    public CanvasView(Context c, AttributeSet attrs) {
        super(c, attrs);
        context = c;
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeWidth(4f);
        shape = Shape.NONE;
    }

    @Override
    protected void onDraw(final Canvas canvas) {
        super.onDraw(canvas);
        //first draw the shapes from before
        for (com.example.rotem.computergraphics.shapes.Shape s : shapes) {
            if (s.getClass().equals(Line.class)) {
                Line l = (Line)s;
                l.draw(canvas);
            }
            else if (s.getClass().equals(Circle.class)) {
                Circle c = (Circle)s;
                c.draw(canvas);
            }
            else if (s.getClass().equals(Curve.class)) {
                Curve c = (Curve)s;
                c.draw(canvas);
            }
            else if (s.getClass().equals(Polygon.class)) {
                Polygon p = (Polygon)s;
                p.draw(canvas);
            }
        }
        //this shows all the points that the user pressed for feedback
        for (Point p : feedBackPoints) {
            Circle c = new Circle(p,new Point(p.x+5,p.y+5),mPaint);
            c.draw(canvas);
        }
        if (shape.equals(Shape.LINE) && points.size() == 2) {
            Line l = new Line(points.get(0),points.get(1),mPaint);
            //add the shape to an array of shapes for the consistency
            shapes.add(l);
            l.draw(canvas);
            points.clear();
        }
        else if (shape.equals(Shape.CIRCLE) && points.size() == 2) {
            Circle c = new Circle(points.get(0),points.get(1),mPaint);
            //add the shape to an array of shapes for the consistency
            shapes.add(c);
            c.draw(canvas);
            points.clear();
        }
        else if (shape.equals(Shape.CURVE) && points.size() == 4) {
            if (Curve.numLines >0) {
                Curve c = new Curve(points.get(0),points.get(1),points.get(2),points.get(3),Curve.numLines,mPaint);
                //add the shape to an array of shapes for the consistency
                shapes.add(c);
                c.draw(canvas);
                points.clear();
            }
        }
        else if (shape.equals(Shape.POLYGON) && points.size() == 2) {
            if (Polygon.numSides >= 3) {
                Polygon p = new Polygon(points.get(0),points.get(1),mPaint);
                //add the shape to an array of shapes for the consistency
                shapes.add(p);
                p.draw(canvas);
                points.clear();
            }
        }
    }


    public void clearCanvas() {
        points.clear();
        shapes.clear();
        feedBackPoints.clear();
        shape = CanvasView.Shape.NONE;
        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                int x = (int) event.getX();
                int y = (int) event.getY();
                //draw the point for user feedback
                feedBackPoints.add(new Point(x, y));
                points.add(new Point(x,y));
                break;
            case MotionEvent.ACTION_UP:
                int count = points.size();
                switch (shape) { //here we do the points count check validation
                    case LINE:
                        if (count == 2) {
                            feedBackPoints.clear();
                        }
                        invalidate(); //and then send it to onDraw
                        break;
                    case CIRCLE:
                        if (count == 2) {
                            feedBackPoints.clear();
                        }
                        invalidate(); //and then send it to onDraw
                        break;
                    case CURVE:
                        if (count == 4) {
                            feedBackPoints.clear();
                        }
                        invalidate(); //and then send it to onDraw
                        break;
                    case POLYGON:
                        if (count == 2) {
                            feedBackPoints.clear();
                        }
                        invalidate(); //and then send it to onDraw
                        break;
                    case NONE:
                        new MaterialDialog.Builder(context)
                                .title("You should choose a shape...")
                                .positiveText("OK")
                                .show();
                        points.clear();
                        feedBackPoints.clear();
                        break;
                }
                break;
        }
        return true;
    }
}
