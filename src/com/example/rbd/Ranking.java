package com.example.rbd;

import java.text.NumberFormat;

import android.graphics.Canvas;
import android.graphics.Rect;

import com.example.rbd.MyButton.ButtonAlign;
import com.example.rbd.Sequence.MainSeq;
import com.example.rbd.Sequence.StageNum;

public class Ranking extends Task {

	private StageNum stageNum= StageNum.SEQ_STAGE1;
	private NumberFormat nfNum = null;
	private double time[];
	private MyButton menuButton = null;
	private MyButton nextButton = null;
	private MyButton prevButton = null;
	private int stage = 0;
	private int buttonFrame = 0;
	private boolean onButtonFlag = true;
	private String readStage = "";
	private MyImage back1 = null;
	private MyImage back2 = null;
	private MyImage back3 = null;
	private MyImage back4 = null;
	//private MyButton route = null;
	private Rect rect = null;
	private boolean routeFlag = false;
	private Circle goalCircle1 = null;
	private MyButton routeEndButton = null;
	private MyButton routeButton = null;


	public Ranking() {
		nfNum = NumberFormat.getNumberInstance();
		nfNum.setMinimumIntegerDigits(2);
		nfNum.setMinimumFractionDigits(2);
		time = new double[Database.getElementNum()];
		menuButton = new MyButton(400, 1110, R.drawable.menu_button);
		menuButton.setAlign(ButtonAlign.CENTER);
		menuButton.setText("メニュー");
		menuButton.setTextSize(80);
		prevButton = new MyButton(100, 1100, R.drawable.button2_left);
		prevButton.setAlign(ButtonAlign.CENTER);
		nextButton = new MyButton(700, 1100, R.drawable.button2_right);
		nextButton.setAlign(ButtonAlign.CENTER);
		back1 = new MyImage(R.drawable.ranking1, false);
		back2 = new MyImage(R.drawable.ranking2, false);
		back3 = new MyImage(R.drawable.ranking3, false);
		back4 = new MyImage(R.drawable.ranking4, false);
		rect = new Rect(600, 345, 770, 475);
		//route = new MyButton(rect);
		goalCircle1 = new Circle(383, 110, 30);

		routeEndButton = new MyButton(new Rect(0, 0, 800, 1205));
		routeButton = new MyButton(680, 410, R.drawable.button4);
		routeButton.setText("R");
		routeButton.setAlign(ButtonAlign.CENTER);
		routeButton.setTextSize(80);
	}

	public void init() {
		stageNum = StageNum.SEQ_STAGE1;
		stage = 0;
		readStage = "stage1.txt";
	}

	public void update() {
		if(!routeFlag) {
			if(menuButton.onButton()) {
				Sequence.setSequence(MainSeq.SEQ_MENU);
			}

			///////////////////////////////////////////

			if(nextButton.onButton() && onButtonFlag) {
				stage = ++stage % Database.getStageNum();
				onButtonFlag = false;

			} else if(prevButton.onButton() && onButtonFlag) {
				stage = (stage+3) % Database.getStageNum();
				onButtonFlag = false;
			}

			if(!onButtonFlag) {
				if(buttonFrame >= 5) {
					buttonFrame = 0;
					onButtonFlag = true;
				} else {
					buttonFrame++;
				}
			}

			if(routeButton.onButton()) {
				routeFlag = true;
			}

			switch(stage) {
			case 0:
				stageNum = StageNum.SEQ_STAGE1;
				readStage = "stage1.txt";
				break;

			case 1:
				stageNum = StageNum.SEQ_STAGE2;
				readStage = "stage2.txt";
				break;

			case 2:
				stageNum = StageNum.SEQ_STAGE3;
				readStage = "stage3.txt";
				break;

			case 3:
				stageNum = StageNum.SEQ_STAGE4;
				readStage = "stage4.txt";
				break;
			}
		} else if(routeFlag) {
			if(routeEndButton.onButton()) {
				routeFlag = false;
			}
		}
	}

	public void draw(Canvas canvas) {
		if(!routeFlag) {
			switch(stageNum) {
			case SEQ_STAGE1:
				back1.draw(0, 0);
				break;

			case SEQ_STAGE2:
				back2.draw(0, 0);
				break;

			case SEQ_STAGE3:
				back3.draw(0, 0);
				break;

			case SEQ_STAGE4:
				//back4.draw(0, 0);
				break;
			}


			text.setTextSize(90);
			canvas.drawText("Rank", 150, 315, text);
			canvas.drawText("Time", 500, 315, text);
			text.setTextSize(100);
			for(int i = 0; i < Database.getElementNum(); i++) {
				canvas.drawText(""+(i+1), 150, 450+i*140, text);
				canvas.drawText(nfNum.format(MainLoop.ref(i+1, stageNum)), 430, 450+i*140, text);
			}
			menuButton.draw(canvas);
			nextButton.draw();
			prevButton.draw();
			routeButton.draw(canvas);

		} else if(routeFlag) {
			switch(stageNum) {
			case SEQ_STAGE1:
				Stage1.back.draw(0, 0);
				goalCircle1.draw();
				break;

			case SEQ_STAGE2:
				Stage2.back.draw(0, 0);
				break;

			case SEQ_STAGE3:
				//Stage3.back.draw(0,0);
				break;

			case SEQ_STAGE4:
				//stageBack4.draw(0,0);
				break;
			}
			FileIO.read(readStage, Surface.getCanvas());
		}
	}
}
