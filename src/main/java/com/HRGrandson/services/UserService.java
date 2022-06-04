package com.HR.Grandson.HR.Grandson.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.HR.Grandson.HR.Grandson.models.User;
import com.HR.Grandson.HR.Grandson.repos.UserRepository;

public class UserService {
	@Autowired
	private UserRepository userRepo;
	
	
}
