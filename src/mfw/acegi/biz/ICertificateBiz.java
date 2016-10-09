package mfw.acegi.biz;

import java.util.List;

import mfw.acegi.Exception.ApplicateionException;
import mfw.acegi.dao.util.QueryPage;
import mfw.acegi.form.CertificateForm;
import mfw.acegi.pojo.Book;
import mfw.acegi.pojo.Company;
import mfw.acegi.pojo.Person;
import mfw.acegi.vo.PersonAssignVo;
import mfw.acegi.vo.SubCategoryStatisticsVo;

import org.apache.struts.upload.FormFile;

public abstract interface ICertificateBiz {
	public abstract List getAllSubCategory();

	public abstract void initData(int paramInt, FormFile paramFormFile) throws Exception;

	public abstract List getAllPerson();

	public abstract List getPersonByPage(QueryPage paramQueryPage);

	public abstract void saveCertificate(Book paramBook);

	public abstract Book getCertificateById(Integer paramInteger);

	public abstract void test() throws Exception;

	public abstract List<SubCategoryStatisticsVo> getStatisticsVo(Short paramShort);

	public abstract List<Company> getAllCompanyList();

	public abstract int savePerson(Person paramPerson, String paramString, Book paramBook) throws Exception;

	public abstract Person getPersonById(Integer paramInteger);

	public abstract void lockCertificate(int paramInt);

	public abstract void unlockCertificate(int paramInt);

	public abstract void deleteBook(Book paramBook);

	public abstract void scramblePersonInfo();

	public abstract List<PersonAssignVo> getAssignPersonList(String paramString, int[] paramArrayOfInt1, int[] paramArrayOfInt2)
			throws ApplicateionException;

	public abstract void batchAssign(int[] paramArrayOfInt, String paramString);

	public abstract List getPersonListBy(CertificateForm certificationForm);
}

/*
 * Location: D:\Documents\certificate\WEB-INF\classes\ Qualified Name:
 * mfw.acegi.biz.ICertificateBiz JD-Core Version: 0.6.2
 */