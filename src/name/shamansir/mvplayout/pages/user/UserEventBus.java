package name.shamansir.mvplayout.pages.user;

import com.mvp4g.client.annotation.Event;
import com.mvp4g.client.annotation.Events;

import name.shamansir.mvplayout.pages.base.ChildEventBus;
import name.shamansir.mvplayout.pages.user.view.ListView;

@Events(startView = ListView.class)
public interface UserEventBus extends ChildEventBus {
	
	@Event(name = "list", navigationEvent = true)
	public void users();
	
	@Event(name = "show", navigationEvent = true)
	public void show(int uid);
	
	@Event(name = "edit", navigationEvent = true)
	public void edit(int uid);	

}
