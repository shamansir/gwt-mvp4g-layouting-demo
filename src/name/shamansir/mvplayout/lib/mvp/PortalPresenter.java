package name.shamansir.mvplayout.lib.mvp;

import name.shamansir.mvplayout.lib.ui.LayoutBuilder;
import name.shamansir.mvplayout.lib.ui.LayoutBuilders;
import name.shamansir.mvplayout.lib.ui.Pluggable;
import name.shamansir.mvplayout.lib.ui.Portal;
import name.shamansir.mvplayout.lib.ui.Portal.PortalUrlBuilder;
import name.shamansir.mvplayout.lib.ui.Portal.UrlBuilder;
import name.shamansir.mvplayout.lib.ui.structure.Place;

import com.mvp4g.client.presenter.LazyPresenter;

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
