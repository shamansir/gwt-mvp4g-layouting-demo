/**
 * 
 */
package name.shamansir.mvplayout.lib.mvp.state;

import com.mvp4g.client.view.LazyView;

import name.shamansir.mvplayout.lib.mvp.ChildEventBus;
import name.shamansir.mvplayout.lib.mvp.PortalPresenter;
import name.shamansir.mvplayout.lib.ui.LayoutBuilder;
import name.shamansir.mvplayout.lib.ui.state.HandlesStateChange;
import name.shamansir.mvplayout.lib.ui.state.HasStatesPanels;
import name.shamansir.mvplayout.lib.ui.state.PlugsStates;
import name.shamansir.mvplayout.lib.ui.state.PortalStateDirector;
import name.shamansir.mvplayout.lib.ui.state.State;
import name.shamansir.mvplayout.lib.ui.state.StateDirector;
import name.shamansir.mvplayout.lib.ui.structure.Place;
import name.shamansir.mvplayout.lib.ui.structure.Portal;

/**
 * <dl>
 * <dt>Project:</dt> <dd>gwt-mvp4g-layouting-demo</dd>
 * <dt>Package:</dt> <dd>name.shamansir.mvplayout.lib.ui</dd>
 * </dl>
 *
 * <code>StatedPortalPresenter</code>
 *
 * <p>Description</p>
 *
 * @author Ulric Wilfred <shaman.sir@gmail.com>
 * @date Apr 28, 2011 10:16:17 PM 
 *
 */
public abstract class StatedPortalPresenter<V extends LazyView &
                                                      HasStatesPanels &
                                                      HandlesStateChange,  
                                   E extends ChildEventBus,
                                   L extends LayoutBuilder<E>> 
                                   extends PortalPresenter<V, E, L> 
								   implements PlugsStates {
    
    protected StateDirector state;    
    
    protected StatedPortalPresenter(Portal portal) {
        super(portal);
    }  
    
    @Override
    public void bindView() {
        state = new PortalStateDirector<V>(view, eventBus);
    }
    
    @Override    
    public void plugEmpty(Place where) {
        eventBus.plug(where, view.getViewFor(State.NO_DATA));
    }
    
    @Override    
    public void plugLoading(Place where) {
        eventBus.plug(where, view.getViewFor(State.LOADING_DATA));
    }
    
    @Override
    public void plugNoMatches(Place where) {
        eventBus.plug(where, view.getViewFor(State.NO_MATCHES));
    }

}
