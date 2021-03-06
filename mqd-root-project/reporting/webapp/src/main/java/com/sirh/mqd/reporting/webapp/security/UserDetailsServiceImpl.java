package com.sirh.mqd.reporting.webapp.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sirh.mqd.commons.exchanges.dto.security.UserDTO;
import com.sirh.mqd.reporting.api.resources.IMessageSourceBundle;
import com.sirh.mqd.reporting.core.api.IUserService;
import com.sirh.mqd.reporting.core.constantes.CoreConstantes;
import com.sirh.mqd.reporting.webapp.constantes.ContextConstantes;
import com.sirh.mqd.reporting.webapp.factory.UserModelFactory;

@Service(ContextConstantes.USER_DETAILS_SERVICE)
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	@Qualifier(CoreConstantes.USER_SERVICE)
	private IUserService userService;

	@Autowired
	@Qualifier(ContextConstantes.MESSAGE)
	private IMessageSourceBundle messagesBundle;

	@Override
	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
		final Optional<UserDTO> user = userService.rechercherUtilisateurAvecMotDePasse(username);
		if (user.isPresent()) {
			return UserModelFactory.createUserModel(user.get());
		}
		throw new UsernameNotFoundException(
				messagesBundle.getMessage("security.error.functional.no.username.found", new Object[] { username }));
	}

	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(final IUserService userService) {
		this.userService = userService;
	}

	public IMessageSourceBundle getMessagesBundle() {
		return messagesBundle;
	}

	public void setMessagesBundle(final IMessageSourceBundle messagesBundle) {
		this.messagesBundle = messagesBundle;
	}
}
