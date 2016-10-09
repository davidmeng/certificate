package mfw.acegi.dao;


import java.io.Serializable;

import java.util.ArrayList;

import java.util.HashMap;

import java.util.Iterator;

import java.util.List;

import java.util.Map;

import mfw.acegi.dao.util.BeanUtils;

import mfw.acegi.dao.util.CriteriaAliasObject;

import mfw.acegi.dao.util.OrderByUtil;

import mfw.acegi.dao.util.QueryPage;

import org.hibernate.Criteria;

import org.hibernate.criterion.CriteriaSpecification;

import org.hibernate.criterion.Criterion;

import org.hibernate.criterion.Projection;

import org.hibernate.criterion.Projections;

import org.hibernate.impl.CriteriaImpl;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;


public class BaseDaoImpl extends HibernateDaoSupport
{
	public Object get(Class entityClass, Serializable id)
	{
		return getHibernateTemplate().get(entityClass, id);
		}

	
	public void delele(Object o)
	{
		super.getHibernateTemplate().delete(o);
		}

	
	public void save(Object o)
	{
		super.getHibernateTemplate().save(o);
		}

	
	public List getAll(Class entityClass)
	{
		return super.getSession().createCriteria(entityClass).list();
		}

	
	public List getByPage(QueryPage queryPage, Class entityClass)
	{
		Criteria criteria = super.getSession().createCriteria(entityClass);
		for (Iterator i = queryPage.getCriterionList().iterator(); i.hasNext();) {
			criteria.add((Criterion) i.next());
			}
		
		Map aliasMap = new HashMap();
		for (Iterator i = queryPage.getCritieraAliasList().iterator(); i
		.hasNext();)
		{
			CriteriaAliasObject alias = (CriteriaAliasObject) i.next();
			
			Criteria criteriaAlias = null;
			if (aliasMap.containsKey(alias.getAliasName())) {
				criteriaAlias = (Criteria) aliasMap.get(alias.getAliasName());
				} else {
				criteriaAlias = criteria.createAlias(alias.getObjectName(),
				alias.getAliasName());
				aliasMap.put(alias.getAliasName(), criteriaAlias);
				}
			criteriaAlias.add(alias.getCriterion());
			}
		
		return pagedQuery(queryPage, criteria);
		}

	
	public List pagedQuery(QueryPage queryCtx, Criteria criteria)
	{
		int pageSize = queryCtx.getPageSize();
		if (pageSize <= 0) {
			pageSize = 10;
			}
		
		int targetPage = queryCtx.getTargetPage();
		
		int pageCount = 0;
		int recordCount = 0;
		CriteriaImpl impl = (CriteriaImpl) criteria;
		
		Projection projection = impl.getProjection();
		try
		{
			List orderEntries = (List) BeanUtils.forceGetProperty(impl,
			"orderEntries");
			BeanUtils.forceSetProperty(impl, "orderEntries", new ArrayList());
			} catch (Exception e) {
			throw new InternalError(" Runtime Exception impossibility throw ");
			}

		Object obj = criteria.setProjection(Projections.rowCount())
		.uniqueResult();
		if (obj == null) {
			return null;
			}
		recordCount = Integer.parseInt(obj.toString());
		
		criteria.setProjection(projection);
		if (projection == null) {
			criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
			}
		
		queryCtx.setRecordCount(recordCount);
		pageCount = recordCount / pageSize;
		if (recordCount % pageSize > 0) {
			pageCount++;
			}
		
		if (pageCount < targetPage)
		{
			targetPage = pageCount;
			}
		
		queryCtx.setPageCount(pageCount);
		
		int startIndex = targetPage * pageSize;
		
		if (queryCtx.isSorted()) {
			OrderByUtil.sortCriteria(criteria, queryCtx.getSortMap(), queryCtx
			.getAliasMap());
			}
		
		List list = criteria.setFirstResult(startIndex).setMaxResults(pageSize)
		.list();
		
		return list;
		}
	
}

/*
 * Location: D:\Documents\certificate\WEB-INF\classes\ Qualified Name:
 * mfw.acegi.dao.BaseDaoImpl JD-Core Version: 0.6.2
 */