package org.mypackage.jdbcDemo.graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class ContentPane extends JPanel{
	
	//
	//private int x;
	//
	//private int y;
	
	//
	private BufferedImage bgImage;
	
	public ContentPane(){
		//
		setBorder(BorderFactory.createLineBorder(Color.black));
	}
	
	@Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);       
        //
        g.drawImage(bgImage, 0, 0, getParent().getWidth(), getParent().getHeight(), null);
    }
    
    public void paintBackground(int imageID) {
    	try {
    		//
    		if (imageID == 1) {
    			bgImage = ImageIO.read(ContentPane.class.getResource("/images/backgroundimg/farm.jpg"));
    		} else if (imageID == 2) {
    			bgImage = ImageIO.read(ContentPane.class.getResource("/images/backgroundimg/fruitsalad1.png"));
    		} else if (imageID == 3) {
    			bgImage = ImageIO.read(ContentPane.class.getResource("/images/backgroundimg/fruitsalad2.jpg"));
    		} else if (imageID == 4) {
    			bgImage = ImageIO.read(ContentPane.class.getResource("/images/backgroundimg/fruitsalad3.jpg"));
    		} else if (imageID == 5) {
    			bgImage = ImageIO.read(ContentPane.class.getResource("/images/backgroundimg/fruitsalad4.jpg"));
    		}
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    	//
    	paintComponent(this.getGraphics());
    }
	
}