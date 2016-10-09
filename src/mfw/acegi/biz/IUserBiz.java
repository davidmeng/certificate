package mfw.acegi.biz;

import mfw.acegi.pojo.User;

public abstract interface IUserBiz {
	public abstract User getUserById(Integer paramInteger);

	public abstract void saveUser(User paramUser);

	public abstract User getUserByUserName(String paramString);
}

/*
 * Location: D:\Documents\certificate\WEB-INF\classes\ Qualified Name:
 * mfw.acegi.biz.IUserBiz JD-Core Version: 0.6.2
 */