package org.mypackage.jdbcDemo;

import org.mypackage.jdbcDemo.pages.pagetype.MainMenu;

public class jdbcDemo {
	
	//The Main function. Is first function to be called upon system startup
	public static void main(String[] args) {
		//Create a new MainMenu (extends Menu(itself extends JFrame)) instance
		new MainMenu();
	}
}
