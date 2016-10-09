package mfw.acegi.pojo;


import java.io.Serializable;

import java.util.HashSet;

import java.util.Set;


public class Role
implements Serializable
{
	private Integer id;
	private String roleName;
	private Set userRoles = new HashSet(0);

	
	public Role()
	{
		}

	
	public Role(String roleName)
	{
		this.roleName = roleName;
		}

	
	public Role(String roleName, Set userRoles)
	{
		this.roleName = roleName;
		this.userRoles = userRoles;
		}

	
	public Integer getId()
	{
		return this.id;
		}

	
	public void setId(Integer id) {
		this.id = id;
		}

	
	public String getRoleName() {
		return this.roleName;
		}

	
	public void setRoleName(String roleName) {
		this.roleName = roleName;
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
 * mfw.acegi.pojo.Role JD-Core Version: 0.6.2
 */