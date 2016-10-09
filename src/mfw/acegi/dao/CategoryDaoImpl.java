package mfw.acegi.dao;


import java.util.List;

import mfw.acegi.dao.util.QueryPage;

import mfw.acegi.pojo.Category;


public class CategoryDaoImpl extends BaseDaoImpl
implements ICategoryDao
{
	@Override
	public Category getById(Integer id)
	{
		return (Category) super.get(Category.class, id);
		}

	
	@Override
	public List getAll()
	{
		return super.getAll(Category.class);
		}

	
	@Override
	public void delete(Category type)
	{
		super.delele(type);
		}

	
	@Override
	public void save(Category type)
	{
		super.save(type);
		}

	
	@Override
	public List getByPage(QueryPage page)
	{
		return super.getByPage(page, Category.class);
		}
	
}

/*
 * Location: D:\Documents\certificate\WEB-INF\classes\ Qualified Name:
 * mfw.acegi.dao.CategoryDaoImpl JD-Core Version: 0.6.2
 */