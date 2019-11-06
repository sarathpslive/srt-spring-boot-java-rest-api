package com.srt.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.srt.dao.AuthDao;
import com.srt.dao.UserDao;
import com.srt.model.Header;
import com.srt.model.Response;
import com.srt.model.AuthUser;
import com.srt.model.UserDetails;

@RestController
public class HelloWorldController {

	@Autowired
	private AuthDao auth;
	@Autowired
	private UserDao userPofile;

	@RequestMapping(value = { "/", "test" }, method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public Response<String> hello() {
		Response<String> resp = new Response<String>();
		resp.setData("Hello World");
		Header head = new Header();
		head.setMessage("Successfully Executed");
		head.setSuccess(true);
		resp.setHeader(head);
		return resp;
	}

	@RequestMapping(value = { "/{dateOfBirth}/{phoneNumber}/{eMail}" }, method = RequestMethod.GET)
	public ResponseEntity<Response<UserDetails>> hello(@PathVariable("dateOfBirth") String dateOfBirth,
			@PathVariable("phoneNumber") String phoneNumber, @PathVariable("eMail") String eMail) {

		UserDetails user = new UserDetails();
		user.setDateOfBirth(dateOfBirth);
		user.seteMail(eMail);
		user.setPhoneNumber(phoneNumber);
		Response<UserDetails> resp = userPofile.getUserProfile(user);

		return new ResponseEntity<Response<UserDetails>>(resp, HttpStatus.OK);
	}

	@RequestMapping(value = { "/login" }, method = { RequestMethod.OPTIONS, RequestMethod.POST })
	public ResponseEntity<Response<AuthUser>> hello(@RequestBody AuthUser user) {

		Response<AuthUser> resp = auth.login(user);
		return new ResponseEntity<Response<AuthUser>>(resp, HttpStatus.OK);

	}

}
