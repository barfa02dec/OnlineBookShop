package com.app.controller;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Map;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.app.exception.BookException;
import com.app.model.User;
import com.app.service.UserServiceInf;
import com.app.validation.ValidateUserData;

@CrossOrigin(origins = "http://localhost:4200",allowedHeaders = "*")
@RestController
/**
 * Controller that performs CRUD operation that related to User
 * Work as navigator between model and view
 * */
@Produces(MediaType.APPLICATION_XML)
public class UserController {

	@Autowired
	UserServiceInf userService;
	
	/********************** Create User **************************************/
	
	/**
	 *  Input User data
	 *  check validation on user data
	 *  return user details except password
	 * @throws BookException 
	 * */
	@PostMapping("/user")
	public ResponseEntity<User> addUser(@RequestBody User user) throws BookException {
		return new ResponseEntity<User>(userService.addUser(user), HttpStatus.CREATED);
	}
	/************************************* Read User ***********************************/
	
	/**
	 * Taking user id and validate it
	 * return User details
	 * @throws BookException
	 */
	@GetMapping("/user/{userId}")
	public ResponseEntity<User> getUserById(@PathVariable("userId") int userId) throws BookException{
		return new ResponseEntity<User>(userService.getUserById(userId),HttpStatus.FOUND) ;
	}
	/**
	 * @param user email and password and check validation
	 * @return User if exist
	 * @throws BookException
	 */
	@PostMapping("/user/login")
	public ResponseEntity<User> logIn(@RequestBody Map<String,String> user)throws BookException {
		ValidateUserData.checkNullValue(user.get("email"), "email");
		ValidateUserData.checkNullValue(user.get("password"), "password");
		return new ResponseEntity<User>(userService.logIn(user.get("email"), user.get("password")),HttpStatus.FOUND);
	}
	
	/************************************** Update User *********************************/
	/**
	 * Taking user detail and validate each field of user
	 * replace old user details by new details based on user id
	 * return updated user details
	 * @throws BookException
	 * @throws SQLIntegrityConstraintViolationException 
	 */
	@PutMapping("/user")
	public @ResponseBody User updateUser(@RequestBody User user) throws BookException{
		return userService.updateUser(user);
	}
	
	/******************************* Remove User ********************************/
	/**
	 * Taking the user id and check validation on user id
	 * remove all orders placed by user from order detail table and booking table
	 * return true if user data deleted
	 * @throws BookException 
	 * */
	@DeleteMapping("/user/{userId}")
	public @ResponseBody boolean removeUser(@PathVariable("userId") int userId) throws BookException {
		ValidateUserData.checkNullValue(userId, "userId");
		return userService.removeUser(userId);
	}
}
