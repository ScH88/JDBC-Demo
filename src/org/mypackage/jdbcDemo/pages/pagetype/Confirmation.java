package org.mypackage.jdbcDemo.pages.pagetype;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.KeyStroke;

import org.mypackage.jdbcDemo.input.InputHandler;
import org.mypackage.jdbcDemo.pages.Menu;

public class Confirmation extends JDialog {
private static final long serialVersionUID = 1L;
	
	//Set the width of the JDialog panel
	private int width = 450;
	//Set the height of the JDialog panel
	private int height = 100;
	//JButtons for choosing whether to exit the program or not
	private JButton okay;
	//Rectangle for setting boundaries of the "okay" button
	private Rectangle rOkay;
	//Integer for setting button width
	private int button_width = 180;
	//Integer for setting button height
	private int button_height = 40;
	//Integer for the type of confirmation depending on the page type of the creator
	private int confirmType;
	//JLabel for storing the text
	private JLabel text;
	
	//JPanel for containing components (labels, buttons e.t.c)
	JPanel window = new JPanel();
	//Menu object for referencing the Menu superclass
	Menu menu;
	
	public Confirmation(Menu menu, int confirmType) {
		//Call the superclass (JDialog) constructor, passing it the Menu instance in the parameter
		super(menu);
		//Set the menu that this instance will reference as the one passed to the parameter
		this.menu = menu;
		//Set the confirmation type integer of this instance as the one passed to the parameter
		this.confirmType = confirmType;
		//If the confirmation type is 1
		if (confirmType == 1) {
			//Set the title of this JDialog instance
			setTitle("ITEM ADDED");
		//If the confirmation type is 2
		} else if (confirmType == 2) {
			//Set the title of this JDialog instance
			setTitle("ITEM DELETED");
		//If the confirmation type is 3
		} else if (confirmType == 3) {
			//Set the title of this JDialog instance
			setTitle("ITEM EDITED");
		}
		//Set the background colour of the window JPanel to yellow
		window.setBackground(Color.YELLOW);
		//Set the size as the default integer values
		setSize(new Dimension(width, height));
		//Add the window JPanel value to this JDialog frame
		add(window);
		//Set undecorated to true, removing frame borders and the top bar
		setUndecorated(true);
		//Set locationRelativeTo null, placing this frame on the center of the screen
		setLocationRelativeTo(null);
		//Set resizable to false
		setResizable(false);
		//Set visible to true, making this frame appear
		setVisible(true);
		//Set the JPanel's layout to null, allowing absolute component placement
		window.setLayout(null);
		//Call the drawContent function
		drawContent();
		//Call the JPanel's repaint function, updating it of the changes made
		window.repaint();
		//Set the modality of this JDialog (always puts this dialog in front of the JFrame application, even when minimized)
		setModal(true);
	}

	private void drawContent() {
		//Set a local String variable 
		String dialogText = "";
		//If the confirmation type integer is 1
		if(confirmType == 1) {
			//Set the local dialog String as the following
			dialogText = "Your new entry has been added";
		//If the confirmation type integer is 2
		} else if (confirmType == 2) {
			//Set the local dialog String as the following
			dialogText = "Your have now deleted your selected fruit";
		//If the confirmation type integer is 3
		} else if (confirmType == 3) {
			//Set the local dialog String as the following
			dialogText = "Your selected fruit has now been edited";
		}
		//String for HTML dialog text
		String dialogTextHTML = "<html>"
		+ "<h3 style='font-size:12px; color:#FF0000;'>" + dialogText + "</h3>"
		+ "</html>";
		//Instantiate the "text" JLabel
		text = new JLabel(dialogTextHTML);
		//Set the bounds of the text JLabel
		text.setBounds(20, -100, 250, 250);
		//Add the JLabel to the JPanel
		window.add(text);
		//Instantiate the "okay" JButton
		okay = new JButton("OKAY");
		//Instantiate the "okay" JButton rectangle bounds
		rOkay = new Rectangle((width/2) - (button_width/2), (height-40), button_width, (button_height - 10));
		//Set the boundaries (size, location) of the "okay" button as the rectangle
		okay.setBounds(rOkay);
		//Add an ActionListener to the "okay" JButton
		okay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Dispose of this instance
				dispose();
				//Set the menu's frame's setFocusableWindowState and setFocusable to true, allowing all content to be clicked again
				menu.setFocusableWindowState(true);
				menu.setFocusable(true);
			}
		});
		//Add to the "okay" JButton's input map a keystroke for the enter key
		okay.getInputMap().put(KeyStroke.getKeyStroke("ENTER"), "enterPressed");
		//Add to the "okay" JButton's action map an abstractAction for if the enter key is pressed
		okay.getActionMap().put("enterPressed", new AbstractAction(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//Dispose of this instance
				dispose();
				//Set the menu's frame's setFocusableWindowState and setFocusable to true, allowing all content to be clicked again
				menu.setFocusableWindowState(true);
				menu.setFocusable(true);
			}
		});
		//Add the "okay" button to the "window" JPanel
		window.add(okay);
		//Have the program focus on this instance
		this.requestFocus();
		//Get the root pane (i.e. the main JFrame)
		JRootPane rp = super.getRootPane();
		//Add to the "YES" JButton's input map a keystroke for the left key
		rp.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("LEFT"), "confFocus");
		//Add to the "YES" JButton's input map a keystroke for the right key
		rp.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("RIGHT"), "confFocus");
		//Add to the "YES" JButton's input map a keystroke for the up key
		rp.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("UP"), "confFocus");
		//Add to the "YES" JButton's input map a keystroke down the left key
		rp.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("DOWN"), "confFocus");
		//Add to the root pane's action map an abstractAction for if the left key is pressed
		rp.getActionMap().put("confFocus", new AbstractAction(){
			@Override
			public void actionPerformed(ActionEvent e) {
					//Set focus on the "okay" JButton
					okay.requestFocus();
				}
			});
	}
}
