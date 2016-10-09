package mfw.acegi.vo;

public class CertificateQueryVo {

	private String name;
	private String personInfo;
	private String number;
	private Integer categoryId;
	private Integer subCategoryId;
	private Integer education;
	private Integer position;
	private String educationStartDateStr;
	private String educationEndDateStr;
	private String positionStartDateStr;
	private String positionEndDateStr;
	private String companyName;
	private Short typeId;
	private String lendInfo;
	private String usedInfo;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPersonInfo() {
		return personInfo;
	}

	public void setPersonInfo(String personInfo) {
		this.personInfo = personInfo;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public Integer getSubCategoryId() {
		return subCategoryId;
	}

	public void setSubCategoryId(Integer subCategoryId) {
		this.subCategoryId = subCategoryId;
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

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Short getTypeId() {
		return typeId;
	}

	public void setTypeId(Short typeId) {
		this.typeId = typeId;
	}

	public String getLendInfo() {
		return lendInfo;
	}

	public void setLendInfo(String lendInfo) {
		this.lendInfo = lendInfo;
	}

	public String getUsedInfo() {
		return usedInfo;
	}

	public void setUsedInfo(String usedInfo) {
		this.usedInfo = usedInfo;
	}
}
