package name.shamansir.mvplayout.client.pages.company;

import name.shamansir.mvplayout.client.pages.company.view.CompanyListView;
import name.shamansir.mvplayout.lib.mvp.ChildEventBus;

import com.mvp4g.client.annotation.Event;
import com.mvp4g.client.annotation.Events;

@Events(module = CompanyModule.class, startView = CompanyListView.class)
public interface CompanyEventBus extends ChildEventBus {
	
	@Event(/*name = "list", navigationEvent = true*/)
	public void companies();
	
	@Event(/*name = "show", navigationEvent = true*/)
	public void show(int cid);
	
	@Event(/*name = "edit", navigationEvent = true*/)
	public void edit(int cid);	

}
