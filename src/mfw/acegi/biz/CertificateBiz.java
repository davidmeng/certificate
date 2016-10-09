package mfw.acegi.biz;


import java.io.FileNotFoundException;

import java.io.IOException;

import java.sql.Timestamp;

import java.text.ParseException;

import java.text.SimpleDateFormat;

import java.util.ArrayList;

import java.util.Collections;

import java.util.Date;

import java.util.HashMap;

import java.util.Iterator;

import java.util.List;

import java.util.Map;


import mfw.acegi.Exception.ApplicateionException;

import mfw.acegi.constants.NameUtils;

import mfw.acegi.constants.Utils;

import mfw.acegi.dao.IBookDao;

import mfw.acegi.dao.IBookTypeDao;

import mfw.acegi.dao.ICategoryDao;

import mfw.acegi.dao.ICompanyDao;

import mfw.acegi.dao.IPersonBookDao;

import mfw.acegi.dao.IPersonDao;

import mfw.acegi.dao.ISubCategoryDao;

import mfw.acegi.dao.util.QueryPage;
import mfw.acegi.form.CertificateForm;

import mfw.acegi.pojo.Book;

import mfw.acegi.pojo.BookType;

import mfw.acegi.pojo.Category;

import mfw.acegi.pojo.Company;

import mfw.acegi.pojo.Person;

import mfw.acegi.pojo.PersonBook;

import mfw.acegi.pojo.SubCategory;

import mfw.acegi.vo.AssignVo;

import mfw.acegi.vo.PersonAssignVo;

import mfw.acegi.vo.SubCategoryStatisticsVo;


import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import org.apache.poi.ss.usermodel.Row;

import org.apache.poi.ss.usermodel.Sheet;

import org.apache.poi.ss.usermodel.Workbook;

import org.apache.poi.ss.usermodel.WorkbookFactory;

import org.apache.struts.upload.FormFile;





public class CertificateBiz
implements ICertificateBiz
{
	Short lock = new Short((short) 1);
	Short unlock = new Short((short) 2);
	
	Short otherType = new Short((short) 1);
	Short projectType = new Short((short) 2);
	IBookDao bookDao;
	private ICategoryDao categoryDao;
	ISubCategoryDao subCategoryDao;
	private IBookTypeDao bookTypeDao;
	ICompanyDao companyDao;
	IPersonDao personDao;
	private IPersonBookDao personBookDao;
	private Person prePerson;
	BookType t1 = new BookType();
	BookType t2 = new BookType();
	
	Map<Integer, SubCategory> subCategoryMap = null;

	
	@Override
	public void lockCertificate(int id) {
		Book c = this.bookDao.getById(new Integer(id));
		
		if (c.getBookType().getId().intValue() == this.projectType.intValue()) {
			Person p = c.getPerson();
			p.setBook(c);
			this.personDao.save(p);
			}
		c.setLockInfo(this.lock);
		this.bookDao.save(c);
		}

	@Override
	public void unlockCertificate(int id) {
		Book c = this.bookDao.getById(new Integer(id));
		c.setLockInfo(this.unlock);
		
		if (c.getBookType().getId().intValue() == this.projectType.intValue()) {
			Person p = c.getPerson();
			p.setBook(null);
			this.personDao.save(p);
			}
		
		this.bookDao.save(c);
		}

	
	@Override
	public void saveCertificate(Book c) {
		this.bookDao.save(c);
		}

	
	@Override
	public Book getCertificateById(Integer id) {
		return this.bookDao.getById(id);
		}

	
	@Override
	public List<Company> getAllCompanyList() {
		return this.companyDao.getAll();
		}

	
	public CertificateBiz() {
		this.t1.setId(new Integer(1));
		this.t2.setId(new Integer(2));
		}

	
	@Override
	public List getAllSubCategory()
	{
		if (this.subCategoryMap == null) {
			this.subCategoryMap = new HashMap();
			List list = this.subCategoryDao.getAll();
			for (Iterator itor = list.iterator(); itor.hasNext();) {
				SubCategory sc = (SubCategory) itor.next();
				this.subCategoryMap.put(sc.getId(), sc);
				}
			}
		
		return new ArrayList(this.subCategoryMap.values());
		}

	
	@Override
	public List getAllPerson() {
		return this.personDao.getAll();
		}

	
	@Override
	public List getPersonByPage(QueryPage page) {
		return this.personDao.getByPage(page);
		}

	
	@Override
	public void initData(int subCategoryId, FormFile workFile) throws Exception
	{
		String seq = null;
		
		int i = 2;
		try {
			Workbook wb = WorkbookFactory.create(workFile.getInputStream());
			Sheet sheet = wb.getSheetAt(0);
			
			Row r = null;
			while (true) {
				r = sheet.getRow(i++);
				System.out.println("error occurs at row :" + i);
				if ((r == null) || (r.getCell(6) == null)) {
					break;
					}
				String number = r.getCell(6).getStringCellValue();
				if ((number != null) && (!number.trim().equals("")))
				{
					if (Utils.isNotNull(r.getCell(0).getStringCellValue())) {
						seq = r.getCell(0).getStringCellValue().trim();
						}
					String name = r.getCell(1).getStringCellValue();
					String id = r.getCell(3).getStringCellValue();
					String gender = r.getCell(2).getStringCellValue();
					String work = r.getCell(4).getStringCellValue();
					
					String issueStr = r.getCell(7).getStringCellValue();
					
					Person person = new Person();
					
					Company c = getComanyByName(work);
					Person p;
					if ((name != null) && (!name.equals(""))) {
						person.setCompany(c);
						person.setName(name);
						person.setPersonInfo(id);
						person.setGender(getGender(gender));
						
						p = getPerson(person);
						this.prePerson = p;
						} else {
						p = this.prePerson;
						}
					
					SubCategory sc = new SubCategory();
					sc.setId(new Integer(subCategoryId));
					
					Book certificate = new Book();
					certificate.setSequance(new Integer(seq));
					certificate.setIssueDate(new Timestamp(getDate(issueStr).getTime()));
					certificate.setNumber(number);
					certificate.setSubCategory(sc);
					certificate.setBookType(getCertificateType(number));
					
					this.bookDao.save(certificate);
					
					PersonBook pc = new PersonBook();
					pc.setBook(certificate);
					pc.setPerson(p);
					
					this.personBookDao.save(pc);
					}
				}
			} catch (InvalidFormatException e) {
			System.out.println("error occurs at row :" + i);
			} catch (FileNotFoundException e) {
			System.out.println("error occurs at row :" + i);
			} catch (IOException e) {
			System.out.println("error occurs at row :" + i);
			}
		}

	
	@Override
	public void test()
	throws Exception
	{
		Company c = new Company();
		c.setName("testing");
		this.companyDao.save(c);
		
		SubCategory sc = new SubCategory();
		sc.setDescr("test");
		Category ca = new Category();
		ca.setId(new Integer(9));
		
		this.subCategoryDao.save(sc);
		
		if (c != null)
			throw new Exception("testing");
		}

	
	public Company getComanyByName(String name)
	throws Exception
	{
		if (Utils.isNull(name)) {
			name = "天津公司";
			}
		
		Company c = this.companyDao.getByNameAndId(name);
		
		if (c == null) {
			c = new Company();
			c.setName(name);
			this.companyDao.save(c);
			}
		
		return c;
		}

	
	@Override
	public void scramblePersonInfo() {
		List<Person> personList = this.personDao.getAll();
		for (Person p : personList) {
			p.setName(NameUtils.getName());
			p.setPersonInfo(p.getPersonInfo().substring(0, 10) + NameUtils.getPersonId());
			this.personDao.save(p);
			}
		}

	
	public Person getPerson(Person person) throws Exception {
		Person p = this.personDao.getByNameAndId(person.getPersonInfo());
		
		if (p == null) {
			this.personDao.save(person);
			return person;
			}
		return p;
		}

	
	private Short getGender(String g) {
		if ((g != null) && (g.equals("男"))) {
			return new Short((short) 1);
			}
		return new Short((short) 0);
		}

	
	private Date getDate(String date) throws ParseException {
		if ((date == null) || (date.trim().equals(""))) {
			return new Date();
			}
		
		String[] dateArr = date.split("\\.");
		if (dateArr.length == 2) {
			dateArr[2] = "01";
			}
		StringBuffer sb = new StringBuffer();
		sb.append(dateArr[0]);
		sb.append(dateString(dateArr[1]));
		sb.append(dateString(dateArr[2]));
		
		String dateStr = sb.toString();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		
		return sdf.parse(dateStr);
		}

	
	@Override
	public int savePerson(Person person, String companyName, Book b) throws Exception {
		Company c = getComanyByName(companyName);
		
		Person p = this.personDao.getByNameAndId(person.getPersonInfo());
		if (p != null)
			person.setId(p.getId());
		else {
			p = new Person();
			}
		p.setEducation(person.getEducation());
		p.setPosition(person.getPosition());
		p.setGender(person.getGender());
		p.setCompany(c);
		p.setName(person.getName());
		p.setPersonInfo(person.getPersonInfo());
		p.setEducation(person.getEducation());
		p.setEducationDate(person.getEducationDate());
		p.setPosition(person.getPosition());
		p.setPositionDate(person.getPositionDate());
		p.setIdExist(person.getIdExist());
		p.setCertificateExist(person.getCertificateExist());
		
		this.personDao.save(p);
		
		if (b != null) {
			b.setPerson(p);
			b.setBookType(getCertificateType(b.getNumber()));
			
			this.bookDao.save(b);
			}
		return p.getId().intValue();
		}

	
	private String dateString(String d) {
		if (d.length() == 1) {
			return "0" + d;
			}
		return d;
		}

	
	@Override
	public void deleteBook(Book book) {
		this.bookDao.delete(book);
		}

	
	private BookType getCertificateType(String c) {
		if (c.indexOf("项") > -1) {
			return this.t2;
			}
		return this.t1;
		}

	
	@Override
	public List getPersonListBy(CertificateForm certificationForm)
	{
		return this.personDao.getPersonListBy(certificationForm);
		}

	
	@Override
	public List<SubCategoryStatisticsVo> getStatisticsVo(Short typeId)
	{
		return this.subCategoryDao.getStatList(typeId.shortValue());
		}

	
	@Override
	public List<PersonAssignVo> getAssignPersonList(String companyName, int[] subCategoryIds, int[] quantitys) throws ApplicateionException
	{
		getAllSubCategory();
		List<SubCategoryStatisticsVo> voList = getStatisticsVo(Short.valueOf((short) 2));
		Map map = new HashMap();
		Map assignVoMap = new HashMap();
		
		for (SubCategoryStatisticsVo vo : voList) {
			map.put(Integer.valueOf(vo.getSubCategoryId()), vo);
			}
		
		int length = quantitys.length;
		Object assignVoList = new ArrayList();
		for (int i = 0; i < length; i++)
		{
			AssignVo vo = new AssignVo();
			vo.setRemainQuantity(((SubCategoryStatisticsVo) map.get(Integer.valueOf(subCategoryIds[i]))).getCount());
			vo.setRequireQuantity(quantitys[i]);
			vo.setSubCategoryId(subCategoryIds[i]);
			vo.setSubCategoryDescr(((SubCategoryStatisticsVo) map.get(Integer.valueOf(subCategoryIds[i]))).getDescr());
			
			assignVoMap.put(Integer.valueOf(subCategoryIds[i]), vo);
			if (quantitys[i] != 0)
				((List) assignVoList).add(vo);
			}
		Collections.sort((List) assignVoList);
		List resultPersonList = new ArrayList();
		
		int subCategoryId = fetchSubcategory((List) assignVoList);
		while (subCategoryId != -1)
		{
			List<Person> personList = this.personDao.getAllBooksPersonListBy(Integer.valueOf(subCategoryId), companyName);
			removePersonList(personList, resultPersonList);
			
			List personVoList = new ArrayList();
			for (Person p : personList) {
				if (!p.isLockInfo())
				{
					PersonAssignVo v = new PersonAssignVo(p, assignVoMap);
					v.setSubCategoryId(subCategoryId);
					v.setSubCategoryDescr(this.subCategoryMap.get(Integer.valueOf(subCategoryId)).getDescr());
					personVoList.add(v);
					}
				}
			if (personVoList.size() == 0) {
				throw new ApplicateionException("系统无法找到最佳配证方式，请手动进行配证。");
				}
			
			Collections.sort(personVoList);
			resultPersonList.add(personVoList.get(0));
			removePersonFromMap(((PersonAssignVo) personVoList.get(0)).getPerson(), assignVoMap);
			subCategoryId = fetchSubcategory((List) assignVoList);
			}
		
		return resultPersonList;
		}

	
	int fetchSubcategory(List<AssignVo> assignVoList)
	{
		if (assignVoList.size() == 0) {
			return -1;
			}
		
		AssignVo vo = assignVoList.get(0);
		vo.setRequireQuantity(vo.getRequireQuantity() - 1);
		if (vo.getRequireQuantity() == 0) {
			assignVoList.remove(0);
			}
		return vo.getSubCategoryId();
		}

	
	void removePersonFromMap(Person person, Map<Integer, AssignVo> assignVoMap) throws ApplicateionException
	{
		for (Iterator itor = person.getBooks().iterator(); itor.hasNext();)
		{
			Book b = (Book) itor.next();
			removeSubCategoryFromMap(b.getSubCategory().getId().intValue(), assignVoMap);
			}
		}

	
	void removeSubCategoryFromMap(int subCategoryId, Map<Integer, AssignVo> assignVoMap) throws ApplicateionException
	{
		AssignVo vo = assignVoMap.get(Integer.valueOf(subCategoryId));
		if (vo != null) {
			vo.setRemainQuantity(vo.getRemainQuantity() - 1);
			if (vo.getRemainQuantity() < 0)
				throw new ApplicateionException("系统无法找到最佳配证方式，请手动进行配证。");
			}
		}

	
	void removePersonList(List<Person> personList, List<PersonAssignVo> personVoList)
	{
		if ((personList == null) || (personList.size() == 0)) {
			return;
			}
		
		if ((personVoList == null) || (personVoList.size() == 0)) {
			return;
			}
		
		for (PersonAssignVo vo : personVoList)
			for (Person person : personList)
				if (person.getId().equals(vo.getPerson().getId())) {
					personList.remove(person);
					break;
					}
		}

	
	@Override
	public void batchAssign(int[] books, String usedInfo)
	{
		for (int bookId : books) {
			Book book = this.bookDao.getById(Integer.valueOf(bookId));
			book.setUsedInfo(usedInfo);
			book.setLockInfo(this.lock);
			
			Person p = book.getPerson();
			p.setBook(book);
			this.personDao.save(p);
			
			this.bookDao.save(book);
			}
		}

	
	@Override
	public Person getPersonById(Integer id) {
		return this.personDao.getById(id);
		}

	
	public ICategoryDao getCategoryDao()
	{
		return this.categoryDao;
		}

	
	public void setCategoryDao(ICategoryDao categoryDao)
	{
		this.categoryDao = categoryDao;
		}

	
	public ISubCategoryDao getSubCategoryDao()
	{
		return this.subCategoryDao;
		}

	
	public void setSubCategoryDao(ISubCategoryDao subCategoryDao)
	{
		this.subCategoryDao = subCategoryDao;
		}

	
	public ICompanyDao getCompanyDao()
	{
		return this.companyDao;
		}

	
	public void setCompanyDao(ICompanyDao companyDao)
	{
		this.companyDao = companyDao;
		}

	
	public IPersonDao getPersonDao()
	{
		return this.personDao;
		}

	
	public void setPersonDao(IPersonDao personDao)
	{
		this.personDao = personDao;
		}

	
	public IBookDao getBookDao() {
		return this.bookDao;
		}

	
	public void setBookDao(IBookDao bookDao) {
		this.bookDao = bookDao;
		}

	
	public IBookTypeDao getBookTypeDao() {
		return this.bookTypeDao;
		}

	
	public void setBookTypeDao(IBookTypeDao bookTypeDao) {
		this.bookTypeDao = bookTypeDao;
		}

	
	public IPersonBookDao getPersonBookDao() {
		return this.personBookDao;
		}

	
	public void setPersonBookDao(IPersonBookDao personBookDao) {
		this.personBookDao = personBookDao;
		}
	
}

/*
 * Location: D:\Documents\certificate\WEB-INF\classes\ Qualified Name:
 * mfw.acegi.biz.CertificateBiz JD-Core Version: 0.6.2
 */