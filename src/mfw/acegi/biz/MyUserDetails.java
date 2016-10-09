package mfw.acegi.biz;


import java.util.ArrayList;

import java.util.Iterator;

import java.util.List;

import mfw.acegi.pojo.Role;

import mfw.acegi.pojo.User;

import mfw.acegi.pojo.UserRole;

import org.acegisecurity.GrantedAuthority;

import org.acegisecurity.GrantedAuthorityImpl;

import org.acegisecurity.userdetails.UserDetails;


public class MyUserDetails
implements UserDetails
{
	User user;
	private GrantedAuthority[] authorityArray;

	
	MyUserDetails(User user)
	{
		this.user = user;
		this.authorityArray = initAuthorities();
		}

	
	MyUserDetails() {
		}

	
	public GrantedAuthority[] initAuthorities() {
		List roleList = new ArrayList();
		for (Iterator itor = this.user.getUserRoles().iterator(); itor.hasNext();)
		{
			UserRole userRole = (UserRole) itor.next();
			roleList.add(userRole.getRole());
			}
		
		GrantedAuthority[] gaArray = new GrantedAuthority[roleList.size()];
		for (int i = 0; i < roleList.size(); i++)
		{
			gaArray[i] = new GrantedAuthorityImpl(((Role) roleList.get(i)).getRoleName());
			}
		
		return gaArray;
		}

	
	@Override
	public String getPassword()
	{
		return this.user.getPassword();
		}

	
	@Override
	public String getUsername()
	{
		return this.user.getUserName();
		}

	
	@Override
	public boolean isAccountNonExpired()
	{
		return true;
		}

	
	@Override
	public boolean isAccountNonLocked()
	{
		return true;
		}

	
	@Override
	public boolean isCredentialsNonExpired()
	{
		return true;
		}

	
	@Override
	public boolean isEnabled()
	{
		return true;
		}

	
	@Override
	public GrantedAuthority[] getAuthorities() {
		return this.authorityArray;
		}

	public User getUser() {
		return this.user;
		}

	public void setUser(User user) {
		this.user = user;
		}
	
}

/*
 * Location: D:\Documents\certificate\WEB-INF\classes\ Qualified Name:
 * mfw.acegi.biz.MyUserDetails JD-Core Version: 0.6.2
 */