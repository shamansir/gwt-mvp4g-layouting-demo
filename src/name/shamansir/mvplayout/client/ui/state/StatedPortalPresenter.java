/**
 * 
 */
package name.shamansir.mvplayout.client.ui.state;

import name.shamansir.mvplayout.client.ui.LayoutBuilder;
import name.shamansir.mvplayout.client.ui.Portal;
import name.shamansir.mvplayout.client.ui.PortalPresenter;
import name.shamansir.mvplayout.client.ui.Layouts.Place;
import name.shamansir.mvplayout.client.ui.pages.base.ChildEventBus;

import com.mvp4g.client.view.LazyView;

/**
 * <dl>
 * <dt>Project:</dt> <dd>gwt-mvp4g-layouting-demo</dd>
 * <dt>Package:</dt> <dd>name.shamansir.mvplayout.client.ui</dd>
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
public abstract class StatedPortalPresenter<V extends HasStatesPanels 
                                                      & HandlesStateChange
                                                      & LazyView, 
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
        eventBus.plug(where, view.getEmptyView());
    }
    
    @Override    
    public void plugLoading(Place where) {
        eventBus.plug(where, view.getLoadingView());
    }
    
    @Override
    public void plugNoMatches(Place where) {
        eventBus.plug(where, view.getNoMatchesView());
    }    

}
