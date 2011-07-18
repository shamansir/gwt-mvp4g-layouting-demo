package name.shamansir.mvplayout.client.page.user.presenter;

import name.shamansir.mvplayout.client.page.user.UserEventBus;
import name.shamansir.mvplayout.client.page.user.view.UserInfoView;
import name.shamansir.mvplayout.client.service.UserServiceAsync;
import name.shamansir.mvplayout.lib.mvp.state.IsStatedPortletView;
import name.shamansir.mvplayout.lib.mvp.state.StatedPortletPresenter;
import name.shamansir.mvplayout.lib.utils.SafeCallback;
import name.shamansir.mvplayout.shared.dao.User;

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
