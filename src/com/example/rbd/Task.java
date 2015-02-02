package com.example.rbd;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;

public abstract class Task {
	protected Paint text = null;

	public Task() {
		text = new Paint();
		text.setTextAlign(Align.CENTER);
	}

	public abstract void update();
	public abstract void draw(Canvas canvas);
}
