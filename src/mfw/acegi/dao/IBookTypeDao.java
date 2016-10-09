package mfw.acegi.dao;

import java.util.List;
import mfw.acegi.dao.util.QueryPage;
import mfw.acegi.pojo.BookType;

public abstract interface IBookTypeDao {
	public abstract BookType getById(Integer paramInteger);

	public abstract List getAll();

	public abstract void delete(BookType paramBookType);

	public abstract void save(BookType paramBookType);

	public abstract List getByPage(QueryPage paramQueryPage);
}

/*
 * Location: D:\Documents\certificate\WEB-INF\classes\ Qualified Name:
 * mfw.acegi.dao.IBookTypeDao JD-Core Version: 0.6.2
 */