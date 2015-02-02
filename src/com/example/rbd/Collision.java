package com.example.rbd;

import android.graphics.PointF;

public class Collision {


	//点が多角形の中に含まれているか判定
	public static boolean isPointInPolygon(PointF target, PointF point[]) {

		int iCountCrossing = 0;
		int length = point.length;

		PointF point0 = point[0];
		boolean bFlag0x = (target.x <= point0.x);
		boolean bFlag0y = (target.y <= point0.y);

		for(int i = 1; i < length + 1; i++) {
			PointF point1 = point[i%length];
			boolean bFlag1x = (target.x <= point1.x);
			boolean bFlag1y = (target.y <= point1.y);

			if(bFlag0y != bFlag1y) {
				if(bFlag0x == bFlag1x) {
					if(bFlag0x) {
						iCountCrossing += (bFlag0y ? -1 : 1);
					}
				}
				else {
					if(target.x <= (point0.x + (point1.x - point0.x) * (target.y - point0.y) / (point1.y - point0.y))) {
						iCountCrossing += (bFlag0y ? -1 : 1);
					}
				}
			}

			point0 = point1;
			bFlag0x = bFlag1x;
			bFlag0y = bFlag1y;
		}


		return (0 != iCountCrossing);
	}

	public static boolean isPointInCircle(PointF p, PointF c, float r) {
		boolean bool = false;

		if(((p.x-c.x)*(p.x-c.x) + (p.y-c.y)*(p.y-c.y)) <= r*r) {
			bool = true;
		}
		return bool;
	}

}
