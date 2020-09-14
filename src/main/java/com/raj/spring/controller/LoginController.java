package com.raj.spring.controller;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class LoginController {

	@RequestMapping(value = { "/"}, method = RequestMethod.GET)
	public ModelAndView welcomePage() {
		ModelAndView model = new ModelAndView();
		model.setViewName("welcomePage");
		return model;
	}
	
	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public ModelAndView accesssDenied() {

		ModelAndView model = new ModelAndView();

					model.addObject("msg", 
			"You do not have permission to access this page!");
		

		model.setViewName("Access_Denied");
		return model;

	}
	@RequestMapping(value = { "/Success"}, method = RequestMethod.GET)
	public ModelAndView SuccessPage(HttpServletRequest req) {
		
		
		return new ModelAndView("Success");
	}
	@RequestMapping(value = { "/admin"}, method = RequestMethod.GET)
	public ModelAndView homePage(HttpServletRequest req) {
		
		
		return new ModelAndView("adminPage");
	}
	
	@RequestMapping(value = "/loginPage", method = RequestMethod.GET)
	public ModelAndView loginPage(@RequestParam(value = "error",required = false) String error,
	@RequestParam(value = "logout",	required = false) String logout) {
		
		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", "Invalid Credentials provided.");
			model.setViewName("loginPage");
			return model;
		}

		else if (logout != null) {
			model.addObject("message", "Logged out successfully.");
			model.setViewName("loginPage");
			return model;
		}
		else if (logout == null&& error==null) {
			return new ModelAndView("loginPage");
		}

		
		return new ModelAndView("Success");
	}

}

