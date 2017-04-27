package org.mypackage.jdbcDemo.pages.pagetype;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.HashMap;

import javax.swing.AbstractAction;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JRootPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.UIManager;

import org.mypackage.jdbcDemo.dao.HibernateDaoImpl;
import org.mypackage.jdbcDemo.graphics.ButtonLabel;
import org.mypackage.jdbcDemo.input.InputHandler;
import org.mypackage.jdbcDemo.model.Fruit;
import org.mypackage.jdbcDemo.model.Orange;
import org.mypackage.jdbcDemo.pages.Menu;

//public class Options extends Menu {
public class Remove {
	
	//JButton object for the "okay" button
	private JButton okay;
	//ButtonLabel for the "back" button
	private ButtonLabel backButt;
	//Menu instance that this instance will reference
	private Menu menu;
	//JComboBoxes for the fruit type options and the entries for each fruit type
	private JComboBox fruitTypeOptions, entries;
	//Hashmap for the currently selected fruit type
	private HashMap<Integer, String> fruits;
	
	public Remove(Menu menu) {
		//Set the menu reference of this instance as the one passed to the parameter 
		this.menu = menu;
		//Instantiate the "okay" JButton
		okay = new JButton();
		//Set the "okay" button's contentAreaFilled criteria to allow recolouring
		okay.setContentAreaFilled(false);
		//Set the "okay" button's opacity to true
		okay.setOpaque(true);
		//Instantiate the "back" ButtonLabel
		backButt = new ButtonLabel("back", 90, 40);
	    //Instantiate the fruit type options JComboBox
		fruitTypeOptions = new JComboBox();
		//Instantiate the fruit type entries JComboBox
		entries = new JComboBox();
		//Instantiate the fruits hashmap
		fruits = new HashMap<>();
	}
	
	public void setText() {
		//String for HTML text, with size of 22 pixels, colour green and underlined
		String text = "<html><body>"
		+ "<h1 style='font-size:22px; color:#7CFC00; text-decoration:underline;'>Remove Fruit</h1>"		
		+ "</html></body>";
		//Pass the HTML String to the menu's setText function, with width 100 and height 150
		menu.setText(text, 100, 150);
	}
	
	public void renderContent() {
		//HTML String for the fruit type label text
		String text1 = "<html><body>"
		+ "<h1 style='font-size:12px; color:#FFFFFF;'>Select Fruit Type</h1>"		
		+ "</html></body>";
		//JLabel form = new JLabel();
		JLabel fruitText = new JLabel(text1);
		//For each Fruit instance in the menu's Hibernate Data Access Object implementation's getAllApples function (using the parameter value of "Apple" to retrieve only Apple instances)
		for (Fruit app : menu.getDAO().getAllFruitOfType("Apple")) {
			//Empty string for the description (as entries exceeding 25 characters will stretch the JComboBox, pushing the JLabel)
			String desc;
			//If the number of characters in the description exceed or equal 25
			if (app.getDescription().toString().trim().length() >= 25) {
				//Get a substring from the start of the description to 22 characters across, with a few "..." added at the end
				desc = app.getDescription().toString().substring(0, 22) + "...";
			//Otherwise
			} else {
				//Just set the description string with the whole value
				desc = app.getDescription();
			}
			//Add to the fruits hashmap the following, using the current Fruit instance's ID and description
			fruits.put(app.getID(), "ID: " + app.getID() + "; Description: " + desc);
		}
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
							//Add the following String to the fruits String array, using the ID and description values of the current Fruit instance
							fruits.put(app.getID(), "ID: " + app.getID() + "; Description: " + desc);
						}
					} else if (fruitTypeOptions.getSelectedItem() == "Orange") {
						//Clear the fruits hashmap of all entries
						fruits.clear();
						//For each Fruit (named ora) in the DAO's getAllFruitOfType return value (parameter is "Orange", so only Orange instances are returned)
						for (Fruit ora : menu.getDAO().getAllFruitOfType("Orange")) {
							//Empty string for the description (as entries exceeding 25 characters will stretch the JComboBox, pushing the JLabel)
							String desc = "";
							//If the number of characters in the description exceed or equal 25
							if (ora.getDescription().toString().trim().length() >= 25) {
								//Get a substring from the start of the description to 22 characters across, with a few "..." added at the end
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
					//Change the model of the entries JComboBox as that of the fruits hashmap converted to Array
					entries.setModel(new DefaultComboBoxModel(fruits.values().toArray()));
				}
			}
		});
		
		//Set the text of the "okay" button
		okay.setText("REMOVE FRUIT");
		//Add a mouse listener to the "okay" button
		okay.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				//Call the removeFruit function to add the inputted information as a new Fruit instance
				removeFruit();
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
		//Pass the "okay" JComboBox to the setKeyBindings function, along with appropriate String name
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
	
	public void setKeyBindings(JComponent comp, String compName) {
		//If the parameter String component does not equal that of the "back" button
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
								okay.requestFocus();
							}
						//If the popup menu is open
						} else {
				     		//If the next index does not equal/exceed the JComboBox's options' item count
							if (((JComboBox) comp).getSelectedIndex() + 1 < ((JComboBox) comp).getItemCount()) {
								//Set the currently selected index as the next one down the line
					  			((JComboBox) comp).setSelectedIndex(((JComboBox) comp).getSelectedIndex() + 1);
							}
						}
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
				    //Call the "removeFruit" function
					removeFruit();
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
	
	public void renderButton() {
		//Add the following action listener to the play button
		backButt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Call the setupMainMenu function of this instance
				setupMainMenu();
			}
		});
		//Pass the "back" ButtonLabel to the setKeyBindings function, along with relevant String name
		setKeyBindings(backButt, "back");
		//Call the menu's renderButton function, passing it the "okay" button to add to the lower JPanel
		menu.renderButton(backButt);
	}
	
	public void setupMainMenu() {
		//Clear the fruits hashmap of all entries
		fruits.clear();
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

