package name.shamansir.mvp4glayout.client.mvp;

import com.mvp4g.client.presenter.LazyPresenter;

import name.shamansir.mvp4glayout.client.ui.Portal.PortalUrlBuilder;
import name.shamansir.mvp4glayout.client.ui.Portal.UrlBuilder;
import name.shamansir.mvp4glayout.client.ui.structure.Place;

public abstract class PortletPresenter<V extends IsPortletView,
                                       E extends ChildEventBus> 
                      extends LazyPresenter<V, E> {
    
    protected final UrlBuilder url = PortalUrlBuilder.get();
    
    public void plug(Place where) {
        eventBus.plug(where, view.getMainView());
    }
    	
}
