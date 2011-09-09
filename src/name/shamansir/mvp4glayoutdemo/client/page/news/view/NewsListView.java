package name.shamansir.mvp4glayoutdemo.client.page.news.view;

import java.util.HashSet;
import java.util.Set;

import name.shamansir.mvp4glayout.client.ui.widget.Portlet;
import name.shamansir.mvp4glayoutdemo.client.page.news.presenter.NewsListPresenter;
import name.shamansir.mvp4glayoutdemo.client.page.news.widget.NewsItemWidget;
import name.shamansir.mvp4glayoutdemo.shared.dao.NewsItem;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public final class NewsListView extends Portlet implements NewsListPresenter.Display {

    @UiTemplate("NewsListView.ui.xml")	
    interface ListViewUiBinder extends UiBinder<Widget, NewsListView> { }	
    private static ListViewUiBinder uiBinder = GWT.create(ListViewUiBinder.class);
    
    @UiField VerticalPanel newsHolder;
    
    final Set<NewsItemWidget> widgets = new HashSet<NewsItemWidget>();
    
    @Override
    public void createView() {
    	initWidget(uiBinder.createAndBindUi(this));
    }

    @Override
    public void showNews(Set<NewsItem> news) {
        widgets.clear();
        newsHolder.clear();
        for (NewsItem item: news) {
            final NewsItemWidget widget = new NewsItemWidget(item);
            widgets.add(widget);
            newsHolder.add(widget);
        }
        
    }

    @Override
    public Set<NewsItemWidget> getWidgets() {
        return widgets;
    }

}
