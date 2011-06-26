package name.shamansir.mvplayout.client.ui.pages.user.presenter;

import name.shamansir.mvplayout.client.ui.pages.user.UserEventBus;
import name.shamansir.mvplayout.client.ui.pages.user.view.UserAvatarView;
import name.shamansir.mvplayout.client.ui.state.IsStatedPortletView;
import name.shamansir.mvplayout.client.ui.state.StatedPortletPresenter;
import name.shamansir.mvplayout.client.ui.state.LayoutWithState.State;
import name.shamansir.mvplayout.shared.dao.User;

import com.mvp4g.client.annotation.Presenter;

@Presenter(view = UserAvatarView.class)
public class UserAvatarPresenter extends StatedPortletPresenter<UserAvatarPresenter.Display, UserEventBus> {
	
	public interface Display extends IsStatedPortletView {
        void showAvatar(String avatar);
	}	

	public UserAvatarPresenter() {
	    super(State.NO_DATA);
	}
	
    public void onUserSelected(User user) {
        state.gotData();
        view.showAvatar(user.avatar);
    }
		
}
