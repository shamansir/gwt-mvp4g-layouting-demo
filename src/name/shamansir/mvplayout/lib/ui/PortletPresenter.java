package name.shamansir.mvplayout.lib.ui;

import name.shamansir.mvplayout.lib.mvp.ChildEventBus;
import name.shamansir.mvplayout.lib.ui.Layouts.Place;

import com.mvp4g.client.presenter.LazyPresenter;
import com.mvp4g.client.view.LazyView;

public abstract class PortletPresenter<V extends LazyView & HasMainView,
                                       E extends ChildEventBus> 
                      extends LazyPresenter<V, E> {
    
    public void plug(Place where) {
        eventBus.plug(where, view.getMainView());
    }
		
}
