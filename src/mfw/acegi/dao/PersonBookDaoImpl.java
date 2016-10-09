package mfw.acegi.dao;


import java.util.List;

import mfw.acegi.dao.util.QueryPage;

import mfw.acegi.pojo.PersonBook;


public class PersonBookDaoImpl extends BaseDaoImpl
implements IPersonBookDao
{
	@Override
	public PersonBook getById(Integer id)
	{
		return (PersonBook) super.get(PersonBook.class, id);
		}

	
	@Override
	public List getAll()
	{
		return super.getAll(PersonBook.class);
		}

	
	@Override
	public void delete(PersonBook type)
	{
		super.delele(type);
		}

	
	@Override
	public void save(PersonBook type)
	{
		super.save(type);
		}

	
	@Override
	public List getByPage(QueryPage page)
	{
		return super.getByPage(page, PersonBook.class);
		}
	
}

/*
 * Location: D:\Documents\certificate\WEB-INF\classes\ Qualified Name:
 * mfw.acegi.dao.PersonBookDaoImpl JD-Core Version: 0.6.2
 */