/**
 * 
 */
package name.shamansir.mvplayout.client.ui.state;

import name.shamansir.mvplayout.client.ui.HasMainView;
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
public final class PortletStateDirector<V extends HasMainView & HandlesStateChange> extends StateDirector {
    
    private final V view;
    protected final UpdatesState reactor;       

    protected PortletStateDirector(V handler, UpdatesState reactor) {
        this.view = handler;
        this.reactor = reactor;
    }
    
    @Override
    public void update(State to) {
        view.prepareFor(to);
        reactor.changeState(view.getMainView().getPlace(), to);
    }
    
}
