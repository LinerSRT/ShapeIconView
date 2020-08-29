package com.liner.shapeiconview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;

import com.liner.shapeiconview.view.ShapeIconView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ShapeIconView shapeIconView = new ShapeIconView(this);
        shapeIconView.setIcon(ContextCompat.getDrawable(this, R.drawable.ic_baseline_attach_money_24));
        shapeIconView.setIconSize(ShapeIconView.IconSize.SMALL);
        shapeIconView.setShape(ShapeIconView.Shape.SUPER_ELLIPSE);
        shapeIconView.setShapeColor(ContextCompat.getColor(this, R.color.colorRed));
    }
}