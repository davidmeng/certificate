package mfw.acegi.filter;


import java.io.IOException;

import javax.servlet.Filter;

import javax.servlet.FilterChain;

import javax.servlet.FilterConfig;

import javax.servlet.ServletException;

import javax.servlet.ServletRequest;

import javax.servlet.ServletResponse;


public class ShopIdUrlFilter
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
		chain.doFilter(request, response);
		}

	
	@Override
	public void init(FilterConfig arg0)
	throws ServletException
	{
		}
	
}

/*
 * Location: D:\Documents\certificate\WEB-INF\classes\ Qualified Name:
 * mfw.acegi.filter.ShopIdUrlFilter JD-Core Version: 0.6.2
 */