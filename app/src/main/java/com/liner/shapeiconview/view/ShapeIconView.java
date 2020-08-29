package com.liner.shapeiconview.view;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.PathParser;

import com.liner.shapeiconview.R;


public class ShapeIconView extends View {
    private Shape shape;
    private Paint shapePaint;
    private int shapeColor;
    private Drawable iconDrawable;
    private IconSize iconSize;
    private float shadowRadius;
    private float shadowSize;
    private int shadowColor;

    public ShapeIconView(Context context) {
        this(context, null);
    }

    public ShapeIconView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ShapeIconView, 0, 0);
        shape = Shape.values()[typedArray.getInt(R.styleable.ShapeIconView_siv_shape, 0)];
        iconSize = IconSize.values()[typedArray.getInt(R.styleable.ShapeIconView_siv_icon_size, 0)];
        shapeColor = typedArray.getColor(R.styleable.ShapeIconView_siv_color, Color.CYAN);
        iconDrawable = ContextCompat.getDrawable(context, typedArray.getResourceId(R.styleable.ShapeIconView_siv_icon, R.mipmap.ic_launcher));
        shadowRadius = typedArray.getDimensionPixelSize(R.styleable.ShapeIconView_siv_shadow_radius, 0);
        shadowSize = typedArray.getDimensionPixelSize(R.styleable.ShapeIconView_siv_shadow_size, 1);
        shadowColor = typedArray.getColor(R.styleable.ShapeIconView_siv_shadow_color, Color.parseColor("#A2000000"));
        shapePaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        shapePaint.setStyle(Paint.Style.FILL);
        shapePaint.setAntiAlias(true);
        shapePaint.setColor(shapeColor);
        shapePaint.setShadowLayer(shadowRadius, 0, 0, shadowColor);
        typedArray.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawPath(shape.getPath(shadowSize, shadowSize, getWidth()-shadowSize, getHeight()-shadowSize), shapePaint);
        iconDrawable.setBounds(iconSize.getSize(), iconSize.getSize(), getWidth() - iconSize.getSize(), getHeight() - iconSize.getSize());
        iconDrawable.draw(canvas);
    }



    public void setShape(Shape shape) {
        this.shape = shape;
        invalidate();
    }

    public void setIcon(Drawable drawable) {
        this.iconDrawable = drawable;
        invalidate();
    }

    public void setIconSize(IconSize iconSize) {
        this.iconSize = iconSize;
        invalidate();
    }

    public void setIcon(Bitmap bitmap) {
        setIcon(new BitmapDrawable(getResources(), bitmap));
    }

    public void setShapeColor(int shapeColor) {
        this.shapeColor = shapeColor;
        shapePaint.setColor(shapeColor);
        invalidate();
    }


    public enum Shape {
        CIRCLE(circlePath()),
        RECT(squarePath()),
        SUPER_ELLIPSE(PathParser.createPathFromPathData("M 50,0 C 10,0 0,10 0,50 C 0,90 10,100 50,100 C 90,100 100,90 100,50 C 100,10 90,0 50,0 Z")),
        TEAR_DROP(PathParser.createPathFromPathData("M 50,0 A 50,50,0,0 1 100,50 L 100,85 A 15,15,0,0 1 85,100 L 50,100 A 50,50,0,0 1 50,0 z")),
        SHIELD(PathParser.createPathFromPathData("m6.6146,13.2292a6.6146,6.6146 0,0 0,6.6146 -6.6146v-5.3645c0,-0.6925 -0.5576,-1.25 -1.2501,-1.25L6.6146,-0 1.2501,-0C0.5576,0 0,0.5575 0,1.25v5.3645A6.6146,6.6146 0,0 0,6.6146 13.2292Z")),
        LEMON(PathParser.createPathFromPathData("M1.2501,0C0.5576,0 0,0.5576 0,1.2501L0,6.6146A6.6146,6.6146 135,0 0,6.6146 13.2292L11.9791,13.2292C12.6716,13.2292 13.2292,12.6716 13.2292,11.9791L13.2292,6.6146A6.6146,6.6146 45,0 0,6.6146 0L1.2501,0z")),
        ROUND_RECT(PathParser.createPathFromPathData("M 50,0 L 70,0 A 30,30,0,0 1 100,30 L 100,70 A 30,30,0,0 1 70,100 L 30,100 A 30,30,0,0 1 0,70 L 0,30 A 30,30,0,0 1 30,0 z"));
        Path path;

        Shape(Path path) {
            this.path = path;
        }

        public Path getPath(float left, float top, float right, float bottom) {
            Path resizedPath = new Path(path);
            RectF source = new RectF();
            resizedPath.computeBounds(source, true);
            Matrix matrix = new Matrix();
            matrix.setRectToRect(source, new RectF(left, top, right, bottom), Matrix.ScaleToFit.CENTER);
            resizedPath.transform(matrix);
            return resizedPath;
        }

        private static Path circlePath() {
            Path path = new Path();
            path.arcTo(new RectF(0f, 0f, 50f, 50f), 0f, 359f);
            path.close();
            return path;
        }

        private static Path squarePath() {
            Path path = new Path();
            path.lineTo(0f, 50f);
            path.lineTo(50f, 50f);
            path.lineTo(50f, 0f);
            path.lineTo(0f, 0f);
            path.close();
            return path;
        }
    }
    public enum IconSize{
        SMALLEST(Utils.dpToPx(24)),
        SMALL(Utils.dpToPx(16)),
        NORMAL(Utils.dpToPx(8)),
        BIG(Utils.dpToPx(4));
        float size;

        IconSize(float size) {
            this.size = size;
        }

        public int getSize() {
            return Math.round(size);
        }
    }
}
