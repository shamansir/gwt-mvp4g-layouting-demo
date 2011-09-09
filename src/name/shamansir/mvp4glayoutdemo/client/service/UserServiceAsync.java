package name.shamansir.mvp4glayoutdemo.client.service;

import java.util.Set;

import name.shamansir.mvp4glayoutdemo.shared.dao.User;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>UserService</code>.
 */
public interface UserServiceAsync {
    void getUsers(String filter, AsyncCallback<Set<User>> callback);
    void getUser(int uid, AsyncCallback<User> callback);
    void saveUser(User user, AsyncCallback<Integer> callback);
}
