package com.example.rbd;

import android.content.Context;

import com.example.rbd.Sequence.MainSeq;
import com.example.rbd.Sequence.StageNum;

public class MainLoop extends Surface {

	private MainSeq mainSeq = Sequence.getMainSequence();
	private Title title = null;
	private Menu menu = null;
	private Game game = null;
	private Ranking rank = null;
	private StageSelect stageSelect = null;
	private static boolean initFlag = false;
	private FpsMgr fpsMgr = null;
	private static Database db = null;

	public MainLoop(Context context) {
		super(context);

		db = new Database(context);
		//db.init();
		title = new Title();
		menu = new Menu();
		game = new Game();
		rank = new Ranking();
		stageSelect = new StageSelect();
		fpsMgr = new FpsMgr(30);
		init();
		FileIO.write("sample.txt", "", 'p');
	}


	public static void setInitFlag(boolean bool) {
		initFlag = bool;
	}

	//ループ内処理
	@Override
	public void loop() {
		mainSeq = Sequence.getMainSequence();
		init();
		update();
		draw();

	}

	//更新
	public void update() {
		switch(mainSeq) {
		case SEQ_TITLE:
			title.update();
			break;

		case SEQ_MENU:
			menu.update();
			break;

		case SEQ_GAME:
			game.update();
			break;

		case SEQ_RANKING:
			rank.update();
			break;

		case SEQ_STAGE_SELECT:
			stageSelect.update();
			break;
		}

		fpsMgr.update();
	}

	//描画
	public void draw() {
		switch(mainSeq) {
		case SEQ_TITLE:
			title.draw(canvas);
			break;

		case SEQ_MENU:
			menu.draw(canvas);
			break;

		case SEQ_GAME:
			game.draw(canvas);
			break;

		case SEQ_RANKING:
			rank.draw(canvas);
			break;

		case SEQ_STAGE_SELECT:
			stageSelect.draw(canvas);
			break;
		}

		fpsMgr.draw(canvas);
	}

	public void init() {
		if(!initFlag) {
			return;
		}
		switch(mainSeq) {
		case SEQ_TITLE:
			title.init();
			break;

		case SEQ_MENU:
			menu.init();
			break;

		case SEQ_GAME:
			game.init();
			break;

		case SEQ_RANKING:
			rank.init();
			break;

		case SEQ_STAGE_SELECT:
			stageSelect.init();
		}
		initFlag = false;
	}

	public static void addTime(int id, double t, StageNum stageNum) {
		db.add(id, t, stageNum);
	}

	public static double ref(int id, StageNum stageNum) {
		return db.ref(id, stageNum);
	}
}
