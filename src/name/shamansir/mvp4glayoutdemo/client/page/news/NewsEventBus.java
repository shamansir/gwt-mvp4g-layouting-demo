package name.shamansir.mvp4glayoutdemo.client.page.news;

import name.shamansir.mvp4glayout.client.mvp.ChildEventBus;
import name.shamansir.mvp4glayout.client.ui.structure.Place;
import name.shamansir.mvp4glayoutdemo.client.page.news.history.NewsHistoryConverter;
import name.shamansir.mvp4glayoutdemo.client.page.news.presenter.NewsEditPresenter;
import name.shamansir.mvp4glayoutdemo.client.page.news.presenter.NewsInfoPresenter;
import name.shamansir.mvp4glayoutdemo.client.page.news.presenter.NewsListPresenter;
import name.shamansir.mvp4glayoutdemo.client.page.news.presenter.TestWidgetPresenter;
import name.shamansir.mvp4glayoutdemo.client.page.news.presenter.UserCardPresenter;
import name.shamansir.mvp4glayoutdemo.client.page.news.view.NewsListView;
import name.shamansir.mvp4glayoutdemo.shared.dao.User;

import com.mvp4g.client.annotation.Event;
import com.mvp4g.client.annotation.Events;

@Events(module = NewsModule.class, startView = NewsListView.class)
public interface NewsEventBus extends ChildEventBus {
    
    // navigation events
    
    @Event(name = "list", navigationEvent = true,
           handlers = NewsListPresenter.class,
           historyConverter = NewsHistoryConverter.class)
    public void news();	
    
    @Event(name = "show", navigationEvent = true,
           handlers = NewsInfoPresenter.class,
           historyConverter = NewsHistoryConverter.class)
    public void show(int nid);
    
    @Event(name = "edit", navigationEvent = true,
           handlers = NewsEditPresenter.class,
           historyConverter = NewsHistoryConverter.class)
    public void edit(int nid);	

    // secondary events    
    
    @Event(handlers = UserCardPresenter.class)
    public void showUserCard(User user);
    
    @Event(handlers = UserCardPresenter.class)
    public void clearUserCard();
    
    // projecting events, portlets
    
    @Event(handlers = NewsListPresenter.class, calledMethod = "plug")
    public void plugNewsList(Place where);	
    
    @Event(handlers = UserCardPresenter.class, calledMethod = "plug")
    public void plugUserCard(Place where);
    
    @Event(handlers = TestWidgetPresenter.class, calledMethod = "plug")
    public void plugTestWidget(Place where);
    
    @Event(handlers = NewsInfoPresenter.class, calledMethod = "plug")
    public void plugNewsInfo(Place where);
    
    // projecting events, portals
    
    @Event(handlers = NewsEditPresenter.class, calledMethod = "plugItemEditor")
    public void plugNewsItemEditor(Place where);
    
    @Event(handlers = NewsEditPresenter.class, calledMethod = "plugSaveButton")
    public void plugSaveButton(Place where);
    
}
