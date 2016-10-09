package mfw.acegi.pojo;


import java.io.Serializable;

import java.util.HashSet;

import java.util.Set;


public class SubCategory
implements Serializable
{
	private Integer id;
	private Category category;
	private String descr;
	private Set books = new HashSet(0);

	
	public SubCategory()
	{
		}

	
	public SubCategory(Category category)
	{
		this.category = category;
		}

	
	public SubCategory(Category category, String descr, Set books)
	{
		this.category = category;
		this.descr = descr;
		this.books = books;
		}

	
	public Integer getId()
	{
		return this.id;
		}

	
	public void setId(Integer id) {
		this.id = id;
		}

	
	public Category getCategory() {
		return this.category;
		}

	
	public void setCategory(Category category) {
		this.category = category;
		}

	
	public String getDescr() {
		return this.descr;
		}

	
	public void setDescr(String descr) {
		this.descr = descr;
		}

	
	public Set getBooks() {
		return this.books;
		}

	
	public void setBooks(Set books) {
		this.books = books;
		}
	
}

/*
 * Location: D:\Documents\certificate\WEB-INF\classes\ Qualified Name:
 * mfw.acegi.pojo.SubCategory JD-Core Version: 0.6.2
 */