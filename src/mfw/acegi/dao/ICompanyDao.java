package mfw.acegi.dao;

import java.util.List;
import mfw.acegi.dao.util.QueryPage;
import mfw.acegi.pojo.Company;

public abstract interface ICompanyDao {
	public abstract Company getById(Integer paramInteger);

	public abstract List getAll();

	public abstract void delete(Company paramCompany);

	public abstract void save(Company paramCompany);

	public abstract List getByPage(QueryPage paramQueryPage);

	public abstract Company getByNameAndId(String paramString) throws Exception;
}

/*
 * Location: D:\Documents\certificate\WEB-INF\classes\ Qualified Name:
 * mfw.acegi.dao.ICompanyDao JD-Core Version: 0.6.2
 */