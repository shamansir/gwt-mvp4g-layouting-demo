package name.shamansir.mvp4glayoutdemo.client.page.user.presenter;

import name.shamansir.mvp4glayout.client.mvp.state.IsStatedPortletView;
import name.shamansir.mvp4glayout.client.mvp.state.StatedPortletPresenter;
import name.shamansir.mvp4glayout.client.ui.state.State;
import name.shamansir.mvp4glayoutdemo.client.page.user.UserEventBus;
import name.shamansir.mvp4glayoutdemo.client.page.user.view.UserDetailsView;
import name.shamansir.mvp4glayoutdemo.shared.dao.User;

import com.mvp4g.client.annotation.Presenter;

@Presenter(view = UserDetailsView.class)
public class UserDetailsPresenter extends StatedPortletPresenter<UserDetailsPresenter.Display, UserEventBus> {
    
    public interface Display extends IsStatedPortletView {
        void showDetails(User user);
    }	

    public UserDetailsPresenter() {
        super(State.NO_DATA);
    }
    
    //@FromEventBus
    public void onShowAdditionalInfo(User user) {
        state.gotData();
        view.showDetails(user);
    }
    	
}
