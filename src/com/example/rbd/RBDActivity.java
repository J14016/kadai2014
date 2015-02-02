package com.example.rbd;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class RBDActivity extends Activity {

	private static Resources resource = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		resource = getResources();
		FileIO.setContext(this);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(new MainLoop(this));
		RBDSensor.Inst().onCreate(this);
	}

	@Override
	protected void onResume() { // アクティビティが動き始める時呼ばれる
		super.onResume();
		RBDSensor.Inst().onResume();// 開始時にセンサーを動かし始める
		}

	@Override
	protected void onPause() { // アクティビティの動きが止まる時呼ばれる
		super.onPause();
		RBDSensor.Inst().onPause();// 中断時にセンサーを止める
	}

	public static Resources getResource() {
		return resource;
	}





	/*
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.rbd, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	*/
}
