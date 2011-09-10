package name.shamansir.mvp4glayoutdemo.client.service;

import java.util.Set;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import name.shamansir.mvp4glayoutdemo.client.service.exception.ItemNotFoundException;
import name.shamansir.mvp4glayoutdemo.client.service.exception.NoMatchesException;
import name.shamansir.mvp4glayoutdemo.shared.dao.User;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("users")
public interface UserService extends RemoteService {
    Set<User> getUsers(String filter) throws NoMatchesException;
    User getUser(int uid) throws ItemNotFoundException;
    int saveUser(User user); 
}
