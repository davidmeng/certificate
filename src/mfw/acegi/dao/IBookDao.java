package mfw.acegi.dao;

import java.util.List;
import mfw.acegi.dao.util.QueryPage;
import mfw.acegi.pojo.Book;

public abstract interface IBookDao {
	public abstract Book getById(Integer paramInteger);

	public abstract List getAll();

	public abstract void delete(Book paramBook);

	public abstract void save(Book paramBook);

	public abstract List getByPage(QueryPage paramQueryPage);
}

/*
 * Location: D:\Documents\certificate\WEB-INF\classes\ Qualified Name:
 * mfw.acegi.dao.IBookDao JD-Core Version: 0.6.2
 */