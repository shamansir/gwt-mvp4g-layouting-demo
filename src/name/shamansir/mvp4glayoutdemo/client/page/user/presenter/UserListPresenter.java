package name.shamansir.mvp4glayoutdemo.client.page.user.presenter;

import java.util.Set;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Anchor;
import com.google.inject.Inject;
import com.mvp4g.client.annotation.Presenter;

import name.shamansir.mvp4glayout.client.SafeCallback;
import name.shamansir.mvp4glayout.client.mvp.state.IsStatedPortletView;
import name.shamansir.mvp4glayout.client.mvp.state.StatedPortletPresenter;

import name.shamansir.mvp4glayoutdemo.client.id.P;
import name.shamansir.mvp4glayoutdemo.client.page.user.UserEventBus;
import name.shamansir.mvp4glayoutdemo.client.page.user.view.UserListView;
import name.shamansir.mvp4glayoutdemo.client.service.UserServiceAsync;
import name.shamansir.mvp4glayoutdemo.client.service.exception.NoMatchesException;
import name.shamansir.mvp4glayoutdemo.shared.dao.User;

@Presenter(view = UserListView.class)
public class UserListPresenter extends StatedPortletPresenter<UserListPresenter.Display, UserEventBus> {
    
    public interface Display extends IsStatedPortletView {
    	public void showUsers(Set<User> users);
        public Set<UserRow> getRows();
    }
    
    public interface UserRow extends HasClickHandlers {
        public User getUser();
        public Anchor getEditAnchor();
        public Anchor getShowAnchor();
    }    

    public UserListPresenter() { }
    
    @Inject UserServiceAsync service;

    public void onUsers(String filter) {	    
        //state.loading();
        
        service.getUsers(filter, new SafeCallback<Set<User>>(eventBus) {
    		
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
                            /* row.setShowHref("#" + url.build(P.USER_SHOW, 
                                        String.valueOf(row.getUser().getId()))); */
                            url.link(row.getShowAnchor(), P.USER_SHOW, 
                                    String.valueOf(row.getUser().getId()));
                            url.link(row.getEditAnchor(), P.USER_EDIT, 
                                    String.valueOf(row.getUser().getId()));                            
                        }
                    }
    		        
    		    }.schedule(3000); // emulate slow data load
    		}
    		
    		@Override
    		public void onFailure(Throwable caught) {
    		    if (caught instanceof NoMatchesException) {
    		        state.noMatches();
    		        return;
    		    }
    		    super.onFailure(caught);
    		}
    		
    	});
    }	
    
}
