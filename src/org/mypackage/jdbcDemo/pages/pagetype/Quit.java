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

public class Quit extends JDialog {
	private static final long serialVersionUID = 1L;
	
	//Set the width of the JDialog panel
	private int width = 450;
	//Set the height of the JDialog panel
	private int height = 100;
	//JButtons for choosing whether to exit the program or not
	private JButton NO, YES;
	//Rectangle values for setting the button boundaries
	private Rectangle rNO, rYES;
	//Integer for setting button width
	private int button_width = 180;
	//Integer for setting button height
	private int button_height = 40;
	
	//JPanel for containing components (labels, buttons e.t.c)
	JPanel window = new JPanel();
	public Quit() {
		//Set the background colour of the window JPanel to yellow
		window.setBackground(Color.YELLOW);
		//Set the title of this JDialog instance
		setTitle("ARE YOU SURE YOU WANT TO QUIT GAME?");
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
		//String for dialog text
		String dialogText = "<html>"
		+ "<h3 style='font-size:12px; color:#FF0000;'>Are you sure you want to quit?</h3>"
		+ "</html>";
		//JLabel for storing the text
		JLabel text = new JLabel(dialogText);
		//Set the bounds of the text JLabel
		text.setBounds(20, -100, 250, 250);
		//Add the JLabel to the JPanel
		window.add(text);
		//Instantiate the "NO" JButton
		NO = new JButton("NO");
		//Instantiate the "NO" JButton recangle bounds
		rNO = new Rectangle(20, (height-40), button_width, (button_height - 10));
		NO.setBounds(rNO);
		//Add the "NO" button to the JPanel
		window.add(NO);
		//Instantiate the "YES" JButton
		YES = new JButton("YES");
		//Instantiate the "YES" JButton recangle bounds
		rYES = new Rectangle((width - 20) - button_width, (height-40), button_width, (button_height - 10));
		YES.setBounds(rYES);
		//Add the "YES" button to the JPanel
		window.add(YES);
		//Add an ActionListener to the "NO" JButton
		NO.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Dispose of this instance
				dispose();
				//Call the menu's toggleButtonUse function, allowing buttons to be clickable again
				//menu.toggleButtonUse();
				//Set the menu's frame's setFocusableWindowState and setFocusable to true, allowing all content to be clicked again
				//menu.setFocusableWindowState(true);
				//menu.setFocusable(true);
			}
		});
		//Add to the "NO" JButton's input map a keystroke for the enter key
		NO.getInputMap().put(KeyStroke.getKeyStroke("ENTER"), "enterPressed");
		//Add to the "NO" JButton's action map an abstractAction for if the enter key is pressed
		NO.getActionMap().put("enterPressed", new AbstractAction(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//Dispose of this instance
				dispose();
			}		
		});
		//Add to the "NO" JButton's input map a keystroke for the right key
		NO.getInputMap().put(KeyStroke.getKeyStroke("RIGHT"), "rightPressed");
		//Add to the "NO" JButton's action map an abstractAction for if the enter key is pressed
		NO.getActionMap().put("rightPressed", new AbstractAction(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//Have the program focus on the "yes" button
				YES.requestFocus();
			}
		});
		//Add an ActionListener to the "YES" JButton
		YES.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Close this system
				System.exit(0);
			}
		});
		//Add to the "YES" JButton's input map a keystroke for the enter key
		YES.getInputMap().put(KeyStroke.getKeyStroke("ENTER"), "enterPressed");
		//Add to the "YES" JButton's action map an abstractAction for if the enter key is pressed
		YES.getActionMap().put("enterPressed", new AbstractAction(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//Close this system
				System.exit(0);
			}
		});
		//Add to the "YES" JButton's input map a keystroke for the left key
		YES.getInputMap().put(KeyStroke.getKeyStroke("LEFT"), "leftPressed");
		//Add to the "YES" JButton's action map an abstractAction for if the left key is pressed
		YES.getActionMap().put("leftPressed", new AbstractAction(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//Have the program focus on the "no" button
				NO.requestFocus();
			}
		});
		//Have the program focus on this JDialog
		this.requestFocus();
		//Get the root pane (i.e. the main JFrame)
		JRootPane rp = super.getRootPane();
		//Add to the "YES" JButton's input map a keystroke for the left key
		rp.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("LEFT"), "quitFocus");
		//Add to the "YES" JButton's input map a keystroke for the right key
		rp.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("RIGHT"), "quitFocus");
		//Add to the root pane's action map an abstractAction for if the left key is pressed
		rp.getActionMap().put("quitFocus", new AbstractAction(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//If the "yes" JButton does not have focus
				if (!YES.hasFocus()) {
					//Set focus on the "no" JButton
					NO.requestFocus();
				}
				
			}
		});
	}
}