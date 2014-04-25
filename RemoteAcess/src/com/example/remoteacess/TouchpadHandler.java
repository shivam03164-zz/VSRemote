package com.example.remoteacess;

import com.example.network.NetInput;
import com.example.network.Background;


import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class TouchpadHandler implements OnTouchListener {
	float lastX, lastY, lastScrollY, originX, originY;
	String x;
	String y;
	long touchDown;
	boolean multiEvent, dragging, strayedOrigin;
	
	public boolean onTouch(View v, MotionEvent event) {
		if (event.getPointerCount() > 1) multiEvent = true;
		if (!strayedOrigin && !dragging && event.getAction() != MotionEvent.ACTION_DOWN) {
			if (	originX - lastX > 2 || originX - lastX < -2 ||
					originY - lastY > 2 || originY - lastY < -2) {
				strayedOrigin = true;
			}
			if (System.currentTimeMillis() - touchDown > 500) {
				dragging = true;
				NetInput.LeftDown();
			}
		}
		 Background taski = new Background();
		switch (event.getAction())
		{
		case MotionEvent.ACTION_DOWN:
			lastX = event.getX();
			lastY = event.getY();
			originX = lastX;
			originY = lastY;
			touchDown = System.currentTimeMillis();
			break;
			
		case MotionEvent.ACTION_UP:
			if (System.currentTimeMillis() - touchDown < 150)
			{
				if (multiEvent)
					NetInput.RightClick();
				else
					NetInput.LeftClick();
			}
			if (dragging) {
				//NetInput.LeftUp();
			}
			multiEvent = false;
			dragging = false;
			strayedOrigin = false;
			break;
			
		case MotionEvent.ACTION_MOVE:
			
			 x = String.valueOf((int) event.getX());
             y = String.valueOf((int) event.getY());
            
           
	         	taski.execute(x,y,"3");
			
			/*float xOffset = (event.getX() - lastX);//event.getX();
			float yOffset = (event.getY() - lastY);//event.getY();
			lastX =  event.getX();
			lastY = event.getY();
			if (event.getPointerCount() == 1) {
			//NetInput.MoveMouse((int)(xOffset) , (int)(yOffset ));
			NetInput.MoveMouse((int)(xOffset), (int)(yOffset));
			} else { //2 or more touches down
				if (lastScrollY ==0) {
					lastScrollY = lastY;
				} else {
					if (lastScrollY - lastY > 20) {
						//NetInput.ScrollUp();
						lastScrollY = lastY;
					} else if (lastScrollY - lastY < -20) {
						//NetInput.ScrollDown();
						lastScrollY = lastY;
					}
				}
			}*/
			break;
		}
		return true;
	}
}
