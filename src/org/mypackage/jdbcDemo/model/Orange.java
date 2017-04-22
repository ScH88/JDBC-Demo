package org.mypackage.jdbcDemo.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="Orange")
public class Orange extends Fruit{
	
	public Orange(int id, String description){
		//Call the superclass constructor, passing it the ID and description
		super(id, description);
	}
	
	public Orange(){}

	public void setID(int id) {
		//Call the setID function of the superclass, passing it the ID in the parameter
		super.setID(id);
	}
	
	public int getID() {
		//Return the return value of the superclass' getID function of this instance
		return super.getID();
	}
		
	public void setDescription(String description) {
		//Call the setDescription of this instance's superclass, passing it the description in the parameter
		super.setDescription(description);
	}
		
	public String getDescription() {
		//Return the return value of the superclass' getDescription function of this instance
		return super.getDescription();
	}
}