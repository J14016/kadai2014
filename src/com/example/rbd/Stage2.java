package com.example.rbd;

import android.graphics.Canvas;
import android.graphics.PointF;

public class Stage2 extends BasedStage {

	private Circle goalCircle = null;
	public static MyImage back = null;
	private Player player = null;
	private Bar move_bar1 = null;
	private Bar move_bar2 = null;
	private int Count = 0;
	private PointF point1[] = null;
	private PointF point2[] = null;
	private Object object1 = null;
	private Object object2 = null;

	private MyImage floar1 = null;



	public Stage2() {
		goalCircle = new Circle(334, 55, 30);
		player = new Player(338, 790, 3);
		back = new MyImage(R.drawable.stage2);
		move_bar1 = new Bar(new PointF(270, 545), 200, 105);
		move_bar2 = new Bar(new PointF(270, 440), 200, 105);
		floar1 = new MyImage(R.drawable.floar1, false);
		initColObject();


	}

	public void initColObject() {
		point1 = new PointF[4];
		point1[0] = new PointF(268, 17);
		point1[1] = new PointF(396, 17);
		point1[2] = new PointF(396, 212);
		point1[3] = new PointF(268, 212);
		object1 = new Object(point1);

		point2 = new PointF[4];
		point2[0] = new PointF(270, 640);
		point2[1] = new PointF(395, 640);
		point2[2] = new PointF(395, 838);
		point2[3] = new PointF(270, 838);
		object2 = new Object(point2);

	}

	@Override
	public void updateProc() {
		timeCount();
		collision();
		if(getMoveFlag()) {
			player.update();
		}
		if(player.getLifePoint() <= 0 || getTime() <= 0) {
			setGameoverFlag(true);
		}

		Count = ++Count % 60;

	}

	@Override
	public void drawProc(Canvas canvas) {
		back.draw(0, 0);
		//debugObject(canvas);
		goalCircle.draw();
		move_bar1.draw((float)(240+Math.sin(Math.PI*2/60*Count)*240), 430, 200, 220);
		move_bar2.draw((float)(240+Math.sin(-Math.PI*2/60*Count)*240), 210, 200, 220);
		floar1.draw((float)(240+Math.sin(Math.PI*2/60*Count)*240), 430);
		floar1.draw((float)(240+Math.sin(-Math.PI*2/60*Count)*240), 210);
		player.draw(canvas);
		gameoverDraw(canvas);
		gameClearDraw(canvas);
		countDown(canvas);

	}

	public void debugObject(Canvas canvas) {
		object1.setAlpha(100);
		object1.draw(canvas);
		object2.setAlpha(100);
		object2.draw(canvas);
	}

	@Override
	public void initProc() {
		if(player != null) {
			player.init();
		}
		setTimeLimit(20);
	}

	public void collision() {
		boolean goal_col = Collision.isPointInCircle(player.getPosition(), goalCircle.center, goalCircle.r);

		if(goal_col) {
			setGoalFlag(true);
		}

		boolean collision1 = Collision.isPointInPolygon(player.getPosition(), move_bar1.getPoint());
		boolean collision2 = Collision.isPointInPolygon(player.getPosition(), move_bar2.getPoint());

		if(collision1) {
			player.setPosition(move_bar1.getLength(), 0);
		}
		if(collision2) {
			player.setPosition(move_bar2.getLength(), 0);
		}

		boolean col1 = Collision.isPointInPolygon(player.getPosition(), point1);
		boolean col2 = Collision.isPointInPolygon(player.getPosition(), point2);

		if(!col1 && !col2 && !collision1 && !collision2) {
			player.setLifePoint(-1);
			player.initPos();
			setFallFlag(true);
		}
	}

}
