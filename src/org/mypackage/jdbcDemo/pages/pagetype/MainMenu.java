package org.mypackage.jdbcDemo.pages.pagetype;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JRootPane;
import javax.swing.KeyStroke;

import org.mypackage.jdbcDemo.graphics.ButtonLabel;
import org.mypackage.jdbcDemo.input.InputHandler;
import org.mypackage.jdbcDemo.pages.Menu;

public class MainMenu extends Menu {
	private static final long serialVersionUID = 1L;
	
	//Set of JButtons for playing, options, about and quit
	//private JButton seeAll, add, remove, edit, quit;
	private ButtonLabel seeAll, add, remove, edit, quit;
	
	public MainMenu(){
		//Call the superclass' constructor
		super();
		//Call the superclass' setupMainMenu function
		super.setupMainMenu(this);
	}
	
	public void setText() {
		//HTML text for the title. Font size is 16 pixels, colour is green and underlined
		String text = "<html><body>"
		+ "<div style='font-size:16px; color:#7CFC00; text-decoration:underline;'>"
		+ "<p>Hello and welcome to my Apples and Oranges Spring/Hibernate demo.</p>"
		+ "<p>What would you like to do?</p>"		
		+ "</div>"
		+ "</html></body>";
		//Pass the text to the superclass' setText function, with a width of 400 and height of 150
		super.setText(text, 400, 150);
	}
	
	public void renderButtons() {
		//Instantiate the "seeall" ButtonLabel
		seeAll = new ButtonLabel("seeall", 90, 40);
		//Add the following action listener to the play button
		seeAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Call the setupSeeAll function
				setupSeeAll();
			}
		});
		//Pass the "seeall" button to the setKeyBindings function to set keyboard bindings, along with a string ticket value
		setKeyBindings(seeAll, "seeall");
		//Pass the "seeAll" button to the superclass' renderButton function
		super.renderButton(seeAll);
		//Instantiate the "add" ButtonLabel
		add = new ButtonLabel("add", 90, 40);
		//Add the following action listener to the add button
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Call the setupAdd function
				setupAdd();
			}
		});
		//Pass the "add" button to the setKeyBindings function to set keyboard bindings, along with a string ticket value
		setKeyBindings(add, "add");
		//Pass the "add" button to the superclass' renderButton function
		super.renderButton(add);
		//Instantiate the "remove" ButtonLable
		remove = new ButtonLabel("remove", 90, 40);
		//Add the following action listener to the remove button
		remove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Call the setupRemove function
				setupRemove();
			}
		});
		//Pass the "remove" button to the setKeyBindings function to set keyboard bindings, along with a string ticket value
		setKeyBindings(remove, "remove");
		//Pass the "remove" button to the superclass' renderButton function
		super.renderButton(remove);
		//Instantiate the "edit" ButtonLable
		edit = new ButtonLabel("edit", 90, 40);
		//Add the following action listener to the edit button
		edit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Call the setupEdit function
				setupEdit();
			}
		});
		//Pass the "edit" button to the setKeyBindings function to set keyboard bindings, along with a string ticket value
		setKeyBindings(edit, "edit");
		//Pass the "edit" button to the superclass' renderButton function
		super.renderButton(edit);
		//Instantiate the "quit" ButtonLabel
		quit = new ButtonLabel("quit", 90, 40);
		//Add the following action listener to the quit button
		quit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Call the setupQuit function
				setupQuit();
			}
		});
		//Pass the "quit" button to the setKeyBindings function to set keyboard bindings, along with a string ticket value
		setKeyBindings(quit, "quit");
		//Pass the "quit" button to the superclass' renderButton function
		super.renderButton(quit);
		//Call the menu's modifyRootKeyBindings to allow appropriate directional focus for keyboard users
		super.modifyRootKeyBindings("LeftRight");
		//Get the root pane (i.e. the main JFrame)
		JRootPane rp = super.getRootPane();
		//Add to the root pane's action map an abstractAction for if the left key is pressed
		rp.getActionMap().put("focusPane", new AbstractAction(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//If the neither the quit nor the seeAll JButtons do not have focus
				if (!quit.hasFocus() && !seeAll.hasFocus()) {
					//Set focus on the "seeAll" JButton
					seeAll.requestFocus();
				}	
			}
		});
	}
	
	public void setKeyBindings(JComponent butt, String buttName) {
		//If the parameter String does not equal "seeall"
		if (buttName != "seeall") {
			//Add to the parameter ButtonLabel's input map a keystroke for the left key
			butt.getInputMap().put(KeyStroke.getKeyStroke("LEFT"), "leftPressed");
			//Add to the parameter ButtonLabel's action map an abstractAction for if the left key is pressed
			butt.getActionMap().put("leftPressed", new AbstractAction(){
				@Override
				public void actionPerformed(ActionEvent e) {
					//If the parameter String equals "quit"
					if (buttName == "quit") {
						//Have the program focus on the "edit" button to the left
						edit.requestFocus();
					//If the parameter String equals "edit"
					} else if (buttName == "edit") {
						//Have the program focus on the "remove" button to the left
						remove.requestFocus();
					//If the parameter String equals "remove"
					} else if (buttName == "remove") {
						//Have the program focus on the "add" button to the left
						add.requestFocus();
					//If the parameter String equals "add"
					} else if (buttName == "add") {
						//Have the program focus on the "seeall" button to the left
						seeAll.requestFocus();
					}
				}
			});
		}
		//Add a key binding to the parameter ButtonLabel's input map for when the enter key is pressed
		butt.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0,false), "enterPressed");
		//Add to the parameter ButtonLabel's action map an abstract action that will perform when the enter key is pressed
		butt.getActionMap().put("enterPressed",new AbstractAction(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//Call the buttonLabel's setClicked function
				((ButtonLabel) butt).setClicked();
			}
		});
		//Add a key binding to the parameter ButtonLabel's input map for when the enter key is released
		butt.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0,true), "enterReleased");
		//Add to the parameter ButtonLabel's action map an abstract action that will perform when the enter key is released
		butt.getActionMap().put("enterReleased",new AbstractAction(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//Call the ButtonLabel's setReleased function
				((ButtonLabel) butt).setEnteredOrReleased();
				//If the parameter String equals "seeall"
				if (buttName == "seeall") {
					//Call the setupSeeAll function
					setupSeeAll();
				//If the parameter String equals "add"
				} else if (buttName == "add") {
					//Call the setupAdd function
					setupAdd();
				//If the parameter String equals "remove"
				} else if (buttName == "remove") {
					//Call the setupRemove function
					setupRemove();
				//If the parameter String equals "edit"
				} else if (buttName == "edit") {
					//Call the setupEdit function
					setupEdit();
				//If the parameter String equals "quit"
				} else if (buttName == "quit") {
					//Call the setupQuit function
					setupQuit();
				}
			}
		});
		//If the parameter String does not equal "quit"
		if (buttName != "quit") {
			//Add to the parameter ButtonLabel's input map a keystroke for the right key
			butt.getInputMap().put(KeyStroke.getKeyStroke("RIGHT"), "rightPressed");
			//Add to the parameter ButtonLabel's action map an abstractAction for if the enter key is pressed
			butt.getActionMap().put("rightPressed", new AbstractAction(){
				@Override
				public void actionPerformed(ActionEvent e) {
					//If the parameter String equals "seeall"
					if (buttName == "seeall") {
						//Have the program focus on the "add" button to the right
						add.requestFocus();
					//If the parameter String equals "add"
					} else if (buttName == "add") {
						//Have the program focus on the "remove" button to the right
						remove.requestFocus();
					//If the parameter String equals "remove"
					} else if (buttName == "remove") {
						//Have the program focus on the "edit" button to the right
						edit.requestFocus();
					//If the parameter String equals "edit"
					} else if (buttName == "edit") {
						//Have the program focus on the "quit" button to the right
						quit.requestFocus();
					}
				}
			});
		}
	}
	
	public void cleansePage(){
		//Remove the "seeAll" button
		remove(seeAll);
		//Remove the "add" button
		remove(add);
		//Remove the "remove" button
		remove(remove);
		//Remove the "edit" button
		remove(edit);
		//Remove the "quit" button
		remove(quit);
		//Call the superclass' cleansePage function
		super.cleansePage();
	}
	
	public void setupSeeAll() {
		//Call the cleansePage function
		cleansePage();
		//Call the superclass' setupSeeAll function
		super.setupSeeAll();
	}
	
	public void setupAdd() {
		//Call the cleansePage function
		cleansePage();
		//Call the superclass' setupAdd function
		super.setupAdd();
	}
	public void setupRemove() {
		//Call the cleansePage function
		cleansePage();
		//Get the root pane (i.e. the main JFrame)
		JRootPane rp = super.getRootPane();
		//Call the superclass' setupRemove function
		super.setupRemove();
	}
	public void setupEdit() {
		//Call the cleansePage function
		cleansePage();
		//Call the superclass' setupEdit function
		super.setupEdit();
	}
	public void setupQuit() {
		//Call the superclass' setupQuit function
		super.setupQuit();
	}
}