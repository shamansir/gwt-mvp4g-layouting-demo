package name.shamansir.mvplayout.client.ui;

import name.shamansir.mvplayout.client.ui.Layouts.Place;
import name.shamansir.mvplayout.client.ui.pages.base.ChildEventBus;

import com.mvp4g.client.presenter.LazyPresenter;
import com.mvp4g.client.view.LazyView;

public abstract class PortletPresenter<V extends LazyView & HasMainView,
                                       E extends ChildEventBus> 
                      extends LazyPresenter<V, E> {
    
    public void plug(Place where) {
        eventBus.plug(where, view.getMainView());
    }
		
}
