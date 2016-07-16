package com.flp.capstore.util;

import com.flp.capstore.domain.CustomException;

public class ValidationUtility {

	public static String validateInteger(String input) throws CustomException{
		CustomException ex = new CustomException();
		if(null!=input||!"".equals(input)){
			try{
				return String.valueOf(Integer.parseInt(input));
			} catch(Exception e){
				ex.setCode("NumberFormatException");
				ex.setSeverity(2);
				throw ex;
			}
		} else{
			ex.setCode("EmptyString");
			ex.setSeverity(2);
			throw ex;
		}
	}
}
