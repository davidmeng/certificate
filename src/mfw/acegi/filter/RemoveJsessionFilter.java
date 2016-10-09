package mfw.acegi.filter;


import java.io.IOException;

import javax.servlet.Filter;

import javax.servlet.FilterChain;

import javax.servlet.FilterConfig;

import javax.servlet.ServletException;

import javax.servlet.ServletRequest;

import javax.servlet.ServletResponse;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;


public class RemoveJsessionFilter
implements Filter
{
	@Override
	public void destroy()
	{
		}

	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
	throws IOException, ServletException
	{
		response = clearSessionId(request, response);
		chain.doFilter(request, response);
		}

	
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		}

	
	private HttpServletResponse clearSessionId(ServletRequest request, ServletResponse response) {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		
		if (httpRequest.isRequestedSessionIdFromURL()) {
			HttpSession session = httpRequest.getSession();
			if (session != null)
				session.invalidate();
			}
		return httpResponse;
		}
	
}

/*
 * Location: D:\Documents\certificate\WEB-INF\classes\ Qualified Name:
 * mfw.acegi.filter.RemoveJsessionFilter JD-Core Version: 0.6.2
 */