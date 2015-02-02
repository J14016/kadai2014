package com.example.rbd;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;

public class Object {


	private Path path = null;
	private Paint paint = null;

	public Object(PointF[] point) {

		if(point.length < 3) {
			return;
		}
		paint = new Paint();
		path = new Path();
		path.moveTo(point[0].x, point[0].y);
		for(int i = 1; i < point.length; i++) {
			path.lineTo(point[i].x, point[i].y);
		};
	}

	public void draw(Canvas canvas) {
		canvas.drawPath(path, paint);

	}

	public void setAlpha(int alpha) {
		paint.setAlpha(alpha);
	}
}
