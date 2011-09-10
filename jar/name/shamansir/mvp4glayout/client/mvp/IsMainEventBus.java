/**
 * 
 */
package name.shamansir.mvp4glayout.client.mvp;

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


/**
 * @author Ulric Wilfred <shaman.sir@gmail.com>
 *
 */
public interface IsMainEventBus extends EventBus, UpdatesStateByPlace {
    
    public void newPortal(Portal portal, CanBuildLayout builder);
    
    public void changeState(Place where, State state); // if null, updates layout state
    
    public void clearPage();
    
    public void portalNotFound(PortalNotFoundException pnfe);
    
    public void plug(Place where, Pluggable what);  
    
    public void handle(Throwable caught);
    
    public void subscribePageResize(PageResizeListener listener);
    
    public void subscribePageScroll(PageScrollListener listener);

}
