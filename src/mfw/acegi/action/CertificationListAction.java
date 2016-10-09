package mfw.acegi.action;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import mfw.acegi.biz.ICertificateBiz;

import mfw.acegi.constants.Utils;

import mfw.acegi.form.CertificateForm;

import org.apache.struts.action.ActionForm;

import org.apache.struts.action.ActionForward;

import org.apache.struts.action.ActionMapping;

import org.apache.struts.actions.DispatchAction;

import org.apache.struts.upload.FormFile;


public class CertificationListAction extends DispatchAction
{
	private ICertificateBiz certificateBiz;
	Short projectTypeId = new Short((short) 1);

	
	public ActionForward home(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	throws Exception
	{
		CertificateForm cerficateForm = (CertificateForm) form;
		if (cerficateForm == null) {
			cerficateForm = new CertificateForm();
			cerficateForm.setSubCategoryList(this.certificateBiz.getAllSubCategory());
			request.setAttribute("cerficateForm", cerficateForm);
			return mapping.findForward("success");
			}
		
		Integer subCategoryId = cerficateForm.getSubCategoryId();
		FormFile file = cerficateForm.getWorkFile();
		
		if ((subCategoryId != null) && (subCategoryId.intValue() != 0) && (file != null)) {
			this.certificateBiz.initData(subCategoryId.intValue(), file);
			}
		
		cerficateForm.setSubCategoryList(this.certificateBiz.getAllSubCategory());
		request.setAttribute("cerficateForm", cerficateForm);
		return mapping.findForward("success");
		}

	
	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		CertificateForm cerficateForm = (CertificateForm) form;
		String id = cerficateForm.getId();
		String number = cerficateForm.getCertificationNum();
		String name = cerficateForm.getName();
		Integer categoryId = cerficateForm.getCategoryId();
		Integer subCategoryId = cerficateForm.getSubCategoryId();
		String companyName = cerficateForm.getCompanyName();
		Short certificateTypeId = cerficateForm.getCertificateTypeId();
		Integer education = cerficateForm.getEducation();
		Integer position = cerficateForm.getPosition();
		String educationDateStartStr = cerficateForm.getEducationStartDateStr();
		String educationDateEndStr = cerficateForm.getEducationEndDateStr();
		String positionStartDateStr = cerficateForm.getPositionStartDateStr();
		String positionEndDateStr = cerficateForm.getPositionEndDateStr();
		
		cerficateForm.setSubCategoryList(this.certificateBiz.getAllSubCategory());
		if (!Utils.isNotNull(certificateTypeId)) {
			certificateTypeId = new Short((short) 2);
			cerficateForm.setCertificateTypeId(certificateTypeId);
			}
		
		List list = this.certificateBiz.getPersonListBy(cerficateForm);
		List statList = this.certificateBiz.getStatisticsVo(certificateTypeId);
		
		cerficateForm.setPersonList(list);
		cerficateForm.setStatList(statList);
		
		if ((cerficateForm.getCertificateTypeId() != null)
				&& (cerficateForm.getCertificateTypeId().shortValue() == this.projectTypeId.shortValue())) {
			return mapping.findForward("list1");
			}
		
		return mapping.findForward("list");
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
 * mfw.acegi.action.CertificationListAction JD-Core Version: 0.6.2
 */