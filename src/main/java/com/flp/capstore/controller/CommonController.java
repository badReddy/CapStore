package com.flp.capstore.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.flp.capstore.entity.User;
import com.flp.capstore.service.interfaces.UserService;

@Controller
public class CommonController {
	
	@Autowired private UserService service;

	static Logger logger = Logger.getLogger(CommonController.class.getName());
	@RequestMapping(value = {"/","/index"}, method = RequestMethod.GET)
	public String showIndexPage(ModelMap model) {
		return "index";
	}
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String showHomePage(ModelMap model) {
		return "home";
	}
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String adminPage(ModelMap model) {
		model.addAttribute("user", getPrincipal());
		return "admin";
	}
	@RequestMapping(value = "/Access_Denied", method = RequestMethod.GET)
	public String accessDeniedPage(ModelMap model) {
		model.addAttribute("user", getPrincipal());
		return "noAccess";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage() {
		return "login";
	}
		
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String signUpPage(@ModelAttribute("signupform") @Valid com.flp.capstore.domain.User user, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "login";
		} else {
			User userToSave = new User();
			userToSave.setEmail(user.getEmail());
			userToSave.setFirstName(user.getFirstName());
			userToSave.setLastName(user.getLastName());
			userToSave.setPassword(user.getPassword());
			userToSave.setUserName(user.getUserName());
			userToSave.setStatus("A");
			service.addUser(userToSave);
			return "redirect:/user/fetchUser?userName=" + user.getUserName();
		}
	}

	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null){    
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/login?logout";
	}

	    @RequestMapping(value="/pageNotFound")
	    public String handlePageNotFound(ModelMap model) {
	        return "redirect:/index?invalidURL";
	    }
	private String getPrincipal(){
		String userName = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			userName = ((UserDetails)principal).getUsername();
		} else {
			userName = principal.toString();
		}
		return userName;
	}
}
