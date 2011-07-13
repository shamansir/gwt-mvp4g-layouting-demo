package name.shamansir.mvplayout.client.page.news;

import name.shamansir.mvplayout.client.page.news.history.NewsHistoryConverter;
import name.shamansir.mvplayout.client.page.news.presenter.NewsListPresenter;
import name.shamansir.mvplayout.client.page.news.view.NewsListView;
import name.shamansir.mvplayout.lib.mvp.ChildEventBus;
import name.shamansir.mvplayout.lib.ui.structure.Place;
import name.shamansir.mvplayout.shared.dao.User;

import com.mvp4g.client.annotation.Event;
import com.mvp4g.client.annotation.Events;

@Events(module = NewsModule.class, startView = NewsListView.class)
public interface NewsEventBus extends ChildEventBus {
	
    // navigation events
    
	@Event(name = "list", navigationEvent = true,
	       handlers = NewsListPresenter.class,
	       historyConverter = NewsHistoryConverter.class)
	public void news();	
	
	@Event(/*name = "show", navigationEvent = true*/)
	public void show(int nid);
	
	@Event(/*name = "edit", navigationEvent = true*/)
	public void edit(int nid);	

    // secondary events	
	
	@Event/*(handlers = NewsListPresenter.class, calledMethod = "show")*/
	public void showUserInfo(User user);
	
	// projecting events, portlets
	
	@Event(handlers = NewsListPresenter.class, calledMethod = "plug")
	public void plugNewsList(Place where);	
	
	@Event/*(handlers = NewsListPresenter.class, calledMethod = "plug")*/
	public void plugUserInfo(Place where);
	
	@Event/*(handlers = NewsListPresenter.class, calledMethod = "plug")*/
	public void plugTestWidget(Place where);
	
	@Event/*(handlers = NewsListPresenter.class, calledMethod = "plug")*/
	public void plugNewsInfo(Place where);
	
	// projecting events, portals	
	
}
