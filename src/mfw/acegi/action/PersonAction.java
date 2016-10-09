package mfw.acegi.action;


import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import mfw.acegi.biz.ICertificateBiz;
import mfw.acegi.constants.Constants;
import mfw.acegi.constants.Utils;
import mfw.acegi.form.PersonForm;
import mfw.acegi.pojo.Book;
import mfw.acegi.pojo.BookStatus;
import mfw.acegi.pojo.Person;
import mfw.acegi.pojo.SubCategory;



public class PersonAction extends DispatchAction
{
	private ICertificateBiz certificateBiz;
	
	public ActionForward addPerson(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	throws Exception
	{
		PersonForm personForm = (PersonForm) form;
		Integer id = personForm.getId();
		if (Utils.isNotNull(id)) {
			Person p = this.certificateBiz.getPersonById(id);
			personForm.setCompanyName(p.getCompany().getName());
			personForm.setPersonInfo(p.getPersonInfo());
			personForm.setGender(p.getGender());
			personForm.setName(p.getName());
			}
		
		personForm.setSubCategoryList(this.certificateBiz.getAllSubCategory());
		return mapping.findForward("newPerson");
		}

	
	public ActionForward savePerson(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	throws Exception
	{
		PersonForm personForm = (PersonForm) form;
		Integer personId = personForm.getPersonId();
		String personInfo = personForm.getPersonInfo();
		String companyName = personForm.getCompanyName();
		String name = personForm.getName();
		Short gender = personForm.getGender();
		Person person = new Person();
		
		if (Utils.isNotNull(personId)) {
			person.setId(personId);
			}
		person.setGender(gender);
		person.setName(name);
		person.setPersonInfo(personInfo);
		person.setEducation(personForm.getEducation());
		person.setPosition(personForm.getPosition());
		person.setEducationDate(getByString(personForm.getEducationDate()));
		person.setPositionDate(getByString(personForm.getPositionDate()));
		person.setCertificateExist(personForm.getCertificateExist() == null ? -1 : personForm.getCertificateExist());
		person.setIdExist(personForm.getIdExist() == null ? -1 : personForm.getIdExist());
		
		Book b = new Book();
		if (Utils.isNull(personForm.getBookNbr())) {
			b = null;
			}
		else {
	        BookStatus valid = new BookStatus();
	        valid.setId(1);
	        b.setBookStatus(valid);
			b.setNumber(personForm.getBookNbr());
			b.setPerson(person);
			SubCategory sc = new SubCategory();
			sc.setId(personForm.getSubCategoryId());
			b.setSubCategory(sc);
			b.setIssueDate(getByString(personForm.getBookCreateDate()));
			b.setEffictiveDate(getByString(personForm.getBookExamDate()));
			b.setSequance(personForm.getBookSequance());
			}
		int id = this.certificateBiz.savePerson(person, companyName, b);
		
		ActionForward f = mapping.findForward("showPersonDetail");
		ActionForward f1 = new ActionForward();
		f1.setRedirect(true);
		f1.setPath(f.getPath() + "&personId=" + id);
		return f1;
		}

	
	public ActionForward personDetail(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	throws Exception
	{
		PersonForm personForm = (PersonForm) form;
		Integer personId = personForm.getPersonId();
		Person person = this.certificateBiz.getPersonById(personId);
		personForm.setName(person.getName());
		personForm.setCompanyName(person.getCompany().getName());
		personForm.setPersonInfo(person.getPersonInfo());
		personForm.setGender(person.getGender());
		personForm.setPerson(person);
		personForm.setEducation(person.getEducation());
		personForm.setEducationDate(person.getEducationDateStr());
		personForm.setPosition(person.getPosition());
		personForm.setPositionDate(person.getPositionDateStr());
		personForm.setIdExist(person.getIdExist());
		personForm.setCertificateExist(person.getCertificateExist());
		personForm.setSubCategoryList(this.certificateBiz.getAllSubCategory());
		
		return mapping.findForward("personDetail");
		}

	
	public ActionForward deleteBook(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	        throws Exception
	{
		PersonForm personForm = (PersonForm) form;
		Integer bookId = personForm.getBookId();
		Book book = this.certificateBiz.getCertificateById(bookId);
		Integer personId = book.getPerson().getId();
		
		this.certificateBiz.deleteBook(book);
		
		ActionForward f = mapping.findForward("showPersonDetail");
		ActionForward f1 = new ActionForward();
		f1.setPath(f.getPath() + "&personId=" + personId);
		f1.setRedirect(true);
		return f1;
	}
	
	public ActionForward expiredBook(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        PersonForm personForm = (PersonForm) form;
        Integer bookId = personForm.getBookId();
        Book book = this.certificateBiz.getCertificateById(bookId);
        Integer personId = book.getPerson().getId();
        
        BookStatus expired = new BookStatus();
        expired.setId(2);
        book.setBookStatus(expired);
        
        this.certificateBiz.saveCertificate(book);
        
        ActionForward f = mapping.findForward("showPersonDetail");
        ActionForward f1 = new ActionForward();
        f1.setPath(f.getPath() + "&personId=" + personId);
        f1.setRedirect(true);
        return f1;
    }
	
	public ActionForward validBook(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        PersonForm personForm = (PersonForm) form;
        Integer bookId = personForm.getBookId();
        Book book = this.certificateBiz.getCertificateById(bookId);
        Integer personId = book.getPerson().getId();
        
        BookStatus valid = new BookStatus();
        valid.setId(1);
        book.setBookStatus(valid);
        
        this.certificateBiz.saveCertificate(book);
        
        ActionForward f = mapping.findForward("showPersonDetail");
        ActionForward f1 = new ActionForward();
        f1.setPath(f.getPath() + "&personId=" + personId);
        f1.setRedirect(true);
        return f1;
    }

	
	public ICertificateBiz getCertificateBiz()
	{
		return this.certificateBiz;
		}

	
	public void setCertificateBiz(ICertificateBiz certificateBiz) {
		this.certificateBiz = certificateBiz;
		}

	
	public Timestamp getByString(String dateStr) throws ParseException
	{
		if (Utils.isNull(dateStr)) {
			return new Timestamp(Constants.DATE_FOR_NULL.getTime());
			}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = sdf.parse(dateStr);
		return new Timestamp(date.getTime());
		}
	
}

/*
 * Location: D:\Documents\certificate\WEB-INF\classes\ Qualified Name:
 * mfw.acegi.action.PersonAction JD-Core Version: 0.6.2
 */