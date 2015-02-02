package com.example.rbd;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public abstract class Surface extends SurfaceView implements Runnable, SurfaceHolder.Callback {

	private static Thread thread = null;
	private int screenWidth = 0;
	private int screenHeight = 0;
	private static float touchX = -2;
	private static float touchY = -2;
	private int backgroundColor = Color.WHITE;
	private SurfaceHolder surfaceHolder = null;
	protected static Canvas canvas = null;

	public Surface(Context context) {
		super(context);
		surfaceHolder = getHolder();
		surfaceHolder.addCallback(this);
	}

	public static Thread getThread() {
		return thread;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		touchX = -1000;
		touchY = -1000;

		switch(event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			touchX = event.getX();
			touchY = event.getY();
			break;
		}

		Log.d("TouchEvent", "X:" + event.getX() + ", Y:" + event.getY());
		return true;
	}

	public static float getTouchX() {
		return touchX;
	}

	public static float getTouchY() {
		return touchY;
	}

	public static void setTouchX(int x) {
		touchX = x;
	}

	public static void setTouchY(int y) {
		touchY = y;
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
		screenWidth = width;
		screenHeight = height;
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		thread = new Thread(this);
		thread.start();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		thread = null;
	}

	@Override
	public void run() {
		while(thread != null) {
			canvas = surfaceHolder.lockCanvas();
			if(canvas != null) {
				canvas.drawColor(backgroundColor);
				loop();
				surfaceHolder.unlockCanvasAndPost(canvas);
			}
		}
	}

	protected abstract void loop();

	public static Canvas getCanvas() {
		return canvas;
	}
}
