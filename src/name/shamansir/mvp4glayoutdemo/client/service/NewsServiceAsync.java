package name.shamansir.mvp4glayoutdemo.client.service;

import java.util.Set;

import com.google.gwt.user.client.rpc.AsyncCallback;

import name.shamansir.mvp4glayoutdemo.shared.dao.NewsItem;

/**
 * The async counterpart of <code>UserService</code>.
 */
public interface NewsServiceAsync {
    void getNews(AsyncCallback<Set<NewsItem>> callback);
    void getNewsItem(int nid, AsyncCallback<NewsItem> callback);
    void saveNewsItem(NewsItem newsItem, AsyncCallback<Integer> callback);
}
