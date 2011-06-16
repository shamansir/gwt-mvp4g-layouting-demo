package name.shamansir.mvplayout.client.ui.pages.user.presenter;

import java.util.Set;

import name.shamansir.mvplayout.client.service.UserServiceAsync;
import name.shamansir.mvplayout.client.ui.ErrorHandlingCallback;
import name.shamansir.mvplayout.client.ui.pages.user.UserEventBus;
import name.shamansir.mvplayout.client.ui.pages.user.view.UserListView;
import name.shamansir.mvplayout.client.ui.state.IsStatedPortletView;
import name.shamansir.mvplayout.client.ui.state.StatedPortletPresenter;
import name.shamansir.mvplayout.shared.dao.User;

import com.google.inject.Inject;
import com.mvp4g.client.annotation.Presenter;

@Presenter(view = UserListView.class)
public class UserListPresenter extends StatedPortletPresenter<UserListPresenter.Display, UserEventBus> {
	
	public interface Display extends IsStatedPortletView {
		public void showUsers(Set<User> users);
	}	

	public UserListPresenter() {
	}
	
	@Inject UserServiceAsync service;

	public void onUsers(String filter) {
		service.getUsers(null, new ErrorHandlingCallback<Set<User>>(eventBus) {
			
			@Override
			public void onSuccess(Set<User> users) {
				state.gotData(users);
				view.showUsers(users);
			}
			
		});
	}	
	
}
