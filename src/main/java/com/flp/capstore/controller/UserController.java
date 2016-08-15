package com.flp.capstore.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.flp.capstore.domain.ResponseMessage;
import com.flp.capstore.domain.UserDTO;
import com.flp.capstore.service.interfaces.UserService;
import com.flp.capstore.util.Constants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
@RequestMapping("/user/")
public class UserController extends CommonController{

	@Autowired private UserService service;
	@Autowired private MessageSource messageSource;

	static Logger logger = Logger.getLogger(UserController.class.getName());

	@RequestMapping(value="/registerUser/**",method=RequestMethod.POST,produces="application/json")
	public @ResponseBody String registerUser(HttpServletRequest request,
			HttpServletResponse response, @ModelAttribute() @Valid UserDTO user, BindingResult bindingResult) throws Exception{
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		ResponseMessage responseBody = new ResponseMessage();
		if(bindingResult.hasErrors()){
			String message = "";
			for(Object object: bindingResult.getAllErrors()){
				if(object instanceof FieldError){
					FieldError error = (FieldError)object;
					String defaultMsg = messageSource.getMessage("defaultMessage",new Object[]{}, Locale.ENGLISH);
					message = messageSource.getMessage(error.getCodes()[0], new Object[]{error.getField()}, defaultMsg, Locale.ENGLISH);
					message.concat("\n");
					logger.error("Errors found while validating the user input for registerUser()--> "+message);
				}
			}
			responseBody.setStatus(Constants.FAILURE_STATUS);
			responseBody.setMessage(message);
		}	else { 
			if(service.registerUser(user)){
				logger.info("registerUser() operation successful for user input--> "+ user.getUserName());
				responseBody.setStatus(Constants.SUCCESS_STATUS);
				responseBody.setMessage("User Registered");
			} else{
				logger.info("registerUser() operation failed for user input--> "+ user.getUserName());
				responseBody.setStatus(Constants.FAILURE_STATUS);
				responseBody.setMessage("User Regisrtation Failed");
			}
		}
		return gson.toJson(responseBody);
	}

	@RequestMapping(value="/fetchUser/**",method=RequestMethod.GET,produces="application/json")
	public @ResponseBody String fetchUser(HttpServletRequest request,
			HttpServletResponse response, @ModelAttribute() @Valid UserDTO user, BindingResult bindingResult) throws Exception{
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		if(bindingResult.hasErrors()){
			String message = "";
			for(Object object: bindingResult.getAllErrors()){
				if(object instanceof FieldError){
					FieldError error = (FieldError)object;
					String defaultMsg = messageSource.getMessage("defaultMessage",new Object[]{}, Locale.ENGLISH);
					message = messageSource.getMessage(error.getCodes()[0], new Object[]{error.getField()}, defaultMsg, Locale.ENGLISH);
					message.concat("\n");
					logger.error("Errors found while validating the user input for fetchUser()--> "+message);
				}
			}
			ResponseMessage responseBody = new ResponseMessage();
			responseBody.setStatus(Constants.FAILURE_STATUS);
			responseBody.setMessage(message);
			return gson.toJson(responseBody);
		}	else {	
			com.flp.capstore.domain.UserDTO userResponse = service.fetchUser(user.getUserName());
			logger.info("fetchUser() operation successful for user input--> "+ user.getUserName());
			return gson.toJson(userResponse);
		}
	}
}
