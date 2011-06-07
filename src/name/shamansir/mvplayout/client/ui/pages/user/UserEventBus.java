package name.shamansir.mvplayout.client.ui.pages.user;

import com.google.gwt.user.client.ui.HasWidgets;
import com.mvp4g.client.annotation.Event;
import com.mvp4g.client.annotation.Events;

import name.shamansir.mvplayout.client.ui.pages.base.ChildEventBus;
import name.shamansir.mvplayout.client.ui.pages.user.presenter.UserListPresenter;
import name.shamansir.mvplayout.client.ui.pages.user.view.UserListView;

@Events(module = UserModule.class, startView = UserListView.class)
public interface UserEventBus extends ChildEventBus {
	
	@Event(name = "list", navigationEvent = true,
		   handlers = UserListPresenter.class)
	public void users();
	
	@Event(name = "show", navigationEvent = true)
	public void show(int uid);
	
	@Event(name = "edit", navigationEvent = true)
	public void edit(int uid);
	
	// projecting events
	
	@Event
	public void projectUserAvatar(HasWidgets where);
	
	@Event
	public void projectUserDetails(HasWidgets where);
	
	@Event
	public void projectUsersList(HasWidgets where);
	
	@Event
	public void projectUsersLoading(HasWidgets where);
	
	@Event
	public void projectUsersEmpty(HasWidgets where);
	
	@Event
	public void projectUsersNoMatches(HasWidgets where);

}
