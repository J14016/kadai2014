package com.example.rbd;

import android.graphics.Canvas;

import com.example.rbd.MyButton.ButtonAlign;
import com.example.rbd.Sequence.MainSeq;

public class Menu extends Task {

	private MyButton button1 = null;
	private MyButton button2 = null;
	private MyButton button3 = null;
	private MyButton button4 = null;
	private MyImage back = null;

	public Menu() {
		button1 = new MyButton(400, 325, R.drawable.button);
		button1.setAlign(ButtonAlign.CENTER);
		button1.setText("コース選択");
		button1.setTextSize(80);
		button2 = new MyButton(400, 575, R.drawable.button);
		button2.setAlign(ButtonAlign.CENTER);
		button2.setText("練習");
		button2.setTextSize(80);
		button3 = new MyButton(400, 825, R.drawable.button);
		button3.setAlign(ButtonAlign.CENTER);
		button3.setText("ランキング");
		button3.setTextSize(80);
		button4 = new MyButton(400, 1075, R.drawable.button);
		button4.setAlign(ButtonAlign.CENTER);
		button4.setText("オプション");
		button4.setTextSize(80);
		back = new MyImage(R.drawable.menu_back, false);
	}


	@Override
	public void update() {
		if(button1.onButton()) {
			Sequence.setSequence(MainSeq.SEQ_STAGE_SELECT);
		}

		if(button3.onButton()) {
			Sequence.setSequence(MainSeq.SEQ_RANKING);
		}

	}

	@Override
	public void draw(Canvas canvas) {
		back.draw(0, 0);
		text.setTextSize(100);
		canvas.drawText("メニュー", 400, 150, text);
		button1.draw(canvas);
		button2.draw(canvas);
		button3.draw(canvas);
		button4.draw(canvas);
	}

	public void init() {

	}
}
