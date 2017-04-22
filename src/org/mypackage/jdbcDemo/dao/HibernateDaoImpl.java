package org.mypackage.jdbcDemo.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.mypackage.jdbcDemo.model.Fruit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class HibernateDaoImpl {

	@Autowired
	private SessionFactory sessFac;
	
	public int getFruitCount(String fruitType) {
		//Open a new session from the session factory
		Session sess = sessFac.openSession();
		//Create a query from the session object, which selects a count of all returned entries from the table indicated by the fruitType parameter value
		Query query = sess.createQuery("select count(*) from " + fruitType);
		//Integer for the result of the query execution
		int result = ((Long) query.uniqueResult()).intValue();
		//Close the session
		sess.close();
		//Return the integer result
		return result;
	}
	
	public String getFruitDescription(int fruitID, String fruitType) {
		//Open a new session from the session factory
		Session sess = sessFac.openSession();
		//Create a query from the session, which selects the description from the table indicated by the fruitType value where the IDs match
		Query query = sess.createQuery("select description from " + fruitType + " where id= " + fruitID);
		//String for the return value of the query executing
		String result = query.uniqueResult().toString();
		//Close the session
		sess.close();
		//Return the return value
		return result;
	}
	
	public Object getFruitForID(int fruitID, String fruitType) {
		//Open a new session from the session factory
		Session sess = sessFac.openSession();
		//
		Query query = sess.createQuery("from " + fruitType + " where id= " + fruitID);
		//
		Object result = query.uniqueResult();
		//Close the session
		sess.close();
		//
		return result;
	}
	
	public List<Fruit> getAllFruitOfType(String fruitType) {
		//Open a new session from the session factory
		Session sess = sessFac.openSession();
		//Create a query from the session, which returns all from the table indicated by the fruitType parameter value
		Query query = sess.createQuery("from " + fruitType);
		//Arraylist for the return value of the query executing
		List<Fruit> result = query.list();
		//Close the session
		sess.close();
		//Return the return value array
		return result;
	}
	
	public void insertFruit(Object newFruit) {
		//Open a new session from the session factory
		Session sess = sessFac.openSession();
		//Begin a new transaction from the new session
		Transaction tx = (Transaction) sess.beginTransaction();
		//Send the orange instance passed to the parameter into the session, where it will be added to the appropriate database
		sess.persist(newFruit);
		//Commit the transaction
		tx.commit();
		//Close the session
		sess.close();
	}
	
	public void removeFruit(int fruitID, String fruitType) {
		//Open a new session from the session factory
		Session sess = sessFac.openSession();
		//Create a query from the session, which deletes an entry from the table indicated by the fruitType parameter value, where the IDs match
		Query query = sess.createQuery("delete from " + fruitType + " where id= " + fruitID);
		//Execute the query
		query.executeUpdate();
		//Close the session
		sess.close();
	}
	
	public void editFruit(int fruitID, String newDescription, String fruitType) {
		//Open a new session from the session factory
		Session sess = sessFac.openSession();
		//Create a query from the session object, which edits an entry in the Table indicated by the fruitType String where the IDs match
		Query query = sess.createQuery("update " + fruitType + " set description = :description where id= " + fruitID);
		//Set the "description" parameter of the query as the new description
		query.setParameter("description", newDescription);
		//Execute the update query
		query.executeUpdate();
		//Close the session
		sess.close();
	}


	//SESSIONFACTORY FUNCTIONS
	
	public SessionFactory getSessionFactory() {
		//Return the session factory value
		return sessFac;
	}

	public void setSessionFactory(SessionFactory sessFac) {
		//Set the session factory value of this instance as the one passed to the parameter
		this.sessFac = sessFac;
	}
}
