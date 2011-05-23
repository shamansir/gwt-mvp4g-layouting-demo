package name.shamansir.mvplayout.client.ui.pages.user.presenter;

import name.shamansir.mvplayout.client.ui.pages.user.UserEventBus;
import name.shamansir.mvplayout.client.ui.pages.user.layout.UserLayoutBuilder;
import name.shamansir.mvplayout.client.ui.pages.user.view.ListView;
import name.shamansir.mvplayout.client.ui.state.StatedPortalPresenter;

import com.mvp4g.client.annotation.Presenter;
import com.mvp4g.client.presenter.LazyPresenter;
import com.mvp4g.client.view.LazyView;

@Presenter(view = ListView.class)
public class ListPresenter extends StatedPortlalPresenter<ListPresenter.IListView, UserEventBus, UserLayoutBuilder> {

	public interface IListView extends LazyView {
		
	}
	
	public void 
	
}
