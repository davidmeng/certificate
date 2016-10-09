package mfw.acegi.dao;

import java.sql.PreparedStatement;

import java.sql.ResultSet;

import java.sql.SQLException;

import java.util.ArrayList;

import java.util.HashMap;

import java.util.List;

import java.util.Map;

import mfw.acegi.dao.util.QueryPage;

import mfw.acegi.pojo.SubCategory;

import mfw.acegi.vo.SubCategoryStatisticsVo;

import org.hibernate.HibernateException;

import org.springframework.dao.DataAccessResourceFailureException;


public class SubCategoryDaoImpl extends BaseDaoImpl
implements ISubCategoryDao
{
	static final String sql = "SELECT COUNT(DISTINCT B.ID) as count , SC.DESCR , SC.ID FROM PERSON P, BOOK B, SUB_CATEGORY SC WHERE P.id = B.person_id AND B.SUB_CATEGORY_ID = SC.ID AND B.BOOK_TYPE_ID = ? and p.BOOK_ID is null  GROUP BY SC.DESCR , SC.ID ORDER BY SC.ID";
	static final String sql1 = "SELECT COUNT(DISTINCT B.ID) as count , SC.DESCR , SC.ID FROM PERSON P, BOOK B, SUB_CATEGORY SC WHERE P.id = B.person_id AND B.SUB_CATEGORY_ID = SC.ID AND B.BOOK_TYPE_ID = ?  GROUP BY SC.DESCR , SC.ID ORDER BY SC.ID";
	static final String sql2 = "SELECT COUNT(DISTINCT B.ID) as count , SC.DESCR , SC.ID FROM PERSON P, BOOK B, SUB_CATEGORY SC WHERE (b.lock_info is null or b.lock_info = 2 ) and P.id = B.person_id AND B.SUB_CATEGORY_ID = SC.ID AND B.BOOK_TYPE_ID = ?  GROUP BY SC.DESCR , SC.ID ORDER BY SC.ID";

	
	@Override
	public SubCategory getById(Integer id)
	{
		return (SubCategory) super.get(SubCategory.class, id);
		}

	
	@Override
	public List getAll()
	{
		return super.getAll(SubCategory.class);
		}

	
	@Override
	public void delete(SubCategory type)
	{
		super.delele(type);
		}

	
	@Override
	public void save(SubCategory type)
	{
		super.save(type);
		}

	
	@Override
	public List getByPage(QueryPage page)
	{
		return super.getByPage(page, SubCategory.class);
		}

	
	@Override
	public List<SubCategoryStatisticsVo> getStatList(int typeId) {
		List list = new ArrayList();
		PreparedStatement ps = null;
		ResultSet rs = null;
		Map map = new HashMap();
		try
		{
			ps = super
					.getSession()
					.connection()
					.prepareStatement(
							"SELECT COUNT(DISTINCT B.ID) as count , SC.DESCR , SC.ID FROM PERSON P, BOOK B, SUB_CATEGORY SC WHERE P.id = B.person_id AND B.SUB_CATEGORY_ID = SC.ID AND B.BOOK_TYPE_ID = ?  GROUP BY SC.DESCR , SC.ID ORDER BY SC.ID");
			ps.setInt(1, typeId);
			System.out
					.println("SELECT COUNT(DISTINCT B.ID) as count , SC.DESCR , SC.ID FROM PERSON P, BOOK B, SUB_CATEGORY SC WHERE P.id = B.person_id AND B.SUB_CATEGORY_ID = SC.ID AND B.BOOK_TYPE_ID = ? and p.BOOK_ID is null  GROUP BY SC.DESCR , SC.ID ORDER BY SC.ID");
			rs = ps.executeQuery();
			while (rs.next()) {
				SubCategoryStatisticsVo vo = new SubCategoryStatisticsVo();
				vo.setTotalCount(rs.getInt("count"));
				vo.setDescr(rs.getString("DESCR"));
				vo.setSubCategoryId(rs.getInt("id"));
				map.put(Integer.valueOf(vo.getSubCategoryId()), vo);
				}
			} catch (DataAccessResourceFailureException e) {
			e.printStackTrace();
			} catch (HibernateException e) {
			e.printStackTrace();
			} catch (IllegalStateException e) {
			e.printStackTrace();
			} catch (SQLException e) {
			e.printStackTrace();
			}
		finally {
			if (rs != null)
				try {
					rs.close();
					} catch (SQLException e) {
					e.printStackTrace();
					}
			if (ps != null)
				try {
					ps.close();
					} catch (SQLException e) {
					e.printStackTrace();
					}
			}
		try
		{
			String mySql = "SELECT COUNT(DISTINCT B.ID) as count , SC.DESCR , SC.ID FROM PERSON P, BOOK B, SUB_CATEGORY SC WHERE P.id = B.person_id AND B.SUB_CATEGORY_ID = SC.ID AND B.BOOK_TYPE_ID = ? and p.BOOK_ID is null  GROUP BY SC.DESCR , SC.ID ORDER BY SC.ID";
			if (typeId == 1) {
				mySql = "SELECT COUNT(DISTINCT B.ID) as count , SC.DESCR , SC.ID FROM PERSON P, BOOK B, SUB_CATEGORY SC WHERE (b.lock_info is null or b.lock_info = 2 ) and P.id = B.person_id AND B.SUB_CATEGORY_ID = SC.ID AND B.BOOK_TYPE_ID = ?  GROUP BY SC.DESCR , SC.ID ORDER BY SC.ID";
				}
			
			ps = super.getSession().connection().prepareStatement(mySql);
			
			ps.setInt(1, typeId);
			System.out.println(mySql);
			rs = ps.executeQuery();
			while (rs.next())
			{
				int id = rs.getInt("id");
				if (map.containsKey(Integer.valueOf(id))) {
					SubCategoryStatisticsVo vo = (SubCategoryStatisticsVo) map.get(Integer.valueOf(id));
					vo.setCount(rs.getInt("count"));
					} else {
					SubCategoryStatisticsVo vo = (SubCategoryStatisticsVo) map.get(Integer.valueOf(id));
					vo.setCount(0);
					}
				}
			} catch (DataAccessResourceFailureException e) {
			e.printStackTrace();
			} catch (HibernateException e) {
			e.printStackTrace();
			} catch (IllegalStateException e) {
			e.printStackTrace();
			} catch (SQLException e) {
			e.printStackTrace();
			}
		finally {
			if (rs != null)
				try {
					rs.close();
					} catch (SQLException e) {
					e.printStackTrace();
					}
			if (ps != null) {
				try {
					ps.close();
					} catch (SQLException e) {
					e.printStackTrace();
					}
				}
			}
		return new ArrayList(map.values());
		}
	
}

/*
 * Location: D:\Documents\certificate\WEB-INF\classes\ Qualified Name:
 * mfw.acegi.dao.SubCategoryDaoImpl JD-Core Version: 0.6.2
 */