package mfw.acegi.form;


import org.apache.struts.action.ActionForm;


public class UserForm extends ActionForm
{
	private static final long serialVersionUID = 1L;
	private String username;
	private String password;
	private Integer id;

	
	public String getUsername()
	{
		return this.username;
		}

	public void setUsername(String username) {
		this.username = username;
		}

	public String getPassword() {
		return this.password;
		}

	public void setPassword(String password) {
		this.password = password;
		}

	public Integer getId() {
		return this.id;
		}

	public void setId(Integer id) {
		this.id = id;
		}
	
}

/*
 * Location: D:\Documents\certificate\WEB-INF\classes\ Qualified Name:
 * mfw.acegi.form.UserForm JD-Core Version: 0.6.2
 */