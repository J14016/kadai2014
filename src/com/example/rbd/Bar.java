package com.example.rbd;

import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.PointF;

public class Bar extends Task {

	private PointF point[] = null;
	private Path path = null;
	private PointF center = null;
	public float width = 0;
	public float height = 0;
	private PointF rotatePoint[] = null;
	private float lengthX = 0;

	public Bar(PointF p, float w, float h) {
		path = new Path();
		point = new PointF[4];
		point[0] = new PointF(p.x, p.y);
		point[1] = new PointF(p.x + w, p.y);
		point[2] = new PointF(p.x + w, p.y + h);
		point[3] = new PointF(p.x, p.y + h);
		rotatePoint = new PointF[4];
		rotatePoint[0] = new PointF(point[0].x, point[0].y);
		rotatePoint[1] = new PointF(point[1].x, point[1].y);
		rotatePoint[2] = new PointF(point[2].x, point[2].y);
		rotatePoint[3] = new PointF(point[3].x, point[3].y);
		width = w;
		height = h;
		float width = w / 2;
		float height = h / 2;
		center = new PointF(p.x + width, p.y + height);
	}

	@Override
	public void update() {

	}

	@Override
	public void draw(Canvas canvas) {
		path.reset();
		path.moveTo(rotatePoint[0].x, rotatePoint[0].y);
		for(int i = 1; i < rotatePoint.length; i++) {
			path.lineTo(rotatePoint[i].x, rotatePoint[i].y);
		}
		canvas.drawPath(path, text);
	}

	public void draw(float x, float y, float w, float h) {
		Canvas canvas = Surface.getCanvas();

		lengthX = x - rotatePoint[0].x;
		rotatePoint[0].x = x;
		rotatePoint[0].y = y;
		rotatePoint[1].x = x+w;
		rotatePoint[1].y = y;
		rotatePoint[2].x = x+w;
		rotatePoint[2].y = y+h;
		rotatePoint[3].x = x;
		rotatePoint[3].y = y+h;
		canvas.drawRect(x, y, x+w, y+h, text);

	}

	public float getLength() {
		return lengthX;
	}



	public void rotate(float ang) {
		for(int i = 0; i < rotatePoint.length; i++) {
			rotatePoint[i].x = (float)(center.x + Math.cos(ang) * (point[i].x - center.x) - Math.sin(ang) * (point[i].y - center.y));
			rotatePoint[i].y = (float)(center.y + Math.sin(ang) * (point[i].x - center.x) + Math.cos(ang) * (point[i].y - center.y));
		}

	}

	public PointF[] getPoint() {
		return rotatePoint;
	}

	public void setColor(int color) {
		text.setColor(color);
	}
}
