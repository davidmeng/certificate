package mfw.acegi.dao;


import java.util.List;

import mfw.acegi.dao.util.QueryPage;

import mfw.acegi.pojo.BookType;


public class BookTypeDaoImpl extends BaseDaoImpl
implements IBookTypeDao
{
	@Override
	public BookType getById(Integer id)
	{
		return (BookType) super.get(BookType.class, id);
		}

	
	@Override
	public List getAll()
	{
		return super.getAll(BookType.class);
		}

	
	@Override
	public void delete(BookType type)
	{
		super.delele(type);
		}

	
	@Override
	public void save(BookType type)
	{
		super.save(type);
		}

	
	@Override
	public List getByPage(QueryPage page)
	{
		return super.getByPage(page, BookType.class);
		}
	
}

/*
 * Location: D:\Documents\certificate\WEB-INF\classes\ Qualified Name:
 * mfw.acegi.dao.BookTypeDaoImpl JD-Core Version: 0.6.2
 */