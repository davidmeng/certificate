package mfw.acegi.dao;


import java.util.List;

import mfw.acegi.dao.util.QueryPage;

import mfw.acegi.pojo.UserRole;


public class UserRoleDaoImpl extends BaseDaoImpl
implements IUserRoleDao
{
	@Override
	public UserRole getById(Integer id)
	{
		return (UserRole) super.get(UserRole.class, id);
		}

	
	@Override
	public List getAll()
	{
		return super.getAll(UserRole.class);
		}

	
	@Override
	public void delete(UserRole type)
	{
		super.delele(type);
		}

	
	@Override
	public void save(UserRole type)
	{
		super.save(type);
		}

	
	@Override
	public List getByPage(QueryPage page)
	{
		return super.getByPage(page, UserRole.class);
		}
	
}

/*
 * Location: D:\Documents\certificate\WEB-INF\classes\ Qualified Name:
 * mfw.acegi.dao.UserRoleDaoImpl JD-Core Version: 0.6.2
 */