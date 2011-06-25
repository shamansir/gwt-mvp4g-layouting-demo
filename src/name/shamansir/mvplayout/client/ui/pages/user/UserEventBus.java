package name.shamansir.mvplayout.client.ui.pages.user;

import com.mvp4g.client.annotation.Event;
import com.mvp4g.client.annotation.Events;

import name.shamansir.mvplayout.client.ui.Layouts.Place;
import name.shamansir.mvplayout.client.ui.pages.base.ChildEventBus;
import name.shamansir.mvplayout.client.ui.pages.user.history.UserHistoryConverter;
import name.shamansir.mvplayout.client.ui.pages.user.presenter.UserAvatarPresenter;
import name.shamansir.mvplayout.client.ui.pages.user.presenter.UserDetailsPresenter;
import name.shamansir.mvplayout.client.ui.pages.user.presenter.UserListPresenter;
import name.shamansir.mvplayout.client.ui.pages.user.view.UserListView;
import name.shamansir.mvplayout.shared.dao.User;

@Events(module = UserModule.class, startView = UserListView.class)
public interface UserEventBus extends ChildEventBus {
	
    // navigation events
    
	@Event(name = "list", navigationEvent = true, passive = true,
		   handlers = UserListPresenter.class, 
		   historyConverter = UserHistoryConverter.class)
	public void users(String filter);
	
	@Event(/*name = "show", navigationEvent = true*/)
	public void show(int uid);
	
	@Event(/*name = "edit", navigationEvent = true*/)
	public void edit(int uid);
	
	// secondary events
	
	@Event(handlers = { UserDetailsPresenter.class, 
	                    UserAvatarPresenter.class })
    public void userSelected(User user);
	
	// projecting events
	
    @Event(handlers = UserListPresenter.class, calledMethod = "plug")
	public void plugUsersList(Place where);
    
    @Event(/*handlers = UserDetailsPresenter.class, calledMethod = "plug"*/)
    public void plugUserDetails(Place where);    
    
    @Event(/*handlers = UserAvatarPresenter.class, calledMethod = "plug"*/)
    public void plugUserAvatar(Place where);    

}
