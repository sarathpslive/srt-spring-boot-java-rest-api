package com.srt.service;

import java.util.Base64;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.srt.dao.AuthDao;
import com.srt.model.Header;
import com.srt.model.Response;
import com.srt.model.AuthUser;

@Service
public class AuthService implements AuthDao {

	@Override
	public Response<AuthUser> login(AuthUser user) {
		Response<AuthUser> resp = new Response<AuthUser>();
		Header head = new Header();
		try {

			Base64.Decoder dec = Base64.getDecoder();

			String decoded = new String(dec.decode(user.getP().getBytes()));

			if (user.getUserName().equals(decoded)) {
				user.setP("");
				resp.setData(user);
				user.setToken(UUID.randomUUID().toString());
				head.setMessage("Successfully Executed");
				head.setSuccess(true);
				resp.setHeader(head);
			} else {
				resp.setData(null);
				head.setMessage("Credentails Mismatch");
				head.setSuccess(false);
				resp.setHeader(head);
			}

			return resp;

		} catch (Exception e) {
			resp.setData(null);
			head.setMessage("Credentails Mismatch");
			head.setSuccess(false);
			resp.setHeader(head);
			return resp;
		}

	}

}
