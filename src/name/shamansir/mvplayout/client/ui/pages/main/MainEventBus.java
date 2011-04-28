package name.shamansir.mvplayout.client.ui.pages.main;

import name.shamansir.mvplayout.client.ui.pages.company.CompanyModule;
import name.shamansir.mvplayout.client.ui.pages.main.view.MainView;
import name.shamansir.mvplayout.client.ui.pages.news.NewsModule;
import name.shamansir.mvplayout.client.ui.pages.user.UserModule;
import name.shamansir.mvplayout.client.ui.state.HandlesStates;

import com.mvp4g.client.annotation.Debug;
import com.mvp4g.client.annotation.Event;
import com.mvp4g.client.annotation.Events;
import com.mvp4g.client.annotation.module.ChildModule;
import com.mvp4g.client.annotation.module.ChildModules;
import com.mvp4g.client.event.EventBus;

@Debug
@Events(startView = MainView.class, module = MainModule.class, historyOnStart = true)
@ChildModules( {
	@ChildModule(moduleClass = UserModule.class, autoDisplay = false, async = true),
	@ChildModule(moduleClass = CompanyModule.class, autoDisplay = false, async = true),	
	@ChildModule(moduleClass = NewsModule.class, autoDisplay = false, async = true),
} )
public interface MainEventBus extends EventBus, HandlesStates {

	@Event(modulesToLoad = UserModule.class)
	public void users();

	@Event(modulesToLoad = CompanyModule.class)	
	public void companies();

	@Event(modulesToLoad = NewsModule.class)
	public void news();
	
}
