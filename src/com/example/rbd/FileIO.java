package com.example.rbd;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

public class FileIO {

	private final static char PRIVATE = 'p';
	private final static char APPEND = 'a';

	private static FileOutputStream out;
	private static OutputStreamWriter writer;
	private static BufferedReader buffReader;
	public static Context context;

	public FileIO(Context c) {
		context = c;
	}


	public static String read(String name, Canvas canvas) {
		String s = "";
		try {
			FileInputStream file = context.openFileInput(name);
			buffReader = new BufferedReader(new InputStreamReader(file));


			String x = "";
			String y = "";

			for(int j = 0;(s = buffReader.readLine()) != null;j++) {
				x = "";
				y = "";

				int i = 0;
				for(i = 0; s.charAt(i) != ','; i++) {
					x += s.charAt(i);
				}

				for(; i < s.length(); i++) {
					if(s.charAt(i) != ',') {
						y += s.charAt(i);
					}

				}

				//System.out.println("X : "+x);
				//System.out.println("Y : "+y);
				Paint text = new Paint();
				text.setStrokeWidth(4);
				canvas.drawPoint(Float.valueOf(x), Float.valueOf(y), text);
				canvas.drawPoint(Float.valueOf(x), Float.valueOf(y), text);

			}
			//System.out.println("END");


			buffReader.close();
			file.close();

		} catch(IOException e) {

		}

		return s;
	}

	public static void  write(String name, String str, char flag) {

		try {
			if(flag == 'p') {
				out = context.openFileOutput(name, context.MODE_PRIVATE);
			} else if(flag == 'a') {
				out = context.openFileOutput(name, context.MODE_APPEND);
			} else {
				return;
			}
			writer = new OutputStreamWriter(out);
			writer.write(str);
			writer.flush();
			writer.close();
			out.close();
		} catch(FileNotFoundException e) {

		} catch(IOException e) {

		}
	}

	public static void setContext(Context c) {
		context = c;
	}

	public static void readAndWrite(String name, String buf) {
		String s;
		write(name, "", 'p');
		try {
			FileInputStream file = context.openFileInput(buf);
			buffReader = new BufferedReader(new InputStreamReader(file));

			for(;(s = buffReader.readLine()) != null; ) {
				write(name, s+"\n", 'a');
			}

		} catch(IOException e) {

		}
	}
}
