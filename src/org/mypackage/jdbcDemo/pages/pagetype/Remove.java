package org.mypackage.jdbcDemo.pages.pagetype;

import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.HashMap;

import javax.swing.AbstractAction;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JRootPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;

import org.mypackage.jdbcDemo.dao.HibernateDaoImpl;
import org.mypackage.jdbcDemo.input.InputHandler;
import org.mypackage.jdbcDemo.model.Fruit;
import org.mypackage.jdbcDemo.model.Orange;
import org.mypackage.jdbcDemo.pages.Menu;

//public class Options extends Menu {
public class Remove {
	
	//JButton objects for the "OK" button
	private JButton backButt, okay;
	//Menu instance that this instance will reference
	private Menu menu;
	//JComboBoxes for the fruit type options and the entries for each fruit type
	private JComboBox fruitTypeOptions, entries;
	//Hashmap for the currently selected fruit type
	private HashMap<Integer, String> fruits;
	
	public Remove(Menu menu) {
		//Set the menu reference of this instance as the one passed to the parameter 
		this.menu = menu;
		//Instantiate the "okay" button
		okay = new JButton();
		//Instantiate the "back" button
		backButt = new JButton();
	    //Instantiate the fruit type options JComboBox
		fruitTypeOptions = new JComboBox();
		//Instantiate the fruit type entries JComboBox
		entries = new JComboBox();
		//Instantiate the fruits hashmap
		fruits = new HashMap<>();
	}
	
	public void setText() {
		//String for HTML text, with size of 14 pixels and underlined
		String text = "<html><body>"
		+ "<h1 style='font-size:14px; text-decoration:underline;'>Remove Fruit</h1>"		
		+ "</html></body>";
		//Pass the HTML String to the menu's setText function, with width 100 and height 150
		menu.setText(text, 100, 150);
	}
	
	public void renderContent() {
		
		//For each Fruit instance in the menu's Hibernate Data Access Object implementation's getAllApples function (using the parameter value of "Apple" to retrieve only Apple instances)
		for (Fruit app : menu.getDAO().getAllFruitOfType("Apple")) {
			//Add to the fruits hashmap the following, using the current Fruit instance's ID and description
			fruits.put(app.getID(), "ID: " + app.getID() + "; Description: " + app.getDescription());
		}
		//HTML String for the fruit type label text
		String text1 = "<html><body>"
		+ "<h1 style='font-size:12px; color:#FFFFFF;'>Select Fruit Type</h1>"		
		+ "</html></body>";
		//JLabel form = new JLabel();
		JLabel fruitText = new JLabel(text1);
		//String array of options for the upcoming fruit type JComboBox
		String[] optionText = {"Apple", "Orange"};
		//Instantiate the fruit type JComboBox using the previous String array
		fruitTypeOptions.setModel(new DefaultComboBoxModel(optionText));	
		//HTML String for the fruit description label text
		String text2 = "<html><body>"
		+ "<h1 style='font-size:12px; color:#FFFFFF;'>Current Entries</h1>"		
		+ "</html></body>";
		JLabel entriesText = new JLabel(text2);
		//Instantiate the fruit description JComboBox, using the fruits HashMap as converted to Array
		entries.setModel(new DefaultComboBoxModel(fruits.values().toArray()));
		//Add and item listener to the fruit type options JComboBox
		fruitTypeOptions.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				//If the ItemEvent's state change is that of SELECTED
				if (e.getStateChange() == ItemEvent.SELECTED) {
					//If the fruit type JComboBox's selected item equals "Apple"
					if (fruitTypeOptions.getSelectedItem() == "Apple") {
						//Clear the fruits hashmap of all entries
						fruits.clear();
						//For each Fruit (named app) in the DAO's getAllFruitOfType return value (parameter is "Apple", so only Apple instances are returned)
						for (Fruit app : menu.getDAO().getAllFruitOfType("Apple")) {
							//Add the following String to the fruits String array, using the ID and description values of the current Fruit instance
							//fruits.add("ID: " + app.getID() + "; Description: " + app.getDescription());
							fruits.put(app.getID(), "ID: " + app.getID() + "; Description: " + app.getDescription());
						}
					} else if (fruitTypeOptions.getSelectedItem() == "Orange") {
						//Clear the fruits hashmap of all entries
						fruits.clear();
						//For each Fruit (named ora) in the DAO's getAllFruitOfType return value (parameter is "Orange", so only Orange instances are returned)
						for (Fruit ora : menu.getDAO().getAllFruitOfType("Orange")) {
							//Add the following String to the fruits String array, using the ID and description values of the current Fruit instance
							//fruits.add("ID: " + ora.getID() + "; Description: " + ora.getDescription());
							fruits.put(ora.getID(), "ID: " + ora.getID() + "; Description: " + ora.getDescription());
						}
					}
					//Change the model of the entries JComboBox as that of the fruits hashmap converted to Array
					entries.setModel(new DefaultComboBoxModel(fruits.values().toArray()));
				}
			}
		});
		
		//Set the text of the "okay" button
		okay.setText("REMOVE FRUIT");
		//Add a new action listener to the "okay" button
		okay.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//Call the removeFruit function to remove the selected fruit
				removeFruit();
			}
		});
		//Add to the "okay" button's input map a keystroke for the enter key
		okay.getInputMap().put(KeyStroke.getKeyStroke("ENTER"), "enterPressed");
		//Add to the "okay" button's action map an abstractAction for if the enter key is pressed
		okay.getActionMap().put("enterPressed", new AbstractAction(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//Call the removeFruit function to remove the selected fruit
				removeFruit();
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
				entries.requestFocus();
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
	        		//Have the program request focus on the "entries" JComboBox below
	        		entries.requestFocus();
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
		//Add to the "entries" JComboBox input map a keystroke for the enter key
		entries.getInputMap().put(KeyStroke.getKeyStroke("ENTER"), "enterPressed");
		//Add to the "entries" JComboBox action map an abstractAction for if the enter key is pressed
		entries.getActionMap().put("enterPressed", new AbstractAction(){
	       @Override
	        public void actionPerformed(ActionEvent e){
	    	 //If the JComboBox's menu is not opened
	        	if (entries.isPopupVisible() == false) {
	        		//Show the popup menu of the JComboBox
	        		entries.showPopup();
	        	//If the JComboBox's menu is opened
	        	} else {
	        		//Close the popup menu, selecting the currently selected index in the process
	        		entries.hidePopup();
	        	}
	       }
		});
		//Add to the "entries" JComboBox input map a keystroke for the down key
		entries.getInputMap().put(KeyStroke.getKeyStroke("DOWN"), "downPressed");
		//Add to the "entries" JComboBox action map an abstractAction for if the down key is pressed
		entries.getActionMap().put("downPressed", new AbstractAction(){
			@Override
			public void actionPerformed(ActionEvent e) {
				 //If the JComboBox's popup menu is not opened
	        	if (entries.isPopupVisible() == false) {
	        		//Have the program request focus on the "okay" button below
	        		okay.requestFocus();
	        	//If the popup menu is open
	        	} else {
	        		//If the next index does not equal/exceed the JComboBox's options' item count
	        		if (entries.getSelectedIndex() + 1 < entries.getItemCount()) {
	        			//Set the currently selected index as the next one down the line
	        			entries.setSelectedIndex(entries.getSelectedIndex() + 1);
	        		}
	        	}
			}
		});
		//Add to the "entries" JComboBox input map a keystroke for the up key
		entries.getInputMap().put(KeyStroke.getKeyStroke("UP"), "upPressed");
		//Add to the "entries" JComboBox action map an abstractAction for if the up key is pressed
		entries.getActionMap().put("upPressed", new AbstractAction(){
			@Override
			public void actionPerformed(ActionEvent e) {
				 //If the JComboBox's popup menu is not opened
			   	 if (entries.isPopupVisible() == false) {
			     		//Have the program request focus on the "fruitTypeOptions" JComboBox above
			       		fruitTypeOptions.requestFocus();
			        	//If the popup menu is open
			      } else {
			        	//If the next index does not preceed the 0 start mark
			        	if (entries.getSelectedIndex() - 1 >= 0) {
			        		//Set the currently selected index as the next one down the line
			        		entries.setSelectedIndex(entries.getSelectedIndex() - 1);
			        	}
			      }
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
		//Pass the fruit text JLabel to the menu's addComponent function to add to it's center JPanel
		menu.addComponent(fruitText, gbc);
		//Set the current grid x position to 1
		gbc.gridx = 1;
		//Have the gbc fill the current cell without any whitespace (for the JComboBox to be longer)
		gbc.fill = GridBagConstraints.HORIZONTAL;
		//Pass the fruit type JComboBox to the menu's addComponent function to add to it's center JPanel
		menu.addComponent(fruitTypeOptions, gbc);
		//Reset the gbc's fill to none
		gbc.fill = GridBagConstraints.NONE;
		//Set the current grid x position back to 0
		gbc.gridx = 0;
		//Set the current grid y position to 2 rows down
		gbc.gridy = 2;
		//Pass the fruit entries JLabel to the menu's addComponent function to add to it's center JPanel
		menu.addComponent(entriesText, gbc);
		//Set the current grid x position to 1
		gbc.gridx = 1;
		//Have the gbc fill the current cell without any whitespace (for the JComboBox to be longer)
		gbc.fill = GridBagConstraints.HORIZONTAL;
		//Pass the fruit entries JComboBox to the menu's addComponent function to add to it's center JPanel
		menu.addComponent(entries, gbc);
		//Set the current grid x position back to 0
		gbc.gridx = 0;
		//Set the current grid y position to 4
		gbc.gridy = 3;
		//Set the current grid width to 5 columns
		gbc.gridwidth = 5;
		//Set the gbc's fill to both horizontal and vertical of cell
		gbc.fill = GridBagConstraints.BOTH;
		//Pass the "okay" button to the menu's addComponent function to add to it's center JPanel
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
	
	public void renderButton() {
		//Set the text of the "back" button
		backButt.setText("BACK");
		//Add the following action listener to the play button
		backButt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Call the setupMainMenu function of this instance
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
		//Call the menu's renderButton function, passing it the "okay" button to add to the lower JPanel
		menu.renderButton(backButt);
	}
	
	public void setupMainMenu() {
		//pass the "okay" button for the menu to remove
		menu.remove(okay);
		//Call the menu's cleansePage function to cleanse all JPanels of children
		menu.cleansePage();
		//Call the menu's setupMainMenu to set up the main menu
		menu.setupMainMenu();
	}
	
	private void removeFruit() {
		//Get an array of Strings out of the entries JComboBox's selected item using the Java split function, with ; and : as delimiters to separate
		String[] step1 = entries.getSelectedItem().toString().split("[;:]");
		//Get the second entry in the String array (ID), trim of all white space and convert to integer, for the ID to delete
		int deleteID = Integer.parseInt(step1[1].trim());
		//Call the DAO's removeFruit function, passing it the ID to delete and the fruit type JComboBox's String value as the fruit type to remove from
		menu.getDAO().removeFruit(deleteID, fruitTypeOptions.getSelectedItem().toString());
		//Remove the same value from the current fruits hashmap
		fruits.remove(deleteID);
		//Set the model of the fruit entries JComboBox as the fruits String converted to Array to update the changes
		entries.setModel(new DefaultComboBoxModel(fruits.values().toArray()));
		//Instantiate a new Confirmation instance, passing it the menu and an integer type of 2 
		Confirmation conf = new Confirmation(menu, 2);
		//Add a new WindowListener to the confirmation instance
		conf.addWindowListener(new WindowListener() {
			@Override
			public void windowOpened(WindowEvent e) {}
			@Override
			public void windowClosing(WindowEvent e) {}
			@Override
			public void windowClosed(WindowEvent e) {
				//As soon as the Confirmation window is closed, Call the menu's toggleButtonUse function to enable use of buttons and other JComponents
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
		//Call the menu's toggleButtonUse function to disable use of buttons and other JComponents
		menu.toggleButtonUse();
	}
}

