package name.shamansir.mvplayout.client.pages.main.presenter;

import name.shamansir.mvplayout.client.id.P;
import name.shamansir.mvplayout.client.pages.main.MainEventBus;
import name.shamansir.mvplayout.client.pages.main.view.MainView;
import name.shamansir.mvplayout.lib.mvp.AMainPresenter;
import name.shamansir.mvplayout.lib.mvp.IsMainView;

import com.google.gwt.user.client.History;
import com.mvp4g.client.annotation.Presenter;

@Presenter(view = MainView.class)
public class MainPresenter extends AMainPresenter<MainPresenter.IMainView, MainEventBus> {
    
	public interface IMainView extends IsMainView {

	}
	
    public void onStart() {        
        if (History.getToken().isEmpty()) {
            History.newItem(url.build(P.USERS_LIST), false);
        }
        //eventBus.users(null); // FIXME: do not calls history converter when not in dev-mode
    }
    
}
