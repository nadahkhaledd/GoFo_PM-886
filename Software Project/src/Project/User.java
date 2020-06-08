package Project;

import java.util.Scanner ;

/**
 *
 * @author Omar 
 */
public class User {

    protected String F_Name ;
    protected String L_Name ;
    protected String Username ;
    protected String NationalID ;
    protected String Phone;
    protected String Password ;
    protected String Email ;

    public User() {}
    public User(String f_Name, String l_Name, String username, String id, String phone, String password, String email) {
		F_Name = f_Name;
		L_Name = l_Name;
		Username = username;
		NationalID = id;
		Phone = phone;
		Password = password;
		Email = email;
	}
    
    
    
	public String getF_Name() {
		return F_Name;
	}
	public void setF_Name(String f_Name) {
		F_Name = f_Name;
	}

	public String getL_Name() {
		return L_Name;
	}
	public void setL_Name(String l_Name) {
		L_Name = l_Name;
	}
 
	
	public String getUsername() {
		return Username;
	}
	public void setUsername(String username) {
		Username = username;
	}

	
	public String getID() {
		return NationalID;
	}
	public void setID(String id) {
		NationalID = id;
	}
	
	public String getPhone()
	{
		return Phone;
	}
	public void setPhone(String phone)
	{
		Phone = phone;
	}

	
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}

	
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}


	
	/**
	 * check if the username and password are correct to login
	 * @param username
	 * @param pass
	 * @return
	 */
	public boolean login_check(String username , String pass )
    {
        if(Username .equals(username)  && Password .equals(pass)) {
        	return true ;
        }else {
        	return false;
        }
    }
    public void register(String fname , String lname ,String username , String id , String pass , String m )
    {
        F_Name = fname ;
        L_Name = lname ;
        Username = username ;
        NationalID = id ;
        Password = pass ;
        Email = m ;
    }
    
    /**
     * print user data 
     */
    public void getInfo() {
		System.out.print("First name : ");
		System.out.println(this.F_Name);
		System.out.print("Last name : ");
		System.out.println(this.L_Name);
		System.out.print("Username : ");
		System.out.println(this.Username);
		System.out.print("National ID : ");
		System.out.println(this.NationalID);
		System.out.print("Phone number: ");
		System.out.println(this.Phone);
		System.out.print("Email : ");
		System.out.println(this.Email);
	}



}