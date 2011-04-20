package name.shamansir.mvplayout.server;

import java.util.HashSet;
import java.util.Set;

import name.shamansir.mvplayout.client.exception.ItemNotFoundException;
import name.shamansir.mvplayout.client.exception.NoMatchesException;
import name.shamansir.mvplayout.client.service.UserService;
import name.shamansir.mvplayout.shared.dao.User;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class UserServiceImpl extends RemoteServiceServlet implements
		UserService {

	@Override
	public Set<User> getUsers(String filter) throws NoMatchesException {
		return new HashSet<User>();
	}

	@Override
	public User getUser(int uid) throws ItemNotFoundException {
		return new User();
	}

	@Override
	public int saveUser(User user) {
		return -1;
	}
}
