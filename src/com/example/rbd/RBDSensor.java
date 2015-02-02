package com.example.rbd;

import java.util.List;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class RBDSensor implements SensorEventListener {
	private SensorManager sensorManager = null;
	private float x, y, z;

	public void onCreate(Context c) {
		sensorManager = (SensorManager)c.getSystemService(Context.SENSOR_SERVICE);
		onResume();
	}


	public void onResume() {
		//センサの取得
		List<Sensor> sensorList = sensorManager.getSensorList(Sensor.TYPE_ACCELEROMETER);
		if(sensorList != null && !sensorList.isEmpty()) {
			Sensor sensor = sensorList.get(0);
			sensorManager.registerListener(this,  sensor, SensorManager.SENSOR_DELAY_FASTEST);
		}
	}

	public void onPause() {
		if(sensorManager == null) {
			return;
		}
		sensorManager.unregisterListener(this);
	}

	public void onSensorChanged(SensorEvent event) {
		if(event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
			x = event.values[SensorManager.DATA_X];
			y = event.values[SensorManager.DATA_Y];
			z = event.values[SensorManager.DATA_Z];
		}
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
	}

	public float getX() {
		return -x;
	}

	public float getY() {
		return y;
	}

	public float getZ() {
		return z;
	}

	//シングルトン
	private static RBDSensor _instance = new RBDSensor();
	private RBDSensor() {
		x = y = z = 0;
	}

	public static RBDSensor Inst() {
		return _instance;
	}
}
