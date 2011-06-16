package name.shamansir.mvplayout.client.ui.pages.user.presenter;

import name.shamansir.mvplayout.client.ui.pages.user.UserEventBus;
import name.shamansir.mvplayout.client.ui.pages.user.view.UserDetailsView;
import name.shamansir.mvplayout.client.ui.state.IsStatedPortletView;
import name.shamansir.mvplayout.client.ui.state.StatedPortletPresenter;

import com.mvp4g.client.annotation.Presenter;

@Presenter(view = UserDetailsView.class)
public class UserDetailsPresenter extends StatedPortletPresenter<UserDetailsPresenter.Display, UserEventBus> {
	
	public interface Display extends IsStatedPortletView {
	}	

	public UserDetailsPresenter() {
	}
		
}
