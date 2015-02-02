package com.example.rbd;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;

public class Circle {

	public PointF center = null;
	public float r = 0;
	public Paint paint = null;

	public Circle(float cx, float cy, float fr) {
		center = new PointF(cx, cy);
		r = fr;
		paint = new Paint();
	}

	public void draw(float x, float y) {
		center = new PointF(x, y);
		Canvas canvas = Surface.getCanvas();
		canvas.drawCircle(x, y, r, paint);
	}

	public void draw(float x, float y, float fr) {
		r = fr;
		draw(x, y);
	}

	public void draw() {
		Canvas canvas = Surface.getCanvas();
		canvas.drawCircle(center.x, center.y, r, paint);
	}

	public void setColor(int color) {
		paint.setColor(color);
	}

	public void setAlpha(int alpha) {
		paint.setAlpha(alpha);
	}

	public void setRadius(float fr) {
		r = fr;
	}

	public float getRadius() {
		return r;
	}

	public PointF getCenter() {
		return center;
	}
}
