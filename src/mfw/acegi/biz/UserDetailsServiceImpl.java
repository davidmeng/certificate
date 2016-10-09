package mfw.acegi.biz;


import mfw.acegi.pojo.User;

import org.acegisecurity.userdetails.UserDetails;

import org.acegisecurity.userdetails.UserDetailsService;

import org.acegisecurity.userdetails.UsernameNotFoundException;

import org.springframework.dao.DataAccessException;


public class UserDetailsServiceImpl
implements UserDetailsService
{
	IUserServiceBiz userServiceBiz;

	
	@Override
	public UserDetails loadUserByUsername(String userName)
	throws UsernameNotFoundException, DataAccessException
	{
		User user = this.userServiceBiz.getUserByUserName(userName);
		if (user == null)
		{
			return null;
			}
		
		MyUserDetails userDetails = new MyUserDetails(user);
		return userDetails;
		}

	
	public IUserServiceBiz getuserServiceBiz()
	{
		return this.userServiceBiz;
		}

	
	public void setuserServiceBiz(IUserServiceBiz userServiceBiz) {
		this.userServiceBiz = userServiceBiz;
		}
	
}

/*
 * Location: D:\Documents\certificate\WEB-INF\classes\ Qualified Name:
 * mfw.acegi.biz.UserDetailsServiceImpl JD-Core Version: 0.6.2
 */