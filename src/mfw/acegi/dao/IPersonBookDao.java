package mfw.acegi.dao;

import java.util.List;
import mfw.acegi.dao.util.QueryPage;
import mfw.acegi.pojo.PersonBook;

public abstract interface IPersonBookDao {
	public abstract PersonBook getById(Integer paramInteger);

	public abstract List getAll();

	public abstract void delete(PersonBook paramPersonBook);

	public abstract void save(PersonBook paramPersonBook);

	public abstract List getByPage(QueryPage paramQueryPage);
}

/*
 * Location: D:\Documents\certificate\WEB-INF\classes\ Qualified Name:
 * mfw.acegi.dao.IPersonBookDao JD-Core Version: 0.6.2
 */