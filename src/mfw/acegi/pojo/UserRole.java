package mfw.acegi.pojo;


import java.io.Serializable;


public class UserRole
implements Serializable
{
	private Integer id;
	private User user;
	private Role role;

	
	public UserRole()
	{
		}

	
	public UserRole(User user, Role role)
	{
		this.user = user;
		this.role = role;
		}

	
	public Integer getId()
	{
		return this.id;
		}

	
	public void setId(Integer id) {
		this.id = id;
		}

	
	public User getUser() {
		return this.user;
		}

	
	public void setUser(User user) {
		this.user = user;
		}

	
	public Role getRole() {
		return this.role;
		}

	
	public void setRole(Role role) {
		this.role = role;
		}
	
}

/*
 * Location: D:\Documents\certificate\WEB-INF\classes\ Qualified Name:
 * mfw.acegi.pojo.UserRole JD-Core Version: 0.6.2
 */