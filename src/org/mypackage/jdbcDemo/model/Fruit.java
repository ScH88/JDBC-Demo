package org.mypackage.jdbcDemo.model;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class Fruit {
	//Integer for ID. Is mapped with the "Id" annotation
	@Id
	private int id;
	//String for the description of the fruit
	private String description;
	
	public Fruit(int id, String description){
		//Set the ID of this instance as the one passed to the parameter
		this.id = id;
		//Set the description of this instance's description as the one passed to the parameter
		this.description = description;
	}
	
	public Fruit() {}
	
	public void setID(int id) {
		//Set the ID of this instance as the one passed to the parameter
		this.id = id;
	}
	
	public int getID() {
		//Return this instance's ID value
		return id;
	}
	
	public void setDescription(String description) {
		//Set the description of this instance as the one passed to the parameter
		this.description = description;
	}
	
	public String getDescription() {
		//Return this instance's description String value
		return description;
	}
}
