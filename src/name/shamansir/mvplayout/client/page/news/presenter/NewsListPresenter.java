package name.shamansir.mvplayout.client.page.news.presenter;

import java.util.Set;

import name.shamansir.mvplayout.client.id.P;
import name.shamansir.mvplayout.client.page.news.NewsEventBus;
import name.shamansir.mvplayout.client.page.news.view.NewsListView;
import name.shamansir.mvplayout.client.page.news.widget.NewsItemWidget;
import name.shamansir.mvplayout.client.service.NewsServiceAsync;
import name.shamansir.mvplayout.lib.mvp.IsPortletView;
import name.shamansir.mvplayout.lib.mvp.PortletPresenter;
import name.shamansir.mvplayout.lib.utils.SafeCallback;
import name.shamansir.mvplayout.shared.dao.NewsItem;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.inject.Inject;
import com.mvp4g.client.annotation.Presenter;

@Presenter(view = NewsListView.class)
public class NewsListPresenter extends PortletPresenter<NewsListPresenter.Display, NewsEventBus> {

    public interface Display extends IsPortletView {
        public void showNews(Set<NewsItem> news);
        public Set<NewsItemWidget> getWidgets();
    }
    
    @Inject NewsServiceAsync service;
    
    public void onNews() {
        eventBus.clearUserCard();
        service.getNews(new SafeCallback<Set<NewsItem>>(eventBus) {

            @Override
            public void onSuccess(Set<NewsItem> news) {
                view.showNews(news);
                
                for (final NewsItemWidget widget: view.getWidgets()) {
                    widget.addClickHandler(new ClickHandler() {                                
                        @Override
                        public void onClick(ClickEvent event) {
                            eventBus.showUserCard(widget.getItem().author);
                        }
                    });
                    url.link(widget.getShowAnchor(), P.NEWS_SHOW, 
                            String.valueOf(widget.getItem().getId()));
                    url.link(widget.getEditAnchor(), P.NEWS_EDIT, 
                            String.valueOf(widget.getItem().getId()));                            
                }                
            }
            
        });
    }
    
}
