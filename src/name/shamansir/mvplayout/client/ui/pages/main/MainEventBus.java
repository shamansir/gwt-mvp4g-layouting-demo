package name.shamansir.mvplayout.client.ui.pages.main;

import name.shamansir.mvplayout.client.exception.PortalNotFoundException;
import name.shamansir.mvplayout.client.ui.Portal;
import name.shamansir.mvplayout.client.ui.LayoutBuilder.CanBuildLayout;
import name.shamansir.mvplayout.client.ui.pages.company.CompanyModule;
import name.shamansir.mvplayout.client.ui.pages.main.presenter.MainPresenter;
import name.shamansir.mvplayout.client.ui.pages.main.view.MainView;
import name.shamansir.mvplayout.client.ui.pages.news.NewsModule;
import name.shamansir.mvplayout.client.ui.pages.user.UserModule;
import name.shamansir.mvplayout.client.ui.state.HandlesStates;
import name.shamansir.mvplayout.client.ui.state.LayoutWithState.State;

import com.mvp4g.client.annotation.Debug;
import com.mvp4g.client.annotation.Event;
import com.mvp4g.client.annotation.Events;
import com.mvp4g.client.annotation.module.ChildModule;
import com.mvp4g.client.annotation.module.ChildModules;
import com.mvp4g.client.event.EventBus;

@Debug
@Events(module = MainModule.class, startView = MainView.class, historyOnStart = true)
@ChildModules( {
	@ChildModule(moduleClass = UserModule.class, autoDisplay = false, async = true),
	@ChildModule(moduleClass = CompanyModule.class, autoDisplay = false, async = true),	
	@ChildModule(moduleClass = NewsModule.class, autoDisplay = false, async = true),
} )
public interface MainEventBus extends EventBus, HandlesStates {
    
    // =============== navigation ==============================================

	@Event(modulesToLoad = UserModule.class)
	public void users();

	@Event(modulesToLoad = CompanyModule.class)	
	public void companies();

	@Event(modulesToLoad = NewsModule.class)
	public void news();
	
	// =============== portals =================================================
	
    @Event(handlers = MainPresenter.class)
    public void newPortal(Portal portal, CanBuildLayout builder);
    
    @Override @Event(handlers = MainPresenter.class, calledMethod = "updateState")
    public void updateState(State state);
    
    @Event(handlers = MainPresenter.class, calledMethod = "clearPage")
    public void clearPage();
    
	@Event(handlers = MainPresenter.class)
	public void portalNotFound(PortalNotFoundException pnfe);
	
	@Event(handlers = MainPresenter.class)
	public void handle(Throwable caught);
	
}
