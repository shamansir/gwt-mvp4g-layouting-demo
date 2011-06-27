package name.shamansir.mvplayout.lib.mvp;

import name.shamansir.mvplayout.lib.exception.PortalNotFoundException;
import name.shamansir.mvplayout.lib.mvp.AMainView.PageResizeListener;
import name.shamansir.mvplayout.lib.mvp.AMainView.PageScrollListener;
import name.shamansir.mvplayout.lib.ui.Pluggable;
import name.shamansir.mvplayout.lib.ui.LayoutBuilder.CanBuildLayout;
import name.shamansir.mvplayout.lib.ui.state.State;
import name.shamansir.mvplayout.lib.ui.state.UpdatesState;
import name.shamansir.mvplayout.lib.ui.structure.Place;
import name.shamansir.mvplayout.lib.ui.structure.Portal;

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
