package name.shamansir.mvp4glayout.client.mvp;

import com.mvp4g.client.presenter.LazyPresenter;

import name.shamansir.mvp4glayout.client.ui.LayoutBuilder;
import name.shamansir.mvp4glayout.client.ui.LayoutBuilders;
import name.shamansir.mvp4glayout.client.ui.Pluggable;
import name.shamansir.mvp4glayout.client.ui.Portal;
import name.shamansir.mvp4glayout.client.ui.Portal.PortalUrlBuilder;
import name.shamansir.mvp4glayout.client.ui.Portal.UrlBuilder;
import name.shamansir.mvp4glayout.client.ui.structure.Place;

public abstract class PortalPresenter<V extends IsPortalView, 
                                      E extends ChildEventBus,
                                      L extends LayoutBuilder<E>> 
                      extends LazyPresenter<V, E> {
    
    protected final UrlBuilder url = PortalUrlBuilder.get();
    protected final Portal portal;
    protected final L layoutBuilder; // FIXME: do we really need it?
    
    @SuppressWarnings("unchecked")
    protected PortalPresenter(Portal portal) {
    	this.portal = portal;
    	this.layoutBuilder = (L) LayoutBuilders.get(portal.group);		
    }
    
    @Override public abstract void bindView();
    
    protected void plug(Place where, Pluggable what) {
        eventBus.plug(where, what);
    }
    
}
