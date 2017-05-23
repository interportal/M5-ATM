package md.curs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import md.curs.model.User;
import md.curs.repository.UserRepository;

@Service
@Transactional(readOnly = true)
public class UserService {

	private UserRepository userRepository;
	
	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public User getUser(long id) {
		return userRepository.getUser(id);
	}
}
