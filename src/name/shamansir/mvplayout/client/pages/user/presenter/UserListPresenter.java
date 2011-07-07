package name.shamansir.mvplayout.client.pages.user.presenter;

import java.util.Set;

import name.shamansir.mvplayout.client.id.P;
import name.shamansir.mvplayout.client.pages.user.UserEventBus;
import name.shamansir.mvplayout.client.pages.user.view.UserListView;
import name.shamansir.mvplayout.client.service.UserServiceAsync;
import name.shamansir.mvplayout.lib.mvp.state.IsStatedPortletView;
import name.shamansir.mvplayout.lib.mvp.state.StatedPortletPresenter;
import name.shamansir.mvplayout.lib.ui.ErrorsSafeCallback;
import name.shamansir.mvplayout.shared.dao.User;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.Timer;
import com.google.inject.Inject;
import com.mvp4g.client.annotation.Presenter;

@Presenter(view = UserListView.class)
public class UserListPresenter extends StatedPortletPresenter<UserListPresenter.Display, UserEventBus> {
    
    public interface UserRow extends HasClickHandlers {
        public User getUser();
        public void setUserLink(String href);
    }
	
	public interface Display extends IsStatedPortletView {
		public void showUsers(Set<User> users);
        public Set<UserRow> getRows();
	}	

	public UserListPresenter() { }
	
	@Inject UserServiceAsync service;

	public void onUsers(String filter) {	    
	    //state.loading();
	    
	    service.getUsers(filter, new ErrorsSafeCallback<Set<User>>(eventBus) {
			
			@Override
			public void onSuccess(final Set<User> users) {
			    new Timer() {

			        @Override public void run() {
                        state.gotData(users);
                        view.showUsers(users);
                        
                        for (final UserRow row: view.getRows()) {
                            row.addClickHandler(new ClickHandler() {                                
                                @Override
                                public void onClick(ClickEvent event) {
                                    eventBus.showAdditionalInfo(row.getUser());
                                }
                            });
                            row.setUserLink(url.build(P.USER_SHOW, 
                                        String.valueOf(row.getUser().getId())));
                        }
                    }
			        
			    }.schedule(3000); // emulate slow data load
			}
			
		});
	}	
	
}
