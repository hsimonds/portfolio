package com.portfolio.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.portfolio.exception.DuplicateProfileException;
import com.portfolio.exception.NotAuthorizedException;
import com.portfolio.exception.UserCreateException;
import com.portfolio.exception.UserUpdateException;

@ControllerAdvice
public class GlobalExceptionHandler {
 
    @ExceptionHandler(NotAuthorizedException.class)
    public ModelAndView handleNotAuthorizedException(Exception ex) {
         ModelAndView mav = new ModelAndView("error");
         mav.addObject("errorMessage", ex.getLocalizedMessage());
         return mav;
    }
    
    @ExceptionHandler(UserCreateException.class)
    public ModelAndView handleUserCreateException (Exception ex) {
    	UserCreateException userCreateExeption = (UserCreateException) ex;
    	ModelAndView mav = new ModelAndView("create-user", userCreateExeption.getBindingResult().getModel());
    	mav.addObject("user", userCreateExeption.getUser());
    	mav.addObject("errorMessage", "Error Creating User");
    	return mav;
    }
    
    @ExceptionHandler(UserUpdateException.class)
    public ModelAndView handleUserUpdateException (Exception ex) {
    	UserUpdateException userUpdateExeption = (UserUpdateException) ex;
    	ModelAndView mav = new ModelAndView("edit-user", userUpdateExeption.getBindingResult().getModel());
    	mav.addObject("user", userUpdateExeption.getUser());
    	mav.addObject("errorMessage", "Error Updating User");
    	return mav;
    }
    
    @ExceptionHandler(DuplicateProfileException.class)
    public ModelAndView handleDuplicateProfileException(Exception ex) {
    	DuplicateProfileException dpeEx = (DuplicateProfileException) ex;
    	ModelAndView mav = new ModelAndView("edit-profile", dpeEx.getBindingResult().getModel());
    	mav.addObject("profile", dpeEx.getProfile());
    	mav.addObject("errorMessage", "Error updating profile");
    	return mav;
    }
     
}