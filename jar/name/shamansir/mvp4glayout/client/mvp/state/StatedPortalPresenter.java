/**
 * 
 */
package name.shamansir.mvp4glayout.client.mvp.state;

import name.shamansir.mvp4glayout.client.mvp.ChildEventBus;
import name.shamansir.mvp4glayout.client.mvp.PortalPresenter;
import name.shamansir.mvp4glayout.client.ui.LayoutBuilder;
import name.shamansir.mvp4glayout.client.ui.Portal;
import name.shamansir.mvp4glayout.client.ui.state.PlugsStates;
import name.shamansir.mvp4glayout.client.ui.state.PortalStateDirector;
import name.shamansir.mvp4glayout.client.ui.state.State;
import name.shamansir.mvp4glayout.client.ui.state.StateDirector;
import name.shamansir.mvp4glayout.client.ui.structure.Place;

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
public abstract class StatedPortalPresenter<V extends IsStatedPortalView,  
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
        plug(where, view.getViewFor(State.NO_DATA));
    }
    
    @Override    
    public void plugLoading(Place where) {
        plug(where, view.getViewFor(State.LOADING_DATA));
    }
    
    @Override
    public void plugNoMatches(Place where) {
        plug(where, view.getViewFor(State.NO_MATCHES));
    }

}
