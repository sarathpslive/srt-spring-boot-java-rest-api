package com.srt.dao;

import com.srt.model.Response;
import com.srt.model.AuthUser;

public interface AuthDao {

	Response<AuthUser> login(AuthUser user);

}
