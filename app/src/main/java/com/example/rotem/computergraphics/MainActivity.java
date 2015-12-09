package com.example.rotem.computergraphics;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import com.afollestad.materialdialogs.MaterialDialog;
import com.example.rotem.computergraphics.shapes.Curve;
import com.example.rotem.computergraphics.shapes.Polygon;
import com.github.johnpersano.supertoasts.SuperToast;

public class MainActivity extends AppCompatActivity {

    private CanvasView canvas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        canvas = (CanvasView) findViewById(R.id.canvas);
    }

    public void drawLine(View v) {
        CanvasView.shape = CanvasView.Shape.LINE;
        //canvas.points.clear();
    }

    public void drawCircle(View v) {
        CanvasView.shape = CanvasView.Shape.CIRCLE;
        //canvas.points.clear();
    }

    public void drawCurve(View v) {
        new MaterialDialog.Builder(this)
                .title("Enter number of lines")
                .content("It will be the number of lines that creates the curve (not 0 or negative)...")
                .inputType(InputType.TYPE_CLASS_NUMBER)
                .input(null, null, new MaterialDialog.InputCallback() {
                    @Override
                    public void onInput(MaterialDialog dialog, CharSequence input) {
                        if (!input.toString().isEmpty()) {
                            int lines = Integer.valueOf(input.toString());
                            if (lines > 0) {
                                CanvasView.shape = CanvasView.Shape.CURVE;
                                Curve.numLines = lines;
                            } else {
                                SuperToast.create(MainActivity.this, "I said 0 isn't allowed!", SuperToast.Duration.SHORT).show();
                            }
                        } else {
                            CanvasView.shape = CanvasView.Shape.NONE;
                            Polygon.numSides = 0;
                        }
                    }

                }).show();
        canvas.points.clear();
    }

    public void drawPolygon(View v) {
        new MaterialDialog.Builder(this)
                .title("Enter number of sides")
                .content("Must be 3 or bigger...")
                .inputType(InputType.TYPE_CLASS_NUMBER)
                .input(null, null, new MaterialDialog.InputCallback() {
                    @Override
                    public void onInput(MaterialDialog dialog, CharSequence input) {
                        if (!input.toString().isEmpty()) {
                            int sides = Integer.valueOf(input.toString());
                            if (sides >= 3) {
                                CanvasView.shape = CanvasView.Shape.POLYGON;
                                Polygon.numSides = sides;
                            }
                            else {
                                SuperToast.create(MainActivity.this,"I said more then 3!",SuperToast.Duration.SHORT).show();
                            }
                        } else {
                            CanvasView.shape = CanvasView.Shape.NONE;
                            Polygon.numSides = 0;
                        }
                    }
                }).show();
        canvas.points.clear();
    }

    public void clearCanvas(View v) {
        canvas.clearCanvas();
    }


}
