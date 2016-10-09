package mfw.acegi.filter;


import java.io.IOException;

import javax.servlet.Filter;

import javax.servlet.FilterChain;

import javax.servlet.FilterConfig;

import javax.servlet.ServletContext;

import javax.servlet.ServletException;

import javax.servlet.ServletRequest;

import javax.servlet.ServletResponse;

import javax.servlet.http.HttpServletRequest;

import mfw.acegi.constants.Constants;


public class InitParameterFilter
implements Filter
{
	private static final long serialVersionUID = 1L;
	boolean init = false;
	ServletContext application;

	
	@Override
	public void destroy()
	{
		}

	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
	throws IOException, ServletException
	{
		if (!this.init) {
			if ((request instanceof HttpServletRequest)) {
				HttpServletRequest httpRequest = (HttpServletRequest) request;
				this.application = httpRequest.getSession().getServletContext();
				}
			this.application.setAttribute("initCompanyName", Constants.companyName);
			this.application.setAttribute("initCompanyIcon", Constants.companyIcon);
			this.init = true;
			}
		chain.doFilter(request, response);
		}

	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException
	{
		this.application = filterConfig.getServletContext();
		}
	
}

/*
 * Location: D:\Documents\certificate\WEB-INF\classes\ Qualified Name:
 * mfw.acegi.filter.InitParameterFilter JD-Core Version: 0.6.2
 */