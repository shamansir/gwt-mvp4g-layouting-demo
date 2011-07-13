package name.shamansir.mvplayout.client.page.company.presenter;

import name.shamansir.mvplayout.client.page.company.CompanyEventBus;
import name.shamansir.mvplayout.client.page.company.view.CompanyListView;

import com.mvp4g.client.annotation.Presenter;
import com.mvp4g.client.presenter.LazyPresenter;
import com.mvp4g.client.view.LazyView;

@Presenter(view = CompanyListView.class)
public class CompanyListPresenter extends LazyPresenter<CompanyListPresenter.Display, CompanyEventBus> {

	public interface Display extends LazyView {
		
	}
	
}
