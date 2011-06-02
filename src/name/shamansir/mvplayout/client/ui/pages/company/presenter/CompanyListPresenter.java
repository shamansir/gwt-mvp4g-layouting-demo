package name.shamansir.mvplayout.client.ui.pages.company.presenter;

import name.shamansir.mvplayout.client.ui.pages.main.MainEventBus;
import name.shamansir.mvplayout.client.ui.pages.company.view.CompanyListView;

import com.mvp4g.client.annotation.Presenter;
import com.mvp4g.client.presenter.LazyPresenter;
import com.mvp4g.client.view.LazyView;

@Presenter(view = CompanyListView.class)
public class CompanyListPresenter extends LazyPresenter<CompanyListPresenter.Display, MainEventBus> {

	public interface Display extends LazyView {
		
	}
	
}
