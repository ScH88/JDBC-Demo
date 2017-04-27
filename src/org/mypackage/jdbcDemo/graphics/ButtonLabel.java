package org.mypackage.jdbcDemo.graphics;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

public class ButtonLabel extends JButton {

	//Integer for the type of button (for use in the file path, i.e. start, options, about, quit)
	String type;
	//Integer for the width
	int width;
	//Integer for the height
	int height;
	//ImageIcon for the images used for this JLabel
	ImageIcon buttOn, buttOff, buttClicked;
	//Boolean for if this button label is clickable, with default value of true
	boolean enabled = true;
		
	//public ButtonLabel(String type, int xPos, int yPos, int width, int height) {
	//public ButtonLabel(String type, int width, int height) {
	public ButtonLabel(String type, int width, int height) {
		//The type that this button label is, for use in the file path (i.e. play, about, options e.t.c)
		this.type = type;
		//Set the width of this instance as the one passed to the constructor parameter
		this.width = width;
		//Set the height of this instance as the one passed to the constructor parameter
		this.height = height;
		//The file path of the "hovered on" image of this button
		String path1 = "C:/Users/Packard Bell/Documents/EclipseProjects/jdbcDemo/resources/images/buttons/" + type + "_on.png";
		//The file path of the default "off" image of this button
		String path2 = "C:/Users/Packard Bell/Documents/EclipseProjects/jdbcDemo/resources/images/buttons/" + type + "_off.png";
		//The file path of the "clicked" image of this button
		String path3 = "C:/Users/Packard Bell/Documents/EclipseProjects/jdbcDemo/resources/images/buttons/" + type + "_clicked.png";
		//Pass all 3 file paths to the setupImages function for scaling
		setupImages(path1, path2, path3);
		//Remove focus painting of this button
		setFocusPainted(false);
	    //Remove the margin of this button
		setMargin(new Insets(0, 0, 0, 0));
	    //Remove content area filler
		setContentAreaFilled(false);
	    //Remove the border of this button
		setBorderPainted(false);
	    //Remove the background of this button
		setOpaque(false);
		//Add a mouse listener to this instance
		this.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {}

			@Override
			public void mousePressed(MouseEvent e) {
				//If this instance is pressed, set the Icon as the "buttClicked" version
				setIcon(buttClicked);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				//If this instance is released, set the Icon as the "buttOff" version
				setIcon(buttOff);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				//If the user's mouse cursor enters this instance, set the Icon as the "buttOn" version
				setIcon(buttOn);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				//If the user's mouse cursor exits this instance, set the Icon as the "buttOff" version
				setIcon(buttOff);
			}
			
		});
		//Add a focus listener to this instance
		this.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				//If this instance is pressed upon focus
				if (getModel().isPressed()){
					//If this instance gains focus, set the Icon as the "buttOn" version
					setIcon(buttClicked);
				//If this instance is not pressed upon focus
				} else {
					//If this instance gains focus, set the Icon as the "buttOn" version
					setIcon(buttOn);
				}
				
			}

			@Override
			public void focusLost(FocusEvent e) {
				//If this instance loses focus, set the Icon as the "buttOff" version
				setIcon(buttOff);
			}
		});
		//Set the default Icon as the "buttOff" version
		setIcon(buttOff);
	}
	
	private void setupImages(String path1, String path2, String path3) {
		//Local ImageIcon for the first path (for the on version)
		ImageIcon imgPt1 = new ImageIcon(path1);
		//Local image storing the first ImageIcon
		Image imgPt2 = imgPt1.getImage();
		//Image for storing a scaled instance of the previous Image (as ImageIcons cannot be scaled)
		//Image imgPt3= imgPt2.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		Image imgPt3= imgPt2.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		//Set the "on" version of this label's background image as the scaled Image before
		buttOn = new ImageIcon(imgPt3);
		//Local ImageIcon for the second path (for the off version)
		ImageIcon imgPt4 = new ImageIcon(path2);
		//Local image storing the second ImageIcon
		Image imgPt5 = imgPt4.getImage();
		//Image for storing a scaled instance of the previous Image (as ImageIcons cannot be scaled)
		Image imgPt6= imgPt5.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		//Set the "off" version of this label's background image as the scaled Image before
		buttOff = new ImageIcon(imgPt6);
		//Local ImageIcon for the third path (for the clicked version)
		ImageIcon imgPt7 = new ImageIcon(path3);
		//Local image storing the third ImageIcon
		Image imgPt8 = imgPt7.getImage();
		//Image for storing a scaled instance of the previous Image (as ImageIcons cannot be scaled)
		Image imgPt9= imgPt8.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		//Set the "clicked" version of this label's background image as the scaled Image before
		buttClicked = new ImageIcon(imgPt9);
	}
	
	public void setClicked() {
		//Change this ButtonLabel's background Icon to it's "clicked" version
		setIcon(buttClicked);
	}
	public void setEnteredOrReleased() {
		////Change this ButtonLabel's background Icon to it's "on" version
		setIcon(buttOn);
	}
	
	public void setOff() {
		////Change this ButtonLabel's background Icon to it's "off" version
		setIcon(buttOff);
	}
	
	public boolean getIfEnabled() {
		//Return the boolean for if this button is usable
		return enabled;
	}
}