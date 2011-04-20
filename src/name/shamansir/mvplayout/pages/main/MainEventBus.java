package name.shamansir.mvplayout.pages.main;

import name.shamansir.mvplayout.pages.company.CompanyModule;
import name.shamansir.mvplayout.pages.main.view.MainView;
import name.shamansir.mvplayout.pages.news.NewsModule;
import name.shamansir.mvplayout.pages.user.view.UserModule;

import com.mvp4g.client.annotation.Debug;
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
public interface MainEventBus extends EventBus {

}
