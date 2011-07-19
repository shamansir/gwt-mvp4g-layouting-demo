/**
 * 
 */
package name.shamansir.mvplayout.lib.mvp.state;

import name.shamansir.mvplayout.lib.mvp.ChildEventBus;
import name.shamansir.mvplayout.lib.mvp.PortletPresenter;
import name.shamansir.mvplayout.lib.ui.Pluggable;
import name.shamansir.mvplayout.lib.ui.state.PortletStateDirector;
import name.shamansir.mvplayout.lib.ui.state.State;
import name.shamansir.mvplayout.lib.ui.state.StateDirector;
import name.shamansir.mvplayout.lib.ui.structure.Place;

import com.allen_sauer.gwt.log.client.Log;

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
public abstract class StatedPortletPresenter<V extends IsStatedPortletView, 
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
            Pluggable plug = view.getViewFor(state);
            plug.changeState(state);
            eventBus.plug(where, plug);
        } else {
            Log.warn("No view regitstered for state " + state +
                     " to place it to " + where);
        }
    }

}
