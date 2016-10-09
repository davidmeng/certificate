package mfw.acegi.filter;


import java.io.IOException;

import java.util.Iterator;

import javax.servlet.Filter;

import javax.servlet.FilterChain;

import javax.servlet.FilterConfig;

import javax.servlet.ServletException;

import javax.servlet.ServletRequest;

import javax.servlet.ServletResponse;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpSession;

import mfw.acegi.biz.IUserServiceBiz;

import mfw.acegi.pojo.User;

import mfw.acegi.pojo.UserRole;


public class PermissionFilter
implements Filter
{
	private IUserServiceBiz userServiceBiz;
	private FilterConfig filterConfig;

	
	@Override
	public void destroy()
	{
		}

	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
	throws IOException, ServletException
	{
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		String username = (String) session.getAttribute("ACEGI_SECURITY_LAST_USERNAME");
		
		if (session.getAttribute("MODIFY") == null)
		{
			if ((username != null) && (!username.equals("")) && (!username.equals("null"))) {
				User user = this.userServiceBiz.getUserByUserName(username);
				session.setAttribute("MODIFY", Boolean.valueOf(false));
				for (Iterator itor = user.getUserRoles().iterator(); itor.hasNext();) {
					UserRole ur = (UserRole) itor.next();
					if (ur.getRole().getRoleName().equals("ROLE_SUPER")) {
						session.setAttribute("MODIFY", Boolean.valueOf(true));
						}
					}
				}
			}
		chain.doFilter(request, response);
		}

	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException
	{
		this.filterConfig = filterConfig;
		}

	
	public IUserServiceBiz getUserServiceBiz() {
		return this.userServiceBiz;
		}

	
	public void setUserServiceBiz(IUserServiceBiz userServiceBiz) {
		this.userServiceBiz = userServiceBiz;
		}
	
}

/*
 * Location: D:\Documents\certificate\WEB-INF\classes\ Qualified Name:
 * mfw.acegi.filter.PermissionFilter JD-Core Version: 0.6.2
 */