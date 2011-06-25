/**
 * 
 */
package name.shamansir.mvplayout.client.ui.state;

import name.shamansir.mvplayout.client.ui.HasMainView;
import name.shamansir.mvplayout.client.ui.PortletPresenter;
import name.shamansir.mvplayout.client.ui.Layouts.Place;
import name.shamansir.mvplayout.client.ui.pages.base.ChildEventBus;
import name.shamansir.mvplayout.client.ui.state.LayoutWithState.State;

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
public abstract class StatedPortletPresenter<V extends LazyView & 
                                                       HasMainView &
                                                       HasStatesPanels &
                                                       HandlesStateChange, 
                                   E extends ChildEventBus> 
                                   extends PortletPresenter<V, E> {
    
    protected StateDirector state;
    
    protected StatedPortletPresenter() {

    }
    
    @Override
    public void bindView() {
        state = new PortletStateDirector<V>(view, eventBus);
    }
    
    public void plugState(Place where, State state) {
        switch (state) {
            case HAS_DATA: plug(where); break; 
            case LOADING_DATA: eventBus.plug(where, view.getLoadingView()); break;
            case NO_DATA: eventBus.plug(where, view.getEmptyView()); break;
            case NO_MATCHES: eventBus.plug(where, view.getNoMatchesView()); break;
        }
    }

}
