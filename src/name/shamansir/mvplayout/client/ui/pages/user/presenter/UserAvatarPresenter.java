package name.shamansir.mvplayout.client.ui.pages.user.presenter;

import name.shamansir.mvplayout.client.ui.pages.user.UserEventBus;
import name.shamansir.mvplayout.client.ui.pages.user.view.UserAvatarView;
import name.shamansir.mvplayout.client.ui.state.PluggableWithStates;
import name.shamansir.mvplayout.client.ui.state.StatedPortletPresenter;

import com.mvp4g.client.annotation.Presenter;
import com.mvp4g.client.view.LazyView;

@Presenter(view = UserAvatarView.class)
public class UserAvatarPresenter extends StatedPortletPresenter<UserAvatarPresenter.Display, UserEventBus> {
	
	public interface Display extends PluggableWithStates, LazyView {
	}	

	public UserAvatarPresenter() {
	}
		
}
