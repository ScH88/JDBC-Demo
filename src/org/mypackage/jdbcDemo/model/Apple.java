package org.mypackage.jdbcDemo.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Apple")
public class Apple extends Fruit {	
	
	public Apple(int id, String description){
		//Call the superclass' constructor, passing it the ID and description in the parameter
		super(id, description);
	}
	
	public Apple(){}

	public void setID(int id) {
		//Call the superclass' setID function, passing it the ID passed to the parameter
		super.setID(id);
	}
	
	public int getID() {
		//Return the return value of this instance's superclass' getID function
		return super.getID();
	}
	
	public void setDescription(String description) {
		//Call the superclass' setDescription function, passing it the description String passed to the parameter
		super.setDescription(description);
	}
	
	public String getDescription() {
		//Return the return value of this instance's superclass' getDescription function
		return super.getDescription();
	}
}