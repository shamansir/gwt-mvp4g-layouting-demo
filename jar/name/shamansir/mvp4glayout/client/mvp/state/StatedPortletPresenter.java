/**
 * 
 */
package name.shamansir.mvp4glayout.client.mvp.state;

import name.shamansir.mvp4glayout.client.mvp.ChildEventBus;
import name.shamansir.mvp4glayout.client.mvp.PortletPresenter;
import name.shamansir.mvp4glayout.client.ui.Pluggable;
import name.shamansir.mvp4glayout.client.ui.state.PortletStateDirector;
import name.shamansir.mvp4glayout.client.ui.state.State;
import name.shamansir.mvp4glayout.client.ui.state.StateDirector;
import name.shamansir.mvp4glayout.client.ui.structure.Place;

import com.allen_sauer.gwt.log.client.Log;

/**
 * <dl>
 * <dt>Project:</dt> <dd>gwt-mvp4g-layouting-demo</dd>
 * <dt>Package:</dt> <dd>name.shamansir.mvp4glayout.client.ui</dd>
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
