package mfw.acegi.dao;


import java.sql.Date;

import java.sql.PreparedStatement;

import java.sql.ResultSet;

import java.sql.SQLException;
import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.HashMap;

import java.util.LinkedHashSet;

import java.util.List;
import java.util.Map;

import java.util.Set;

import mfw.acegi.constants.DateUtils;

import mfw.acegi.constants.Utils;

import mfw.acegi.dao.util.QueryPage;
import mfw.acegi.form.CertificateForm;

import mfw.acegi.pojo.Book;

import mfw.acegi.pojo.Company;

import mfw.acegi.pojo.Person;

import mfw.acegi.pojo.SubCategory;


import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;

import org.springframework.dao.DataAccessResourceFailureException;





public class PersonDaoImpl extends BaseDaoImpl
implements IPersonDao
{
	static String SQL = "select p.id_exist,p.certificate_exist, p.NAME,p.ID as personId,p.education,p.education_date,p.position, p.position_date, PERSON_INFO,  cp.name as companyName, c.NUMBER , sc.id as subCategoryId , sc.DESCR ,c.id as certificateId , c.lock_info ,c.used_info ,  c.lend_info , c.sequance , p.book_id as lock_book_id from person p ,  book c , sub_category sc , company cp  where p.ID = c.person_id   and c.SUB_CATEGORY_ID = sc.ID  and p.company_id = cp.id and c.book_status_id = 1";
	
	static String SQL1 = "select p.id_exist,p.certificate_exist, p.NAME,p.ID as personId,p.education,p.education_date,p.position, p.position_date, PERSON_INFO,  cp.name as companyName, book.NUMBER , sub_category.id as subCategoryId , sub_category.DESCR ,book.id as certificateId , book.lock_info ,book.used_info ,  c.lend_info , c.sequance , p.book_id as lock_book_id from person p join book on p.id = book.person_id and book.book_type_id = 2  and (book.lock_info = 2 or book.lock_info is null) join sub_category  on book.sub_category_id = sub_category.id ,  book c , sub_category sc , company cp  where p.ID = c.person_id   and c.SUB_CATEGORY_ID = sc.ID  and p.company_id = cp.id  and p.book_id is null and c.BOOK_TYPE_ID = 2 and c.sub_category_id = ?  and c.book_status_id = 1";

	Map<Integer, Book> bookMap = new HashMap<Integer, Book>();

	
	@Override
	public Person getById(Integer id)
	{
		return (Person) super.get(Person.class, id);
		}

	
	@Override
	public List getAll()
	{
		return super.getAll(Person.class);
		}

	
	@Override
	public void delete(Person type)
	{
		super.delele(type);
		}

	
	@Override
	public void save(Person type)
	{
		super.save(type);
		}

	
	@Override
	public List getByPage(QueryPage page)
	{
		return super.getByPage(page, Person.class);
		}

	
	@Override
	public Person getByNameAndId(String id) throws Exception
	{
		List list = super.getSession().createCriteria(Person.class)
		.add(Restrictions.eq("personInfo", id)).list();
		
		if ((list == null) || (list.size() == 0)) {
			return null;
			}
		if (list.size() > 1) {
			throw new Exception("two same persons");
			}
		if (list.size() == 1) {
			return (Person) list.get(0);
			}
		return null;
		}

	private void initBookMap() {
		bookMap.clear();
		List<Book> bookList = super.getAll(Book.class);

		for (Book b : bookList) {
			bookMap.put(b.getId(), b);
		}
	}

	@Override
	public List getPersonListBy(CertificateForm form) {
		{
			initBookMap();

			String name = form.getName();
			String personInfo = form.getId();
			Integer categoryId = form.getCategoryId();
			Integer subCategoryId = form.getSubCategoryId();
			String companyName = form.getCompanyName();
			Short typeId = form.getCertificateTypeId();
			String number = form.getCertificationNum();
			String ii = form.getLendInfo();
			String ui = form.getUsedInfo();
			String educationStartDateStr = form.getEducationStartDateStr();
			String educationEndDateStr = form.getEducationEndDateStr();
			Integer education = form.getEducation();
			String positionStartDateStr = form.getPositionStartDateStr();
			String positionEndDateStr = form.getPositionEndDateStr();
			Integer position = form.getPosition();
			Integer idExist = form.getIdExist();
			Integer certificateExist = form.getCertificateExist();
			Integer issueYear = form.getIssueYear();

			String sql = SQL;
			List list = new ArrayList();

			String personNameFlag = "";
			String prePersonNameFlag = "";

			if (Utils.isNotNull(name)) {
				sql = sql + " and p.name like ? ";
			}
			if (Utils.isNotNull(personInfo)) {
				sql = sql + " and p.person_Info like ? ";
			}
			if (Utils.isNotNull(categoryId)) {
				sql = sql + " and sc.category_Id = ? ";
			}
			if (Utils.isNotNull(subCategoryId)) {
				sql = sql + " and sc.id = ? ";
			}
			if (Utils.isNotNull(companyName)) {
				sql = sql + " and cp.name like ? ";
			}
			if (Utils.isNotNull(typeId)) {
				sql = sql + " and c.book_type_id  = ? ";
			}
			if (Utils.isNotNull(number)) {
				sql = sql + " and c.number like ? ";
			}

			if (Utils.isNotNull(ii)) {
				sql = sql + " and c.lend_info like ? ";
			}
			if (Utils.isNotNull(ui)) {
				sql = sql + " and c.used_info like ? ";
			}
			if (Utils.isNotNull(educationStartDateStr)) {
				sql = sql + " and p.education_date > ? ";
			}
			if (Utils.isNotNull(educationEndDateStr)) {
				sql = sql + " and p.education_date <= ? ";
			}
			if (Utils.isNotNull(positionStartDateStr)) {
				sql = sql + " and p.position_date > ? ";
			}
			if (Utils.isNotNull(positionEndDateStr)) {
				sql = sql + " and p.position_date <= ? ";
			}
			if (Utils.isNotNull(education)) {
				sql = sql + " and p.education >= ? ";
			}
			if (Utils.isNotNull(position)) {
				sql = sql + " and p.position >= ? ";
			}
			if (Utils.isNotNull(idExist)) {
				sql = sql + " and p.id_exist = ? ";
			}
			if (Utils.isNotNull(certificateExist)) {
				sql = sql + " and p.certificate_exist = ? ";
			}
			if (Utils.isNotNull(issueYear)) {
				sql = sql + " and year(c.issue_date) = ? ";
			}
			sql = sql + " order by  p.name , p.id , c.id ";
			System.out.println(sql);

			int i = 1;

			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				ps = super.getSession().connection().prepareStatement(sql);

				if (Utils.isNotNull(name)) {
					ps.setString(i++, getLikeString(name));
				}
				if (Utils.isNotNull(personInfo)) {
					ps.setString(i++, getLikeString(personInfo));
				}
				if (Utils.isNotNull(categoryId)) {
					ps.setInt(i++, categoryId.intValue());
				}
				if (Utils.isNotNull(subCategoryId)) {
					ps.setInt(i++, subCategoryId.intValue());
				}
				if (Utils.isNotNull(companyName)) {
					ps.setString(i++, getLikeString(companyName));
				}
				if (Utils.isNotNull(typeId)) {
					ps.setInt(i++, typeId.intValue());
				}
				if (Utils.isNotNull(number)) {
					ps.setString(i++, getLikeString(number));
				}
				if (Utils.isNotNull(ii)) {
					ps.setString(i++, getLikeString(ii));
				}
				if (Utils.isNotNull(ui)) {
					ps.setString(i++, getLikeString(ui));
				}
				if (Utils.isNotNull(educationStartDateStr)) {
					ps.setDate(i++, DateUtils.formatToSqlDate(educationStartDateStr));
				}
				if (Utils.isNotNull(educationEndDateStr)) {
					ps.setDate(i++, DateUtils.formatToSqlDate(educationEndDateStr));
				}
				if (Utils.isNotNull(positionStartDateStr)) {
					ps.setDate(i++, DateUtils.formatToSqlDate(positionStartDateStr));
				}
				if (Utils.isNotNull(positionEndDateStr)) {
					ps.setDate(i++, DateUtils.formatToSqlDate(positionEndDateStr));
				}
				if (Utils.isNotNull(education)) {
					ps.setInt(i++, education);
				}
				if (Utils.isNotNull(position)) {
					ps.setInt(i++, position);
				}
				if (Utils.isNotNull(idExist)) {
					ps.setInt(i++, idExist);
				}
				if (Utils.isNotNull(certificateExist)) {
					ps.setInt(i++, certificateExist);
				}
				if (Utils.isNotNull(issueYear)) {
					ps.setInt(i++, issueYear);
				}
				rs = ps.executeQuery();
				Person p = null;
				Set certificateSet = null;
				System.out.println(sql);

				while (rs.next()) {
					String personName = rs.getString("p.NAME");
					String personId = rs.getString("PERSON_INFO");
					String cn = rs.getString("companyName");
					String certificateNumber = rs.getString("c.NUMBER");
					String subCategoryDescr = rs.getString("sc.DESCR");
					String usedInfo = rs.getString("used_info");
					String lendInfo = rs.getString("lend_info");
					int educationInt = rs.getInt("education");
					Date educationDate = rs.getDate("education_date");

					int positionInt = rs.getInt("position");
					Date positionDate = rs.getDate("position_date");

					int sequance = rs.getInt("sequance");
					int lockBookId = rs.getInt("lock_book_id");

					int scId = rs.getInt("subCategoryId");
					int certificateId = rs.getInt("certificateId");
					short lock = rs.getShort("lock_info");
					int pId = rs.getInt("personId");
					int idExist1 = rs.getInt("id_exist");
					int certificateExist1 = rs.getInt("certificate_exist");

					boolean locked = false;

					personNameFlag = personId;
					if (!personNameFlag.equals(prePersonNameFlag)) {
						p = new Person();
						certificateSet = new LinkedHashSet();
						p.setId(new Integer(pId));
						p.setName(personName);
						p.setPersonInfo(personId);
						p.setBooks(certificateSet);
						p.setEducation(educationInt);
						if (educationDate != null) {

							p.setEducationDate(new Timestamp(educationDate.getTime()));
						}
						p.setPosition(positionInt);

						if (positionDate != null) {

							p.setPositionDate(new Timestamp(positionDate.getTime()));
						}
						p.setIdExist(idExist1);
						p.setCertificateExist(certificateExist1);

						list.add(p);
					}
					prePersonNameFlag = personNameFlag;
					Company cp = new Company();
					cp.setName(cn);

					Book c = new Book();

					SubCategory sc = new SubCategory();
					sc.setDescr(subCategoryDescr);
					sc.setId(new Integer(scId));

					c.setNumber(certificateNumber);
					c.setSubCategory(sc);
					c.setId(new Integer(certificateId));
					c.setLockInfo(new Short(lock));
					c.setLendInfo(lendInfo);
					c.setUsedInfo(usedInfo);
					c.setSequance(Integer.valueOf(sequance));
					p.setCompany(cp);

					if ((lockBookId != 0) && (typeId.intValue() == 2)) {
						certificateSet.clear();
						Book b = bookMap.get(lockBookId);
						p.setLockInfo(true);

						p.setUsedInfo(b.getUsedInfo());
						p.setLendInfo(b.getLendInfo());

						certificateSet.add(b);
					} else {
						certificateSet.add(c);
					}
				}
			} catch (DataAccessResourceFailureException e) {
				e.printStackTrace();

				if (rs != null)
					try {
						rs.close();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				if (ps != null)
					try {
						ps.close();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
			} catch (HibernateException e) {
				e.printStackTrace();

				if (rs != null)
					try {
						rs.close();
					} catch (SQLException e1) {
						e.printStackTrace();
					}
				if (ps != null)
					try {
						ps.close();
					} catch (SQLException e1) {
						e.printStackTrace();
					}
			} catch (IllegalStateException e) {
				e.printStackTrace();

				if (rs != null)
					try {
						rs.close();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				if (ps != null)
					try {
						ps.close();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
			} catch (SQLException e) {
				e.printStackTrace();

				if (rs != null)
					try {
						rs.close();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				if (ps != null)
					try {
						ps.close();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
			} finally {
				if (rs != null)
					try {
						rs.close();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				if (ps != null) {
					try {
						ps.close();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
			return list;
		}
	}

	
	public List getPersonListBy(String name, String personInfo, String number, Integer categoryId, Integer subCategoryId,
			Integer education, Integer position, String educationStartDateStr, String educationEndDateStr, String positionStartDateStr,
			String positionEndDateStr, String companyName, Short typeId, String ii, String ui)
	{
		String sql = SQL;
		List list = new ArrayList();
		
		String personNameFlag = "";
		String prePersonNameFlag = "";
		
		if (Utils.isNotNull(name)) {
			sql = sql + " and p.name like ? ";
			}
		if (Utils.isNotNull(personInfo)) {
			sql = sql + " and p.person_Info like ? ";
			}
		if (Utils.isNotNull(categoryId)) {
			sql = sql + " and sc.category_Id = ? ";
			}
		if (Utils.isNotNull(subCategoryId)) {
			sql = sql + " and sc.id = ? ";
			}
		if (Utils.isNotNull(companyName)) {
			sql = sql + " and cp.name like ? ";
			}
		if (Utils.isNotNull(typeId)) {
			sql = sql + " and c.book_type_id  = ? ";
			}
		if (Utils.isNotNull(number)) {
			sql = sql + " and c.number like ? ";
			}
		
		if (Utils.isNotNull(ii)) {
			sql = sql + " and c.lend_info like ? ";
			}
		if (Utils.isNotNull(ui)) {
			sql = sql + " and c.used_info like ? ";
			}
		if (Utils.isNotNull(educationStartDateStr)) {
			sql = sql + " and p.education_date > ? ";
		}
		if (Utils.isNotNull(educationEndDateStr)) {
			sql = sql + " and p.education_date <= ? ";
		}
		if (Utils.isNotNull(positionStartDateStr)) {
			sql = sql + " and p.position_date > ? ";
		}
		if (Utils.isNotNull(positionEndDateStr)) {
			sql = sql + " and p.position_date <= ? ";
		}
		if (Utils.isNotNull(education)) {
			sql = sql + " and p.education >= ? ";
		}
		if (Utils.isNotNull(position)) {
			sql = sql + " and p.position >= ? ";
		}
		sql = sql + " order by  p.name , p.id , c.id ";
		System.out.println(sql);
		
		int i = 1;
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = super.getSession().connection().prepareStatement(sql);
			
			if (Utils.isNotNull(name)) {
				ps.setString(i++, getLikeString(name));
				}
			if (Utils.isNotNull(personInfo))
			{
				ps.setString(i++, getLikeString(personInfo));
				}
			if (Utils.isNotNull(categoryId)) {
				ps.setInt(i++, categoryId.intValue());
				}
			if (Utils.isNotNull(subCategoryId))
			{
				ps.setInt(i++, subCategoryId.intValue());
				}
			if (Utils.isNotNull(companyName)) {
				ps.setString(i++, getLikeString(companyName));
				}
			if (Utils.isNotNull(typeId)) {
				ps.setInt(i++, typeId.intValue());
				}
			if (Utils.isNotNull(number)) {
				ps.setString(i++, getLikeString(number));
				}
			if (Utils.isNotNull(ii)) {
				ps.setString(i++, getLikeString(ii));
				}
			if (Utils.isNotNull(ui)) {
				ps.setString(i++, getLikeString(ui));
				}
			if (Utils.isNotNull(educationStartDateStr)) {
				ps.setDate(i++, DateUtils.formatToSqlDate(educationStartDateStr));
			}
			if (Utils.isNotNull(educationEndDateStr)) {
				ps.setDate(i++, DateUtils.formatToSqlDate(educationEndDateStr));
			}
			if (Utils.isNotNull(positionStartDateStr)) {
				ps.setDate(i++, DateUtils.formatToSqlDate(positionStartDateStr));
			}
			if (Utils.isNotNull(positionEndDateStr)) {
				ps.setDate(i++, DateUtils.formatToSqlDate(positionEndDateStr));
			}
			if (Utils.isNotNull(education)) {
				ps.setInt(i++, education);
			}
			if (Utils.isNotNull(position)) {
				ps.setInt(i++, position);
			}
			rs = ps.executeQuery();
			Person p = null;
			Set certificateSet = null;
			System.out.println(sql);
			
			while (rs.next()) {
				String personName = rs.getString("p.NAME");
				String personId = rs.getString("PERSON_INFO");
				String cn = rs.getString("companyName");
				String certificateNumber = rs.getString("c.NUMBER");
				String subCategoryDescr = rs.getString("sc.DESCR");
				String usedInfo = rs.getString("used_info");
				String lendInfo = rs.getString("lend_info");
				int educationInt = rs.getInt("education");
				Date educationDate = rs.getDate("education_date");

				int positionInt = rs.getInt("position");
				Date positionDate = rs.getDate("position_date");

				int sequance = rs.getInt("sequance");
				int lockBookId = rs.getInt("lock_book_id");
				
				int scId = rs.getInt("subCategoryId");
				int certificateId = rs.getInt("certificateId");
				short lock = rs.getShort("lock_info");
				int pId = rs.getInt("personId");
				
				boolean locked = false;
				
				personNameFlag = personId;
				if (!personNameFlag.equals(prePersonNameFlag)) {
					p = new Person();
					certificateSet = new LinkedHashSet();
					p.setId(new Integer(pId));
					p.setName(personName);
					p.setPersonInfo(personId);
					p.setBooks(certificateSet);
					p.setEducation(educationInt);
					if (educationDate != null) {

						p.setEducationDate(new Timestamp(educationDate.getTime()));
					}
					p.setPosition(positionInt);

					if (positionDate != null) {

						p.setPositionDate(new Timestamp(positionDate.getTime()));
					}
					
					list.add(p);
					}
				prePersonNameFlag = personNameFlag;
				Company cp = new Company();
				cp.setName(cn);
				
				Book c = new Book();
				
				SubCategory sc = new SubCategory();
				sc.setDescr(subCategoryDescr);
				sc.setId(new Integer(scId));
				
				c.setNumber(certificateNumber);
				c.setSubCategory(sc);
				c.setId(new Integer(certificateId));
				c.setLockInfo(new Short(lock));
				c.setLendInfo(lendInfo);
				c.setUsedInfo(usedInfo);
				c.setSequance(Integer.valueOf(sequance));
				p.setCompany(cp);
				
				if ((lockBookId != 0) && (typeId.intValue() == 2))
				{
					certificateSet.clear();
					Book b = (Book) super.get(Book.class, Integer.valueOf(lockBookId));
					p.setLockInfo(true);
					
					p.setUsedInfo(b.getUsedInfo());
					p.setLendInfo(b.getLendInfo());
					
					certificateSet.add(b);
					}
				else {
					certificateSet.add(c);
					}
				}
			} catch (DataAccessResourceFailureException e) {
			e.printStackTrace();
			
			if (rs != null)
				try {
					rs.close();
					} catch (SQLException e1) {
					e1.printStackTrace();
					}
			if (ps != null)
				try {
					ps.close();
					} catch (SQLException e1) {
					e1.printStackTrace();
					}
			}
		catch (HibernateException e)
		{
			e.printStackTrace();
			
			if (rs != null)
				try {
					rs.close();
					} catch (SQLException e1) {
					e.printStackTrace();
					}
			if (ps != null)
				try {
					ps.close();
					} catch (SQLException e1) {
					e.printStackTrace();
					}
			}
		catch (IllegalStateException e)
		{
			e.printStackTrace();
			
			if (rs != null)
				try {
					rs.close();
					} catch (SQLException e1) {
					e1.printStackTrace();
					}
			if (ps != null)
				try {
					ps.close();
					} catch (SQLException e1) {
					e1.printStackTrace();
					}
			}
		catch (SQLException e)
		{
			e.printStackTrace();
			
			if (rs != null)
				try {
					rs.close();
					} catch (SQLException e1) {
					e1.printStackTrace();
					}
			if (ps != null)
				try {
					ps.close();
					} catch (SQLException e1) {
					e1.printStackTrace();
					}
			}
		finally
		{
			if (rs != null)
				try {
					rs.close();
					} catch (SQLException e1) {
					e1.printStackTrace();
					}
			if (ps != null) {
				try {
					ps.close();
					} catch (SQLException e1) {
					e1.printStackTrace();
					}
				}
			}
		return list;
		}

	
	@Override
	public List getAllBooksPersonListBy(Integer subCategoryId, String companyName)
	{
		String sql = SQL1;
		List list = new ArrayList();
		
		String personNameFlag = "";
		String prePersonNameFlag = "";
		
		if (Utils.isNotNull(companyName)) {
			sql = sql + " and cp.name like ? ";
			}
		sql = sql + " order by  p.name , p.id , certificateId ";
		System.out.println(sql);
		
		int i = 1;
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = super.getSession().connection().prepareStatement(sql);
			
			ps.setInt(i++, subCategoryId.intValue());
			
			if (Utils.isNotNull(companyName)) {
				ps.setString(i++, getLikeString(companyName));
				}
			
			rs = ps.executeQuery();
			Person p = null;
			Set certificateSet = null;
			System.out.println(sql);
			
			while (rs.next()) {
				String personName = rs.getString("p.NAME");
				String personId = rs.getString("PERSON_INFO");
				String cn = rs.getString("companyName");
				String certificateNumber = rs.getString("NUMBER");
				String subCategoryDescr = rs.getString("DESCR");
				String usedInfo = rs.getString("used_info");
				String lendInfo = rs.getString("lend_info");
				int sequance = rs.getInt("sequance");
				int lockBookId = rs.getInt("lock_book_id");
				
				int scId = rs.getInt("subCategoryId");
				int certificateId = rs.getInt("certificateId");
				short lock = rs.getShort("lock_info");
				int pId = rs.getInt("personId");
				
				boolean locked = false;
				
				personNameFlag = personId;
				if (!personNameFlag.equals(prePersonNameFlag)) {
					p = new Person();
					certificateSet = new LinkedHashSet();
					p.setId(new Integer(pId));
					p.setName(personName);
					p.setPersonInfo(personId);
					p.setBooks(certificateSet);
					
					list.add(p);
					}
				prePersonNameFlag = personNameFlag;
				Company cp = new Company();
				cp.setName(cn);
				
				Book c = new Book();
				
				SubCategory sc = new SubCategory();
				sc.setDescr(subCategoryDescr);
				sc.setId(new Integer(scId));
				
				c.setNumber(certificateNumber);
				c.setSubCategory(sc);
				c.setId(new Integer(certificateId));
				c.setLockInfo(new Short(lock));
				c.setLendInfo(lendInfo);
				c.setUsedInfo(usedInfo);
				c.setSequance(Integer.valueOf(sequance));
				p.setCompany(cp);
				if ((lockBookId != 0) && (lockBookId == 2))
				{
					certificateSet.clear();
					Book b = (Book) super.get(Book.class, Integer.valueOf(lockBookId));
					p.setLockInfo(true);
					
					p.setUsedInfo(b.getUsedInfo());
					p.setLendInfo(b.getLendInfo());
					
					certificateSet.add(b);
					}
				else {
					certificateSet.add(c);
					}
				}
			} catch (DataAccessResourceFailureException e) {
			e.printStackTrace();
			
			if (rs != null)
				try {
					rs.close();
					} catch (SQLException e1) {
					e.printStackTrace();
					}
			if (ps != null)
				try {
					ps.close();
					} catch (SQLException e1) {
					e.printStackTrace();
					}
			}
		catch (HibernateException e)
		{
			e.printStackTrace();
			
			if (rs != null)
				try {
					rs.close();
					} catch (SQLException e1) {
					e.printStackTrace();
					}
			if (ps != null)
				try {
					ps.close();
					} catch (SQLException e1) {
					e.printStackTrace();
					}
			}
		catch (IllegalStateException e)
		{
			e.printStackTrace();
			
			if (rs != null)
				try {
					rs.close();
					} catch (SQLException e1) {
					e.printStackTrace();
					}
			if (ps != null)
				try {
					ps.close();
					} catch (SQLException e1) {
					e.printStackTrace();
					}
			}
		catch (SQLException e)
		{
			e.printStackTrace();
			
			if (rs != null)
				try {
					rs.close();
					} catch (SQLException e1) {
					e1.printStackTrace();
					}
			if (ps != null)
				try {
					ps.close();
					} catch (SQLException e1) {
					e1.printStackTrace();
					}
			}
		finally
		{
			if (rs != null)
				try {
					rs.close();
					} catch (SQLException e1) {
					e1.printStackTrace();
					}
			if (ps != null) {
				try {
					ps.close();
					} catch (SQLException e1) {
					e1.printStackTrace();
					}
				}
			}
		return list;
		}

	
	String getLikeString(String a)
	{
		return "%" + a + "%";
		}
	
}

/*
 * Location: D:\Documents\certificate\WEB-INF\classes\ Qualified Name:
 * mfw.acegi.dao.PersonDaoImpl JD-Core Version: 0.6.2
 */