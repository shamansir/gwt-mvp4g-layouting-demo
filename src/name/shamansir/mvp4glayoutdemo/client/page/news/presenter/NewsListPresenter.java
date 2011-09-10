package name.shamansir.mvp4glayoutdemo.client.page.news.presenter;

import java.util.Set;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.inject.Inject;
import com.mvp4g.client.annotation.Presenter;

import name.shamansir.mvp4glayout.client.SafeCallback;
import name.shamansir.mvp4glayout.client.mvp.IsPortletView;
import name.shamansir.mvp4glayout.client.mvp.PortletPresenter;

import name.shamansir.mvp4glayoutdemo.client.id.P;
import name.shamansir.mvp4glayoutdemo.client.page.news.NewsEventBus;
import name.shamansir.mvp4glayoutdemo.client.page.news.view.NewsListView;
import name.shamansir.mvp4glayoutdemo.client.page.news.widget.NewsItemWidget;
import name.shamansir.mvp4glayoutdemo.client.service.NewsServiceAsync;
import name.shamansir.mvp4glayoutdemo.shared.dao.NewsItem;

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
