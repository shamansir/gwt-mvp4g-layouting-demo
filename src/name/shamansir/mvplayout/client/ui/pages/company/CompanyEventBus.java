package name.shamansir.mvplayout.client.ui.pages.company;

import com.mvp4g.client.annotation.Event;
import com.mvp4g.client.annotation.Events;

import name.shamansir.mvplayout.client.ui.pages.base.ChildEventBus;
import name.shamansir.mvplayout.client.ui.pages.news.view.ListView;

@Events(module = CompanyModule.class, startView = ListView.class)
public interface CompanyEventBus extends ChildEventBus {
	
	@Event(name = "list", navigationEvent = true)
	public void companies();
	
	@Event(name = "show", navigationEvent = true)
	public void show(int cid);
	
	@Event(name = "edit", navigationEvent = true)
	public void edit(int cid);	

}
