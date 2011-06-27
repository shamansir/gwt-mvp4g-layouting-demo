package name.shamansir.mvplayout.server;

import java.util.HashSet;
import java.util.Set;

import name.shamansir.mvplayout.client.service.UserService;
import name.shamansir.mvplayout.lib.exception.ItemNotFoundException;
import name.shamansir.mvplayout.lib.exception.NoMatchesException;
import name.shamansir.mvplayout.shared.dao.User;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class UserServiceImpl extends RemoteServiceServlet implements
		UserService {
    
    final Set<User> users = new HashSet<User>();
    
    public UserServiceImpl() {
        User userOne = new User();
        userOne.name = "John";
        userOne.familyName = "Fassbinder";
        userOne.age = 12;
        userOne.avatar = "1.jpg";        
        users.add(userOne);
        
        User userTwo = new User();
        userTwo.name = "Bruce";
        userTwo.familyName = "Lee";
        userTwo.age = 27;
        userTwo.avatar = "lee.jpg";        
        users.add(userTwo);
        
        User userThree = new User();
        userThree.name = "Chuck";
        userThree.familyName = "Boo";
        userThree.age = 37;
        userThree.avatar = "484930.png";        
        users.add(userThree);        
    }

	@Override
	public Set<User> getUsers(String filter) throws NoMatchesException {	    
		return users;
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
