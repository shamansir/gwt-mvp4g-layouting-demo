package name.shamansir.mvplayout.client.service;

import java.util.Set;

import name.shamansir.mvplayout.shared.dao.User;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>UsersService</code>.
 */
public interface UsersServiceAsync {
	public void getUsers(String filter, AsyncCallback<Set<User>> callback);
	public void getUser(int uid, AsyncCallback<User> callback);
}
