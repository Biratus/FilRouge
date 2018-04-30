package fr.dta.formafond.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import fr.dta.formafond.exception.UserAlreadyExistsException;
import fr.dta.formafond.model.User;
import fr.dta.formafond.service.SecurityService;
import fr.dta.formafond.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	UserService userv;
	
	@Autowired
	SecurityService secuServ;
	
	public UserController() {
		// TODO Auto-generated constructor stub
	}
	
	@RequestMapping(value="/current",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin
	public ObjectNode getConnectedUser() {
		return secuServ.getConnectedUser().toJson();
	}
	
	@RequestMapping(method=RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin
	public ObjectNode createUser(@RequestBody User u) {
		ObjectNode node=JsonNodeFactory.instance.objectNode();
		try {
			userv.createUser(u);
			node.put("state", "success");
		} catch (UserAlreadyExistsException e) {
			node.put("state", "failed");
		}
		return node;
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.POST)
	@CrossOrigin
	public ObjectNode getUserById(@PathVariable int id) {
		User u = userv.getById(id);
		if(u==null) {
			ObjectNode node=JsonNodeFactory.instance.objectNode();
			node.put("state", "failed");
			return node;
		} else return u.toJson();
	}
	
	@RequestMapping(method=RequestMethod.PUT,consumes=MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin
	public void updateUser(@RequestBody User u) {
		userv.updateUser(u);
	}

}
