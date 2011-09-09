package name.shamansir.mvp4glayoutdemo.client.service;

import java.util.Set;

import name.shamansir.mvp4glayout.client.exception.ItemNotFoundException;
import name.shamansir.mvp4glayoutdemo.shared.dao.NewsItem;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("news")
public interface NewsService extends RemoteService {
    Set<NewsItem> getNews();
    NewsItem getNewsItem(int nid) throws ItemNotFoundException;
    int saveNewsItem(NewsItem newsItem); 
}
