package co.edu.icesi.ci.tallerfinal.back.security;

import java.util.List;

import co.edu.icesi.ci.tallerfinal.back.repositories.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import co.edu.icesi.ci.tallerfinal.back.model.PersonRole;
import co.edu.icesi.ci.tallerfinal.back.model.Userr;
import co.edu.icesi.ci.tallerfinal.back.service.PersonService;


@Service          
public class MyCustomUserDetailsService implements UserDetailsService{
	
	private UserRepository userRepository;
	private PersonService personService;
	
	public  MyCustomUserDetailsService(UserRepository userRepository, PersonService personService) {
		this.userRepository = userRepository;
		this.personService = personService;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Userr userApp = userRepository.findByuserName(username);
		if (userApp != null) {
			List<PersonRole> userRoles = personService.getPersonRoles(userApp.getPerson());
			String roles = "";
			for(int i=0; i<userRoles.size();i++){
				roles+=userRoles.get(i).getRolee().getRoleName();
					roles+=",";
			}
			User.UserBuilder builder = User.withUsername(username).password(userApp.getUserPassword()).roles(userApp.getRole());

			return builder.build();
		} else {
			throw new UsernameNotFoundException("User not found.");
		}
	}

	
	
}