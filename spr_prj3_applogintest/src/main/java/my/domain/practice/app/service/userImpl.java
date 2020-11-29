package my.domain.practice.app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import my.domain.practice.app.entity.User;
import my.domain.practice.app.repository.UserRepository;

@Service
public class userImpl implements UserService{

	@Autowired
	UserRepository repository;
	
	@Override
	public Iterable<User> getAllUsers() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	private boolean checkUsernameAvalible(User user) throws Exception {
		
		Optional<User> userFound = repository.findByUsername(user.getUsername());
		
		if(userFound.isPresent()) {
			throw new Exception("Usuario no disponible");
		}
		
		return true;
		
	}
	
	private boolean checkPasswordMatch(User user) throws Exception {
		
		if(!user.getPassword().equals(user.getConfirmPassword())) {
			throw new Exception("Las contraseñas no coinciden");
		}
		
		return true;
		
	}

	@Override
	public User createuser(User user) throws Exception {

		if(checkUsernameAvalible(user) && checkPasswordMatch(user)) {
			user = repository.save(user);
		}
		
		return user;
	}

	@Override
	public User getUserByid(long id) throws Exception {
		return repository.findById(id).orElseThrow(() -> new Exception("El usuario no existe"));
	}
	
}
