package com.example.rbd;

import android.graphics.Canvas;
import android.graphics.Paint.FontMetrics;
import android.graphics.Rect;

public class MyButton extends Task {

	enum ButtonAlign {
		CENTER,
		LEFT,
		RIGHT,
	};
	private ButtonAlign buttonAlign = ButtonAlign.LEFT;

	private MyImage image = null;
	private String buttonText = "";
	private Rect position = null;
	private FontMetrics fontMetrics = null;


	public MyButton(Rect r) {
		position = r;
	}

	public MyButton(int x, int y, int id) {
		image = new MyImage(id);
		position = new Rect(x, y, (int)(x + image.getWidth()), (int)(y + image.getHeight()));
	}

	public boolean onButton() {
		boolean result = false;

		float touchX = Surface.getTouchX();
		float touchY = Surface.getTouchY();
		int left = position.left;
		int top = position.top;
		int right = position.right;
		int bottom = position.bottom;

		switch(buttonAlign) {
		case LEFT:
			break;

		case CENTER:
			left -= image.getWidth()/2;
			top -= image.getHeight()/2;
			right = left + image.getWidth();
			bottom = top + image.getHeight();
			break;

		case RIGHT:
			left -= image.getWidth();
			top -= image.getHeight();
			right -= image.getWidth();
			bottom -= image.getHeight();
			break;
		}


		boolean bool1 = touchX >= left;
		boolean bool2 = touchX <= right;
		boolean bool3 = touchY >= top;
		boolean bool4 = touchY <= bottom;



		if(bool1 && bool2 && bool3 && bool4) {
			result = true;
		}

		return result;
	}

	public void update() {

	}

	public void draw(Canvas canvas) {
		if(image == null) {
			return;
		}

		fontMetrics = text.getFontMetrics();
		float textX = position.left + image.getWidth()/2;
		float centerY =  position.top - image.getHeight()/2;
		float textY = centerY - (fontMetrics.ascent + fontMetrics.descent) / 2 ;


		switch(buttonAlign) {
		case LEFT:
			image.draw(position.left, position.top);
			canvas.drawText(buttonText, textX, textY, text);
			break;

		case CENTER:
			textX = position.left;
			textY = position.top - (fontMetrics.ascent + fontMetrics.descent) / 2;
			image.draw(position.left - image.getWidth()/2 , position.top - image.getHeight()/2);
			canvas.drawText(buttonText, textX, textY, text);
			break;

		case RIGHT:
			image.draw(position.left - image.getWidth(), image.getHeight());
			break;
		}
	}

	public void draw() {
		if(image == null) {
			return;
		}

		switch(buttonAlign) {
		case LEFT:
			image.draw(position.left, position.top);
			break;

		case CENTER:
			image.draw(position.left - image.getWidth()/2 , position.top - image.getHeight()/2);
			break;

		case RIGHT:
			image.draw(position.left - image.getWidth(), image.getHeight());
			break;
		}
	}

	public void setText(String str) {
		buttonText = str;
	}

	public void setTextSize(int size) {
		text.setTextSize(size);
	}

	public void resize(double w, double h) {
		image.resize(w, h);
		position.right = position.left + image.getWidth();
		position.bottom = position.top + image.getHeight();
	}

	public void resize(int w, int h) {
		image.resize(w, h);
		position.right = position.left + image.getWidth();
		position.bottom = position.top + image.getHeight();
	}

	public void setAlign(ButtonAlign align) {
		buttonAlign = align;
	}
}
