package com.ariv.Idea.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import  org.springframework.validation.Validator;

import com.ariv.Idea.models.User;
import com.ariv.Idea.repositories.UserRepository;

@Component
public class UserValidator implements Validator {
	@Autowired
	private UserRepository uRepo;
	@Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }
	@Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        
        if(this.uRepo.existsByEmail(user.getEmail())) {
        	//errors.rejectValue("email", "Unique");
        	errors.rejectValue("email", "", "There is already user with this email!");
        }
        
        if (!user.getPasswordConfirmation().equals(user.getPassword())) {
            errors.rejectValue("passwordConfirmation", "Match");
        }         
    }
}
