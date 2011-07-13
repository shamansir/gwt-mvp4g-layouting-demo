package name.shamansir.mvplayout.client.service;

import java.util.Set;

import name.shamansir.mvplayout.lib.exception.ItemNotFoundException;
import name.shamansir.mvplayout.shared.dao.NewsItem;

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
