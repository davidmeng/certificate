package mfw.acegi.pojo;


import java.io.Serializable;

import java.util.HashSet;

import java.util.Set;


public class BookType
implements Serializable
{
	private Integer id;
	private String descr;
	private Set books = new HashSet(0);

	
	public BookType()
	{
		}

	
	public BookType(String descr)
	{
		this.descr = descr;
		}

	
	public BookType(String descr, Set books)
	{
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
 * mfw.acegi.pojo.BookType JD-Core Version: 0.6.2
 */