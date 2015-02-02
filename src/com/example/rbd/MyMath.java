package com.example.rbd;

public class MyMath {

	public MyMath() {

	}

	public static double roundDown(double d, int digit) {
		double tmp1 = d;
		int tmp2 = 1;
		double result = 0;

		if(digit > 0) {
			for(int i = 0; i < digit; i++) {
				tmp2 *= 10;
			}
			int tmp3 = (int)(tmp1 * tmp2);
			result = (double)tmp3 / (double)tmp2;
		}
		if(digit < 0) {
			for(int i = 0; i < -digit; i++) {
				tmp2 *= 10;
			}
			int tmp3 = (int)(tmp1 / tmp2);
			result = tmp3 * tmp2;
		}

		return result;
	}

	public static double toSecond(long l) {
		return (double)l / 1000.0f;
	}

}
