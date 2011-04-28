package name.shamansir.mvplayout.client.ui.pages.news;

import com.mvp4g.client.annotation.Event;
import com.mvp4g.client.annotation.Events;

import name.shamansir.mvplayout.client.ui.pages.base.ChildEventBus;
import name.shamansir.mvplayout.client.ui.pages.news.view.ListView;

@Events(module = NewsModule.class, startView = ListView.class)
public interface NewsEventBus extends ChildEventBus {
	
	@Event(name = "list", navigationEvent = true)
	public void news();
	
	@Event(name = "show", navigationEvent = true)
	public void show(int nid);
	
	@Event(name = "edit", navigationEvent = true)
	public void edit(int nid);	

}
