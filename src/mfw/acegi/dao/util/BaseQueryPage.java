package mfw.acegi.dao.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class BaseQueryPage {

	Map sortMap = null;

	Map aliasMap = null;

	int pageSize = QueryPage.ROWCOUNT_PER_PAGE;

	int pageCount;

	int recordCount;

	int targetPage;

	List criterionList = new ArrayList();

	List criteriaAliasObjectList = new ArrayList();

	public static BaseQueryPage initPage(HttpServletRequest request) {

		BaseQueryPage page = new BaseQueryPage();
		String targetPageStr = request.getParameter("targetPageStr");
		if (targetPageStr != null && !targetPageStr.equals("")) {

			page.setTargetPage((new Integer(targetPageStr)).intValue());
		} else {

			page.setTargetPage(1);
		}

		return page;
	}

	public Map getSortMap() {
		return sortMap;
	}

	public void setSortMap(Map sortMap) {
		this.sortMap = sortMap;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}

	public int getTargetPage() {
		return targetPage;
	}

	public void setTargetPage(int targetPage) {
		this.targetPage = targetPage;
	}

	public List getCriterionList() {
		return criterionList;
	}

	public void setCriterionList(List criterionList) {
		this.criterionList = criterionList;
	}

	public List getCriteriaAliasObjectList() {
		return criteriaAliasObjectList;
	}

	public void setCriteriaAliasObjectList(List criteriaAliasObjectList) {
		this.criteriaAliasObjectList = criteriaAliasObjectList;
	}

	public Map getAliasMap() {
		return aliasMap;
	}

	public void setAliasMap(Map aliasMap) {
		this.aliasMap = aliasMap;
	}

}
