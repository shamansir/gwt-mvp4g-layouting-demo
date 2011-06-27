package name.shamansir.mvplayout.server;

import java.util.HashSet;
import java.util.Set;

import name.shamansir.mvplayout.client.service.NewsService;
import name.shamansir.mvplayout.lib.exception.ItemNotFoundException;
import name.shamansir.mvplayout.lib.exception.NoMatchesException;
import name.shamansir.mvplayout.shared.dao.NewsItem;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class NewsServiceImpl extends RemoteServiceServlet implements NewsService {

	@Override
	public Set<NewsItem> getNews(String filter) throws NoMatchesException {
		return new HashSet<NewsItem>();
	}

	@Override
	public NewsItem getNewsItem(int nid) throws ItemNotFoundException {
		return new NewsItem(nid);
	}

	@Override
	public int saveNewsItem(NewsItem newsItem) {
		return -1;
	}

}
