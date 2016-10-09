package mfw.acegi.pojo;


import java.io.Serializable;

import java.util.HashSet;

import java.util.Set;


public class Category
implements Serializable
{
	private Integer id;
	private String descr;
	private Set subCategories = new HashSet(0);

	
	public Category()
	{
		}

	
	public Category(String descr)
	{
		this.descr = descr;
		}

	
	public Category(String descr, Set subCategories)
	{
		this.descr = descr;
		this.subCategories = subCategories;
		}

	
	public Integer getId()
	{
		return this.id;
		}

	
	public void setId(Integer id) {
		this.id = id;
		}

	
	public String getDescr() {
		return this.descr;
		}

	
	public void setDescr(String descr) {
		this.descr = descr;
		}

	
	public Set getSubCategories() {
		return this.subCategories;
		}

	
	public void setSubCategories(Set subCategories) {
		this.subCategories = subCategories;
		}
	
}

/*
 * Location: D:\Documents\certificate\WEB-INF\classes\ Qualified Name:
 * mfw.acegi.pojo.Category JD-Core Version: 0.6.2
 */