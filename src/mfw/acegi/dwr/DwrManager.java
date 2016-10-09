package mfw.acegi.dwr;


import mfw.acegi.biz.ICertificateBiz;

import mfw.acegi.biz.IUserBiz;

import mfw.acegi.pojo.Book;

import mfw.acegi.pojo.User;


public class DwrManager
{
	private ICertificateBiz certificateBiz;
	private IUserBiz userBiz;
	Short lock = new Short((short) 1);
	Short unlock = new Short((short) 2);

	
	public void lockCertificate(int id)
	{
		this.certificateBiz.lockCertificate(id);
		}

	
	public void unlockCertificate(int id) {
		this.certificateBiz.unlockCertificate(id);
		}

	public void updateUsedInfo(int id, String usedInfo) {
		Book c = this.certificateBiz.getCertificateById(new Integer(id));
		c.setUsedInfo(usedInfo);
		this.certificateBiz.saveCertificate(c);
		}

	
	public void updateLendInfo(int id, String lendInfo) {
		Book c = this.certificateBiz.getCertificateById(new Integer(id));
		c.setLendInfo(lendInfo);
		this.certificateBiz.saveCertificate(c);
		}

	
	public Book getCertificateById(int id) {
		return this.certificateBiz.getCertificateById(new Integer(id));
		}

	
	public boolean isUserExist(String userName)
	{
		User user = this.userBiz.getUserByUserName(userName);
		if (user == null)
			return false;
		return true;
		}

	
	public ICertificateBiz getCertificateBiz()
	{
		return this.certificateBiz;
		}

	
	public void setCertificateBiz(ICertificateBiz certificateBiz) {
		this.certificateBiz = certificateBiz;
		}

	public IUserBiz getUserBiz() {
		return this.userBiz;
		}

	public void setUserBiz(IUserBiz userBiz) {
		this.userBiz = userBiz;
		}
	
}

/*
 * Location: D:\Documents\certificate\WEB-INF\classes\ Qualified Name:
 * mfw.acegi.dwr.DwrManager JD-Core Version: 0.6.2
 */