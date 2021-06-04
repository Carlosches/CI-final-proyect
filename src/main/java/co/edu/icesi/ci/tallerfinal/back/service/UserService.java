package co.edu.icesi.ci.tallerfinal.back.service;

import java.util.Optional;

import co.edu.icesi.ci.tallerfinal.back.model.Userr;

public interface UserService {

	public Userr save(Userr user);

	public Optional<Userr> findById(long id);

	public Iterable<Userr> findAll();
	
	public void delete(Userr user);


}
