package com.example.network;

import java.lang.*;

public class NetInput {

		static String x1;
		static String y1;
	    private NetInput() { }

	    public static void MoveMouse(int X, int Y) {
	    	
	    	x1 = String.valueOf(X);
	    	y1 = String.valueOf(Y);
	    	
	    	
	    	Background task = new Background();
	    	task.execute(x1,y1,"3");
	    }

	    public static void LeftClick() {
	    	Background task = new Background();
	         task.execute("0","0","1");
	    }

	    public static void RightClick() {
	    	Background task = new Background();
	         task.execute("0","0","2");
	    }

	    public static void LeftDown() {
	       // SendMessage(NetCommands.Mouse, NetCommands.LeftDown);
	    }

	    public static void LeftUp() {
	        //SendMessage(NetCommands.Mouse, NetCommands.LeftUp);
	    }

	     public static void RightDown() {
	        //SendMessage(NetCommands.Mouse, NetCommands.RightDown);
	    }

	    public static void RightUp() {
	        //SendMessage(NetCommands.Mouse, NetCommands.RightUp);
	    }

	    public static void MiddleDown() {
	        //SendMessage(NetCommands.Mouse, NetCommands.MiddleDown);
	    }

	    public static void MiddleUp() {
	        //SendMessage(NetCommands.Mouse, NetCommands.MiddleUp);
	    }

	    public static void ScrollDown() {
	        //SendMessage(NetCommands.Mouse, NetCommands.ScrollDown);
	    }

	    public static void ScrollUp() {
	        //SendMessage(NetCommands.Mouse, NetCommands.ScrollUp);
	    }

	    public static void VolumeDown() {
	       // SendMessage(NetCommands.Mixer, NetCommands.VolumeDown);
	    }

	    public static void VolumeUp() {
	     //   SendMessage(NetCommands.Mixer, NetCommands.VolumeUp);
	    }

	    private static void SendMessage(short Primary, short Secondary) {
	       // SendMessage(new ScratchMessage(Primary, Secondary));
	    }

	    /*private static void SendMessage(ScratchMessage message) {
	        ScratchEvents client = Network.getClient();
	        if (client != null) {
	            client.Send(message);
	        } else
	            Network.Connect();
	    }*/
	}

