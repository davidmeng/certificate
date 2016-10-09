package mfw.acegi.dao;


import java.util.List;


import mfw.acegi.dao.util.QueryPage;

import mfw.acegi.pojo.Company;

import org.hibernate.criterion.Restrictions;


public class CompanyDaoImpl extends BaseDaoImpl
implements ICompanyDao
{
	@Override
	public Company getById(Integer id)
	{
		return (Company) super.get(Company.class, id);
		}

	
	@Override
	public List getAll()
	{
		return super.getAll(Company.class);
		}

	
	@Override
	public void delete(Company type)
	{
		super.delele(type);
		}

	
	@Override
	public void save(Company type)
	{
		super.save(type);
		}

	
	@Override
	public List getByPage(QueryPage page)
	{
		return super.getByPage(page, Company.class);
		}

	
	@Override
	public Company getByNameAndId(String name) throws Exception
	{
		List list = super.getSession().createCriteria(Company.class)
		.add(Restrictions.eq("name", name)).list();
		
		if ((list == null) || (list.size() == 0)) {
			return null;
			}
		if (list.size() > 1) {
			throw new Exception("two same companies");
			}
		if (list.size() == 1) {
			return (Company) list.get(0);
			}
		return null;
		}
	
}

/*
 * Location: D:\Documents\certificate\WEB-INF\classes\ Qualified Name:
 * mfw.acegi.dao.CompanyDaoImpl JD-Core Version: 0.6.2
 */