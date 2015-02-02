package com.example.rbd;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PointF;

public class Player extends Task {

	enum PlayerState {
		INV,
		NONE,
	}
	public PlayerState playerState = PlayerState.NONE;
	public int inv = 0;


	private final static float MAX_SPEED = 10;
	private Vec vec = new Vec();
	private Vec sensorVec = new Vec();
	private PointF playerPos = null;
	private PointF initPos = null;
	private Circle circle = null;
	private MyImage rest_life = null;
	private MyImage lose_life = null;
	private MyImage life[] = null;
	private int MAX_LIFE = 3;
	private int lifePoint = MAX_LIFE;







	public Player(float x, float y, int l) {
		MAX_LIFE = l;
		initPos = new PointF(x, y);
		playerPos = new PointF(x, y);
		circle = new Circle(initPos.x, initPos.y, 30);
		circle.setColor(Color.YELLOW);
		lifePoint = MAX_LIFE;
		rest_life = new MyImage(R.drawable.life);
		lose_life = new MyImage(R.drawable.lose_life);
		life = new MyImage[MAX_LIFE];
		for(int i = 0; i < MAX_LIFE; i++) {
			life[i] = new MyImage(R.drawable.life);
		}
	}

	private void setVec() {
		float x = RBDSensor.Inst().getX()*2;
		float y = RBDSensor.Inst().getY()*2;
		sensorVec.x = x < 0 ? -x*x : x*x;
		sensorVec.y = y < 0 ? -y*y : y*y;
		sensorVec.setLengthCap(MAX_SPEED);
		vec.blend(sensorVec, 0.05f);
	}

	public void init() {
		lifePoint = MAX_LIFE;
		inv = 0;
		for(int i = 0; i < MAX_LIFE; i++) {
			life[i] = new MyImage(R.drawable.life);
		}
		circle.center.x = initPos.x;
		circle.center.y = initPos.y;
		playerPos.x = initPos.x;
		playerPos.y = initPos.y;
		vec.x = 0;
		vec.y = 0;
		sensorVec.x = 0;
		sensorVec.y = 0;



		////////////////////////////
		FileIO.write("sample.txt", "", 'p');
		///////////////////////////
	}

	public void initPos() {
		circle.center.x = initPos.x;
		circle.center.y = initPos.y;
		playerPos.x = initPos.x;
		playerPos.y = initPos.y;
		vec.x = 0;
		vec.y = 0;
		sensorVec.x = 0;
		sensorVec.y = 0;

	}

	@Override
	public void update() {
		if(inv <= 0) {
			playerState = PlayerState.NONE;
			inv = 0;
		} else {
			playerState = PlayerState.INV;
			inv--;
		}
		for(int i = MAX_LIFE-1; i >= lifePoint && i >= 0; i--) {
			life[i] = lose_life;
		}
		setVec();
		move();

		///////////////////////////////////////////////////////////////////////////////
		FileIO.write("sample.txt", circle.center.x+","+circle.center.y+"\n", 'a');
		///////////////////////////////////////////////////////////////////////////////
	}

	@Override
	public void draw(Canvas canvas) {
		if(inv <= 0) {
			circle.setAlpha(255);
		} else {
			circle.setAlpha(100);
		}
		canvas.drawCircle(circle.center.x, circle.center.y, circle.getRadius(), circle.paint);
		for(int i = 0; i < MAX_LIFE; i++) {
			life[i].draw(i*64+20, i+20);
		}
	}

	public void move() {
		circle.center.x += vec.x;
		circle.center.y += vec.y;
		playerPos.x = circle.center.x;
		playerPos.y = circle.center.y;
	}

	public PointF getPosition() {
		return playerPos;
	}

	public void setPosition(float x, float y) {
		circle.center.x += x;
		circle.center.y += y;
		playerPos.x = x;
		playerPos.y = y;
	}

	public void setLifePoint(int n) {
		lifePoint += n;
		if(lifePoint <= 0) {
			lifePoint = 0;
		}
	}

	public int getLifePoint() {
		return lifePoint;
	}

	public void setState(PlayerState state) {
		playerState = state;
	}

}
