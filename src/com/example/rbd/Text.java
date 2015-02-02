package com.example.rbd;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;

public class Text {

	private static Canvas canvas = null;
	private static Paint paint = new Paint();
	private static int flashCount = 0;

	public Text() {
	}

	public void update() {

	}


	public static void draw(String str, int x, int y) {
		canvas = Surface.getCanvas();
		paint.setTextAlign(Align.CENTER);
		canvas.drawText(str, x, y, paint);
	}

	public static void draw(String str, int x, int y, int size) {
		canvas = Surface.getCanvas();
		paint.setTextAlign(Align.CENTER);
		paint.setTextSize(size);
		paint.setColor(Color.BLUE);
		canvas.drawText(str, x, y, paint);
	}

	public static void draw(String str, int x, int y, int size, int frame) {
		if(flashCount /frame == 0) {
			draw(str, x, y, size);
		}

		flashCount++;
		if(flashCount >= frame * 2) {
			flashCount = 0;
		}
	}
}
