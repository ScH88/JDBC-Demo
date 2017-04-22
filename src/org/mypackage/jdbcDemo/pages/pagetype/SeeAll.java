package org.mypackage.jdbcDemo.pages.pagetype;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;

import org.mypackage.jdbcDemo.dao.HibernateDaoImpl;
import org.mypackage.jdbcDemo.input.InputHandler;
import org.mypackage.jdbcDemo.model.Fruit;
import org.mypackage.jdbcDemo.pages.Menu;

public class SeeAll {
	
	//JButton objects for the "OK" button
	private JButton backButt;
	//Menu which this instance will reference
	private Menu menu;
	//JScrollPane
	private JScrollPane pane;
	
	public SeeAll(Menu menu) {
		//Set the menu reference of this instance as the one passed to the parameter 
		this.menu = menu;
		//Instantiate the JScrollPane
		pane = new JScrollPane();
		//Instantiate the JButton
		backButt = new JButton();
	}
	
	public void setText() {
		//HTML String for the text
		String text = "<html><body>"
		+ "<h1 style='font-size:12px; text-decoration:underline;'>List Of Apples And Oranges</h1>"		
		+ "</html></body>";
		//Pass the text to the menu's setText function, with width of 200 and height of 150
		menu.setText(text, 200, 150);
	}
	
	public void renderContent() {
		//List of fruit instances by calling the menu's Hibernate Data Access Object (DAO) implementation's getAllFruitOfType function, passing it "Apple" to ensure only Apples are retrieved
		List<Fruit> apples = menu.getDAO().getAllFruitOfType("Apple");
		//List of fruit instances by calling the DAO's getAllFruitOfType function, passing it "Orange" to ensure only Oranges are retrieved
		List<Fruit> oranges = menu.getDAO().getAllFruitOfType("Orange");
		//HTML String for the content. Is incomplete on purpose.
		String textCont = "<html><body>"
				+ "<div style='color:#FFFFFF; font-size:12px;'>";
		//For each Fruit instance in the apples array
		for (Fruit app : apples) {
			//To the existing HTML text, add a line containing the current insance's ID and description, while colouring it green
			textCont = textCont + "<p style='color:#00FF00;'>ID: " + app.getID() + ". Description: " + app.getDescription() + "</p>";
		}
		//For each Fruit instance in the oranges array
		for (Fruit ora : oranges) {
			//To the existing HTML text, add a line containing the current insance's ID and description, while colouring it orange
			textCont = textCont + "<p style='color:#FF6600;'>ID: " + ora.getID() + ". Description: " + ora.getDescription() + "</p>";
		}
		//Add closing tags to the HTML text to finish it off
		textCont = textCont + "</div>"		
				+ "</html></body>";
		//Create a JLabel for storing the text
		JLabel text = new JLabel(textCont);
		//Set the vertical alignment of the JLabel to top
		text.setVerticalAlignment(JLabel.TOP);
		//Set the horizontal alignment of the jLabel to left
		text.setHorizontalAlignment(JLabel.LEFT);
		//Set the size of the text JLabel
		text.setSize(new Dimension(220, 220));
		//Add the text JLabel to the scroll pane's viewport
		pane.getViewport().add(text);
		//Set both the scroll pane and it's viewport's opacity to false, removing it's background
		pane.setOpaque(false);
		pane.getViewport().setOpaque(false);
		//Remove the scrollpane's border
		pane.setBorder(null);
		//Set the scrollpane's preferred size
		pane.setPreferredSize(new Dimension(500, 250));
		//Give the scroll pane a vertical scrollbar
		pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		//Add a key listener to the JScrollPane (because when implementing a certain if statement, key bindings would not let it work for some reason)
		pane.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {}
			@Override
			public void keyPressed(KeyEvent e) {}
			@Override
			public void keyReleased(KeyEvent e) {
				//If the KeyEvent's KeyCode is that of the down button being released
				if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					//If the pane's vertical scroll bar's value + height equals the maximum amount if can go down
					if ((pane.getVerticalScrollBar().getValue() + pane.getVerticalScrollBar().getHeight())
						== pane.getVerticalScrollBar().getMaximum()) {
						//Set the program focus on the "back" button
						backButt.requestFocus();
					}
				}
			}
		});
		//Pass the scrollpane to the menu's addComponent function to render it to the central JPanel
		menu.addComponent(pane);
		//Call the menu's modifyRootKeyBindings to allow appropriate directional focus for keyboard users
		menu.modifyRootKeyBindings("UpDown");
		//Get the root pane (i.e. the main JFrame)
		JRootPane rp = menu.getRootPane();
		//Add to the root pane's action map an abstractAction for if the left key is pressed
		rp.getActionMap().put("focusPane", new AbstractAction(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//Have the program focus on the pane
				pane.requestFocus();
			}
		});
	}
	
	public void renderButton() {
		//Set the text of the "Back" button
		backButt.setText("BACK");
		//Add the following action listener to the "back" button
		backButt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Call the setupMainMenu in the SeeAll class
				setupMainMenu();
			}
		});
		//Add to the back button's input map a keystroke for the enter key
		backButt.getInputMap().put(KeyStroke.getKeyStroke("ENTER"), "enterPressed");
		//Add to the back button's action map an abstractAction for if the enter key is pressed
		backButt.getActionMap().put("enterPressed", new AbstractAction(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//Call the setupMainMenu function
				setupMainMenu();
			}
		});
		//Add to the back button's input map a keystroke for the up key
		backButt.getInputMap().put(KeyStroke.getKeyStroke("UP"), "upPressed");
		//Add to the back button's action map an abstractAction for if the up key is pressed
		backButt.getActionMap().put("upPressed", new AbstractAction(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//Set the program focus on the above JScollPane
				pane.requestFocus();
			}
		});
		//Call the menu's renderButton function, passing it the "back" button recently created
		menu.renderButton(backButt);
	}
	
	public void setupMainMenu() {
		//Have the menu remove the "back" button
		menu.remove(backButt);
		//Call the menu's cleansePage function
		menu.cleansePage();
		//Call the menu's setupMainMenu function
		menu.setupMainMenu();
	}
}

