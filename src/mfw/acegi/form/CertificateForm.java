package mfw.acegi.form;


import java.util.List;


import org.apache.struts.action.ActionForm;

import org.apache.struts.upload.FormFile;


public class CertificateForm extends ActionForm
{
	private static final long serialVersionUID = 1L;
	private List subCategoryList;
	private Integer subCategoryId;
	private FormFile workFile;
	private List personList;
	private List statList;
	private String name;
	private String id;
	private String companyName;
	private Integer companyId;
	private List companyList;
	private String certificationNum;
	private Integer categoryId;
	private Short certificateTypeId;
	private String lendInfo;
	private String usedInfo;
	private List categoryList;
	private Integer education;
	private Integer position;
	private String educationStartDateStr;
	private String educationEndDateStr;
	private String positionStartDateStr;
	private String positionEndDateStr;
	private Integer idExist;
	private Integer certificateExist;
	private Integer issueYear;

	
	public Short getCertificateTypeId()
	{
		return this.certificateTypeId;
		}

	public void setCertificateTypeId(Short certificateTypeId) {
		this.certificateTypeId = certificateTypeId;
		}

	
	public List getCategoryList()
	{
		return this.categoryList;
		}

	public void setCategoryList(List categoryList) {
		this.categoryList = categoryList;
		}

	public Integer getCategoryId() {
		return this.categoryId;
		}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
		}

	public List getPersonList() {
		return this.personList;
		}

	public void setPersonList(List personList) {
		this.personList = personList;
		}

	public List getSubCategoryList() {
		return this.subCategoryList;
		}

	public void setSubCategoryList(List subCategoryList) {
		this.subCategoryList = subCategoryList;
		}

	public FormFile getWorkFile() {
		return this.workFile;
		}

	public void setWorkFile(FormFile workFile) {
		this.workFile = workFile;
		}

	public Integer getSubCategoryId() {
		return this.subCategoryId;
		}

	public void setSubCategoryId(Integer subCategoryId) {
		this.subCategoryId = subCategoryId;
		}

	public String getName() {
		return this.name;
		}

	public void setName(String name) {
		this.name = name;
		}

	public String getId() {
		return this.id;
		}

	public void setId(String id) {
		this.id = id;
		}

	public String getCompanyName() {
		return this.companyName;
		}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
		}

	public Integer getCompanyId() {
		return this.companyId;
		}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
		}

	public List getCompanyList() {
		return this.companyList;
		}

	public void setCompanyList(List companyList) {
		this.companyList = companyList;
		}

	public String getCertificationNum() {
		return this.certificationNum;
		}

	public void setCertificationNum(String certificationNum) {
		this.certificationNum = certificationNum;
		}

	public List getStatList() {
		return this.statList;
		}

	public void setStatList(List statList) {
		this.statList = statList;
		}

	public String getLendInfo() {
		return this.lendInfo;
		}

	public void setLendInfo(String lendInfo) {
		this.lendInfo = lendInfo;
		}

	public String getUsedInfo() {
		return this.usedInfo;
		}

	public void setUsedInfo(String usedInfo) {
		this.usedInfo = usedInfo;
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

	public String getEducationStartDateStr() {
		return educationStartDateStr;
	}

	public void setEducationStartDateStr(String educationStartDateStr) {
		this.educationStartDateStr = educationStartDateStr;
	}

	public String getEducationEndDateStr() {
		return educationEndDateStr;
	}

	public void setEducationEndDateStr(String educationEndDateStr) {
		this.educationEndDateStr = educationEndDateStr;
	}

	public String getPositionStartDateStr() {
		return positionStartDateStr;
	}

	public void setPositionStartDateStr(String positionStartDateStr) {
		this.positionStartDateStr = positionStartDateStr;
	}

	public String getPositionEndDateStr() {
		return positionEndDateStr;
	}

	public void setPositionEndDateStr(String positionEndDateStr) {
		this.positionEndDateStr = positionEndDateStr;
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

	public Integer getIssueYear() {
		return issueYear;
	}

	public void setIssueYear(Integer issueYear) {
		this.issueYear = issueYear;
	}

}

/*
 * Location: D:\Documents\certificate\WEB-INF\classes\ Qualified Name:
 * mfw.acegi.form.CertificateForm JD-Core Version: 0.6.2
 */