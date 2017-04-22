package org.mypackage.jdbcDemo.pages.pagetype;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JRootPane;
import javax.swing.KeyStroke;

import org.mypackage.jdbcDemo.input.InputHandler;
import org.mypackage.jdbcDemo.pages.Menu;

public class MainMenu extends Menu {
	private static final long serialVersionUID = 1L;
	
	//Set of JButtons for playing, options, about and quit
	private JButton seeAll, add, remove, edit, quit;
	
	public MainMenu(){
		//Call the superclass' constructor
		super();
		//Call the superclass' setupMainMenu function
		super.setupMainMenu(this);
	}
	
	public void setText() {
		//HTML text for the title. Font size is 12 pixels
		String text = "<html><body>"
		+ "<div style='font-size:12px;'>"
		+ "<p>Hello and welcome to my Apples and Oranges Spring/Hibernate demo.</p>"
		+ "<p>What would you like to do?</p>"		
		+ "</div>"
		+ "</html></body>";
		//Pass the text to the superclass' setText function, with a width of 400 and height of 150
		super.setText(text, 400, 150);
	}
	
	public void renderButtons() {
		//New JButton object for "play"
		seeAll = new JButton("SEE ALL");
		//Add the following action listener to the play button
		seeAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Call the setupSeeAll function
				setupSeeAll();
			}
		});
		//Add to the "seeAll" JButton's input map a keystroke for the enter key
		seeAll.getInputMap().put(KeyStroke.getKeyStroke("ENTER"), "enterPressed");
		//Add to the "seeAll" JButton's action map an abstractAction for if the enter key is pressed
		seeAll.getActionMap().put("enterPressed", new AbstractAction(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//Call the setupSeeAll function
				setupSeeAll();
			}
		});
		//Add to the "seeAll" JButton's input map a keystroke for the right key
		seeAll.getInputMap().put(KeyStroke.getKeyStroke("RIGHT"), "rightPressed");
		//Add to the "seeAll" JButton's action map an abstractAction for if the enter key is pressed
		seeAll.getActionMap().put("rightPressed", new AbstractAction(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//Set the program focus on the "add" button
				add.requestFocus();
			}
		});
		//Pass the "seeAll" button to the superclass' renderButton function
		super.renderButton(seeAll);
		
		//New JButton object for "Add"
		add = new JButton("ADD");
		//Add the following action listener to the "See All" button
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Call the setupAdd function
				setupAdd();
			}
		});
		//Add to the "add" JButton's input map a keystroke for the enter key
		add.getInputMap().put(KeyStroke.getKeyStroke("ENTER"), "enterPressed");
		//Add to the "add" JButton's action map an abstractAction for if the enter key is pressed
		add.getActionMap().put("enterPressed", new AbstractAction(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//Call the setupAdd function
				setupAdd();
			}
		});
		//Add to the "add" JButton's input map a keystroke for the left key
		add.getInputMap().put(KeyStroke.getKeyStroke("LEFT"), "leftPressed");
		//Add to the "add" JButton's action map an abstractAction for if the left key is pressed
		add.getActionMap().put("leftPressed", new AbstractAction(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//Set the program focus on the "seeAll" button
				seeAll.requestFocus();
			}
		});
		//Add to the "add" JButton's input map a keystroke for the right key
		add.getInputMap().put(KeyStroke.getKeyStroke("RIGHT"), "rightPressed");
		//Add to the "add" JButton's action map an abstractAction for if the enter key is pressed
		add.getActionMap().put("rightPressed", new AbstractAction(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//Set the program focus on the "remove" button
				remove.requestFocus();
			}
		});
		//Pass the "add" button to the superclass' renderButton function
		super.renderButton(add);
		//New JButton object for "Remove"
		remove = new JButton("REMOVE");
		//Add the following action listener to the "Remove" button
		remove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Call the setupRemove function
				setupRemove();
			}
		});
		//Add to the "remove" JButton's input map a keystroke for the enter key
		remove.getInputMap().put(KeyStroke.getKeyStroke("ENTER"), "enterPressed");
		//Add to the "remove" JButton's action map an abstractAction for if the enter key is pressed
		remove.getActionMap().put("enterPressed", new AbstractAction(){
			@Override
				public void actionPerformed(ActionEvent e) {
				//Call the setupRemove function
				setupRemove();
			}
		});
		//Add to the "remove" JButton's input map a keystroke for the left key
		remove.getInputMap().put(KeyStroke.getKeyStroke("LEFT"), "leftPressed");
		//Add to the "remove" JButton's action map an abstractAction for if the left key is pressed
		remove.getActionMap().put("leftPressed", new AbstractAction(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//Set the program focus on the "add" button
				add.requestFocus();
			}
		});
		//Add to the "remove" JButton's input map a keystroke for the right key
		remove.getInputMap().put(KeyStroke.getKeyStroke("RIGHT"), "rightPressed");
		//Add to the "remove" JButton's action map an abstractAction for if the enter key is pressed
		remove.getActionMap().put("rightPressed", new AbstractAction(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//Set the program focus on the "edit" button
				edit.requestFocus();
			}
		});
		//Pass the "remove" button to the superclass' renderButton function
		super.renderButton(remove);
		//New JButton object for "Edit"
		edit = new JButton("EDIT");
		//Add the following action listener to the "Edit" button
		edit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Call the setupEdit function
				setupEdit();
			}
		});
		//Add to the "edit" JButton's input map a keystroke for the enter key
		edit.getInputMap().put(KeyStroke.getKeyStroke("ENTER"), "enterPressed");
		//Add to the "edit" JButton's action map an abstractAction for if the enter key is pressed
		edit.getActionMap().put("enterPressed", new AbstractAction(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//Call the setupEdit function
				setupEdit();
			}
		});
		//Add to the "edit" JButton's input map a keystroke for the left key
		edit.getInputMap().put(KeyStroke.getKeyStroke("LEFT"), "leftPressed");
		//Add to the "edit" JButton's action map an abstractAction for if the left key is pressed
		edit.getActionMap().put("leftPressed", new AbstractAction(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//Set the program focus on the "remove" button
				remove.requestFocus();
			}
		});
		//Add to the "edit" JButton's input map a keystroke for the right key
		edit.getInputMap().put(KeyStroke.getKeyStroke("RIGHT"), "rightPressed");
		//Add to the "edit" JButton's action map an abstractAction for if the enter key is pressed
		edit.getActionMap().put("rightPressed", new AbstractAction(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//Set the program focus on the "quit" button
				quit.requestFocus();
			}
		});
		//Pass the "edit" button to the superclass' renderButton function
		super.renderButton(edit);
		
		//New JButton object for "quit game"
		quit = new JButton("QUIT");
		//Add the following action listener to the quit button
		quit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Call the setupQuit function
				setupQuit();
			}
		});
		//Add to the "quit" JButton's input map a keystroke for the enter key
		quit.getInputMap().put(KeyStroke.getKeyStroke("ENTER"), "enterPressed");
		//Add to the "edit" JButton's action map an abstractAction for if the enter key is pressed
		quit.getActionMap().put("enterPressed", new AbstractAction(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//Call the setupQuit function
				setupQuit();
			}
		});
		//Add to the "quit" JButton's input map a keystroke for the left key
		quit.getInputMap().put(KeyStroke.getKeyStroke("LEFT"), "leftPressed");
		//Add to the "quit" JButton's action map an abstractAction for if the left key is pressed
		quit.getActionMap().put("leftPressed", new AbstractAction(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//Set the program focus on the "edit" button
				edit.requestFocus();
			}
		});
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