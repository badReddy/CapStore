package com.flp.capstore.controller;

import java.util.Locale;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.flp.capstore.domain.CustomException;
import com.flp.capstore.domain.ResponseMessage;
import com.flp.capstore.util.Constants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@ControllerAdvice
public class ExceptionController {

	@Autowired MessageSource messageSource;
	static Logger logger = Logger.getLogger(ExceptionController.class.getName());
	
	@ExceptionHandler(Exception.class)
	public @ResponseBody String handleException(Exception e) {
		ResponseMessage response = new ResponseMessage();
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		String message = messageSource.getMessage("Exception", new Object[]{}, "defaultMessage", Locale.ENGLISH);
		response.setStatus(Constants.FAILURE_STATUS);
		response.setMessage(message);
		return gson.toJson(response);
	}
	
	@ExceptionHandler(CustomException.class)
	public @ResponseBody String handleCustomException(CustomException e) {
		ResponseMessage response = new ResponseMessage();
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		String message = messageSource.getMessage(e.getCode(), new Object[]{}, "defaultMessage", Locale.ENGLISH);
		response.setStatus(Constants.FAILURE_STATUS);
		response.setMessage(message);
		return gson.toJson(response);
	}
}
