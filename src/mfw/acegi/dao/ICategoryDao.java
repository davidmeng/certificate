package mfw.acegi.dao;

import java.util.List;
import mfw.acegi.dao.util.QueryPage;
import mfw.acegi.pojo.Category;

public abstract interface ICategoryDao {
	public abstract Category getById(Integer paramInteger);

	public abstract List getAll();

	public abstract void delete(Category paramCategory);

	public abstract void save(Category paramCategory);

	public abstract List getByPage(QueryPage paramQueryPage);
}

/*
 * Location: D:\Documents\certificate\WEB-INF\classes\ Qualified Name:
 * mfw.acegi.dao.ICategoryDao JD-Core Version: 0.6.2
 */