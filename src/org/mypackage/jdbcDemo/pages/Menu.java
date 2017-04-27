package org.mypackage.jdbcDemo.pages;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;

import org.mypackage.jdbcDemo.dao.HibernateDaoImpl;
import org.mypackage.jdbcDemo.graphics.ContentPane;
import org.mypackage.jdbcDemo.input.InputHandler;
import org.mypackage.jdbcDemo.pages.pagetype.Add;
import org.mypackage.jdbcDemo.pages.pagetype.Confirmation;
import org.mypackage.jdbcDemo.pages.pagetype.Edit;
import org.mypackage.jdbcDemo.pages.pagetype.MainMenu;
import org.mypackage.jdbcDemo.pages.pagetype.Quit;
import org.mypackage.jdbcDemo.pages.pagetype.Remove;
import org.mypackage.jdbcDemo.pages.pagetype.SeeAll;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Menu extends JFrame {
	private static final long serialVersionUID = 1L;
	//Default width of window
	private int width = 800;
	//Default height of window
	private int height = 600;
	//Boolean for if the program is usable (for if the user clicks the top-right "X". Has default value of true
	private boolean toggleUse = true;
	//
	public ContentPane backPane;
	//JPanels for the North(title), Center(content), South(navigation buttons), east(padding) and west(padding) of the screen
	public JPanel panelNorth, panelCenter, panelSouth, panelEast, panelWest;
	//MainMenu page instance
	private MainMenu mainMenu;
	//SeeAll page instance
	private SeeAll seeAll;
	//Add page instance
	private Add add;
	//Remove page instance
	private Remove remove;
	//Edit page instance
	private Edit edit;
	//Central interface for Spring configuration. Is passed a reference to the "spring.xml" file in the resources folder
	ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
	//Hibernate Data Access Object (DAO) Implementation instance
	//Is instantiated by getting the "hibernateDaoImpl" bean from the Spring context
	HibernateDaoImpl dao = ctx.getBean("hibernateDaoImpl", HibernateDaoImpl.class);
	
	public Menu() {
		//Set the title of the frame by calling the superclass of the superclass (JFrame)
		super("Apples And Oranges");
		//Set the size of this instance
		setSize(width, height);
		//Set undecorated value of the Jframe to remove frame border and head (commented out for testing purposes)
		//setUndecorated(true);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		//Set the frame's location as centered to the screen
		setLocationRelativeTo(null);
		//Disable manual resizability of the frame
		setResizable(false);
		//Instantiate a local InputHandler variable
		InputHandler input = new InputHandler();
		//Add a key listener to the frame using the input handler
		addKeyListener(input);
		//Add a focus listener to the frame using the input handler (ensures the new program is automatically selected)
		addFocusListener(input);
		//Add a mouse listener to the frame using the input handler
		addMouseListener(input);
		//Add a mouse motion listener to the frame using the input handler
		addMouseMotionListener(input);
		//Instantiate the background ContentPane custom JPanel
		backPane = new ContentPane();
		//Set the ContentPane's layout as a BorderLayout
		backPane.setLayout(new BorderLayout());
		//Instantiate the north JPanel
		panelNorth = new JPanel();
		//Set the border of the north JPanel to provide padding to the content
		panelNorth.setBorder(new EmptyBorder(30, 30, 30, 30));
		//Have the JPanel be a transparent background
		panelNorth.setOpaque(false);
		//Instantiate the center JPanel
		panelCenter = new JPanel();
		//Set the border of the center JPanel to provide padding to the content
		panelCenter.setBorder(new EmptyBorder(20, 20, 20, 20));
		//Have the JPanel be a transparent background
		panelCenter.setOpaque(false);
		//Instantiate the south JPanel
		panelSouth = new JPanel();
		//Set the border of the south JPanel to provide padding to the content
		panelSouth.setBorder(new EmptyBorder(30, 30, 30, 30));
		//Have the JPanel be a transparent background
		panelSouth.setOpaque(false);
		//Instantiate the east JPanel
		panelEast = new JPanel();
		//Set the border of the east JPanel to provide padding to the content
		panelEast.setBorder(new EmptyBorder(50, 50, 50, 50));
		//Have the JPanel be a transparent background
		panelEast.setOpaque(false);
		//Instantiate the west JPanel
		panelWest = new JPanel();
		//Set the border of the west JPanel to provide padding to the content
		panelWest.setBorder(new EmptyBorder(50, 50, 50, 50));
		//Have the JPanel be a transparent background
		panelWest.setOpaque(false);
		//Add the ContentPane to the JFrame's content pane
		getContentPane().add(backPane);
		//Add the north JPanel to the ContentPane, giving it the BorderLayout's NORTH value
		backPane.add(panelNorth, BorderLayout.NORTH);
		//Add the center JPanel to the ContentPane, giving it the BorderLayout's CENTER value
		backPane.add(panelCenter, BorderLayout.CENTER);
		//Add the south JPanel to the ContentPane, giving it the BorderLayout's SOUTH value
		backPane.add(panelSouth, BorderLayout.SOUTH);
		//Add the east JPanel to the ContentPane, giving it the BorderLayout's EAST value
		backPane.add(panelEast, BorderLayout.EAST);
		//Add the west JPanel to the ContentPane, giving it the BorderLayout's WEST value
		backPane.add(panelWest, BorderLayout.WEST);
		//Instantiate a WindowListener for the event which the close button in the top header bar is clicked
		WindowListener exitListener = new WindowAdapter() {
			@Override
		    public void windowClosing(WindowEvent e) {	    
				if (toggleUse == true) {
					//Call this class' setupQuit function
					setupQuit();
				}
		    }
		};
		//Add the windowListener to the frame
		addWindowListener(exitListener);
		//Set the visibility of the new JFrame to true
		setVisible(true);
	}
	
	public void setText(String text, int tWidth, int tHeight) {
		//Instantiate a JLabel storing the text 
		JLabel textLabel = new JLabel(text);
		//Set the size of the JLabel
		textLabel.setSize(tWidth, tHeight);
		//Add the JLabel to the north JPanel
		panelNorth.add(textLabel);
	}
	
	public void renderButton(JButton butt){
		//Add the JButton passed to the parameter into the south JPanel
		panelSouth.add(butt);
	}
	
	public void cleansePage() {
		//Call the north JPanel's removeAll function to remove all children
		panelNorth.removeAll();
		//Call the center JPanel's removeAll function to remove all children
		panelCenter.removeAll();
		//Set the center JPanel's background to null, removing all colour
		panelCenter.setBackground(null);
		//Call the south JPanel's removeAll function to remove all children
		panelSouth.removeAll();
	}
	
	
	public void setupMainMenu(MainMenu m) {
		//If the main menu's value has not been instantiated
		if (mainMenu == null) {
			//Instantiate the main menu by passing it the parameter value
			mainMenu = m;
		}
		//Set the back ContentPane's image by calling it's paintBackground function and passing it 1
		backPane.paintBackground(1);
		//Set the opacity of the center JPanel back to false to remove background colour
		panelCenter.setOpaque(false);
		//Set the layout of the center JPanel as a new FlowLayout
		panelCenter.setLayout(new FlowLayout());
		//Call the main menu's setText function
		mainMenu.setText();
		//Call the main menu's renderButtons function
		mainMenu.renderButtons();
		//Call the updateJPanels to update the north, central and south JPanels
		updateJPanels();
	}
	
	public void setupMainMenu() {
		//If the main menu's value has not been instantiated
		if (mainMenu == null) {
			//Instantiate the main menu by passing it the parameter value
			mainMenu = new MainMenu();
		}
		//Change the background ContentPane by calling it's paintBackground function and passing it 1
		backPane.paintBackground(1);
		//Set the opacity of the center JPanel back to false to remove backgrounbd colour
		panelCenter.setOpaque(false);
		//Set the layout of the center JPanel as a new FlowLayout
		panelCenter.setLayout(new FlowLayout());
		//Call the main menu's setText function
		mainMenu.setText();
		//Call the main menu's renderButtons function
		mainMenu.renderButtons();
		//Call the updateJPanels to update the north, central and south JPanels
		updateJPanels();
	}
	
	public void setupSeeAll() {
		//If there is no seeAll page instance
		if (seeAll == null) {
			//Instantiate the seeAll page, passing it this instance for it to reference
			seeAll = new SeeAll(this);
		}
		//Change the image of the background ContentPane by calling it's paintBackground function and passing it 2
		backPane.paintBackground(2);
		//Set the center JPanel's opacity to true to enable background colour
		panelCenter.setOpaque(true);
		//Set the background colour of the center JPanel to gray
		panelCenter.setBackground(Color.GRAY);
		//Call the seeAll page's setText function
		seeAll.setText();
		//Call the seeAll page's renderButton function
		seeAll.renderButton();
		//Call the seeAll page's renderContent function
		seeAll.renderContent();
		//Call the updateJPanels to update the north, central and south JPanels
		updateJPanels();
	}
	
	public void setupAdd() {
		//If there is no Add page instantiated
		if (add == null) {
			//Instantiate the Add page, passing it this Menu instance for it to reference
			add = new Add(this);
		}
		//Change the image of the background ContentPane by calling it's paingBackground function and passing it 3
		backPane.paintBackground(3);
		//Set the layout of the center JPanel as a new GridBagLayout
		panelCenter.setLayout(new GridBagLayout());
		//Set the center JPanel's opacity to true to enable background colour
		panelCenter.setOpaque(true);
		//Set the background colour of the center JPanel to gray
		panelCenter.setBackground(Color.GRAY);
		//Call the Add page's setText function
		add.setText();
		//Call the Add page's renderButton function
		add.renderButton();
		//Call the Add page's renderContent function
		add.renderContent();
		//Call the updateJPanels to update the north, central and south JPanels
		updateJPanels();
	}
	
	public void setupRemove() {
		//If there is no Remove page instantiated
		if (remove == null) {
			//Instantiate the Remove page, passing it this instance for it to reference
			remove = new Remove(this);
		}
		//Change the image of the background ContentPane by calling it's paintBackground function and passing it 4
		backPane.paintBackground(4);
		//Set the layout of the center JPanel as a new GridLayout, with a single column and as many rows as needed
		panelCenter.setLayout(new GridBagLayout());
		//Set the center JPanel's opacity to true to enable background colour
		panelCenter.setOpaque(true);
		//Set the background of the center JPanel to gray
		panelCenter.setBackground(Color.GRAY);
		//Call the Remove page's setText function
		remove.setText();
		//Call the Remove page's renderButton function
		remove.renderButton();
		//Call the Remove page's renderContent function
		remove.renderContent();
		//Call the updateJPanels to update the north, central and south JPanels
		updateJPanels();
	}
	
	public void setupEdit() {
		//If the Edit page has not been instantiated
		if (edit == null) {
			//Instantiate the Edit page, passing it this instance for it to reference
			edit = new Edit(this);
		}
		//Change the image of the background ContentPane by calling it's paintBackground function and passing it 5
		backPane.paintBackground(5);
		//Set the layout of the center JPanel as a new GridLayout, with a single column and as many rows as needed
		panelCenter.setLayout(new GridBagLayout());
		//Set the center JPanel's opacity to true to enable background colour
		panelCenter.setOpaque(true);
		//Set the background colour of the center JPanel to gray
		panelCenter.setBackground(Color.GRAY);
		//Call the Edit page's setText function
		edit.setText();
		//Call the Edit page's renderButtons function
		edit.renderButtons();
		//Call the Edit page's renderContent function
		edit.renderContent();
		//Call the updateJPanels to update the north, central and south JPanels
		updateJPanels();
	}
	
	public void updateJPanels() {
		//Repaint the north JPanel to update the changes
		panelNorth.repaint();
		//Repaint the center JPanel to update the changes
		panelCenter.repaint();
		//Repaint the south JPanel to update the changes
		panelSouth.repaint();
	}
	
	public void setupQuit() {
		//Set up a new "Quit" instance
		Quit quit = new Quit();
		//Add a WindowListener to the Confirmation JDialog
		quit.addWindowListener(new WindowListener() {
			@Override
			public void windowOpened(WindowEvent e) {}
			@Override
			public void windowClosing(WindowEvent e) {}
			@Override
			public void windowClosed(WindowEvent e) {
				//If this confirmation JDialog has been closed, call the menu's toggleButtonUse function to enable JComponent use again
				toggleButtonUse();
				//Set this frame's setFocusableWindowState and setFocusable to true, allowing all content to be clicked again
				setFocusableWindowState(true);
				setFocusable(true);
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
		//Call the toggleButtonUse function to temporarily disable JComponent use in the south and center JPanels
		toggleButtonUse();
	}
	
	public void toggleButtonUse() {
		//Array of Component objects from the south JPanel, created from the return value of the getComponents function (returns all children)
		Component[] components = panelSouth.getComponents();
		//Array of Component objects from the center JPanel, created from the return value of the getComponents function (returns all children)
		Component[] components2 = panelCenter.getComponents();
		//For each Component in the south panel's component array
		for (int i = 0; i < components.length; i++) {
		    //If the current component is a JButton or a ButtonLabel custom class
			if (components[i].getClass().getName().toString().equals("javax.swing.JButton")
				|| components[i].getClass().getName().toString().equals("org.mypackage.jdbcDemo.graphics.ButtonLabel")){   
				//If the current button is enabled
				if (components[i].isEnabled() == true) {
		        	//Disable the button
		        	components[i].setEnabled(false);
		        //Otherwise, if the current button is disabled
		        } else if (components[i].isEnabled() == false) {
		        	//Enable it again
		        	components[i].setEnabled(true);
		        }
		    } 
		}
		//For each Component in the center panel's component array
		for (int i = 0; i < components2.length; i++) {
			//If the current component is a JButton, JTextArea, JComboBox or JTextField
			if (components2[i].getClass().getName().toString().equals("javax.swing.JButton")
				|| components2[i].getClass().getName().toString().equals("javax.swing.JTextArea")
				|| components2[i].getClass().getName().toString().equals("javax.swing.JComboBox")
				|| components2[i].getClass().getName().toString().equals("javax.swing.JTextField")
			){   
				//If the current component is enabled
				if (components2[i].isEnabled() == true) {
		        	//Disable the component
		        	components2[i].setEnabled(false);
		        //Otherwise, if the current component is disabled
		        } else if (components2[i].isEnabled() == false) {
		        	//Enable it again
		        	components2[i].setEnabled(true);
		        }
		    }
		}
		//If the toggleUse boolean is true
		if (toggleUse == true) {
			//Set the toggleUse value to false
			toggleUse = false;
		//Otherwise, if the toggleUse value is false
		} else if (toggleUse == false) {
			//Set the toggleUse value to true
			toggleUse = true;
		}
	}
	
	public void addComponent(JComponent comp, GridBagConstraints gbc) {
		//Add the JComponent to the center JPanel
		panelCenter.add(comp, gbc);
	}
	
	//Alternate addComponent for non-GridBagLayout pages
	public void addComponent(JComponent comp) {
		//Add the JComponent to the center JPanel
		panelCenter.add(comp);
	}
	
	public void modifyRootKeyBindings(String type) {
		//Get the root pane (i.e. the main JFrame)
		JRootPane rp = getRootPane();
		//If the type passed to the parameter equals "LeftRight"
		if (type == "LeftRight") {
			//Add to the root pane's input map a keystroke for the left key
			rp.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("LEFT"), "leftfocusPane");
			//Add to the root pane's input map a keystroke for the right key
			rp.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("RIGHT"), "focusPane");
			//Clear the root pane's key binding for the up key
			rp.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("UP"), "");
			//Clear the root pane's key binding for the down key
			rp.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("DOWN"), "");
		//If the type passed to the parameter equals "UpDown"
		} else if (type == "UpDown") {
			//Clear the root pane's key binding for the left key
			rp.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("LEFT"), "");
			//Clear the root pane's key binding for the right key
			rp.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("RIGHT"), "");
			//Add to the root pane's input map a keystroke for the up key
			rp.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("UP"), "focusPane");
			//Add to the root pane's input map a keystroke for the down key
			rp.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("DOWN"), "focusPane");
		}
	}
	
	//GETTER FUNCTIONS
	
	public int getWidth() {
		//Return the width of the JFrame
		return width;
	}
	
	public int getHeight() {
		//Return the height of the JFrame
		return height;
	}
	
	public HibernateDaoImpl getDAO() {
		//Return the Data Access Object implementation instance attached to this instance
		return dao;
	}
}
