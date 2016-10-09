package mfw.acegi.dao;


import java.util.List;

import mfw.acegi.dao.util.QueryPage;

import mfw.acegi.pojo.Book;


public class BookDaoImpl extends BaseDaoImpl
implements IBookDao
{
	@Override
	public Book getById(Integer id)
	{
		return (Book) super.get(Book.class, id);
		}

	
	@Override
	public List getAll()
	{
		return super.getAll(Book.class);
		}

	
	@Override
	public void delete(Book type)
	{
		super.delele(type);
		}

	
	@Override
	public void save(Book type)
	{
		super.save(type);
		}

	
	@Override
	public List getByPage(QueryPage page)
	{
		return super.getByPage(page, Book.class);
		}
	
}

/*
 * Location: D:\Documents\certificate\WEB-INF\classes\ Qualified Name:
 * mfw.acegi.dao.BookDaoImpl JD-Core Version: 0.6.2
 */