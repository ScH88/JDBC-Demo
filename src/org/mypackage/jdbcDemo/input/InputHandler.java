package org.mypackage.jdbcDemo.input;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class InputHandler implements MouseListener, KeyListener, MouseMotionListener, 
FocusListener {
	
public boolean[] key = new boolean[68836];
public static int MouseX;
public static int MouseY;
public static int MouseDX; //D = Drag
public static int MouseDY;
public static int MousePX; //P = Pressed mouse co-ordinates
public static int MousePY;
public static int MouseButton;
public static boolean dragged = false;
public boolean forward, back, left, right, rotLeft, rotRight, jump, crouch, run; 

public void tick(){
	 forward = key[KeyEvent.VK_W];
	 back = key[KeyEvent.VK_S];
	 left = key[KeyEvent.VK_A];
	 right = key[KeyEvent.VK_D];
	 rotLeft = key[KeyEvent.VK_LEFT];
	 rotRight = key[KeyEvent.VK_RIGHT];
	 jump = key[KeyEvent.VK_SPACE];
	 crouch = key[KeyEvent.VK_CONTROL];
	 run = key[KeyEvent.VK_SHIFT];
}

public void focusGained(FocusEvent e) {
}


public void focusLost(FocusEvent e) {
for (int i = 0; i<key.length; i++) {
key[i] = false;
}
}


public void mouseDragged(MouseEvent e) {
	MouseDX = e.getX();
	MouseDY = e.getY();
}


public void mouseMoved(MouseEvent e) {
	MouseX = e.getX();
	MouseY = e.getY();
}


public void keyTyped(KeyEvent e) {
}


public void keyPressed(KeyEvent e) {
int keyCode = e.getKeyCode();
if (keyCode > 0 && keyCode < key.length)
	key[keyCode] = true;
}

	public void keyReleased(KeyEvent e) {
	int keyCode = e.getKeyCode();
	if (keyCode > 0 && keyCode < key.length)
	key[keyCode] = false;
	}
	
	public void mouseClicked(MouseEvent e) {
		
	}
	
	public void mousePressed(MouseEvent e) {
		MouseButton = e.getButton();
		MousePX = e.getX();
		MousePY = e.getY();
	}
	
	public void mouseReleased(MouseEvent e) {
		dragged = false;
		MouseButton = 0;
	}
	
	public void mouseEntered(MouseEvent e) {
	}
	
	public void mouseExited(MouseEvent e) {
	}
	
}