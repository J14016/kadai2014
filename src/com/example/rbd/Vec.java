package com.example.rbd;

public class Vec {

	public float x, y;

	public Vec() {
		x = y = 0;
	}

	public Vec(float fx, float fy) {
		x = fx;
		y = fy;
	}

	public float getAngle() {
		return (float)Math.atan2(y, x);
	}

	public float getLength() {
		return (float)Math.sqrt(x*x + y*y);
	}

	public void setLengthCap(float maxLength) {
		float len = getLength();
		if(maxLength == 0) {
			return;
		}
		if(len > maxLength) {
			float rate = len/maxLength;
			x /= rate;
			y /= rate;
		}
	}

	public void blend(Vec vec, float rate) {
		float w = vec.x - x;
		float h = vec.y - y;
		x += w*rate;
		y += h*rate;
	}
}
