/**
 * 
 */
package name.shamansir.mvplayout.client.pages.user.presenter;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Timer;
import com.google.inject.Inject;
import com.mvp4g.client.annotation.Presenter;

import name.shamansir.mvplayout.client.id.P;
import name.shamansir.mvplayout.client.pages.user.UserEventBus;
import name.shamansir.mvplayout.client.pages.user.layout.UserLayoutBuilder;
import name.shamansir.mvplayout.client.pages.user.view.UserEditView;
import name.shamansir.mvplayout.client.service.UserServiceAsync;
import name.shamansir.mvplayout.lib.mvp.state.IsStatedPortalView;
import name.shamansir.mvplayout.lib.mvp.state.StatedPortalPresenter;
import name.shamansir.mvplayout.lib.ui.ErrorsSafeCallback;
import name.shamansir.mvplayout.lib.ui.Pluggable;
import name.shamansir.mvplayout.lib.ui.structure.Place;
import name.shamansir.mvplayout.shared.dao.User;

/**
 * @author Ulric Wilfred <shaman.sir@gmail.com>
 *
 */
@Presenter(view = UserEditView.class)
public class UserEditPresenter extends 
             StatedPortalPresenter<UserEditPresenter.Display, UserEventBus,
                                   UserLayoutBuilder> {
    
    public interface Display extends IsStatedPortalView {
        
        public void clear();        
        public User collect();
        public void showUser(User user);
        
        public HasClickHandlers getSaveButton();
        public void userSavedAs(int newId);        
        
        public Pluggable getAgeEditor();
        public Pluggable getInfoEditor();
        public Pluggable getAvatarEditor();
        public Pluggable getTestWidget();

    }
    
    @Inject UserServiceAsync service;
    
    public UserEditPresenter() {
        super(P.USER_EDIT.portal);
    }
    
    @Override
    public void bindView() {
        super.bindView();
        
        view.getSaveButton().addClickHandler(new ClickHandler() {
            
            @Override
            public void onClick(ClickEvent event) {
                service.saveUser(view.collect(), new ErrorsSafeCallback<Integer>(eventBus) {

                    @Override
                    public void onSuccess(Integer newId) {
                        History.newItem(url.build(P.USER_EDIT, String.valueOf(newId)));
                        view.userSavedAs(newId);
                    }
                    
                });
            }
            
        });
    }
    
    public void onEdit(final int uid) {
        if (uid != -1) {
            service.getUser(uid, new ErrorsSafeCallback<User>(eventBus) {
                
                @Override
                public void onSuccess(final User user) {
                    new Timer() {
    
                        @Override public void run() {
                            //currentId = uid;                            
                            state.gotData();
                            view.showUser(user);
                        }
                        
                    }.schedule(3000); // emulate slow data load
    
                }
            });            
        } else {
            //currentId = -1;
            state.gotData();
            view.clear();
        }
    }
    
    public void plugInfoEditor(Place where) {
        eventBus.plug(where, view.getInfoEditor());
    }
    
    public void plugAgeEditor(Place where) {
        eventBus.plug(where, view.getAgeEditor());
    }
    
    public void plugAvatarEditor(Place where) {
        eventBus.plug(where, view.getAvatarEditor());
    }
    
    public void plugTestWidget(Place where) {
        eventBus.plug(where, view.getTestWidget());
    }

}
