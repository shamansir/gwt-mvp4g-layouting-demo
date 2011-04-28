package name.shamansir.mvplayout.client.ui;

import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import com.mvp4g.client.event.EventBus;
import com.mvp4g.client.presenter.LazyPresenter;
import com.mvp4g.client.view.LazyView;

public abstract class PortletPresenter<V extends LazyView,
                                       E extends EventBus> 
                      extends LazyPresenter<V, E>{

	protected final void project(HasWidgets where) {
		// Log.debug("Projecting " + this + "(" + view.id() + ") to the " + where);		
        where.clear();
        where.add((Widget)view);
	}
		
}
