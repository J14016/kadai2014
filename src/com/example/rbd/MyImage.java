package com.example.rbd;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class MyImage {
	private Bitmap bitmap = null;
	private int width = 0;
	private int height = 0;
	private int image_id = 0;

	public MyImage(int id) {
		image_id = id;
		BitmapFactory.Options opt = new BitmapFactory.Options();
		opt.inScaled = true;
		bitmap = BitmapFactory.decodeResource(RBDActivity.getResource(), id, opt);
		width = bitmap.getWidth();
		height = bitmap.getHeight();
	}

	public MyImage(int id, boolean flag) {
		image_id = id;
		BitmapFactory.Options opt = new BitmapFactory.Options();
		opt.inScaled = flag;
		bitmap = BitmapFactory.decodeResource(RBDActivity.getResource(), id, opt);
		width = bitmap.getWidth();
		height = bitmap.getHeight();

	}

	public void draw(float x, float y) {
		Surface.getCanvas().drawBitmap(bitmap, x, y, null);
	}

	public void resize(double w, double h) {
		width = (int)(width * w);
		height = (int)(height * h);
		bitmap = Bitmap.createScaledBitmap(bitmap, width, height, false);
	}

	public void resize(int w, int h) {
		width = w;
		height = h;
		bitmap = Bitmap.createScaledBitmap(bitmap, width, height, false);
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
}
