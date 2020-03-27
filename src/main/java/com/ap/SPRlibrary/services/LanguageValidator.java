package com.ap.SPRlibrary.services;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class LanguageValidator implements ConstraintValidator<Language, String>{
	enum Language {
    	Italian {
            public String toString() {
                return "Italian";
            }
    	},
    	English {
            public String toString() {
                return "English";
            }
    	}
    }
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		System.out.println("VALUE: "+value);
		if(
					(!value.equalsIgnoreCase(Language.Italian.toString()))
					&&(!value.equalsIgnoreCase(Language.English.toString()))
					
					)
				return false;
		
		
		return true;
	}
	
}
