package name.shamansir.mvplayout.client.ui.pages.base;

import name.shamansir.mvplayout.client.ui.Portal;
import name.shamansir.mvplayout.client.ui.LayoutBuilder.CanBuildLayout;
import name.shamansir.mvplayout.client.ui.state.HandlesStates;
import name.shamansir.mvplayout.client.ui.state.LayoutWithState.State;

import com.mvp4g.client.annotation.Event;
import com.mvp4g.client.event.EventBus;

public interface ChildEventBus extends EventBus, HandlesStates {
    
    @Event(forwardToParent = true)
    public void newPortal(Portal portal, CanBuildLayout builder);
    
    @Event(forwardToParent = true)
    public void updateState(State state);
    
    @Event(forwardToParent = true)
    public void clearPage();
    
    /* 
    @Event(forwardToParent = true)
    public void forceLayout(LayoutId layout);

    @Event(forwardToParent = true)
    public void project(HasWidgets where, Widget what);
    
    @Event(forwardToParent = true)
    public void changeWidget(Place where, Widget widget);
    */
    
}
