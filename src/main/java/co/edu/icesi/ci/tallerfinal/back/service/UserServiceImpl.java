package co.edu.icesi.ci.tallerfinal.back.service;

import java.util.Optional;

import co.edu.icesi.ci.tallerfinal.back.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.icesi.ci.tallerfinal.back.model.Userr;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	

	@Override
	public Userr save(Userr user) {
		userRepository.save(user);
		return user;
	}

	@Override
	public Optional<Userr> findById(long id) {
		return userRepository.findById(id);
	}

	@Override
	public Iterable<Userr> findAll() {
		return userRepository.findAll();
	}

	@Override
	public void delete(Userr user) {
		userRepository.delete(user);
		
	}

	
}
