package mfw.acegi.pojo;


import java.io.Serializable;

import java.util.HashSet;

import java.util.Set;


public class Company
implements Serializable
{
	private Integer id;
	private String name;
	private Set persons = new HashSet(0);

	
	public Company()
	{
		}

	
	public Company(String name)
	{
		this.name = name;
		}

	
	public Company(String name, Set persons)
	{
		this.name = name;
		this.persons = persons;
		}

	
	public Integer getId()
	{
		return this.id;
		}

	
	public void setId(Integer id) {
		this.id = id;
		}

	
	public String getName() {
		return this.name;
		}

	
	public void setName(String name) {
		this.name = name;
		}

	
	public Set getPersons() {
		return this.persons;
		}

	
	public void setPersons(Set persons) {
		this.persons = persons;
		}
	
}

/*
 * Location: D:\Documents\certificate\WEB-INF\classes\ Qualified Name:
 * mfw.acegi.pojo.Company JD-Core Version: 0.6.2
 */