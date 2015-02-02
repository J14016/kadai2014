package com.example.rbd;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;

import com.example.rbd.MyButton.ButtonAlign;
import com.example.rbd.Sequence.MainSeq;


public abstract class BasedStage extends Task {

	private boolean startFlag = false;
	private boolean gameoverFlag = false;
	private boolean initFlag = false;
	private boolean moveFlag = false;
	private boolean goalFlag = false;
	private boolean countStartFlag = false;
	private boolean fallFlag = false;
	private String countDownStr = "";
	private int countDownFrame = 0;
	private int gameoverFrame = 0;
	private int fallFrame = 0;
	private int goalFrame = 0;
	private int cdStrAlpha = 255;
	private Paint countDownPaint = null;
	private Paint timeCountPaint = null;
	private Paint gameoverPaint = null;
	private Paint goalPaint = null;
	private long previousTime = 0;
	private long currentTime = 0;
	private long time = 0;
	private double timeLimit = 0;
	private MyButton restartButton = null;
	private MyButton gameoverTitleButton = null;
	private MyButton titleButton = null;
	private MyButton menuButton = null;


	public BasedStage() {
		countDownPaint = new Paint();
		countDownPaint.setTextAlign(Align.CENTER);
		countDownPaint.setTextSize(100);
		timeCountPaint = new Paint();
		timeCountPaint.setTextAlign(Align.RIGHT);
		timeCountPaint.setTextSize(80);
		gameoverPaint = new Paint();
		gameoverPaint.setTextAlign(Align.CENTER);
		gameoverPaint.setTextSize(130);
		goalPaint = new Paint();
		goalPaint.setTextAlign(Align.CENTER);
		goalPaint.setTextSize(130);
		restartButton = new MyButton(400, 1205/2 - 100, R.drawable.button);
		restartButton.setAlign(ButtonAlign.CENTER);
		restartButton.setText("やりなおす");
		restartButton.setTextSize(80);
		gameoverTitleButton = new MyButton(400, 1205/2+100, R.drawable.button);
		gameoverTitleButton.setText("タイトル");
		gameoverTitleButton.setTextSize(80);
		gameoverTitleButton.setAlign(ButtonAlign.CENTER);
		titleButton = new MyButton(400, 1205/2-100, R.drawable.button);
		titleButton.setAlign(ButtonAlign.CENTER);
		titleButton.setText("タイトル");
		titleButton.setTextSize(80);
		menuButton = new MyButton(400, 1205/2 + 100, R.drawable.button);
		menuButton.setAlign(ButtonAlign.CENTER);
		menuButton.setText("メニュー");
		menuButton.setTextSize(80);
		init();
	}

	public void init() {
		startFlag = true;
		gameoverFlag = false;
		initFlag = false;
		moveFlag = false;
		goalFlag = false;
		countStartFlag = false;
		fallFlag = false;
		countDownStr = "";
		countDownFrame = 0;
		gameoverFrame = 0;
		goalFrame = 0;
		fallFrame = 0;
		cdStrAlpha = 255;
		countDownPaint.setColor(Color.argb(255, 0, 0, 0));
		previousTime = 0;
		currentTime = 0;
		initProc();
	}

	@Override
	public void update() {
		if(initFlag) {
			init();
		}
		if(goalFlag) {
			goalUpdate();
			goalFrame++;
		}

		if(!gameoverFlag && !goalFlag) {
			fall();
			updateProc();
		}
		if(gameoverFlag) {
			gameoverFrame++;
		}
	}

	@Override
	public void draw(Canvas canvas) {
		drawProc(canvas);
		canvas.drawText(String.format("%.2f", MyMath.roundDown(time/1000.0f, 2)), 780, 80, timeCountPaint);
	}

	public void countDown(Canvas canvas) {
		if(!startFlag) {
			return;
		}

		switch(countDownFrame / FpsMgr.getFps()) {
		case 0:
			countDownStr = "";
			break;

		case 1:
			countDownStr = "3";
			break;

		case 2:
			countDownStr = "2";
			break;

		case 3:
			countDownStr = "1";
			break;

		default:
			moveFlag = true;
			countStartFlag = true;
			countDownStr = "START!";
			countDownPaint.setTextSize(80);
			countDownPaint.setColor(Color.argb(cdStrAlpha, 128, 128, 0));
			cdStrAlpha -= 5;
			if(cdStrAlpha < 0) {
				cdStrAlpha = 0;
				startFlag = false;
			}
			break;
		}
		countDownPaint.setTextSize(180);
		countDownPaint.setColor(Color.argb(cdStrAlpha, 255, 156, 0));
		canvas.drawText(countDownStr, 400, 1205/2, countDownPaint);
		countDownFrame++;
	}

	public void timeCount() {
		if(countStartFlag && !goalFlag) {
			if(previousTime  == 0) {
				previousTime = System.currentTimeMillis();
			}

			currentTime = System.currentTimeMillis();
			long t = currentTime - previousTime;
			 previousTime = currentTime;

			time -= t;

			if(time < 0) {
				time = 0;
			}
		}
	}

	public void setGameoverFlag(boolean flag) {
		gameoverFlag = flag;
	}

	public void setGoalFlag(boolean flag) {
		goalFlag = flag;
	}

	public void setMoveFlag(boolean flag) {
		moveFlag = flag;
	}

	public boolean getMoveFlag() {
		return moveFlag;
	}

	public void setFallFlag(boolean flag) {
		fallFlag = flag;
	}

	public double getTime() {
		return time;
	}

	public void gameoverDraw(Canvas canvas) {
		if(!gameoverFlag) {
			return;
		}

		if(gameoverFrame / FpsMgr.getFps() < 2) {
			gameoverPaint.setColor(Color.RED);
			canvas.drawText("GAME OVER", 400, 1205/2, gameoverPaint);
		} else {
			restartButton.draw(canvas);
			gameoverTitleButton.draw(canvas);
			gameoverFrame = 1000;
			if(restartButton.onButton()) {
				init();
			}
			if(gameoverTitleButton.onButton()) {
				Sequence.setSequence(MainSeq.SEQ_TITLE);
			}
		}
	}

	public void gameClearDraw(Canvas canvas) {
		if(!goalFlag) {
			return;
		}

		if(goalFrame / FpsMgr.getFps() < 2) {
			goalPaint.setColor(Color.BLUE);
			canvas.drawText("CLEAR!", 400, 1205/2, goalPaint);
		} else {
			goalFrame = 1000;
			titleButton.draw(canvas);
			menuButton.draw(canvas);
			if(titleButton.onButton()) {
				Sequence.setSequence(MainSeq.SEQ_TITLE);
			}
			if(menuButton.onButton()) {
				Sequence.setSequence(MainSeq.SEQ_MENU);
			}
		}
	}

	public void setTimeLimit(long t) {
		time = t * 1000;
		timeLimit = t;
	}

	public void goalUpdate() {
		if(goalFrame == 0) {
			double prev = timeLimit - MyMath.roundDown(MyMath.toSecond(time), 2);
			double current = 0;

			for(int i = 0; i < Database.getElementNum(); i++) {
				current = MainLoop.ref(i+1, Sequence.getStageSequence());
				if(prev < current || current == 0) {
					if(i == 0) {
						String s = "";
						switch(Sequence.getStageSequence()) {
						case SEQ_STAGE1:
							s = "stage1.txt";
							break;

						case SEQ_STAGE2:
							s = "stage2.txt";
							break;

						case SEQ_STAGE3:
							s = "stage3.txt";
							break;

						case SEQ_STAGE4:
							s = "stage4.txt";
							break;
						}
						FileIO.readAndWrite(s, "sample.txt");
					}

					MainLoop.addTime(i+1, prev, Sequence.getStageSequence());
					prev = current;
				}
	 		}
		}
	}

	public void fall() {
		if(fallFlag) {
			if(fallFrame < FpsMgr.getFps() * 1) {
				moveFlag = false;
				fallFrame++;
			} else {
				moveFlag = true;
				fallFlag = false;
				fallFrame = 0;
			}
		}
	}


	abstract public void updateProc();
	abstract public void drawProc(Canvas canvas);
	abstract public void initProc();
}
