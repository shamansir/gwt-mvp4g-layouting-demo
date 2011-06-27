package name.shamansir.mvplayout.client.service;

import java.util.Set;

import name.shamansir.mvplayout.lib.exception.ItemNotFoundException;
import name.shamansir.mvplayout.lib.exception.NoMatchesException;
import name.shamansir.mvplayout.shared.dao.User;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("users")
public interface UserService extends RemoteService {
	Set<User> getUsers(String filter) throws NoMatchesException;
	User getUser(int uid) throws ItemNotFoundException;
	int saveUser(User user); 
}
