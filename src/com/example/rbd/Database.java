package com.example.rbd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.rbd.Sequence.StageNum;

public class Database extends SQLiteOpenHelper {

	private final static int DB_VERSION = 1;
	private final static String DB_NAME = "rbd_db";
	private final static String TABLE_NAME = "rbd_table";
	private final static String ID = "id";
	private final static String TIME = "time";
	private static int elementNum = 5;
	private static int stageNum = 4;
	private static SQLiteDatabase sqlDB= null;

	public Database(Context context) {
		super(context, DB_NAME, null, DB_VERSION);

	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String query = "create table " +  TABLE_NAME + "(id INTEGER PRIMARY KEY AUTOINCREMENT, time DOUBLE);";
		db.execSQL(query);


		ContentValues cv = new ContentValues();


		for(int i = 0; i < elementNum*stageNum; i++) {
			cv.put(TIME, 0);
			db.insert(TABLE_NAME, null, cv);
		}

	}

	public void init() {
		sqlDB = getReadableDatabase();
		ContentValues values = new ContentValues();
		for(int i = 0; i < elementNum * stageNum; i++) {
			values.put(TIME, 0);
			sqlDB.update(TABLE_NAME, values, "id = " + (i+1), null);
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("drop table if exists" + TABLE_NAME);
		onCreate(db);
	}

	public static int getElementNum() {
		return elementNum;
	}

	public static int getStageNum() {
		return stageNum;
	}


	public void add(int id, double time, StageNum stageNum) {
		if(id > elementNum || id <= 0) {
			return;
		}

		int sn = 0;
		switch(stageNum) {
		case SEQ_STAGE1:
			sn = Database.getElementNum()*0;
			break;

		case SEQ_STAGE2:
			sn = Database.getElementNum()*1;
			break;

		case SEQ_STAGE3:
			sn = Database.getElementNum()*2;
			break;

		case SEQ_STAGE4:
			sn = Database.getElementNum()*3;
			break;

		default:
			return;
		}


		sqlDB = getReadableDatabase();
		ContentValues values = new ContentValues();
		values.put(TIME, time);
		sqlDB.update(TABLE_NAME, values, "id = " + (id+sn), null);
		sqlDB.close();
	}

	public double ref(int id, StageNum stageNum) {
		if(id > elementNum || id <= 0) {
			return -1;
		}

		int sn = 0;
		switch(stageNum) {
		case SEQ_STAGE1:
			sn = Database.getElementNum()*0;
			break;

		case SEQ_STAGE2:
			sn = Database.getElementNum()*1;
			break;

		case SEQ_STAGE3:
			sn = Database.getElementNum()*2;
			break;

		case SEQ_STAGE4:
			sn = Database.getElementNum()*3;
			break;

		default:
			return -1;
		}

		sqlDB = getReadableDatabase();
		Cursor cursor = sqlDB.rawQuery("SELECT time FROM " + TABLE_NAME, null);
		cursor.moveToFirst();

		for(int i = 1; i < id+sn; i++) {
			cursor.moveToNext();
		}

		double time = cursor.getDouble(cursor.getColumnIndex(TIME));

		cursor.close();
		sqlDB.close();

		return time;
	}
}
