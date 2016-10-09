package mfw.acegi.biz;


import mfw.acegi.dao.IUserDao;

import mfw.acegi.dao.IUserRoleDao;

import mfw.acegi.pojo.Role;

import mfw.acegi.pojo.User;

import mfw.acegi.pojo.UserRole;


public class UserBizImpl
implements IUserBiz
{
	IUserDao userDao;
	IUserRoleDao userRoleDao;

	
	@Override
	public User getUserById(Integer id)
	{
		return this.userDao.getById(id);
		}

	
	@Override
	public User getUserByUserName(String userName)
	{
		User user = this.userDao.getUserByUserName(userName);
		
		return user;
		}

	
	@Override
	public void saveUser(User user)
	{
		Role r = new Role();
		UserRole ur = new UserRole();
		r.setId(Integer.valueOf(Integer.parseInt("3")));
		ur.setRole(r);
		
		this.userDao.save(user);
		
		ur.setUser(user);
		this.userRoleDao.save(ur);
		}

	
	public IUserDao getUserDao() {
		return this.userDao;
		}

	
	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
		}

	
	public IUserRoleDao getUserRoleDao() {
		return this.userRoleDao;
		}

	
	public void setUserRoleDao(IUserRoleDao userRoleDao) {
		this.userRoleDao = userRoleDao;
		}
	
}

/*
 * Location: D:\Documents\certificate\WEB-INF\classes\ Qualified Name:
 * mfw.acegi.biz.UserBizImpl JD-Core Version: 0.6.2
 */