package org.mypackage.jdbcDemo.pages.pagetype;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JRootPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import org.mypackage.jdbcDemo.dao.HibernateDaoImpl;
import org.mypackage.jdbcDemo.graphics.ButtonLabel;
import org.mypackage.jdbcDemo.input.InputHandler;
import org.mypackage.jdbcDemo.model.Apple;
import org.mypackage.jdbcDemo.model.Fruit;
import org.mypackage.jdbcDemo.model.Orange;
import org.mypackage.jdbcDemo.pages.Menu;

//public class Options extends Menu {
public class Edit {
	
	//JButton objects for the "okay" button
	private JButton okay;
	//ButtonLabel instance for the "back" button
	private ButtonLabel backButt;
	//Menu instance for this instance to reference
	private Menu menu;
	//JComboBoxes for the fruit type options and the entries for each fruit type
	private JComboBox fruitTypeOptions, entries;
	//JTextArea for the user to type in the new description
	private JTextArea newDescription;
	//Hashmap for the currently selected fruit type
	private HashMap<Integer, String> fruits;
	
	public Edit(Menu menu) {
		//Set the Menu value as the one passed to the parameter
		this.menu = menu;
		//Instantiate the "back" ButtonLabel
		backButt = new ButtonLabel("back", 90, 40);
		//Instantiate the "okay" button
		okay = new JButton();
		//Set the "okay" button's contentAreaFilled criteria to allow recolouring
		okay.setContentAreaFilled(false);
		//Set the "okay" button's opacity to true
		okay.setOpaque(true);
		//Instantiate the fruit type options JComboBox
		fruitTypeOptions = new JComboBox();
		//Instantiate the fruit type entries JComboBox
		entries = new JComboBox();
		//Instantiate the JTextArea for the new description to replace the old one with
		newDescription = new JTextArea(2, 5);
		//Set up text wrapping for both word and line in the JTextArea
		newDescription.setWrapStyleWord(true);
		newDescription.setLineWrap(true);
		//Set up a padding of 8 around the JTextArea
		newDescription.setBorder(BorderFactory.createCompoundBorder(
			newDescription.getBorder(), 
		    BorderFactory.createEmptyBorder(8, 8, 8, 8)));
		//Instantiate the current fruits hashmap
		fruits = new HashMap<>();
	}
	
	public void setText() {
		//String containing HTML text of size 22 pixels, underlined and coloured green
		String text = "<html><body>"
		+ "<h1 style='font-size:22px; color:#7CFC00; text-decoration:underline;'>Edit Fruit</h1>"		
		+ "</html></body>";
		//Pass the HTML text to the menu's setText function, with width of 50 ad height of 150
		menu.setText(text, 50, 150);
	}
	
	public void renderContent() {
		//For each Fruit instance (named app) returned from the menu's Hibernate Data Access Object(DAO) Implementation's getAllFruitOfType (parameter is "Apple", so only Apple instances are returned
		for (Fruit app : menu.getDAO().getAllFruitOfType("Apple")) {
			//Empty string for the description (as entries exceeding 25 characters will stretch the JComboBox, pushing the JLabel)
			String desc = "";
			//If the number of characters in the description exceed or equal 25
			if (app.getDescription().toString().trim().length() >= 25) {
				//Get a substring from the start of the description to 22 characters across, with a few "..." at the end
				desc = app.getDescription().toString().substring(0, 22) + "...";
			//Otherwise
			} else {
				//Just set the description string with the whole value
				desc = app.getDescription();
			}
			//Add the following String to the fruits hashmap, using the current Fruit's ID and description values
			fruits.put(app.getID(), "ID: " + app.getID() + "; Description: " + desc);
		}
		//HTML String for the fruit type label text
		String text1 = "<html><body>"
		+ "<h1 style='font-size:12px; color:#FFFFFF;'>Select Fruit Type</h1>"		
		+ "</html></body>";
		//Instantiate a new JLabel, displaying the HTML text
		JLabel fruitText = new JLabel(text1);
		//Set the padding of the JLable
		fruitText.setBorder(new EmptyBorder(0,10,0,10));
		//String array of options for use in upcoming JComboBox
		String[] optionText = {"Apple", "Orange"};
		//Instantiate a new JComboBox, using the previous String array for options
		fruitTypeOptions.setModel(new DefaultComboBoxModel(optionText));// = new JComboBox(optionText);		
		//Add a new ItemListener to the fruit type JComboBox
		fruitTypeOptions.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				//If the ItemEvent's state change is that of SELECTED
				if (e.getStateChange() == ItemEvent.SELECTED) {
					//If the fruit type JComboBox's selected item equals "Apple"
					if (fruitTypeOptions.getSelectedItem() == "Apple") {
						//Clear the fruits array of all entries
						fruits.clear();
						//For each Fruit (named app) in the DAO's getAllFruitOfType return value (parameter is "Apple", so only Apple instances are returned)
						for (Fruit app : menu.getDAO().getAllFruitOfType("Apple")) {
							//Empty string for the description (as entries exceeding 25 characters will stretch the JComboBox, pushing the JLabel)
							String desc = "";
							//If the number of characters in the description exceed or equal 25
							if (app.getDescription().toString().trim().length() >= 25) {
								//Get a substring from the start of the description to 22 characters across, with a few "..." at the end
								desc = app.getDescription().toString().substring(0, 22) + "...";
							//Otherwise
							} else {
								//Just set the description string with the whole value
								desc = app.getDescription();
							}
							//Add the following String to the fruits hashmap, using the ID and description values of the current Fruit instance
							fruits.put(app.getID(), "ID: " + app.getID() + "; Description: " + desc);
						}
					} else if (fruitTypeOptions.getSelectedItem() == "Orange") {
						//Clear the fruits array of all entries
						fruits.clear();
						//For each Fruit (named ora) in the DAO's getAllFruitOfType return value (parameter is "Orange", so only Orange instances are returned)
						for (Fruit ora : menu.getDAO().getAllFruitOfType("Orange")) {
							//Empty string for the description (as entries exceeding 25 characters will stretch the JComboBox, pushing the JLabel)
							String desc = "";
							//If the number of characters in the description exceed or equal 25
							if (ora.getDescription().toString().trim().length() >= 25) {
								//Get a substring from the start of the description to 22 characters across, with a few "..." at the end
								desc = ora.getDescription().toString().substring(0, 22) + "...";
							//Otherwise
							} else {
								//Just set the description string with the whole value
								desc = ora.getDescription();
							}
							//Add the following String to the fruits hashmap, using the ID and description values of the current Fruit instance
							fruits.put(ora.getID(), "ID: " + ora.getID() + "; Description: " + desc);
						}
					}
					//Change the model of the entries JComboBox as that of the fruits array converted to ArrayList
					entries.setModel(new DefaultComboBoxModel(fruits.values().toArray()));
				}
			}
		});
		//HTML String for the fruit type label text
		String text2 = "<html><body>"
				+ "<h1 style='font-size:12px; color:#FFFFFF;'>Current Entries</h1>"		
				+ "</html></body>";
		//Instantiate a new JLabel using the previous HTML text String
		JLabel entriesText = new JLabel(text2);
		//Set the padding of the JLable
		entriesText.setBorder(new EmptyBorder(0,10,0,10));
		//Instantiate a new JComboBox, storing the fruits array converted to ArrayList
		entries.setModel(new DefaultComboBoxModel(fruits.values().toArray()));
		//Add a new ItemListener to the fruit type JComboBox
		entries.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {		
				//Get an array of Strings out of the entries JComboBox's selected item using the Java split function, with ; and : as delimiters to separate
				String[] step1 = entries.getSelectedItem().toString().split("[;:]");
				//Get the second entry in the String array (ID), trim of all white space and convert to integer, for the ID to edit
				String newDesc = step1[3].toString().trim();
				//Set the text content of the new Description JTextArea
				newDescription.setText(newDesc);
			}
		});
		//HTML String for the new descripton label text
		String text3 = "<html><body>"
				+ "<h1 style='font-size:12px; color:#FFFFFF;'>Write Your New Description</h1>"		
				+ "</html></body>";
		//Instantiate a new JLabel using the previous HTML text String
		JLabel newDescText = new JLabel(text3);
		//Set the padding of the JLable
		newDescText.setBorder(new EmptyBorder(0,10,0,10));
		//Add a PlainDocument to the JTextArea to enforce a character limit of 50
		newDescription.setDocument(new PlainDocument() {
			@Override
		    public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
			     //If the string passed to the parameter is null or greater than than/equal to 50
				 if (str == null || newDescription.getText().length() >= 50) {
					 //Return from this function
					 return;
			     }
		        //Assuming the statement above is bypassed, pass the offset, String and attributeSet to the superclass' insertString function
				super.insertString(offs, str, a);
			}
		});
		//Set the text of the "okay" button
		okay.setText("Change Fruit");
		//Add a mouse listener to the "okay" button
		okay.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				//Call the editFruit function to add the inputted information as a new Fruit instance
				editFruit();
			}

			@Override
			public void mousePressed(MouseEvent e) {
				//Change the background colour of the "okay" button to red
				okay.setBackground(Color.RED);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				//Reset the background colour of the "okay" button to it's default
				okay.setBackground(UIManager.getColor("Button.background"));
			}
			@Override
			public void mouseEntered(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {}
		});
		//Pass the fruitTypeOptions JComboBox to the setKeyBindings function, along with appropriate String name
		setKeyBindings(fruitTypeOptions, "fto");
		//Pass the entries JComboBox to the setKeyBindings function, along with appropriate String name
		setKeyBindings(entries, "entries");
		//Pass the new description JTextArea to the setKeyBindings function, along with appropriate String name
		setKeyBindings(newDescription, "newDesc");
		//Pass the "okay" JButton to the setKeyBindings function, along with appropriate String name
		setKeyBindings(okay, "okay");
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
		//Pass the fruit type JLabel to the menu's addComponent function to add to the center JPanel
		menu.addComponent(fruitText, gbc);
		//Set the current grid x position to 1
		gbc.gridx = 1;
		//Have the gbc fill the current cell without any whitespace (for the JComboBox to be longer)
		gbc.fill = GridBagConstraints.HORIZONTAL;
		//Pass the fruit type JCombobox to the menu's addComponent function to add to the center JPanel
		menu.addComponent(fruitTypeOptions, gbc);
		//Reset the gbc's fill to none
		gbc.fill = GridBagConstraints.NONE;
		//Set the current grid x position back to 0
		gbc.gridx = 0;
		//Set the current grid y position to 2 rows down
		gbc.gridy = 2;
		//Pass the fruit entries JLabel to the menu's addComponent function to add to the center JPanel
		menu.addComponent(entriesText, gbc);
		//Set the current grid x position to 1
		gbc.gridx = 1;
		//Have the gbc fill the current cell without any whitespace (for the JComboBox to be longer)
		gbc.fill = GridBagConstraints.HORIZONTAL;
		//Pass the fruit entries JComboBox to the menu's addComponent function to add to the center JPanel
		menu.addComponent(entries, gbc);
		//Reset the gbc's fill to none
		gbc.fill = GridBagConstraints.NONE;
		//Set the current grid x position back to 0
		gbc.gridx = 0;
		//Set the current grid y position to 2 rows down
		gbc.gridy = 3;
		//Pass the new description JTextArea to the menu's addComponent function to add to the center JPanel
		menu.addComponent(newDescText, gbc);
		//Set the current grid x position to 1
		gbc.gridx = 1;
		//Have the gbc fill the current cell without any whitespace (for the JComboBox to be longer)
		gbc.fill = GridBagConstraints.HORIZONTAL;
		//Pass the new description JTextArea to the menu's addComponent function to add to the center JPanel
		menu.addComponent(newDescription, gbc);
		//Set the current grid x position back to 0
		gbc.gridx = 0;
		//Set the current grid y position to 4
		gbc.gridy = 4;
		//Set the current grid width to 5 columns
		gbc.gridwidth = 5;
		//Set the gbc's fill to both horizontal and vertical
		gbc.fill = GridBagConstraints.BOTH;
		//Pass the "okay" JButton to the menu's addComponent function to add to the center JPanel
		menu.addComponent(okay, gbc);
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
	
	public void setKeyBindings(JComponent comp, String compName) {
		//If the parameter String compnent does not equal that of the "back" button
		if (compName != "back") {
			//Add to the JComponent's input map a keystroke for the down key
			comp.getInputMap().put(KeyStroke.getKeyStroke("DOWN"), "downPressed");
			//Add to the JComponent's action map an abstractAction for if the down key is pressed
			comp.getActionMap().put("downPressed", new AbstractAction(){
				@Override
				public void actionPerformed(ActionEvent e){
					//If the component name is "fto" (fruit type options)
					if (comp.getClass().toString().equals("class javax.swing.JComboBox") ) {
						//If the JComboBox's popup menu is not opened
						if (((JComboBox) comp).isPopupVisible() == false) {
							//If the component name is "fto" (fruit type options)
							if (compName == "fto") {
								//Have the program request focus on the entries JComboBox below
								entries.requestFocus();
							//Otherwise, If the component name is "entries"
							} else if (compName == "entries") {
								//Have the program request focus on the "okay" JButton below
								newDescription.requestFocus();
							}
						//If the popup menu is open
						} else {
				     		//If the next index does not equal/exceed the JComboBox's options' item count
							if (((JComboBox) comp).getSelectedIndex() + 1 < ((JComboBox) comp).getItemCount()) {
								//Set the currently selected index as the next one down the line
					  			((JComboBox) comp).setSelectedIndex(((JComboBox) comp).getSelectedIndex() + 1);
							}
						}
					//If the component name is "newDesc"
					} else if (compName == "newDesc") {
						//Have the program focus on the "back" ButtonLabel
						okay.requestFocus();
					//If the component name is "okay"
					} else if (compName == "okay") {
						//Have the program focus on the "back" ButtonLabel
						backButt.requestFocus();
					}
				}
			});
		}
		//Add a key binding to the parameter JComponent's input map for when the enter key is pressed
		comp.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0,false), "enterPressed");
		//Add to the parameter ButtonLabel's action map an abstract action that will perform when the enter key is pressed
		comp.getActionMap().put("enterPressed",new AbstractAction(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//If the component's class is that of ButtonLabel
				if (comp.getClass().toString().equals("class org.mypackage.jdbcDemo.graphics.ButtonLabel")) {
					//Call the buttonLabel's setClicked function
					((ButtonLabel) comp).setClicked();
				}
			}
		});
		//Add a key binding to the parameter JComponent's input map for when the enter key is released
		comp.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0,true), "enterReleased");
		//Add to the parameter ButtonLabel's action map an abstract action that will perform when the enter key is released
		comp.getActionMap().put("enterReleased",new AbstractAction(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//If the component name is that of "back"
				if (compName == "back") {
					//Call the buttonLabel's setReleased function
					((ButtonLabel) comp).setEnteredOrReleased();
					//Call the setupMenu function
					setupMainMenu();
				//If the component name is that of "fto" (fruit type options) or "entries
				} else if (compName == "fto" || compName == "entries") {
					//If the JComboBox's menu is not opened
				    if (((JComboBox) comp).isPopupVisible() == false) {
				       	//Show the popup menu of the JComboBox
				    	((JComboBox) comp).showPopup();
				   	//If the JComboBox's menu is opened
			      	} else {
				   		//Close the popup menu, selecting the currently selected index in the process
			      		((JComboBox) comp).hidePopup();
			    	}
				//If the component name is that of "tex"(textarea)
				} else if (compName == "okay") {
				    //Call the "editFruit" function
					editFruit();
				}
			}
		});
		//If the component name is not that of "fto" (fruit type options)
		if (compName != "fto") {
			//Add a key binding to the parameter JComponent's input map for when the up key is pressed
			comp.getInputMap().put(KeyStroke.getKeyStroke("UP"), "upPressed");
			//Add to the JComponent's action map an abstractAction for if the up key is pressed
			comp.getActionMap().put("upPressed", new AbstractAction(){
				@Override
				public void actionPerformed(ActionEvent e){
					//If the component name equals that of "back"
					if (compName == "back") {
						//Have the program focus on the "okay" JButton
						okay.requestFocus();
					//If the component name equals that of "okay"
					} else if (compName == "okay") {
						 //Have the program focus on the "entries" JComboBox
						newDescription.requestFocus();
					//If the component name equals that of "newDesc"
					} else if (compName == "newDesc") {
						 //Have the program focus on the "entries" JComboBox
						entries.requestFocus();
					//	If the component name equals that of "entries"
					} else if (compName == "entries") {
						//If the JComboBox's popup menu is not opened
						if (((JComboBox) comp).isPopupVisible() == false) {
							//Have the program request focus on the "fruitTypeOptions" JComboBox above
							fruitTypeOptions.requestFocus();
						//If the popup menu is open
						} else {
				     		//If the index before the current one is greater than/equal to 0
							if (((JComboBox) comp).getSelectedIndex() - 1 >= 0) {
								//Set the currently selected index as the next one down the line
					  			((JComboBox) comp).setSelectedIndex(((JComboBox) comp).getSelectedIndex() - 1);
							}
						}
					}
				}
			});
		}
	}
	
	public void renderButtons() {
		//Add the following action listener to the "back" button
		backButt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Call the setupMainMenu function
				setupMainMenu();
			}
		});
		//Pass the ButtonLabel to the setKeyBindings function
		setKeyBindings(backButt, "back");
		//Call the menu's renderButton function, passing it the "okay" button recently instantiated
		menu.renderButton(backButt);
	}
	
	public void setupMainMenu() {
		//Clear the fruits hashmap of all entries
		fruits.clear();
		//Call the remove function on the menu to remove the "back" button
		menu.remove(backButt);
		//Call the menu's cleansePage function to remove all children in all JPanels
		menu.cleansePage();
		//Call the menu's setupMainMenu function
		menu.setupMainMenu();
	}
	
	public void editFruit() {
		//Get an array of Strings out of the entries JComboBox's selected item using the Java split function, with ; and : as delimiters to separate
		String[] step1 = entries.getSelectedItem().toString().split("[;:]");
		//Get the second entry in the String array (ID), trim of all white space and convert to integer, for the ID to edit
		int editID = Integer.parseInt(step1[1].trim());
		//Call the DAO's editFruit function, passing it the ID to edit, the new description JTextField's value and the fruit type JComboBox's selected value
		menu.getDAO().editFruit(editID, newDescription.getText(), fruitTypeOptions.getSelectedItem().toString());
		//Create a String for replacing the edited table entry's equivelant in the local String fruits array
		String editedEntry = step1[0].trim() + ": " + step1[1].trim() + "; " + step1[2].trim() + ": " + newDescription.getText().trim();
		
		//Change the equivelant in the fruits array
		//fruits.set(editID - 1, editedEntry);
		fruits.put(editID, editedEntry);
		//Set the model of the entries JComboBox to update the changes
		entries.setModel(new DefaultComboBoxModel(fruits.values().toArray()));
		//Instantiate a new Confirmation Instance
		Confirmation conf = new Confirmation(menu, 3);
		//Add a WindowListener to the new Confirmation instance
		conf.addWindowListener(new WindowListener() {
			@Override
			public void windowOpened(WindowEvent e) {}
			@Override
			public void windowClosing(WindowEvent e) {}
			@Override
			public void windowClosed(WindowEvent e) {
				//As soon as the Confirmation window is closed, call the menu's toggleButtonUse function to enable JButtons and other JComponents
				menu.toggleButtonUse();
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
		//Call the menu's toggleButtonUse function to disable JButtons and other JComponents
		menu.toggleButtonUse();
	}
}


