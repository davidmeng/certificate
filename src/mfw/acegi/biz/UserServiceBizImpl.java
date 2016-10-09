package mfw.acegi.biz;


import mfw.acegi.dao.IUserDao;

import mfw.acegi.pojo.User;


public class UserServiceBizImpl
implements IUserServiceBiz
{
	IUserDao userDao;

	
	@Override
	public User getUserByUserName(String userName)
	{
		User user = this.userDao.getUserByUserName(userName);
		
		return user;
		}

	
	public IUserDao getUserDao()
	{
		return this.userDao;
		}

	
	public void setUserDao(IUserDao userDao)
	{
		this.userDao = userDao;
		}
	
}

/*
 * Location: D:\Documents\certificate\WEB-INF\classes\ Qualified Name:
 * mfw.acegi.biz.UserServiceBizImpl JD-Core Version: 0.6.2
 */