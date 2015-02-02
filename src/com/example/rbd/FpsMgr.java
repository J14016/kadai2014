package com.example.rbd;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint.Align;

public class FpsMgr extends Task {

	private static int fps = 60;
	private long currentTime = 0;
	private long previousTime = 0;
	private long baseTime = 0;
	private int count = 0;
	private double eachFrameTime[];
	private double average;
	private long sleepTime;

	public FpsMgr(int f) {
		fps = f;
		for(int i = 0; i < fps; i++) {
			eachFrameTime = new double[fps];
		}
	}

	public static int getFps() {
		return fps;
	}

	@Override
	public void update() {
		try {
			if(count == 0) {
				if(previousTime == 0) {
					sleepTime = 0;
				} else {
					sleepTime = baseTime + 1000L - System.currentTimeMillis();
				}
			} else {
				sleepTime = (baseTime+(long)(count*(1000.0f/fps)) - System.currentTimeMillis());
			}

			if(sleepTime > 0) {
				Surface.getThread().sleep(sleepTime);
			}

			currentTime = System.currentTimeMillis();
			eachFrameTime[count] = currentTime - previousTime;
			previousTime = currentTime;

			if(count == 0) {
				baseTime = currentTime;
			}

			if(count == fps - 1) {
				average = 0;
				for(int i = 0; i < fps; i++) {
					average += eachFrameTime[i];
				}
				average /= fps;
			}

			count = (++count) % fps;

		} catch(InterruptedException e){}
	}

	@Override
	public void draw(Canvas canvas) {
		text.setTextSize(30);
		text.setTextAlign(Align.LEFT);
		text.setColor(Color.BLUE);
		if(average != 0) {
			//canvas.drawText("fps:" + 1000/average, 10, 60, text);
		}
	}
}
