package name.shamansir.mvp4glayoutdemo.client.page.news.presenter;

import com.google.inject.Inject;
import com.mvp4g.client.annotation.Presenter;

import name.shamansir.mvp4glayout.client.SafeCallback;
import name.shamansir.mvp4glayout.client.mvp.IsPortletView;
import name.shamansir.mvp4glayout.client.mvp.PortletPresenter;

import name.shamansir.mvp4glayoutdemo.client.page.news.NewsEventBus;
import name.shamansir.mvp4glayoutdemo.client.page.news.view.NewsInfoView;
import name.shamansir.mvp4glayoutdemo.client.service.NewsServiceAsync;
import name.shamansir.mvp4glayoutdemo.shared.dao.NewsItem;

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
