package name.shamansir.mvplayout.client.page.news.presenter;

import name.shamansir.mvplayout.client.page.news.NewsEventBus;
import name.shamansir.mvplayout.client.page.news.view.NewsInfoView;
import name.shamansir.mvplayout.client.service.NewsServiceAsync;
import name.shamansir.mvplayout.lib.mvp.IsPortletView;
import name.shamansir.mvplayout.lib.mvp.PortletPresenter;
import name.shamansir.mvplayout.lib.utils.SafeCallback;
import name.shamansir.mvplayout.shared.dao.NewsItem;

import com.google.inject.Inject;
import com.mvp4g.client.annotation.Presenter;

@Presenter(view = NewsInfoView.class)
public class NewsInfoPresenter extends PortletPresenter<NewsInfoPresenter.Display, NewsEventBus> {

	public interface Display extends IsPortletView {
	    public void showItem(NewsItem item);
	}
	
	@Inject NewsServiceAsync service;
	
	public void onShow(int nid) {
	    service.getNewsItem(nid, new SafeCallback<NewsItem>(eventBus) {

            @Override
            public void onSuccess(NewsItem item) {
                view.showItem(item);
                eventBus.showUserCard(item.author);
            }
        });
	}
	
}
