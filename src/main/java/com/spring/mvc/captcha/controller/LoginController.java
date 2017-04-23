package com.spring.mvc.captcha.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.mvc.captcha.model.Login;
import com.spring.mvc.captcha.util.VerifyRecaptcha;

@Controller
public class LoginController {

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@ModelAttribute("login") Login login, Model model,
			HttpServletRequest request) throws IOException {

		String gRecaptchaResponse = request
				.getParameter("g-recaptcha-response");
		System.out.println(gRecaptchaResponse);
		boolean verify = VerifyRecaptcha.verify(gRecaptchaResponse);

		if (login.getUsername().equalsIgnoreCase("user")
				&& login.getPassword().equalsIgnoreCase("password") && verify) {
			model.addAttribute("message", "login successfully completed");
			return "login";
		} else {
			model.addAttribute("message", "Invalid credential");
			return "login";
		}
	}

}
