package name.shamansir.mvplayout.client.ui.pages.user.presenter;

import name.shamansir.mvplayout.client.service.UserServiceAsync;
import name.shamansir.mvplayout.client.ui.Portal;
import name.shamansir.mvplayout.client.ui.pages.user.UserEventBus;
import name.shamansir.mvplayout.client.ui.pages.user.layout.UserLayoutBuilder;
import name.shamansir.mvplayout.client.ui.pages.user.view.UserListView;
import name.shamansir.mvplayout.client.ui.state.StatedPortalPresenter;
import name.shamansir.mvplayout.client.ui.state.ViewWithStates;

import com.google.inject.Inject;
import com.mvp4g.client.annotation.Presenter;
import com.mvp4g.client.view.LazyView;

@Presenter(view = UserListView.class)
public class UserListPresenter extends StatedPortalPresenter<UserListPresenter.Display, UserEventBus, UserLayoutBuilder> {
	
	public interface Display extends LazyView, ViewWithStates {
		
	}	

	public UserListPresenter() {
		super(Portal.USERS_LIST);
	}
	
	@Inject UserServiceAsync service;

	public void onUsers() {
		
	}
	
}
