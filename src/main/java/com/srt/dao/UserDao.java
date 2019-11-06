package com.srt.dao;

import com.srt.model.Response;
import com.srt.model.UserDetails;

public interface UserDao {
	Response<UserDetails> getUserProfile(UserDetails user);
}
