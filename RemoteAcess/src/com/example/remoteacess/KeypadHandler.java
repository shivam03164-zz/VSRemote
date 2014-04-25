package com.example.remoteacess;

import com.example.network.Background;

import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;

public class KeypadHandler implements OnKeyListener{
	
	public boolean onKey(View v, int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK || keyCode == KeyEvent.KEYCODE_MENU) return false;
		
		switch (event.getAction())
		{
		case KeyEvent.ACTION_UP:
			SendKeyCode(keyCode, (char)event.getUnicodeChar());
			return true;
			
		case KeyEvent.ACTION_MULTIPLE:
			if (keyCode == KeyEvent.KEYCODE_UNKNOWN)
			{
				//Background task = new Background();
				//task.execute("multiple","0",event.getCharacters());
			}
			else
			{
				for (int i = 0; i < event.getRepeatCount(); i++)
				{
					//SendKeyCode(keyCode, (char)event.getUnicodeChar());
				}
			}
			return true;
		}
		return false;
	}
	
	private void SendKeyCode(int keyCode, char UnicodeChar)
	{
		Background task = new Background();
		switch (keyCode)
		{
		case KeyEvent.KEYCODE_DEL:
			
			task.execute("0","0","6");
			break;
			
		case KeyEvent.KEYCODE_ENTER:
			
			task.execute("0","0","5");
			break;
		
		default:
			
	         task.execute("0","0","0",String.valueOf(UnicodeChar));
			break;
		}
	}
}
