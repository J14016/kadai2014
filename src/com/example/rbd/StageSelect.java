package com.example.rbd;

import android.graphics.Canvas;

import com.example.rbd.MyButton.ButtonAlign;
import com.example.rbd.Sequence.MainSeq;
import com.example.rbd.Sequence.StageNum;

public class StageSelect extends Task {

	private MyButton img_stage1 = null;
	private MyButton img_stage2 = null;
	private MyButton img_stage3 = null;
	private MyButton img_stage4 = null;
	private MyImage back = null;
	private MyButton menuButton = null;

	public StageSelect() {
		img_stage1 = new MyButton(230, 450, R.drawable.stage1);
		img_stage1.resize(0.3, 0.3);
		img_stage1.setAlign(ButtonAlign.CENTER);
		img_stage1.setText("ステージ1");
		img_stage1.setTextSize(40);
		img_stage2 = new MyButton(570, 450, R.drawable.stage2);
		img_stage2.resize(0.3, 0.3);
		img_stage2.setAlign(ButtonAlign.CENTER);
		img_stage2.setText("ステージ2");
		img_stage2.setTextSize(40);
		img_stage3 = new MyButton(230, 900, R.drawable.stage3);
		img_stage3.resize(0.3, 0.3);
		img_stage3.setAlign(ButtonAlign.CENTER);
		img_stage3.setText("ステージ3");
		img_stage3.setTextSize(40);
		img_stage4 = new MyButton(570, 900, R.drawable.stage4);
		img_stage4.resize(0.3, 0.3);
		img_stage4.setAlign(ButtonAlign.CENTER);
		img_stage4.setText("ステージ4");
		img_stage4.setTextSize(40);
		back = new MyImage(R.drawable.ss_back, false);
		menuButton = new MyButton(400, 1130, R.drawable.menu_button);
		menuButton.setAlign(ButtonAlign.CENTER);
		menuButton.setText("メニュー");
		menuButton.setTextSize(80);
	}

	@Override
	public void update() {
		if(img_stage1.onButton()) {
			Sequence.setSequence(MainSeq.SEQ_GAME);
			Sequence.setSequence(StageNum.SEQ_STAGE1);
		}
		if(img_stage2.onButton()) {
			Sequence.setSequence(MainSeq.SEQ_GAME);
			Sequence.setSequence(StageNum.SEQ_STAGE2);
		}
		if(menuButton.onButton()) {
			Sequence.setSequence(MainSeq.SEQ_MENU);
		}
	}

	@Override
	public void draw(Canvas canvas) {
		back.draw(0, 0);
		text.setTextSize(100);
		canvas.drawText("ステージ選択", 400, 200, text);
		img_stage1.draw(canvas);
		img_stage2.draw(canvas);
		img_stage3.draw(canvas);
		img_stage4.draw(canvas);
		menuButton.draw(canvas);
	}

	public void init() {

	}

}
