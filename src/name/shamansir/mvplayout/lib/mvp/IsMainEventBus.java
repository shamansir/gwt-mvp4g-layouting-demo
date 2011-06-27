/**
 * 
 */
package name.shamansir.mvplayout.lib.mvp;

import com.mvp4g.client.event.EventBus;

import name.shamansir.mvplayout.lib.exception.PortalNotFoundException;
import name.shamansir.mvplayout.lib.mvp.AMainView.PageResizeListener;
import name.shamansir.mvplayout.lib.mvp.AMainView.PageScrollListener;
import name.shamansir.mvplayout.lib.ui.Pluggable;
import name.shamansir.mvplayout.lib.ui.LayoutBuilder.CanBuildLayout;
import name.shamansir.mvplayout.lib.ui.state.State;
import name.shamansir.mvplayout.lib.ui.state.UpdatesState;
import name.shamansir.mvplayout.lib.ui.structure.Place;
import name.shamansir.mvplayout.lib.ui.structure.Portal;


/**
 * @author Ulric Wilfred <shaman.sir@gmail.com>
 *
 */
public interface IsMainEventBus extends EventBus, UpdatesState {
    
    public void newPortal(Portal portal, CanBuildLayout builder);
    
    public void changeState(Place where, State state); // if null, updates layout state
    
    public void clearPage();
    
    public void portalNotFound(PortalNotFoundException pnfe);
    
    public void plug(Place where, Pluggable what);  
    
    public void handle(Throwable caught);
    
    public void subscribePageResize(PageResizeListener listener);
    
    public void subscribePageScroll(PageScrollListener listener);

}
