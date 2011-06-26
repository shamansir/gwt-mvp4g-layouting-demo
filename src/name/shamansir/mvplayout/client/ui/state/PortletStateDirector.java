/**
 * 
 */
package name.shamansir.mvplayout.client.ui.state;

import name.shamansir.mvplayout.client.ui.CanBePlaced;
import name.shamansir.mvplayout.client.ui.HasMainView;
import name.shamansir.mvplayout.client.ui.Layouts.Place;
import name.shamansir.mvplayout.client.ui.state.LayoutWithState.State;

/**
 * <dl>
 * <dt>Project:</dt> <dd>gwt-mvp4g-layouting-demo</dd>
 * <dt>Package:</dt> <dd>name.shamansir.mvplayout.client.ui.state</dd>
 * </dl>
 *
 * <code>PortletStateDirector</code>
 *
 * <p>Description</p>
 *
 * @author Ulric Wilfred <shaman.sir@gmail.com>
 * @date Jun 10, 2011 9:54:56 AM 
 *
 */
public final class PortletStateDirector<V extends HasMainView 
                                                  & HandlesStateChange
                                                  & CanBePlaced> extends StateDirector {
    
    private final V view;
    protected final UpdatesState reactor;       

    protected PortletStateDirector(V handler, UpdatesState reactor) {
        this.view = handler;
        this.reactor = reactor;
    }
    
    @Override
    public void update(State to) {
        final Place place = view.getPlace();
        if (place == null) throw new IllegalStateException("Cannot determine current place");
        view.prepareFor(to);        
        reactor.changeState(place, to);
    }
    
}
