package com.demo.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.Entity.User;
import com.demo.Repository.IUserRepo;

@Service
public class UserService {

	@Autowired
	private IUserRepo userRepo;

	public User findUser(String userName, String password) {
		Optional<User> optionalUser = userRepo.findByUserNameAndPassword(userName, password);
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        }
        return null;
	}

	public List<User> getAllUsers() {
		return userRepo.findAll();
	}

	public List<User> getAllUsers(String keyword) {
		if (keyword  != null) {
			return userRepo.search(keyword);
		}else
		
		return (List<User>) userRepo.findAll();
	}
}
