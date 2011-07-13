package name.shamansir.mvplayout.client.page.user;

import name.shamansir.mvplayout.client.page.user.history.UserHistoryConverter;
import name.shamansir.mvplayout.client.page.user.presenter.UserAvatarPresenter;
import name.shamansir.mvplayout.client.page.user.presenter.UserDetailsPresenter;
import name.shamansir.mvplayout.client.page.user.presenter.UserEditPresenter;
import name.shamansir.mvplayout.client.page.user.presenter.UserInfoPresenter;
import name.shamansir.mvplayout.client.page.user.presenter.UserListPresenter;
import name.shamansir.mvplayout.client.page.user.view.UserListView;
import name.shamansir.mvplayout.lib.mvp.ChildEventBus;
import name.shamansir.mvplayout.lib.ui.structure.Place;
import name.shamansir.mvplayout.shared.dao.User;

import com.mvp4g.client.annotation.Event;
import com.mvp4g.client.annotation.Events;

@Events(module = UserModule.class, startView = UserListView.class)
public interface UserEventBus extends ChildEventBus {
	
    // navigation events
    
	@Event(name = "list", navigationEvent = true,
		   handlers = UserListPresenter.class, 
		   historyConverter = UserHistoryConverter.class)
	public void users(String filter);
	
	@Event(name = "show", navigationEvent = true,
	       handlers = UserInfoPresenter.class, 
	       historyConverter = UserHistoryConverter.class)
	public void show(int uid);
	
	@Event(name = "edit", navigationEvent = true,
	       handlers = UserEditPresenter.class,
	       historyConverter = UserHistoryConverter.class)
	public void edit(int uid);
	
	// secondary events
	
	@Event(handlers = { UserDetailsPresenter.class, 
	                    UserAvatarPresenter.class })
    public void showAdditionalInfo(User user);
	
	// projecting events, portlets
	
	@Event(handlers = UserInfoPresenter.class, calledMethod = "plug")
    public void plugUserInfo(Place where);
	
    @Event(handlers = UserListPresenter.class, calledMethod = "plug")
	public void plugUsersList(Place where);
    
    @Event(handlers = UserDetailsPresenter.class, calledMethod = "plug")
    public void plugUserDetails(Place where);    
    
    @Event(handlers = UserAvatarPresenter.class, calledMethod = "plug")
    public void plugUserAvatar(Place where);
    
    // projecting events, portals

    @Event(handlers = UserEditPresenter.class, calledMethod = "plugInfoEditor")
    public void plugUserInfoEditor(Place where);

    @Event(handlers = UserEditPresenter.class, calledMethod = "plugAgeEditor")
    public void plugUserAgeEditor(Place where);

    @Event(handlers = UserEditPresenter.class, calledMethod = "plugAvatarEditor")
    public void plugUserAvatarEditor(Place where);

    @Event(handlers = UserEditPresenter.class, calledMethod = "plugTestWidget")
    public void plugTestWidget(Place where);

    @Event(handlers = UserEditPresenter.class, calledMethod = "plugLoading")
    public void plugLoadingUserInEditor(Place where);

    @Event(handlers = UserEditPresenter.class, calledMethod = "plugEmpty")
    public void plugNoUserInEditor(Place where);

}
