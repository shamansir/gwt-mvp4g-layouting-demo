package name.shamansir.mvplayout.client.ui.pages.news.presenter;

import name.shamansir.mvplayout.client.ui.pages.main.MainEventBus;
import name.shamansir.mvplayout.client.ui.pages.user.view.ListView;

import com.mvp4g.client.annotation.Presenter;
import com.mvp4g.client.presenter.LazyPresenter;
import com.mvp4g.client.view.LazyView;

@Presenter(view = ListView.class)
public class ListPresenter extends LazyPresenter<ListPresenter.IListView, MainEventBus> {

	public interface IListView extends LazyView {
		
	}
	
}
