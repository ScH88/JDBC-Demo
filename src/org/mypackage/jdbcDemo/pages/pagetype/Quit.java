package org.mypackage.jdbcDemo.pages.pagetype;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.KeyStroke;
import javax.swing.UIManager;

import org.mypackage.jdbcDemo.graphics.ButtonLabel;
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
		//Set the JPanel's layout to null, allowing absolute component placement
		window.setLayout(null);
		//Call the JPanel's repaint function, updating it of the changes made
		window.repaint();
		//Call the drawContent function
		drawContent();
		//Set the modality of this JDialog (always puts this dialog in front of the JFrame application, even when minimized)
		//Must be called before setVisible in order to work, and must be called after all content is drawn
		setModal(true);
		//Set visible to true, making this frame appear
		setVisible(true);
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
		//Set the "NO" button's contentAreaFilled criteria to allow recolouring
		NO.setContentAreaFilled(false);
		//Set the "NO" button's opacity to true
		NO.setOpaque(true);
		//Instantiate the "NO" JButton rectangle bounds
		rNO = new Rectangle(20, (height-40), button_width, (button_height - 10));
		NO.setBounds(rNO);
		//Instantiate the "YES" JButton
		YES = new JButton("YES");
		//Set the "YES" button's contentAreaFilled criteria to allow recolouring
		YES.setContentAreaFilled(false);
		//Set the "YES" button's opacity to true
		YES.setOpaque(true);
		//Instantiate the "YES" JButton rectangle bounds
		rYES = new Rectangle((width - 20) - button_width, (height-40), button_width, (button_height - 10));
		YES.setBounds(rYES);
		//Send the "NO" button to the setMouseListener function, along with relevant String
		setMouseListener(NO, "no");
		//Send the "YES" button to the setMouseListener function, along with relevant String
		setMouseListener(YES, "yes");
		//Send the "YES" button to the setKeyBindings function, along with relevant String
		setKeyBindings(YES, "yes");
		//Send the "NO" button to the setKeyBindings function, along with relevant String
		setKeyBindings(NO, "no");
		//Send the "YES" button to the setKeyBindings function, along with relevant String
		setKeyBindings(YES, "yes");
		//Add the "NO" button to the JPanel
		window.add(NO);
		//Add the "YES" button to the JPanel
		window.add(YES);
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
		//Have the program focus on this JDialog
		this.requestFocus();
	}
	
	public void setMouseListener(JComponent comp, String compName) {
		//Add a mouse listener to the "okay" button
		comp.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//If the String parameter equals "no"
				if (compName.equals("no")) {
					//Dispose of this instance
					dispose();
				//Otherwise, if the String parameter equals "yes"
				} else if (compName.equals("yes")) {
					//Close this system
					System.exit(0);
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {
				//Set background colour of the parameter JComponent button to red
				((JButton)comp).setBackground(Color.RED);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				//Reset background colour of the parameter JComponent back to it's default
				((JButton)comp).setBackground(UIManager.getColor("Button.background"));				
			}
			@Override
			public void mouseEntered(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {}
		});
	}
	
	public void setKeyBindings(JComponent comp, String compName) {
		//If the parameter String component does not equal that of the "back" button
		if (compName != "yes") {
			//Add to the JComponent's input map a keystroke for the down key
			comp.getInputMap().put(KeyStroke.getKeyStroke("RIGHT"), "rightPressed");
			//Add to the JComponent's action map an abstractAction for if the down key is pressed
			comp.getActionMap().put("rightPressed", new AbstractAction(){
				@Override
				public void actionPerformed(ActionEvent e){
					//Have the program focus on the "YES" button
					YES.requestFocus();
				}
			});
		}
		//Add a key binding to the parameter JComponent's input map for when the enter key is pressed
		comp.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0,false), "enterPressed");
		//Add to the parameter ButtonLabel's action map an abstract action that will perform when the enter key is pressed
		comp.getActionMap().put("enterPressed",new AbstractAction(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//Set the background colour of the component to red
				comp.setBackground(Color.RED);
			}
		});
		//Add a key binding to the parameter JComponent's input map for when the enter key is released
		comp.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0,true), "enterReleased");
		//Add to the parameter ButtonLabel's action map an abstract action that will perform when the enter key is released
		comp.getActionMap().put("enterReleased",new AbstractAction(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//Reset the background colour of the component to it's default
				comp.setBackground(UIManager.getColor("Button.background"));
				//If the parameter String equals "no"
				if (compName == "no") {
					//Dispose of this instance
					dispose();
				//If the parameter String equals "yes"
				} else if (compName == "yes") {
					//Close this system
					System.exit(0);
				}
			}
		});
		//If the component name is not that of "fto" (fruit type options)
		if (compName != "no") {
			//Add a key binding to the parameter JComponent's input map for when the up key is pressed
			comp.getInputMap().put(KeyStroke.getKeyStroke("LEFT"), "leftPressed");
			//Add to the JComponent's action map an abstractAction for if the up key is pressed
			comp.getActionMap().put("leftPressed", new AbstractAction(){
				@Override
				public void actionPerformed(ActionEvent e){
					//Have the program focus on the "NO" button
					NO.requestFocus();
				}
			});
		}
	}
}
