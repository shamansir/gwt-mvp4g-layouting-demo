/**
 * 
 */
package name.shamansir.mvplayout.lib.ui.state;

import name.shamansir.mvplayout.lib.mvp.ChildEventBus;
import name.shamansir.mvplayout.lib.ui.CanBePlaced;
import name.shamansir.mvplayout.lib.ui.HasMainView;
import name.shamansir.mvplayout.lib.ui.PortletPresenter;
import name.shamansir.mvplayout.lib.ui.Layouts.Place;
import name.shamansir.mvplayout.lib.ui.state.LayoutWithState.State;

import com.allen_sauer.gwt.log.client.Log;
import com.mvp4g.client.view.LazyView;

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
public abstract class StatedPortletPresenter<V extends LazyView & 
                                                       HasMainView &
                                                       HasStatesPanels &
                                                       HandlesStateChange &
                                                       CanBePlaced, 
                                   E extends ChildEventBus> 
                                   extends PortletPresenter<V, E> {
    
    protected StateDirector state;
    protected final State initialState;
    
    protected StatedPortletPresenter() {
        this(State.LOADING_DATA);
    }
    
    protected StatedPortletPresenter(State initialState) {
        this.initialState = initialState; 
    }
    
    @Override
    public void bindView() {
        state = new PortletStateDirector<V>(view, eventBus);
    }
    
    @Override
    public void plug(Place where) {
        plugState(where, initialState);
    }
    
    public void plugState(Place where, State state) {
        if (view.hasViewFor(state)) {
            eventBus.plug(where, view.getViewFor(state));
        } else {
            Log.warn("No view regitstered for state " + state +
                     " to place it to " + where);
        }
    }

}
