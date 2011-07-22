package name.shamansir.mvplayout.client.service;

import java.util.Set;

import name.shamansir.mvplayout.shared.dao.NewsItem;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>UserService</code>.
 */
public interface NewsServiceAsync {
    void getNews(AsyncCallback<Set<NewsItem>> callback);
    void getNewsItem(int nid, AsyncCallback<NewsItem> callback);
    void saveNewsItem(NewsItem newsItem, AsyncCallback<Integer> callback);
}
