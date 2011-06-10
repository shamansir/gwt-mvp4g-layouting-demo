/**
 * 
 */
package name.shamansir.mvplayout.client.ui.state;

import name.shamansir.mvplayout.client.ui.state.LayoutWithState.State;

/**
 * <dl>
 * <dt>Project:</dt> <dd>gwt-mvp4g-layouting-demo</dd>
 * <dt>Package:</dt> <dd>name.shamansir.mvplayout.client.ui.state</dd>
 * </dl>
 *
 * <code>PortalStateDirector</code>
 *
 * <p>Description</p>
 *
 * @author Ulric Wilfred <shaman.sir@gmail.com>
 * @date Jun 10, 2011 9:56:01 AM 
 *
 */
public final class PortalStateDirector<V extends HandlesStateChange> extends StateDirector {
    
    private final V view;
    protected final UpdatesState reactor;
    
    protected PortalStateDirector(V view, UpdatesState reactor) {
        this.view = view;
        this.reactor = reactor;
    }

    @Override
    public void update(State to) {
        view.prepareFor(to);
        reactor.changeState(null, to);
    }
    
}
