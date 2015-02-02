package com.example.rbd;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;

public class Polygon {

	private PointF point[] = null;
	private Path path = null;
	private Paint paint = null;

	public Polygon() {
		paint = new Paint();
	}

	public void draw(PointF p[]) {
		point = new PointF[p.length];
		point = p;
		path = new Path();
		path.moveTo(p[0].x, p[0].y);
		for(int i = 1; i < p.length; i++) {
			path.lineTo(p[i].x, p[i].y);
		}

		Canvas canvas = Surface.getCanvas();
		canvas.drawPath(path, paint);
	}

	public void setColor(int color) {
		paint.setColor(color);
	}

}
