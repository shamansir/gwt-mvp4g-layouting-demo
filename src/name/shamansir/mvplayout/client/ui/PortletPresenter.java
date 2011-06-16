package name.shamansir.mvplayout.client.ui;

import com.mvp4g.client.event.EventBus;
import com.mvp4g.client.presenter.LazyPresenter;
import com.mvp4g.client.view.LazyView;

public abstract class PortletPresenter<V extends LazyView,
                                       E extends EventBus> 
                      extends LazyPresenter<V, E> {
		
}
