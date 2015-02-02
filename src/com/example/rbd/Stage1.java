package com.example.rbd;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PointF;

public class Stage1 extends BasedStage {

	public static MyImage back = null;
	private Player player = null;
	private Circle goalCircle = null;
	private Circle col_circle1 = null;
	private PointF []point1 = null;
	private PointF []point2 = null;
	private Object object1 = null;
	private Object object2 = null;
	private Bar bar = null;
	private int rotateFrame = 0;
	private int frame = 0;

	public Stage1() {
		super();
		back = new MyImage(R.drawable.stage1, false);
		player = new Player(372, 960, 1);
		goalCircle = new Circle(383, 110, 30);
		initColObject();
		bar = new Bar(new PointF(83, 543), 589, 50);
		bar.setColor(Color.GRAY);
	}

	public void initColObject() {
		col_circle1 = new Circle(370, (int)1205/2-5-50, 300);

		point1 = new PointF[4];
		point1[0] = new PointF(330, 40);
		point1[1] = new PointF(435, 42);
		point1[2] = new PointF(435, 255);
		point1[3] = new PointF(330, 252);
		object1 = new Object(point1);

		point2 = new PointF[4];
		point2[0] = new PointF(315, 843);
		point2[1] = new PointF(420, 843);
		point2[2] = new PointF(420, 1053);
		point2[3] = new PointF(315, 1053);
		object2 = new Object(point2);




	}

	@Override
	public void updateProc() {
		timeCount();

		collision();
		bar.rotate((float)(rotateFrame*Math.PI/180));
		rotateFrame = ++rotateFrame % 360+3;

		if(getMoveFlag()) {
			player.update();
		}
		if(player.getLifePoint() <= 0 || getTime() <= 0) {
			setGameoverFlag(true);
		}

		frame = ++frame % FpsMgr.getFps();
	}

	@Override
	public void drawProc(Canvas canvas) {
		back.draw(0, 0);
		//debugObject(canvas);
		bar.draw(canvas);
		goalCircle.draw();
		player.draw(canvas);
		gameoverDraw(canvas);
		gameClearDraw(canvas);
		countDown(canvas);




	}

	@Override
	public void initProc() {
		if(player != null) {
			player.init();
		}
		setTimeLimit(20);
	}

	public void collision() {
		boolean collision = Collision.isPointInCircle(player.getPosition(), goalCircle.getCenter(), goalCircle.getRadius());
		if(collision) {
			setGoalFlag(true);
		}

		boolean fall1, fall2, fall3;
		fall1 = Collision.isPointInCircle(player.getPosition(), col_circle1.getCenter(), col_circle1.getRadius());
		fall2 = Collision.isPointInPolygon(player.getPosition(), point1);
		fall3 = Collision.isPointInPolygon(player.getPosition(), point2);
		if(!fall1 && !fall2 && !fall3) {
			player.initPos();
			player.setLifePoint(-1);
			setFallFlag(true);
		}


		collision = Collision.isPointInPolygon(player.getPosition(), bar.getPoint());
		if(collision && player.inv <= 0) {
			player.setLifePoint(-1);
			player.inv = 60;
		}
	}

	public void debugObject(Canvas canvas) {
		col_circle1.draw();
		col_circle1.setAlpha(100);
		object1.setAlpha(100);
		object1.draw(canvas);
		object2.setAlpha(100);
		object2.draw(canvas);
	}
}
