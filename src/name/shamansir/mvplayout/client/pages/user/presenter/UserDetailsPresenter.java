package name.shamansir.mvplayout.client.pages.user.presenter;

import name.shamansir.mvplayout.client.pages.user.UserEventBus;
import name.shamansir.mvplayout.client.pages.user.view.UserDetailsView;
import name.shamansir.mvplayout.lib.mvp.state.IsStatedPortletView;
import name.shamansir.mvplayout.lib.mvp.state.StatedPortletPresenter;
import name.shamansir.mvplayout.shared.dao.User;

import com.mvp4g.client.annotation.Presenter;

@Presenter(view = UserDetailsView.class)
public class UserDetailsPresenter extends StatedPortletPresenter<UserDetailsPresenter.Display, UserEventBus> {
	
	public interface Display extends IsStatedPortletView {
        void showDetails(User user);
	}	

	public UserDetailsPresenter() {
	}
	
	@Override
	public void bindView() {
	    super.bindView();
	    
	    state.loading();
	}
	
	//@FromEventBus
	public void onUserSelected(User user) {
	    state.gotData();
	    view.showDetails(user);
	}
		
}
