package my.domain.practice.app.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import my.domain.practice.app.entity.User;
import my.domain.practice.app.repository.RoleRepository;
import my.domain.practice.app.service.UserService;

@Controller
public class UserController {

	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	UserService userService;
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("/userForm")
	public String userForm(Model _model) {
		
		_model.addAttribute("userForm", new User());
		_model.addAttribute("roles", roleRepository.findAll());
		_model.addAttribute("userList", userService.getAllUsers());
		_model.addAttribute("listTab", "active");
		
		return "user-form/user-view";
	}
	
	@PostMapping("/userForm")
	public String createUser(@Valid @ModelAttribute("userForm")User user, BindingResult result, Model _model) {
		
		if(result.hasErrors()) {
			_model.addAttribute("userForm", user);
			_model.addAttribute("roles", roleRepository.findAll());
			_model.addAttribute("userList", userService.getAllUsers());
			_model.addAttribute("formTab", "active");
		}else {
			try {
				userService.createuser(user);
				_model.addAttribute("userForm", new User());
				_model.addAttribute("listTab", "active");
			}catch (Exception e) {
				_model.addAttribute("formErrorMessage", e.getMessage());
				_model.addAttribute("userForm", user);
				_model.addAttribute("roles", roleRepository.findAll());
				_model.addAttribute("userList", userService.getAllUsers());
				_model.addAttribute("formTab", "active");
			}
		}
		
		_model.addAttribute("userList", userService.getAllUsers());
		_model.addAttribute("roles", roleRepository.findAll());
		
		return "user-form/user-view";
	}
	
	@GetMapping("/editUser/{id}")
	public String editUserForm(Model _model, @PathVariable(name="id")long id) throws Exception {
		
		User user = userService.getUserByid(id);
		
		_model.addAttribute("userList", userService.getAllUsers());
		_model.addAttribute("roles", roleRepository.findAll());
		_model.addAttribute("userForm", user);
		_model.addAttribute("formTab", "active");
		
		_model.addAttribute("editMode", true);
		
		return "user-form/user-view";
	}
	
}
