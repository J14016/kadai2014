package com.example.rbd;


public class Sequence {

	enum MainSeq {
		SEQ_TITLE,
		SEQ_MENU,
		SEQ_GAME,
		SEQ_RANKING,
		SEQ_STAGE_SELECT,
	};

	private static MainSeq mainSeq = MainSeq.SEQ_TITLE;

	enum StageNum {
		SEQ_STAGE1,
		SEQ_STAGE2,
		SEQ_STAGE3,
		SEQ_STAGE4,
		SEQ_NONE,
	}

	private static StageNum stageNum = StageNum.SEQ_NONE;


	public static void setSequence(MainSeq seq) {
		mainSeq = seq;
		Surface.setTouchX(-1000);
		Surface.setTouchY(-1000);
		MainLoop.setInitFlag(true);


	}

	public static void setSequence(StageNum seq) {
		stageNum = seq;
		Surface.setTouchX(-1000);
		Surface.setTouchY(-1000);
		MainLoop.setInitFlag(true);
	}

	public static MainSeq getMainSequence() {
		return mainSeq;
	}

	public static StageNum getStageSequence() {
		return stageNum;
	}
}
