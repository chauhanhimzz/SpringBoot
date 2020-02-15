package io.java.springdata.topic;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Topic {

	@Id
	//@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;
	private String name;
	private String descpription;
	
	public Topic() {
		
	}
	
	public Topic(String id, String name, String descpription) {
		super();
		this.id = id;
		this.name = name;
		this.descpription = descpription;
	}
	
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescpription() {
		return descpription;
	}
	public void setDescpription(String descpription) {
		this.descpription = descpription;
	}
	
}
