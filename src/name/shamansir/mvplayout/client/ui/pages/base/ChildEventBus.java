package name.shamansir.mvplayout.client.ui.pages.base;

import name.shamansir.mvplayout.client.exception.PortalNotFoundException;
import name.shamansir.mvplayout.client.ui.Pluggable;
import name.shamansir.mvplayout.client.ui.Portal;
import name.shamansir.mvplayout.client.ui.LayoutBuilder.CanBuildLayout;
import name.shamansir.mvplayout.client.ui.Layouts.Place;
import name.shamansir.mvplayout.client.ui.pages.main.view.MainView.PageResizeListener;
import name.shamansir.mvplayout.client.ui.pages.main.view.MainView.PageScrollListener;
import name.shamansir.mvplayout.client.ui.state.UpdatesState;
import name.shamansir.mvplayout.client.ui.state.LayoutWithState.State;

import com.mvp4g.client.annotation.Event;
import com.mvp4g.client.event.EventBus;

public interface ChildEventBus extends EventBus, UpdatesState {
    
    @Event(forwardToParent = true)
    public void newPortal(Portal portal, CanBuildLayout builder);
    
    @Event(forwardToParent = true)
    public void changeState(Place where, State state);
    
    @Event(forwardToParent = true)
    public void clearPage();
    
    @Event(forwardToParent = true)
    public void portalNotFound(PortalNotFoundException pnfe);

    @Event(forwardToParent = true)
	public void handle(Throwable caught);
    
    @Event(forwardToParent = true)
    public void plug(Place where, Pluggable what);
    
	@Event(forwardToParent = true)
	public void subscribePageResize(PageResizeListener listener);
	
	@Event(forwardToParent = true)
	public void subscribePageScroll(PageScrollListener listener);	    
    
}
