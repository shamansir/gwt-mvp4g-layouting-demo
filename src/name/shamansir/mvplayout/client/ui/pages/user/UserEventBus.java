package name.shamansir.mvplayout.client.ui.pages.user;

import com.mvp4g.client.annotation.Event;
import com.mvp4g.client.annotation.Events;

import name.shamansir.mvplayout.client.ui.pages.base.ChildEventBus;
import name.shamansir.mvplayout.client.ui.pages.user.view.ListView;

@Events(module = UserModule.class, startView = ListView.class)
public interface UserEventBus extends ChildEventBus {
	
	@Event(name = "list", navigationEvent = true)
	public void users();
	
	@Event(name = "show", navigationEvent = true)
	public void show(int uid);
	
	@Event(name = "edit", navigationEvent = true)
	public void edit(int uid);	

}
