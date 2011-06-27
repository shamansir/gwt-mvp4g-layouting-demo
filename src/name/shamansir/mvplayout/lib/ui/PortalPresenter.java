package name.shamansir.mvplayout.lib.ui;

import name.shamansir.mvplayout.lib.mvp.ChildEventBus;
import name.shamansir.mvplayout.lib.ui.Portal.PortalUrlBuilder;
import name.shamansir.mvplayout.lib.ui.Portal.UrlBuilder;

import com.mvp4g.client.presenter.LazyPresenter;
import com.mvp4g.client.view.LazyView;

public abstract class PortalPresenter<V extends LazyView, 
                                      E extends ChildEventBus,
                                      L extends LayoutBuilder<E>> 
                      extends LazyPresenter<V, E> {
    
    protected final UrlBuilder url = PortalUrlBuilder.get();
	protected final Portal portal;
	protected final L layoutBuilder;
	
	@SuppressWarnings("unchecked")
	protected PortalPresenter(Portal portal) {
		this.portal = portal;
		this.layoutBuilder = (L) LayoutBuilders.get(portal.group);		
	}
	
	@Override public abstract void bindView();
	
}
