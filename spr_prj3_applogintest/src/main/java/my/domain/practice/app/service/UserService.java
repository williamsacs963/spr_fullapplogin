package my.domain.practice.app.service;

import my.domain.practice.app.entity.User;

public interface UserService {

	public Iterable<User> getAllUsers();
	public User createuser(User user) throws Exception;
	public User getUserByid(long id) throws Exception;
	
}
