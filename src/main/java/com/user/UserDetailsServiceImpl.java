package com.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImpl implements UserDetailsService {

	public UserDetailsServiceImpl() {
		// TODO Auto-generated constructor stub
	}
	@Autowired
	private UserService usv;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User u = usv.getUserByNameV2(username);
		if(u == null) {
			throw new UsernameNotFoundException("Không thể tìm thấy username");
		}
		return new MyUserDetails(u);
	}

}
