package mfw.acegi.action;


import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import mfw.acegi.biz.IUserBiz;

import mfw.acegi.form.UserForm;

import mfw.acegi.pojo.User;

import org.acegisecurity.providers.encoding.MessageDigestPasswordEncoder;

import org.apache.struts.action.ActionForm;

import org.apache.struts.action.ActionForward;

import org.apache.struts.action.ActionMapping;

import org.apache.struts.actions.DispatchAction;


public class UserAction extends DispatchAction
{
	IUserBiz userBiz;
	MessageDigestPasswordEncoder md5 = new MessageDigestPasswordEncoder("MD5");

	
	public ActionForward addUser(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		return mapping.findForward("newUser");
		}

	
	public ActionForward saveUser(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		UserForm userForm = (UserForm) form;
		User user = new User();
		
		Integer id = userForm.getId();
		if ((id != null) && (id.intValue() != 0))
		{
			user = this.userBiz.getUserById(id);
			}
		user.setUserName(userForm.getUsername());
		if ((id == null) || (id.intValue() == 0))
		{
			this.md5.setEncodeHashAsBase64(false);
			user.setPassword(this.md5.encodePassword(userForm.getPassword(), null));
			}
		
		if ((user.getPassword() != null) && (!user.getPassword().equals("")) && (user.getUserName() != null)
				&& (!"".equals(user.getUserName()))) {
			this.userBiz.saveUser(user);
			}
		return mapping.findForward("save");
		}

	
	public ActionForward login(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		return mapping.findForward("newUser");
		}

	
	public ActionForward logout(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		request.getSession().removeAttribute("ACEGI_SECURITY_LAST_USERNAME");
		
		String url = "/product.do?method=list";
		ActionForward af = new ActionForward();
		af.setPath(url);
		af.setRedirect(true);
		return af;
		}

	
	public ActionForward resetPassword(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		String userName = (String) request.getSession().getAttribute("ACEGI_SECURITY_LAST_USERNAME");
		User user = this.userBiz.getUserByUserName(userName);
		
		String oldPassword = request.getParameter("oldPassword");
		String newPassword = request.getParameter("newPassword");
		this.md5.setEncodeHashAsBase64(false);
		if (this.md5.encodePassword(oldPassword, null).equals(user.getPassword()))
		{
			user.setPassword(this.md5.encodePassword(newPassword, null));
			this.userBiz.saveUser(user);
			return mapping.findForward("success");
			}
		
		request.setAttribute("notEqual", Boolean.TRUE);
		return mapping.findForward("fail");
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
 * mfw.acegi.action.UserAction JD-Core Version: 0.6.2
 */