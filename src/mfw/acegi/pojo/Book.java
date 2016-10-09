package mfw.acegi.pojo;


import java.io.Serializable;

import java.sql.Timestamp;

import java.util.HashSet;

import java.util.Set;


public class Book
implements Serializable
{
	private Integer id;
	private BookType bookType;
	private Person person;
	private SubCategory subCategory;
	private String number;
	private Timestamp issueDate;
	private Timestamp effictiveDate;
	private Timestamp lastExamDate;
	private String remark;
	private Short isNew;
	private Integer sequance;
	private String usedInfo;
	private String lendInfo;
	private Short lockInfo;
	private Set personBooks = new HashSet(0);
	        private BookStatus bookStatus ;

	
	public Book()
	{
		}

	
	public Book(BookType bookType, String number, Timestamp issueDate, String usedInfo, String lendInfo, Short lockInfo)
	{
		this.bookType = bookType;
		this.number = number;
		this.issueDate = issueDate;
		this.usedInfo = usedInfo;
		this.lendInfo = lendInfo;
		this.lockInfo = lockInfo;
		}

	
	public Book(BookType bookType, SubCategory subCategory, String number, Timestamp issueDate, Timestamp effictiveDate,
			Timestamp lastExamDate, String remark, Short isNew, Integer sequance, String usedInfo, String lendInfo, Short lockInfo,
			Set personBooks)
	{
		this.bookType = bookType;
		this.subCategory = subCategory;
		this.number = number;
		this.issueDate = issueDate;
		this.effictiveDate = effictiveDate;
		this.lastExamDate = lastExamDate;
		this.remark = remark;
		this.isNew = isNew;
		this.sequance = sequance;
		this.usedInfo = usedInfo;
		this.lendInfo = lendInfo;
		this.lockInfo = lockInfo;
		this.personBooks = personBooks;
		}

	
	public Integer getId()
	{
		return this.id;
		}

	
	public void setId(Integer id) {
		this.id = id;
		}

	
	public BookType getBookType() {
		return this.bookType;
		}

	
	public void setBookType(BookType bookType) {
		this.bookType = bookType;
		}

	
	public SubCategory getSubCategory() {
		return this.subCategory;
		}

	
	public void setSubCategory(SubCategory subCategory) {
		this.subCategory = subCategory;
		}

	
	public String getNumber() {
		return this.number;
		}

	
	public void setNumber(String number) {
		this.number = number;
		}

	
	public Timestamp getIssueDate() {
		return this.issueDate;
		}

	
	public void setIssueDate(Timestamp issueDate) {
		this.issueDate = issueDate;
		}

	
	public Timestamp getEffictiveDate() {
		return this.effictiveDate;
		}

	
	public void setEffictiveDate(Timestamp effictiveDate) {
		this.effictiveDate = effictiveDate;
		}

	
	public Timestamp getLastExamDate() {
		return this.lastExamDate;
		}

	
	public void setLastExamDate(Timestamp lastExamDate) {
		this.lastExamDate = lastExamDate;
		}

	
	public String getRemark() {
		return this.remark;
		}

	
	public void setRemark(String remark) {
		this.remark = remark;
		}

	
	public Short getIsNew() {
		return this.isNew;
		}

	
	public void setIsNew(Short isNew) {
		this.isNew = isNew;
		}

	
	public Integer getSequance() {
		return this.sequance;
		}

	
	public void setSequance(Integer sequance) {
		this.sequance = sequance;
		}

	
	public String getUsedInfo() {
		return this.usedInfo;
		}

	
	public void setUsedInfo(String usedInfo) {
		this.usedInfo = usedInfo;
		}

	
	public String getLendInfo() {
		return this.lendInfo;
		}

	
	public void setLendInfo(String lendInfo) {
		this.lendInfo = lendInfo;
		}

	
	public Short getLockInfo() {
		return this.lockInfo;
		}

	
	public void setLockInfo(Short lockInfo) {
		this.lockInfo = lockInfo;
		}

	
	public Set getPersonBooks() {
		return this.personBooks;
		}

	
	public void setPersonBooks(Set personBooks) {
		this.personBooks = personBooks;
		}

	
	public boolean isLock() {
		if (this.lockInfo == null)
			return false;
		return this.lockInfo.intValue() == 1;
		}

	
	public Person getPerson() {
		return this.person;
		}

	
	public void setPerson(Person person) {
		this.person = person;
		}
	

    
    public BookStatus getBookStatus() {
        return bookStatus;
    }

    
    public void setBookStatus(BookStatus bookStatus) {
        this.bookStatus = bookStatus;
    }
}

/*
 * Location: D:\Documents\certificate\WEB-INF\classes\ Qualified Name:
 * mfw.acegi.pojo.Book JD-Core Version: 0.6.2
 */