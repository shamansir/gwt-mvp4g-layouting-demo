package name.shamansir.mvplayout.client.ui;

import name.shamansir.mvplayout.client.ui.Portal.PortalUrlBuilder;
import name.shamansir.mvplayout.client.ui.Portal.UrlBuilder;
import name.shamansir.mvplayout.client.ui.pages.base.ChildEventBus;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.user.client.ui.IsWidget;
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
	
	protected final void project(IsOutlet where, Pluggable what) {
		Log.debug("Projecting " + what + "(" + what.id() + ") to the " + where);
		what.refresh();
		where.clear();
		where.add(what.asWidget());
	}
	
    protected final void project(IsOutlet where, IsWidget what, String id) {
        Log.debug("Projecting " + what + "(" + id + ") to the " + where);
        where.clear();
        where.add(what.asWidget());
    }	
	
}
