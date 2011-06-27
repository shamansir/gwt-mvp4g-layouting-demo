package name.shamansir.mvplayout.client.pages.main.presenter;

import name.shamansir.mvplayout.client.pages.main.MainEventBus;
import name.shamansir.mvplayout.client.pages.main.view.MainView;
import name.shamansir.mvplayout.lib.mvp.AMainPresenter;
import name.shamansir.mvplayout.lib.mvp.IsMainView;

import com.mvp4g.client.annotation.Presenter;

@Presenter(view = MainView.class)
public class MainPresenter extends AMainPresenter<MainPresenter.IMainView, MainEventBus> {
    
	public interface IMainView extends IsMainView {

	}
	
    public void onStart() {
        eventBus.users(null);
    }
    
}
