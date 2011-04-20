package name.shamansir.mvplayout.client.service;

import java.util.Set;

import name.shamansir.mvplayout.shared.dao.User;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>UsersService</code>.
 */
public interface UsersServiceAsync {
	void getUsers(String filter, AsyncCallback<Set<User>> callback);
	void getUser(int uid, AsyncCallback<User> callback);
	void saveUser(User user, AsyncCallback<Integer> callback);
}
