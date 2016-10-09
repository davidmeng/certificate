package mfw.acegi.dao;


import java.util.List;

import mfw.acegi.dao.util.QueryPage;

import mfw.acegi.pojo.User;

import org.hibernate.criterion.Restrictions;


public class UserDaoImpl extends BaseDaoImpl
implements IUserDao
{
	@Override
	public User getById(Integer id)
	{
		return (User) super.get(User.class, id);
		}

	
	@Override
	public List getAll()
	{
		return super.getAll(User.class);
		}

	
	@Override
	public void delete(User type)
	{
		super.delele(type);
		}

	
	@Override
	public void save(User type)
	{
		super.save(type);
		}

	
	@Override
	public List getByPage(QueryPage page)
	{
		return super.getByPage(page, User.class);
		}

	
	@Override
	public User getUserByUserName(String userName)
	{
		List list = super.getSession().createCriteria(User.class)
		.add(Restrictions.eq("userName", userName)).list();
		
		if ((list != null) && (list.size() > 0))
		{
			return (User) list.get(0);
			}
		
		return null;
		}
	
}

/*
 * Location: D:\Documents\certificate\WEB-INF\classes\ Qualified Name:
 * mfw.acegi.dao.UserDaoImpl JD-Core Version: 0.6.2
 */