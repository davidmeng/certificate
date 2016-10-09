package mfw.acegi.form;


import java.util.List;

import java.util.Map;

import mfw.acegi.vo.PersonAssignVo;

import mfw.acegi.vo.SubCategoryStatisticsVo;

import org.apache.struts.action.ActionForm;


public class AssignForm extends ActionForm
{
	private static final long serialVersionUID = 4867686475258621124L;
	private String companyName;
	private String[] bookNumber;
	private List<SubCategoryStatisticsVo> subCategoryList;
	private int[] subCategoryIds;
	private int[] quantitys;
	private List<PersonAssignVo> assignList;
	private String errorMessage;
	private Map<Integer, Integer> subCategoryMap;
	private int[] bookIds;
	private String usedInfo;

	
	public String getCompanyName()
	{
		return this.companyName;
		}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
		}

	public String[] getBookNumber() {
		return this.bookNumber;
		}

	public void setBookNumber(String[] bookNumber) {
		this.bookNumber = bookNumber;
		}

	public List<SubCategoryStatisticsVo> getSubCategoryList() {
		return this.subCategoryList;
		}

	public void setSubCategoryList(List<SubCategoryStatisticsVo> subCategoryList) {
		this.subCategoryList = subCategoryList;
		}

	public int[] getSubCategoryIds() {
		return this.subCategoryIds;
		}

	public void setSubCategoryIds(int[] subCategoryIds) {
		this.subCategoryIds = subCategoryIds;
		}

	public int[] getQuantitys() {
		return this.quantitys;
		}

	public void setQuantitys(int[] quantitys) {
		this.quantitys = quantitys;
		}

	public List<PersonAssignVo> getAssignList() {
		return this.assignList;
		}

	public void setAssignList(List<PersonAssignVo> assignList) {
		this.assignList = assignList;
		}

	public String getErrorMessage() {
		return this.errorMessage;
		}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
		}

	public Map<Integer, Integer> getSubCategoryMap() {
		return this.subCategoryMap;
		}

	public void setSubCategoryMap(Map<Integer, Integer> subCategoryMap) {
		this.subCategoryMap = subCategoryMap;
		}

	public int[] getBookIds() {
		return this.bookIds;
		}

	public void setBookIds(int[] bookIds) {
		this.bookIds = bookIds;
		}

	public String getUsedInfo() {
		return this.usedInfo;
		}

	public void setUsedInfo(String usedInfo) {
		this.usedInfo = usedInfo;
		}
	
}

/*
 * Location: D:\Documents\certificate\WEB-INF\classes\ Qualified Name:
 * mfw.acegi.form.AssignForm JD-Core Version: 0.6.2
 */