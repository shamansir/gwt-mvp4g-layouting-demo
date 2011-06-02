package name.shamansir.mvplayout.client.ui.pages.news.presenter;

import name.shamansir.mvplayout.client.ui.pages.main.MainEventBus;
import name.shamansir.mvplayout.client.ui.pages.news.view.NewsListView;

import com.mvp4g.client.annotation.Presenter;
import com.mvp4g.client.presenter.LazyPresenter;
import com.mvp4g.client.view.LazyView;

@Presenter(view = NewsListView.class)
public class NewsListPresenter extends LazyPresenter<NewsListPresenter.Display, MainEventBus> {

	public interface Display extends LazyView {
		
	}
	
}
