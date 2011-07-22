package name.shamansir.mvplayout.lib.mvp;

import name.shamansir.mvplayout.lib.ui.Portal.PortalUrlBuilder;
import name.shamansir.mvplayout.lib.ui.Portal.UrlBuilder;
import name.shamansir.mvplayout.lib.ui.structure.Place;

import com.mvp4g.client.presenter.LazyPresenter;

public abstract class PortletPresenter<V extends IsPortletView,
                                       E extends ChildEventBus> 
                      extends LazyPresenter<V, E> {
    
    protected final UrlBuilder url = PortalUrlBuilder.get();
    
    public void plug(Place where) {
        eventBus.plug(where, view.getMainView());
    }
    	
}
