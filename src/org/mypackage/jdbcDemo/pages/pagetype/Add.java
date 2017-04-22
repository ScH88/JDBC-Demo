package org.mypackage.jdbcDemo.pages.pagetype;

import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.HashMap;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JRootPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import org.mypackage.jdbcDemo.dao.HibernateDaoImpl;
import org.mypackage.jdbcDemo.input.InputHandler;
import org.mypackage.jdbcDemo.model.Apple;
import org.mypackage.jdbcDemo.model.Fruit;
import org.mypackage.jdbcDemo.model.Orange;
import org.mypackage.jdbcDemo.pages.Menu;

//public class Options extends Menu {
public class Add {
	
		//JButton objects for the "Back" and "Okay" buttons
		private JButton backButt, okay;
		//JLabel for form validation dialog
		private JLabel validationLab;
		//Menu which this instance will reference
		private Menu menu;
		//JComboBoxes for the fruit type options
		private JComboBox fruitTypeOptions;
		//JTextArea for the user to type in the new description
		private JTextArea tex;
		
		public Add(Menu menu) {
			//Set the menu reference of this instance as the one passed to the parameter 
			this.menu = menu;
			//Instantiate the "okay" button
			okay = new JButton();
			//Instantiate the "back" button
			backButt = new JButton();
			//Instantiate the fruit type options JComboBox
			fruitTypeOptions = new JComboBox();
			//Instantiate the JTextArea, with size of 5 columns and 15 rows
			tex = new JTextArea(5, 15);
		}
		
		public void setText() {
			//HTML String for the header text
			String text = "<html><body>"
			+ "<h1 style='font-size:14px; text-decoration:underline;'>Insert Apple/Orange</h1>"		
			+ "</html></body>";
			//Pass the text to the menu's setText function, with width of 200 and height of 150
			menu.setText(text, 100, 150);
		}
		
		public void renderContent() {
			//HTML String for the fruit type label text
			String text1 = "<html><body>"
			+ "<h2 style='font-size:12px; color:#FFFFFF;'>Select Fruit Type</h2>"		
			+ "</html></body>";
			//JLabel form = new JLabel();
			JLabel fruitText = new JLabel(text1);
			//Set the padding of the JLable
			fruitText.setBorder(new EmptyBorder(10,10,10,10));
			//String array for JComboBox options
			String[] optionText = {"Select Fruit", "Apple", "Orange"};
			//Instantiate a new JCombobox, containing the String array for options
			fruitTypeOptions.setModel(new DefaultComboBoxModel(optionText));
			//HTML String for the fruit type label text
			String text2 = "<html><body>"
			+ "<h2 style='font-size:12px; color:#FFFFFF;'>Write Fruit Description Here</h2>"		
			+ "</html></body>";
			//JLabel for storing the fruit type label String
			JLabel descText = new JLabel(text2);
			//Set the padding of the JLabel
			descText.setBorder(new EmptyBorder(10,10,10,10));
			//Set up text wrapping for both word and line in the JTextArea
			tex.setWrapStyleWord(true);
			tex.setLineWrap(true);
			//Set up a padding of 10 around the JTextArea
			tex.setBorder(BorderFactory.createCompoundBorder(
			        tex.getBorder(), 
			        BorderFactory.createEmptyBorder(10, 10, 10, 10)));
			//Add a PlainDocument to the JTextArea to enforce a character limit of 50
			tex.setDocument(new PlainDocument() {
				@Override
			    public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
				     //If the string passed to the parameter is null or greater than than/equal to 50
					 if (str == null || tex.getText().length() >= 50) {
						 //Return from this function
						 return;
				     }
			        //Assuming the statement above is bypassed, pass the offset, String and attributeSet to the superclass' insertString function
					super.insertString(offs, str, a);
				}
			});
			//Instantiate the validation JLabel, with default value of null
			validationLab = new JLabel();
			//Set the text of the "okay" button
			okay.setText("ADD FRUIT");
			//Add an action listener to the "okay" button
			okay.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					//Call the addFruit function to add the inputted information as a new Fruit instance
					addFruit();
				}
			});
			//Add to the "okay" button's input map a keystroke for the enter key
			okay.getInputMap().put(KeyStroke.getKeyStroke("ENTER"), "enterPressed");
			//Add to the "okay" button's action map an abstractAction for if the enter key is pressed
			okay.getActionMap().put("enterPressed", new AbstractAction(){
		        @Override
		        public void actionPerformed(ActionEvent e){
		        	//Call the addFruit function to add the inputted information as a new Fruit instance
					addFruit();
		        }
		    });
			//Add to the "okay" button's input map a keystroke for the down key
			okay.getInputMap().put(KeyStroke.getKeyStroke("DOWN"), "downPressed");
			//Add to the "okay" button's action map an abstractAction for if the down key is pressed
			okay.getActionMap().put("downPressed", new AbstractAction(){
		        @Override
		        public void actionPerformed(ActionEvent e){
		        	//Set the program focus on the "back" button
					backButt.requestFocus();
		        }
		    });
			//Add to the "okay" button's input map a keystroke for the up key
			okay.getInputMap().put(KeyStroke.getKeyStroke("UP"), "upPressed");
			//Add to the "okay" button's action map an abstractAction for if the up key is pressed
			okay.getActionMap().put("upPressed", new AbstractAction(){
		        @Override
		        public void actionPerformed(ActionEvent e){
		        	//Set the program focus on the JTextArea
					tex.requestFocus();
		        }
		    });
			//Add to the "fruitTypeOptions" JComboBox's input map a keystroke for the enter key
			fruitTypeOptions.getInputMap().put(KeyStroke.getKeyStroke("ENTER"), "enterPressed");
			//Add to the "fruitTypeOptions" JComboBox's action map an abstractAction for if the enter key is pressed
			fruitTypeOptions.getActionMap().put("enterPressed", new AbstractAction(){
		        @Override
		        public void actionPerformed(ActionEvent e){
		        	//If the JComboBox's menu is not opened
		        	if (fruitTypeOptions.isPopupVisible() == false) {
		        		//Show the popup menu of the JComboBox
		        		fruitTypeOptions.showPopup();
		        	//If the JComboBox's menu is opened
		        	} else {
		        		//Close the popup menu, selecting the currently selected index in the process
		        		fruitTypeOptions.hidePopup();
		        	}
		        }
		    });
			//Add to the "fruitTypeOptions" JComboBox's input map a keystroke for the down key
			fruitTypeOptions.getInputMap().put(KeyStroke.getKeyStroke("DOWN"), "downPressed");
			//Add to the "fruitTypeOptions" JComboBox's action map an abstractAction for if the down key is pressed
			fruitTypeOptions.getActionMap().put("downPressed", new AbstractAction(){
		       @Override
		        public void actionPerformed(ActionEvent e){
		    	   //If the JComboBox's popup menu is not opened
		        	if (fruitTypeOptions.isPopupVisible() == false) {
		        		//Have the program request focus on the content JTextArea below
		        		tex.requestFocus();
		        	//If the popup menu is open
		        	} else {
		        		//If the next index does not equal/exceed the JComboBox's options' item count
		        		if (fruitTypeOptions.getSelectedIndex() + 1 < fruitTypeOptions.getItemCount()) {
		        			//Set the currently selected index as the next one down the line
		        			fruitTypeOptions.setSelectedIndex(fruitTypeOptions.getSelectedIndex() + 1);
		        		}
		        	}
		        }
		    });
			//Add to the "tex" JTextArea's input map a keystroke for the up key
			tex.getInputMap().put(KeyStroke.getKeyStroke("UP"), "upPressed");
			//Add to the "tex" JTextArea's action map an abstractAction for if the up key is pressed
			tex.getActionMap().put("upPressed", new AbstractAction(){
		       @Override
		        public void actionPerformed(ActionEvent e){
		        	//Have the program focus on the fruitTypeOptions JComboBox
		    	   fruitTypeOptions.requestFocus();
		        }
		    });
			//Add to the "tex" JTextArea's input map a keystroke for the down key
			tex.getInputMap().put(KeyStroke.getKeyStroke("DOWN"), "downPressed");
			//Add to the "tex" JTextArea's action map an abstractAction for if the down key is pressed
			tex.getActionMap().put("downPressed", new AbstractAction(){
		       @Override
		        public void actionPerformed(ActionEvent e){
		        	//Have the program focus on the "okay" button
		    	   okay.requestFocus();
		        }
		    });
			//Create a new GridBagConstraints instance
			GridBagConstraints gbc = new GridBagConstraints();
			//Anchor the current table alignment to the left of the table
			gbc.anchor = GridBagConstraints.WEST;
			//Set the current grid x position weight to 0.5
			gbc.weightx = 0.5;
			//Set the current grid y position weight to 0.5
			gbc.weighty = 0.5;
			//Set the current grid x position to 0
			gbc.gridx = 0;
			//Set the current grid y position to 0
			gbc.gridy = 0;
			//Pass the first JLabel text to the menu's addComponent function
			menu.addComponent(fruitText, gbc);
			//Move the current grid x position to 2
			gbc.gridx = 2;
			//Have the gbc fill the current cell without any whitespace (for the JComboBox to be longer)
			gbc.fill = GridBagConstraints.HORIZONTAL;
			//Pass the fruitTypeOptions JComboBox to the menu's addComponent function
			menu.addComponent(fruitTypeOptions, gbc);
			//Reset the gbc's fill to none
			gbc.fill = GridBagConstraints.NONE;
			//Reset the grid x position back to 0
			gbc.gridx = 0;
			//Set the grid y position to 2 rows down
			gbc.gridy = 2;
			//Pass the second JLabel text to the menu's addComponent function
			menu.addComponent(descText, gbc);
			//Set the grid's x position to 2 columns across
			gbc.gridx = 2;
			//Have the gbc fill the current cell without any whitespace (for the JComboBox to be longer)
			gbc.fill = GridBagConstraints.HORIZONTAL;
			//Pass the JTextArea to the menu's addComponent function
			menu.addComponent(tex, gbc);
			//Set the current grid x position back to 0
			gbc.gridx = 0;
			//Set the current grid y position to 4 rows down
			gbc.gridy = 4;
			//Set the current grid width to 5 columns
			gbc.gridwidth = 5;
			//Set the gbc's fill to both horizontal and vertical of cell
			gbc.fill = GridBagConstraints.BOTH;
			//Pass the "Okay" button to the menu's addComponent function
			menu.addComponent(okay, gbc);
			//Reset the grid's fill back to none
			gbc.fill = GridBagConstraints.NONE;
			//Set the grid's current y position to 6 rows down
			gbc.gridy = 6;
			//Anchor the current table alignment to the center of the table
			gbc.anchor = GridBagConstraints.CENTER;
			//Pass the validation JLabel to the menu's addComponent function
			menu.addComponent(validationLab, gbc);
			//Call the menu's modifyRootKeyBindings to allow appropriate directional focus for keyboard users
			menu.modifyRootKeyBindings("UpDown");
			//Get the root pane (i.e. the main JFrame)
			JRootPane rp = menu.getRootPane();
			//Add to the root pane's action map an abstractAction for if the left key is pressed
			rp.getActionMap().put("focusPane", new AbstractAction(){
				@Override
				public void actionPerformed(ActionEvent e) {
					//If the "back" JButton does not have focus
					if (!backButt.hasFocus()) {
						//Set focus on the "fruitTypeOptions" JComboBox
						fruitTypeOptions.requestFocus();
					}
				}
			});
		}
		
		public void renderButton() {
			//Set the text of the "back" button
			backButt.setText("BACK");
			//Add the following action listener to the play button
			backButt.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//Call the setupMainMenu in the SeeAll class
					setupMainMenu();
				}
			});
			//Add to the "back" JButton's input map a key binding for when the enter key is pressed
			backButt.getInputMap().put(KeyStroke.getKeyStroke("ENTER"), "enterPressed");
			//Add to the "back" JButton's action map an abstractAction for if the enter key is pressed
			backButt.getActionMap().put("enterPressed", new AbstractAction(){
		       @Override
		        public void actionPerformed(ActionEvent e){
		    	   //Call this class' setupSeeAll function
		    	   setupMainMenu();
		        }
		    });
			//Add to the "back" JButton's input map a key binding for when the up key is pressed
			backButt.getInputMap().put(KeyStroke.getKeyStroke("UP"), "upPressed");
			//Add to the "back" JButton's action map an abstractAction for if the up key is pressed
			backButt.getActionMap().put("upPressed", new AbstractAction(){
		       @Override
		        public void actionPerformed(ActionEvent e){
		    	   //Set the program focus on the "okay" button
					okay.requestFocus();
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
		
		public void addFruit() {
			//If both the JCombobox and the JTextArea has no input
			if (fruitTypeOptions.getSelectedIndex() == 0 && tex.getText().equals("")) {
				//Set the text of the validation JLabel as the following HTML text
				validationLab.setText(
					"<html><body>"
					+ "<p style='font-size:12px; color:#FFFFFF;'>Both fields are not entered yet</p>"		
					+ "</html></body>"		
				);
			//Otherwise
			} else { 
				//Otherwise, if just the JComboBox has a selected index of 0 ("Select Fruit")
				if (fruitTypeOptions.getSelectedIndex() == 0) {
					//Set the text of the validation JLabel as the following HTML text
					validationLab.setText(
					"<html><body>"
					+ "<p style='font-size:12px; color:#FFFFFF;'>A type of fruit needs to be selected</p>"		
					+ "</html></body>"		
					);
				//If just the JTextArea has no input entered
				} else if (tex.getText().equals("")) {
						//Set the text of the validation JLabel as the following HTML text
						validationLab.setText(
						"<html><body>"
						+ "<p style='font-size:12px; color:#FFFFFF;'>No description has been written</p>"		
						+ "</html></body>"		
						);
				//Otherwise
				} else {
					//Empty the validation label of all text
					validationLab.setText("");
					//Create a Fruit superclass instance, with default value of null;
					Fruit newFruit = null;
					//If the selected item of the JComboBox reads "Apple"
					if (fruitTypeOptions.getSelectedItem() == "Apple") {
						//Instantiate the Fruit as a new Apple, giving it a new ID of (apple array size + 1) and the JTextArea's content
						newFruit = new Apple(menu.getDAO().getFruitCount("Apple") + 1, tex.getText());
						//If the selected item of the JComboBox reads "Orange"
					} else if (fruitTypeOptions.getSelectedItem() == "Orange") {
						//Instantiate the Fruit as a new Orange, giving it a new ID of (orange array size + 1) and the JTextArea's content
						newFruit = new Orange(menu.getDAO().getFruitCount("Orange") + 1, tex.getText());
					}
					//Insert the new Fruit instance by passing it to the menu's Hibernate Data Access Object(DAO) implementation's insertFruit function
					menu.getDAO().insertFruit(newFruit);
					//Instantiate a new Confirmation JDialog, passing it the menu JFrame instance and a number of 1
					Confirmation conf = new Confirmation(menu, 1);
					//Add a WindowListener to the Confirmation JDialog
					conf.addWindowListener(new WindowListener() {
						@Override
						public void windowOpened(WindowEvent e) {}
						@Override
						public void windowClosing(WindowEvent e) {}
						@Override
						public void windowClosed(WindowEvent e) {
							//If this confirmation JDialog has been closed, call the menu's toggleButtonUse function to enable JComponent use again
							menu.toggleButtonUse();
							//Have the program focus on the "okay" button
							okay.requestFocus();
						}
						@Override
						public void windowIconified(WindowEvent e) {}
						@Override
						public void windowDeiconified(WindowEvent e) {}
						@Override
						public void windowActivated(WindowEvent e) {}
						@Override
						public void windowDeactivated(WindowEvent e) {}
					});
					//Call the menu's toggleButtonUse function to temporarily disable JComponent use in the south and center JPanels
					menu.toggleButtonUse();
				}
			}
		}
}


