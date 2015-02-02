package com.example.rbd;

import android.graphics.Canvas;

import com.example.rbd.Sequence.StageNum;

public class Game extends Task {

	private Stage1 stage1 = null;
	private Stage2 stage2 = null;
	//private Stage3 stage3 = null;
	//private Stage4 stage4 = null;
	private StageNum stageNum = Sequence.getStageSequence();

	public Game() {
		stage1 = new Stage1();
		stage2 = new Stage2();
		//stage3 = new Stage3();
		//stage4 = new Stage4();
	}

	@Override
	public void update() {
		stageNum = Sequence.getStageSequence();
		switch(stageNum) {
		case SEQ_STAGE1:
			stage1.update();
			break;

		case SEQ_STAGE2:
			stage2.update();
			break;

		case SEQ_STAGE3:
			//stage3.update();
			break;

		case SEQ_STAGE4:
			//stage4.update();
			break;
		}
	}

	@Override
	public void draw(Canvas canvas) {
		switch(stageNum) {
		case SEQ_STAGE1:
			stage1.draw(canvas);
			break;

		case SEQ_STAGE2:
			stage2.draw(canvas);
			break;

		case SEQ_STAGE3:
			//stage3.draw(canvas);
			break;

		case SEQ_STAGE4:
			//stage4..draw(canvas)l
			break;
		}
	}

	public void init() {
		switch(stageNum) {
		case SEQ_STAGE1:
			stage1.init();
			break;

		case SEQ_STAGE2:
			stage2.init();
			break;

		case SEQ_STAGE3:
			//stage3.init();
			break;

		case SEQ_STAGE4:
			//stage4.init();
			break;
		}
	}

}
