package name.shamansir.mvplayout.client.ui;

import name.shamansir.mvplayout.client.ui.Portal.PortalUrlBuilder;
import name.shamansir.mvplayout.client.ui.Portal.UrlBuilder;
import name.shamansir.mvplayout.client.ui.pages.base.ChildEventBus;
import name.shamansir.mvplayout.client.ui.state.HandlesStateChange;

import com.mvp4g.client.presenter.LazyPresenter;
import com.mvp4g.client.view.LazyView;

public abstract class PortalPresenter<V extends LazyView &
                                                HandlesStateChange, 
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
