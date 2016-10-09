package mfw.acegi.dao;

import java.util.List;
import mfw.acegi.dao.util.QueryPage;
import mfw.acegi.pojo.UserRole;

public abstract interface IUserRoleDao {
	public abstract UserRole getById(Integer paramInteger);

	public abstract List getAll();

	public abstract void delete(UserRole paramUserRole);

	public abstract void save(UserRole paramUserRole);

	public abstract List getByPage(QueryPage paramQueryPage);
}

/*
 * Location: D:\Documents\certificate\WEB-INF\classes\ Qualified Name:
 * mfw.acegi.dao.IUserRoleDao JD-Core Version: 0.6.2
 */