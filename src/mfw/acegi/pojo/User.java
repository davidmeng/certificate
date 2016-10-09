package mfw.acegi.pojo;


import java.io.Serializable;

import java.util.HashSet;

import java.util.Set;


public class User
implements Serializable
{
	private Integer id;
	private String userName;
	private String password;
	private Set userRoles = new HashSet(0);

	
	public User()
	{
		}

	
	public User(String userName, String password, Set userRoles)
	{
		this.userName = userName;
		this.password = password;
		this.userRoles = userRoles;
		}

	
	public Integer getId()
	{
		return this.id;
		}

	
	public void setId(Integer id) {
		this.id = id;
		}

	
	public String getUserName() {
		return this.userName;
		}

	
	public void setUserName(String userName) {
		this.userName = userName;
		}

	
	public String getPassword() {
		return this.password;
		}

	
	public void setPassword(String password) {
		this.password = password;
		}

	
	public Set getUserRoles() {
		return this.userRoles;
		}

	
	public void setUserRoles(Set userRoles) {
		this.userRoles = userRoles;
		}
	
}

/*
 * Location: D:\Documents\certificate\WEB-INF\classes\ Qualified Name:
 * mfw.acegi.pojo.User JD-Core Version: 0.6.2
 */