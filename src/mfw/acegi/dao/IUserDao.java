package mfw.acegi.dao;

import java.util.List;
import mfw.acegi.dao.util.QueryPage;
import mfw.acegi.pojo.User;

public abstract interface IUserDao {
	public abstract User getById(Integer paramInteger);

	public abstract List getAll();

	public abstract void delete(User paramUser);

	public abstract void save(User paramUser);

	public abstract List getByPage(QueryPage paramQueryPage);

	public abstract User getUserByUserName(String paramString);
}

/*
 * Location: D:\Documents\certificate\WEB-INF\classes\ Qualified Name:
 * mfw.acegi.dao.IUserDao JD-Core Version: 0.6.2
 */