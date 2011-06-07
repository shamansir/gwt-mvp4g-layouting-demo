package name.shamansir.mvplayout.client.ui.pages.user.presenter;

import java.util.Set;

import name.shamansir.mvplayout.client.service.UserServiceAsync;
import name.shamansir.mvplayout.client.ui.ErrorHandlingCallback;
import name.shamansir.mvplayout.client.ui.Portal;
import name.shamansir.mvplayout.client.ui.pages.user.UserEventBus;
import name.shamansir.mvplayout.client.ui.pages.user.layout.UserLayoutBuilder;
import name.shamansir.mvplayout.client.ui.pages.user.view.UserListView;
import name.shamansir.mvplayout.client.ui.state.StatedPortalPresenter;
import name.shamansir.mvplayout.client.ui.state.PluggableWithStates;
import name.shamansir.mvplayout.shared.dao.User;

import com.google.inject.Inject;
import com.mvp4g.client.annotation.Presenter;
import com.mvp4g.client.view.LazyView;

@Presenter(view = UserListView.class)
public class UserListPresenter extends StatedPortalPresenter<UserListPresenter.Display, UserEventBus, UserLayoutBuilder> {
	
	public interface Display extends LazyView, PluggableWithStates {
		public void showUsers(Set<User> users);
	}	

	public UserListPresenter() {
		super(Portal.USERS_LIST);
	}
	
	@Inject UserServiceAsync service;

	public void onUsers() {
		service.getUsers(null, new ErrorHandlingCallback<Set<User>>(eventBus) {
			
			@Override
			public void onSuccess(Set<User> users) {
				state.gotData(users);
				view.showUsers(users);
			}
			
		});
	}
	
}
