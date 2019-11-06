package com.srt.service;

import org.springframework.stereotype.Component;

import com.srt.dao.UserDao;
import com.srt.model.Header;
import com.srt.model.Response;
import com.srt.model.UserDetails;

@Component
public class UserService implements UserDao {

	@Override
	public Response<UserDetails> getUserProfile(UserDetails user) {
		Response<UserDetails> resp = new Response<UserDetails>();
 		resp.setData(user);
		Header head = new Header();
		head.setMessage("Successfully Executed");
		head.setSuccess(true);
		resp.setHeader(head);
		return resp; 
	}

}
