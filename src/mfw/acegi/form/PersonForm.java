package mfw.acegi.form;


import java.util.List;


import mfw.acegi.pojo.Company;

import mfw.acegi.pojo.Person;

import mfw.acegi.pojo.SubCategory;


import org.apache.struts.action.ActionForm;


public class PersonForm extends ActionForm
{
	private static final long serialVersionUID = 1L;
	private List<SubCategory> subCategoryList;
	private List<Company> companyList;
	private Integer id;
	private String name;
	private String personInfo;
	private Short gender;
	private String companyName;
	private String bookNbr;
	private String bookCreateDate;
	private String bookExamDate;
	private Integer subCategoryId;
	private Integer personId;
	private Integer bookSequance;
	private Person person;
	private Integer bookId;
	private Integer education;
	private Integer position;
	private String educationDate;
	private String positionDate;
	private Integer idExist;
	private Integer certificateExist;

	
	public List<SubCategory> getSubCategoryList()
	{
		return this.subCategoryList;
		}

	public void setSubCategoryList(List<SubCategory> subCategoryList) {
		this.subCategoryList = subCategoryList;
		}

	public Integer getId() {
		return this.id;
		}

	public void setId(Integer id) {
		this.id = id;
		}

	public String getName() {
		return this.name;
		}

	public void setName(String name) {
		this.name = name;
		}

	public String getPersonInfo() {
		return this.personInfo;
		}

	public void setPersonInfo(String personInfo) {
		this.personInfo = personInfo;
		}

	public String getCompanyName() {
		return this.companyName;
		}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
		}

	public List<Company> getCompanyList() {
		return this.companyList;
		}

	public void setCompanyList(List<Company> companyList) {
		this.companyList = companyList;
		}

	public Short getGender() {
		return this.gender;
		}

	public void setGender(Short gender) {
		this.gender = gender;
		}

	public String getBookNbr() {
		return this.bookNbr;
		}

	public void setBookNbr(String bookNbr) {
		this.bookNbr = bookNbr;
		}

	public String getBookCreateDate() {
		return this.bookCreateDate;
		}

	public void setBookCreateDate(String bookCreateDate) {
		this.bookCreateDate = bookCreateDate;
		}

	public String getBookExamDate() {
		return this.bookExamDate;
		}

	public void setBookExamDate(String bookExamDate) {
		this.bookExamDate = bookExamDate;
		}

	public Integer getSubCategoryId() {
		return this.subCategoryId;
		}

	public void setSubCategoryId(Integer subCategoryId) {
		this.subCategoryId = subCategoryId;
		}

	public Integer getPersonId() {
		return this.personId;
		}

	public void setPersonId(Integer personId) {
		this.personId = personId;
		}

	public Integer getBookSequance() {
		return this.bookSequance;
		}

	public void setBookSequance(Integer bookSequance) {
		this.bookSequance = bookSequance;
		}

	public Person getPerson() {
		return this.person;
		}

	public void setPerson(Person person) {
		this.person = person;
		}

	public Integer getBookId() {
		return this.bookId;
		}

	public void setBookId(Integer bookId) {
		this.bookId = bookId;
		}

	
	public Integer getEducation() {
		return education;
	}

	public void setEducation(Integer education) {
		this.education = education;
	}

	public Integer getPosition() {
		return position;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}

	public String getEducationDate() {
		return educationDate;
	}

	public void setEducationDate(String educationDate) {
		this.educationDate = educationDate;
	}

	public String getPositionDate() {
		return positionDate;
	}

	public void setPositionDate(String positionDate) {
		this.positionDate = positionDate;
	}

	public Integer getIdExist() {
		return idExist;
	}

	public void setIdExist(Integer idExist) {
		this.idExist = idExist;
	}

	public Integer getCertificateExist() {
		return certificateExist;
	}

	public void setCertificateExist(Integer certificateExist) {
		this.certificateExist = certificateExist;
	}
}

/*
 * Location: D:\Documents\certificate\WEB-INF\classes\ Qualified Name:
 * mfw.acegi.form.PersonForm JD-Core Version: 0.6.2
 */