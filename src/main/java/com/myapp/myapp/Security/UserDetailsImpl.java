package com.myapp.myapp.Security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.myapp.myapp.Models.Role;
import com.myapp.myapp.Models.User;



public class UserDetailsImpl  implements UserDetails{
	
	private int id;
    private String email;
    private String password;
    private Role role ;
    private boolean isactive ;

    public boolean isIsactive() {
        return isactive;
    }

    public void setIsactive(boolean isactive) {
        this.isactive = isactive;
    }

    public UserDetailsImpl(User user) {
        this.id = user.getCin();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.role = user.getRole() ;
        this.isactive = user.isActive();
    }

    @Override
    public String getPassword() {
        return password;
    }
   
    public Role getRole() {
    	return role ; 
    }

    @Override
    public String getUsername() {
        return email;
    }

    public int getId() {
        return id;
    }
    
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

}
