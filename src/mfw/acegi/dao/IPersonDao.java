package mfw.acegi.dao;

import java.util.List;

import mfw.acegi.dao.util.QueryPage;
import mfw.acegi.form.CertificateForm;
import mfw.acegi.pojo.Person;

public abstract interface IPersonDao {
	public abstract Person getById(Integer paramInteger);

	public abstract List getAll();

	public abstract void delete(Person paramPerson);

	public abstract void save(Person paramPerson);

	public abstract List getByPage(QueryPage paramQueryPage);

	public abstract Person getByNameAndId(String paramString) throws Exception;

	public abstract List getAllBooksPersonListBy(Integer paramInteger, String paramString);

	public abstract List getPersonListBy(CertificateForm certificationForm);
}

/*
 * Location: D:\Documents\certificate\WEB-INF\classes\ Qualified Name:
 * mfw.acegi.dao.IPersonDao JD-Core Version: 0.6.2
 */