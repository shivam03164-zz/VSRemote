//package simpleserver;
 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.awt.Robot; 
import java.lang.String;
import java.awt.event.*;
import java.awt.event.InputEvent;
import java.awt.event.ComponentEvent;
import java.util.EventObject;
import java.awt.*;

import java.awt.AWTException;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.RenderingHints;

import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.*;

import javax.imageio.ImageIO;
import static java.awt.event.KeyEvent.*;

public class Main
{
 
    private static ServerSocket serverSocket;
    private static Socket clientSocket;
    private static InputStreamReader inputStreamReader;
    private static BufferedReader bufferedReader;
    private static String message;
    private static OutputStream objOut;

		 
    public static void main(String[] args) throws Exception
    {
        int foo;
        String x;
        String[] arr = new String[3];
        String y;
        int x1;
        int y1;
        int z1;
        String z;
        String text;
        int code;

        try
        {
            serverSocket = new ServerSocket(4420);  //Server socket 
        }
        catch (IOException e)
        {
            System.out.println("Could not listen on port: 4420");
        }
 
        System.out.println("Server started. Listening to the port 4420");

        while (true)
        {
            try
            {   
                Robot ro=new Robot();
                
                clientSocket = serverSocket.accept();
                clientSocket.setKeepAlive(true);
                inputStreamReader = new InputStreamReader(clientSocket.getInputStream());
                objOut = clientSocket.getOutputStream();
                bufferedReader = new BufferedReader(inputStreamReader); //get the client message
                String message = bufferedReader.readLine();
                String[] splited = message.split("@");// x, y, z, text
                x = splited[0];
                y = splited[1];
                z = splited[2];
               
                x1 = Integer.parseInt(x);
                y1 = Integer.parseInt(y);
                z1 = Integer.parseInt(z);
           
                switch(z1)
                {

                    case 0:
                        System.out.println("Signal : Keyboard");
                        try
                        {
                            Keyboard keyboard = new Keyboard();
                            text = splited[3];
                            System.out.println("Value Recieved : " + splited[3] + "  ");
                            keyboard.type(text);
                        }
                        catch(Exception e)
                        {
                            System.out.println(e);
                        } 
                    break;

                    case 1:
                        System.out.println("Signal : Left Click");
                        try
                        {
                            ro.mousePress(InputEvent.BUTTON1_MASK);
                            ro.mouseRelease(InputEvent.BUTTON1_MASK);
                        }
                        catch(Exception e)
                        {
                            System.out.println(e);
                        }  
                    break;


                    case 2:
                        System.out.println("Signal : Right Click"); 
                        try
                        {
                            ro.mousePress(InputEvent.BUTTON3_MASK);
                            ro.mouseRelease(InputEvent.BUTTON3_MASK);
                        }
                        catch(Exception e)
                        {
                            System.out.println(e);
                        }
                    break;

                    case 3:
                        System.out.println("Signal : Mouse Move");
                        //System.out.println("X Axis : "+ x1);
                        //System.out.println("Y Axis : "+ y1);
                        try
                        {
                            ro.mouseMove(x1*2,y1*1);
                        }
                        catch(Exception e)
                        {
                            System.out.println(e);
                        }
                    break;

                    case 4:
                        System.out.println("Mouse scroll");
                        try
                        {
                             ro.mouseWheel(-100);
                        }
                        catch(Exception e)
                        {
                            System.out.println(e);
                        }
                    break;

                    case 5:
                        System.out.print("Signal : Keyboard.Enter_Key");
                       try
                       {
                            ro.keyPress(KeyEvent.VK_ENTER);
                            ro.keyRelease(KeyEvent.VK_ENTER);
                        }
                        catch(Exception e)
                        {
                            System.out.println(e);
                        }
                    break;

                    case 6:
                        System.out.println("Signal : Keyboard.Back_Space");
                        try
                        {
                            ro.keyPress(KeyEvent.VK_BACK_SPACE);
                            ro.keyRelease(KeyEvent.VK_BACK_SPACE);
                        }
                        catch(Exception e)
                        {
                            System.out.println(e);
                        }
                    break;

                    case 7:
                        System.out.println("Signal : Live Screen");
                        Runnable r = new Runnable()
                        {
                            public void run()
                            {
                                try
                                {
                                    ByteArrayOutputStream out = new ByteArrayOutputStream();
                                    Robot robot = new Robot();
                                    while(true)
                                    {
                                        BufferedImage image = robot.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
                                        if(image != null)
                                        {
                                            ImageIO.write(image, "JPEG", out);
                                        }
                            
                                        objOut.write(new byte[] {(byte) (out.size() >> 24), (byte) (out.size() >> 16), (byte) (out.size() >> 8), (byte) out.size()});
                                        objOut.write(out.toByteArray());
                                        out.reset();
                                        objOut.flush();
                                        image.flush();
                                    }

                                }
                                catch(Exception e)
                                {
                                    System.out.println(e);
                                }
                            }

                        };
                        
                        new Thread(r).start();
                    break;


                }
                inputStreamReader.close();
                clientSocket.close();
            }

            catch (IOException ex)
            {
                System.out.println("Problem in message reading");
            }
        }
     }
 }
