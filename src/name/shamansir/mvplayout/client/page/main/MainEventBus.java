package name.shamansir.mvplayout.client.page.main;

import name.shamansir.mvplayout.client.page.company.CompanyModule;
import name.shamansir.mvplayout.client.page.main.presenter.MainPresenter;
import name.shamansir.mvplayout.client.page.main.view.MainView;
import name.shamansir.mvplayout.client.page.news.NewsModule;
import name.shamansir.mvplayout.client.page.user.UserModule;
import name.shamansir.mvplayout.lib.exception.PortalNotFoundException;
import name.shamansir.mvplayout.lib.mvp.AMainView.PageResizeListener;
import name.shamansir.mvplayout.lib.mvp.AMainView.PageScrollListener;
import name.shamansir.mvplayout.lib.mvp.IsMainEventBus;
import name.shamansir.mvplayout.lib.ui.LayoutBuilder.CanBuildLayout;
import name.shamansir.mvplayout.lib.ui.Pluggable;
import name.shamansir.mvplayout.lib.ui.Portal;
import name.shamansir.mvplayout.lib.ui.state.State;
import name.shamansir.mvplayout.lib.ui.structure.Place;

import com.mvp4g.client.annotation.Debug;
import com.mvp4g.client.annotation.Event;
import com.mvp4g.client.annotation.Events;
import com.mvp4g.client.annotation.InitHistory;
import com.mvp4g.client.annotation.NotFoundHistory;
import com.mvp4g.client.annotation.Start;
import com.mvp4g.client.annotation.module.ChildModule;
import com.mvp4g.client.annotation.module.ChildModules;

@Debug
@Events(module = MainModule.class, startView = MainView.class, historyOnStart = true)
@ChildModules( {
	@ChildModule(moduleClass = UserModule.class, autoDisplay = false, async = true),
	@ChildModule(moduleClass = CompanyModule.class, autoDisplay = false, async = true),	
	@ChildModule(moduleClass = NewsModule.class, autoDisplay = false, async = true),
} )
public interface MainEventBus extends IsMainEventBus {
    
    @Start
    @InitHistory
    @Event(handlers = MainPresenter.class)
    public void start();
    
    // =============== navigation ==============================================

	@Event(modulesToLoad = UserModule.class)
	public void users(String filter);

	@Event(modulesToLoad = CompanyModule.class)	
	public void companies();

	@Event(modulesToLoad = NewsModule.class)
	public void news();
	
	// =============== portals =================================================
	
    @Event(handlers = MainPresenter.class)
    public void newPortal(Portal portal, CanBuildLayout builder);
    
    @Override @Event(handlers = MainPresenter.class, calledMethod = "updateState")
    public void changeState(Place where, State state); // if null, updates layout state
    
    @Event(handlers = MainPresenter.class, calledMethod = "clearPage")
    public void clearPage();
    
	@Event(handlers = MainPresenter.class)
	public void portalNotFound(PortalNotFoundException pnfe);
	
	@Event(handlers = MainPresenter.class, calledMethod = "plug")
    public void plug(Place where, Pluggable what);	
	
	@Event(handlers = MainPresenter.class)
	public void handle(Throwable caught);
	
	@Event(handlers = MainPresenter.class, calledMethod = "subscribePageResize")
	public void subscribePageResize(PageResizeListener listener);
	
	@Event(handlers = MainPresenter.class, calledMethod = "subscribePageScroll")
	public void subscribePageScroll(PageScrollListener listener);
	
	// =============== special =================================================
	
	@NotFoundHistory
	@Event(handlers = MainPresenter.class)
	public void show404();
	
}
