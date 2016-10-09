package mfw.acegi.action;


import java.util.HashMap;

import java.util.List;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import mfw.acegi.Exception.ApplicateionException;

import mfw.acegi.biz.ICertificateBiz;

import mfw.acegi.form.AssignForm;

import mfw.acegi.vo.SubCategoryStatisticsVo;

import org.apache.struts.action.ActionForm;

import org.apache.struts.action.ActionForward;

import org.apache.struts.action.ActionMapping;

import org.apache.struts.actions.DispatchAction;


public class AssignAction extends DispatchAction
{
	private ICertificateBiz certificateBiz;

	
	public ActionForward create(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	throws Exception
	{
		AssignForm assignForm = (AssignForm) form;
		assignForm.setSubCategoryList(this.certificateBiz.getStatisticsVo(Short.valueOf((short) 2)));
		return mapping.findForward("create");
		}

	
	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		AssignForm assignForm = (AssignForm) form;
		List<SubCategoryStatisticsVo> list = this.certificateBiz.getStatisticsVo(Short.valueOf((short) 2));
		assignForm.setSubCategoryList(list);
		Map subCategoryMap = new HashMap();
		for (SubCategoryStatisticsVo v : list) {
			subCategoryMap.put(Integer.valueOf(v.getSubCategoryId()), Integer.valueOf(v.getCount()));
			}
		assignForm.setSubCategoryMap(subCategoryMap);
		
		int[] subCategoryIds = assignForm.getSubCategoryIds();
		int[] quantitys = assignForm.getQuantitys();
		String companyName = assignForm.getCompanyName();
		try
		{
			List assignList = this.certificateBiz.getAssignPersonList(companyName, subCategoryIds, quantitys);
			assignForm.setAssignList(assignList);
			}
		catch (ApplicateionException e) {
			assignForm.setErrorMessage(e.getMessage());
			}
		return mapping.findForward("create");
		}

	
	public ActionForward assign(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		AssignForm assignForm = (AssignForm) form;
		int[] books = assignForm.getBookIds();
		String usedInfo = assignForm.getUsedInfo();
		this.certificateBiz.batchAssign(books, usedInfo);
		
		return mapping.findForward("assign");
		}

	
	public ICertificateBiz getCertificateBiz() {
		return this.certificateBiz;
		}

	
	public void setCertificateBiz(ICertificateBiz certificateBiz) {
		this.certificateBiz = certificateBiz;
		}
	
}

/*
 * Location: D:\Documents\certificate\WEB-INF\classes\ Qualified Name:
 * mfw.acegi.action.AssignAction JD-Core Version: 0.6.2
 */