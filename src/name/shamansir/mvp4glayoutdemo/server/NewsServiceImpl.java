package name.shamansir.mvp4glayoutdemo.server;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import name.shamansir.mvp4glayout.client.exception.ItemNotFoundException;
import name.shamansir.mvp4glayoutdemo.client.service.NewsService;
import name.shamansir.mvp4glayoutdemo.shared.dao.NewsItem;
import name.shamansir.mvp4glayoutdemo.shared.dao.User;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class NewsServiceImpl extends RemoteServiceServlet implements NewsService {
    
    final Map<Integer, NewsItem> news = new HashMap<Integer, NewsItem>();
    
    public NewsServiceImpl() {
        int n1id = news.size();
        NewsItem newsOne = new NewsItem(n1id);
        newsOne.author = createUser("Joe", "Simpson", 12, "wooo.png");
        newsOne.title = "Fassbinder";        
        newsOne.text = "Blah blah blah";
        newsOne.postTime = new Date();        
        news.put(n1id, newsOne);
        
        int n2id = news.size();
        NewsItem newsTwo = new NewsItem(n2id);
        newsTwo.author = createUser("Moo", "Wangwang", 27, "foo.png");
        newsTwo.title = "Second news";        
        newsTwo.text = "Hello! The news are from the second hand";
        newsTwo.postTime = new Date();
        news.put(n2id, newsTwo);
        
        int n3id = news.size();
        NewsItem newsThree = new NewsItem(n3id);
        newsThree.author = createUser("Boo", "Trolalah", 81, "foo.png");
        newsThree.title = "I don't know Internet";        
        newsThree.text = "I had a dream, when I was young, a dream of sweet illusion.";
        newsThree.postTime = new Date();
        news.put(n3id, newsThree);
    }
    
    private User createUser(String name, String familyName, int age, String avatar) {
        final User result = new User();
        result.name = name;
        result.familyName = familyName;
        result.age = age;
        result.avatar = avatar;
        return result;
    }

    @Override
    public Set<NewsItem> getNews() {
        return new HashSet<NewsItem>(news.values());
    }

    @Override
    public NewsItem getNewsItem(int nid) throws ItemNotFoundException {
        if (!news.containsKey(nid)) throw new ItemNotFoundException(nid);
        return news.get(nid);
    }

    @Override
    public int saveNewsItem(NewsItem item) {
        int id = item.getId();
        if (id != -1) {
            news.put(id, item);
        } else {
            id = news.size();
            final NewsItem newItem = new NewsItem(id);
            newItem.author = item.author;
            newItem.title = item.title;
            newItem.text = item.text;            
            newItem.postTime = item.postTime;           
            news.put(id, newItem);
        }
        return id;
    }    

}
