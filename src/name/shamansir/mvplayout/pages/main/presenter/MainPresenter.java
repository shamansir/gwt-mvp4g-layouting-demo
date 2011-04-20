package name.shamansir.mvplayout.pages.main.presenter;

import name.shamansir.mvplayout.pages.main.MainEventBus;
import name.shamansir.mvplayout.pages.main.view.MainView;

import com.mvp4g.client.annotation.Presenter;
import com.mvp4g.client.presenter.LazyPresenter;
import com.mvp4g.client.view.LazyView;

@Presenter(view = MainView.class)
public class MainPresenter extends LazyPresenter<MainPresenter.IMainView, MainEventBus> {

	public interface IMainView extends LazyView {
		
	}
	
}
