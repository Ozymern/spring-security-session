package com.ozymern.spring.security.session.models.services;

import org.springframework.transaction.annotation.Transactional;

import com.ozymern.spring.security.session.models.entities.Role;
import com.ozymern.spring.security.session.models.entities.User;
import com.ozymern.spring.security.session.models.repositories.UserRepository;
import com.ozymern.spring.security.session.security.UserPrincipal;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	private Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

	// metodo para cargar al usuario junto con sus roles
	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		User userEntity = userRepository.findByEmail(email);

		if (userEntity == null) {
			logger.error("Error login: no existe el usuario: " + email + " en la base de datos");
			throw new UsernameNotFoundException("email " + email + " no existe!");

		}

		// optener los roles de usuario GrantedAuthority= es una interfaz que sirve para
		// obtener los privilegio o persimos
		List<GrantedAuthority> authorities = new ArrayList<>();

		// asigno el rol de mi usuario a la interfaz authorities
		for (Role roleEntity : userEntity.getRoles()) {
			authorities.add(new SimpleGrantedAuthority("ROLE_" + roleEntity.getRole().toUpperCase()));
		}

		// con java 8
		userEntity.getRoles().forEach(role -> {
			authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRole().toUpperCase()));
		});

		// valido si la authorities tienes roles asignados
		if (authorities.isEmpty()) {
			logger.error("Error login: no existe el usuario " + email + " no tiene roles asignados");
			throw new UsernameNotFoundException(
					"Error login: no existe el usuario " + email + " no tiene roles asignados");
		}
		return new UserPrincipal(userEntity);

	}

}
