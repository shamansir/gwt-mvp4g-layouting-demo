package name.shamansir.mvp4glayoutdemo.client.page.user.presenter;

import com.mvp4g.client.annotation.Presenter;

import name.shamansir.mvp4glayout.client.mvp.state.IsStatedPortletView;
import name.shamansir.mvp4glayout.client.mvp.state.StatedPortletPresenter;
import name.shamansir.mvp4glayout.client.ui.state.State;

import name.shamansir.mvp4glayoutdemo.client.page.user.UserEventBus;
import name.shamansir.mvp4glayoutdemo.client.page.user.view.UserAvatarView;
import name.shamansir.mvp4glayoutdemo.shared.dao.User;

@Presenter(view = UserAvatarView.class)
public class UserAvatarPresenter extends StatedPortletPresenter<UserAvatarPresenter.Display, UserEventBus> {
    
    public interface Display extends IsStatedPortletView {
        void showAvatar(String avatar);
    }	

    public UserAvatarPresenter() {
        super(State.NO_DATA);
    }
    
    public void onShowAdditionalInfo(User user) {
        state.gotData();
        view.showAvatar(user.avatar);
    }
    	
}
