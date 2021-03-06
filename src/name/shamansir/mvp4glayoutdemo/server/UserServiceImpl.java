package name.shamansir.mvp4glayoutdemo.server;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import name.shamansir.mvp4glayoutdemo.client.service.UserService;
import name.shamansir.mvp4glayoutdemo.client.service.exception.ItemNotFoundException;
import name.shamansir.mvp4glayoutdemo.client.service.exception.NoMatchesException;
import name.shamansir.mvp4glayoutdemo.shared.dao.User;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class UserServiceImpl extends RemoteServiceServlet implements
    	UserService {
    
    final Map<Integer, User> users = new HashMap<Integer, User>();
    
    public UserServiceImpl() {
        int u1id = users.size();
        User userOne = new User(u1id);
        userOne.name = "John";
        userOne.familyName = "Fassbinder";
        userOne.age = 12;
        userOne.avatar = "1.jpg";        
        users.put(u1id, userOne);
        
        int u2id = users.size();
        User userTwo = new User(u2id);
        userTwo.name = "Bruce";
        userTwo.familyName = "Lee";
        userTwo.age = 27;
        userTwo.avatar = "lee.jpg";        
        users.put(u2id, userTwo);
        
        int u3id = users.size();
        User userThree = new User(u3id);
        userThree.name = "Chuck";
        userThree.familyName = "Boo";
        userThree.age = 37;
        userThree.avatar = "484930.png";        
        users.put(u3id, userThree);
    }

    @Override
    public Set<User> getUsers(String filter) throws NoMatchesException {
        if ((filter == null) || filter.isEmpty()) {
            return new HashSet<User>(users.values());	        
        } else {
            final Set<User> store = new HashSet<User>();
            for (User user: users.values()) {
                if (user.name.startsWith(filter) ||
                    user.familyName.startsWith(filter)) {
                    store.add(user);
                }
            }
            if (store.isEmpty()) throw new NoMatchesException();
            return store;
        }

    }

    @Override
    public User getUser(int uid) throws ItemNotFoundException {
        if (!users.containsKey(uid)) throw new ItemNotFoundException(uid);
    	return users.get(uid);
    }

    @Override
    public int saveUser(User user) {
        int id = user.getId();
        if (id != -1) {
            users.put(id, user);
        } else {
            id = users.size();
            final User newUser = new User(id);
            newUser.name = user.name;
            newUser.familyName = user.familyName;
            newUser.age = user.age;
            newUser.avatar = user.avatar;	        
            users.put(id, newUser);
        }
        return id;
    }
    
}
