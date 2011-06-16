package name.shamansir.mvplayout.client.ui.pages.user;

import com.mvp4g.client.annotation.Event;
import com.mvp4g.client.annotation.Events;

import name.shamansir.mvplayout.client.ui.Layouts.Place;
import name.shamansir.mvplayout.client.ui.pages.base.ChildEventBus;
import name.shamansir.mvplayout.client.ui.pages.user.history.UserHistoryConverter;
import name.shamansir.mvplayout.client.ui.pages.user.presenter.UserListPresenter;
import name.shamansir.mvplayout.client.ui.pages.user.view.UserListView;

@Events(module = UserModule.class, startView = UserListView.class)
public interface UserEventBus extends ChildEventBus {
	
	@Event(name = "list", navigationEvent = true,
		   handlers = UserListPresenter.class,
		   historyConverter = UserHistoryConverter.class)
	public void users(String filter);
	
	@Event(name = "show", navigationEvent = true)
	public void show(int uid);
	
	@Event(name = "edit", navigationEvent = true)
	public void edit(int uid);
	
	// projecting events
	
	@Event/*(handlers = UserAvatarPresenter.class, calledMethod = "plugUserAvatar")*/
	public void plugUserAvatar(Place where);
	
    @Event/*(handlers = UserDetailsPresenter.class, calledMethod = "plug")*/
	public void plugUserDetails(Place where);
	
    @Event(handlers = UserListPresenter.class, calledMethod = "plug")
	public void plugUsersList(Place where);

}
