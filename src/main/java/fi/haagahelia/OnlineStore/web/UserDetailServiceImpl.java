package fi.haagahelia.OnlineStore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import fi.haagahelia.OnlineStore.domain.User;
import fi.haagahelia.OnlineStore.domain.UserRepository;

/**
 * Handles users and their roles.
 * 
 * @author aburkova
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {

	private final UserRepository repository;

	@Autowired
	public UserDetailServiceImpl(UserRepository userRepository) {
		this.repository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User curruser = repository.findByUsername(username);
		UserDetails user = new org.springframework.security.core.userdetails.User(username, curruser.getPasswordHash(),
				AuthorityUtils.createAuthorityList(curruser.getRole()));
		return user;
	}
}
