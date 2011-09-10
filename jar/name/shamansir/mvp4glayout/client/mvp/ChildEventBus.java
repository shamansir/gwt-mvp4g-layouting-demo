package name.shamansir.mvp4glayout.client.mvp;

import com.mvp4g.client.annotation.Event;
import com.mvp4g.client.event.EventBus;

import name.shamansir.mvp4glayout.client.exception.PortalNotFoundException;
import name.shamansir.mvp4glayout.client.mvp.AMainView.PageResizeListener;
import name.shamansir.mvp4glayout.client.mvp.AMainView.PageScrollListener;
import name.shamansir.mvp4glayout.client.ui.LayoutBuilder.CanBuildLayout;
import name.shamansir.mvp4glayout.client.ui.Pluggable;
import name.shamansir.mvp4glayout.client.ui.Portal;
import name.shamansir.mvp4glayout.client.ui.state.State;
import name.shamansir.mvp4glayout.client.ui.state.UpdatesStateByPlace;
import name.shamansir.mvp4glayout.client.ui.structure.Place;

public interface ChildEventBus extends EventBus, UpdatesStateByPlace {
    
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
