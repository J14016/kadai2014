package com.example.rbd;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;

import com.example.rbd.Sequence.MainSeq;

public class Title extends Task {

	private MyImage back = null;
	private MyButton button = null;

	public Title() {
		back = new MyImage(R.drawable.top, false);
		Rect rect = new Rect(0, 0, 800, 1205);
		button = new MyButton(rect);
	}

	//更新
	public void update() {
		if(button.onButton()) {
			Sequence.setSequence(MainSeq.SEQ_MENU);
		}
	}

	//描画
	public void draw(Canvas canvas) {
		text.setTextSize(100);
		back.draw(0, 0);
		text.setColor(Color.BLUE);
		Text.draw("Touch to Start", 400, 1000, 80, 30);
	}

	public void init() {

	}
}
