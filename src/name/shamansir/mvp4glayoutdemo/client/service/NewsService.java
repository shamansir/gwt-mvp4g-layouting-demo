package name.shamansir.mvp4glayoutdemo.client.service;

import java.util.Set;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import name.shamansir.mvp4glayoutdemo.client.service.exception.ItemNotFoundException;
import name.shamansir.mvp4glayoutdemo.shared.dao.NewsItem;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("news")
public interface NewsService extends RemoteService {
    Set<NewsItem> getNews();
    NewsItem getNewsItem(int nid) throws ItemNotFoundException;
    int saveNewsItem(NewsItem newsItem); 
}
