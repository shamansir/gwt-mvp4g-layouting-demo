package name.shamansir.mvp4glayoutdemo.client.page.user.presenter;

import name.shamansir.mvp4glayout.client.mvp.state.IsStatedPortletView;
import name.shamansir.mvp4glayout.client.mvp.state.StatedPortletPresenter;
import name.shamansir.mvp4glayout.util.SafeCallback;
import name.shamansir.mvp4glayoutdemo.client.page.user.UserEventBus;
import name.shamansir.mvp4glayoutdemo.client.page.user.view.UserInfoView;
import name.shamansir.mvp4glayoutdemo.client.service.UserServiceAsync;
import name.shamansir.mvp4glayoutdemo.shared.dao.User;

import com.google.gwt.user.client.Timer;
import com.google.inject.Inject;
import com.mvp4g.client.annotation.Presenter;

@Presenter(view = UserInfoView.class)
public class UserInfoPresenter extends StatedPortletPresenter<UserInfoPresenter.Display, UserEventBus> {    
    
    public interface Display extends IsStatedPortletView {
    	public void showUser(User user);
    }	

    public UserInfoPresenter() { }
    
    @Inject UserServiceAsync service;

    public void onShow(int uid) {	    
        //state.loading();
        if (uid != -1) {
            service.getUser(uid, new SafeCallback<User>(eventBus) {
    
                @Override
                public void onSuccess(final User user) {
                    new Timer() {
    
                        @Override public void run() {
                            state.gotData();
                            view.showUser(user);
                            
                            eventBus.showAdditionalInfo(user);
                        }
                        
                    }.schedule(3000); // emulate slow data load
    
                }
            });
        } else throw new IllegalArgumentException("UID is -1");    
    }	
    
}
